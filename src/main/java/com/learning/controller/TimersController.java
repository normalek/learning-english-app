package com.learning.controller;

import com.learning.models.Gradations;
import com.learning.models.History;
import com.learning.models.Timers;
import com.learning.models.Users;
import com.learning.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/exercises")
public class TimersController {
    private static final String CURRENT_DATE = LocalDate.now().toString();
    @Autowired
    private TimersRepository timersRepository;
    @Autowired
    private GradationsRepository gradationsRepository;
    @Autowired
    private TimersWrapper wrapper;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private History history;

    @RequestMapping("/view")
    public @ResponseBody
    Timers getTimersId(@RequestParam Integer id) {
        return timersRepository.findOne(id);
    }

    @RequestMapping("/list")
    public String getTimersList(Model model) {
        model.addAttribute("timers", timersRepository.findAll());
        return "exercises_list";
    }

    @RequestMapping("/prepare")
    public String prepareTest(Model model) {
        model.addAttribute("timers", timersRepository.findAll());
        return "prepare";
    }

    @RequestMapping("/test")
    public String getTimersTest(Model model) {
        //flexible approach with studying words from user profile
        UserDetails  current_user_details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users current_user = usersRepository.findByName(current_user_details.getUsername());
        Pageable topWords = new PageRequest(0, current_user.getWords());
        List<Timers> timers = timersRepository.findFirstWorst(topWords);
        wrapper.setTimers(new ArrayList<>(timers));
        model.addAttribute("timersWrapper", wrapper);
        return "test";
    }

    @PostMapping("/test_result")
    public String timersSubmit(HttpServletRequest request, @ModelAttribute ArrayList<Timers> timersWrapper) {
        analyzeTest(request);
        return "redirect:list";
    }

    private void analyzeTest(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        String input_word = "";
        byte bad_counter = 0;
        byte good_counter = 0;
        for (String key : map.keySet()) {
            if (key.contains("current_word")) {
                input_word = map.get(key)[0];
            } else if (key.contains("id_rec")) {
                String id_rec = map.get(key)[0];
                Timers record = timersRepository.findOne(Integer.valueOf(id_rec));
                System.out.println("id_rec: " + id_rec + ",input_word: " + input_word + ", actual_word: " + record.getDictionaryItem().getWord());
                Integer id_curr_grad = record.getGradationItem().getId_rec();
                //section for storing records (History module)
                if (!record.getDictionaryItem().getWord().equals(input_word)) {
                    bad_counter++;
                } else {
                    good_counter++;
                }
                //section for decreasing/increasing records just in case for not Maroon color
                if (record.getGradationItem().getId_rec() != 1 && !record.getDictionaryItem().getWord().equals(input_word)) {//decreasing gradation case
//                    System.out.println("into decreasing" + record.getDictionaryItem().getWord());
                    Gradations bad_gradation = gradationsRepository.findOne(id_curr_grad - 1);
                    record.setGradationItem(bad_gradation);
                    System.out.println("--new bad gradation: " + record.getGradationItem().getName());
                    timersRepository.save(record);
                } else if (record.getGradationItem().getId_rec() != 4 && record.getDictionaryItem().getWord().equals(input_word)) {//increasing gradation case
                    Gradations good_gradation = gradationsRepository.findOne(id_curr_grad + 1);
                    record.setGradationItem(good_gradation);
                    if (record.getGradationItem().getId_rec().equals(4)) {
                        record.setDate_learned(CURRENT_DATE);
                        System.out.println("--one more learned word--");
                    }
                    System.out.println("--new good gradation: " + record.getGradationItem().getName());
                    timersRepository.save(record);
                }
            }
        }
        //add records for history
        history.setDate(CURRENT_DATE);
        history.setCorrect(good_counter);
        history.setIncorrect(bad_counter);
        history.setTotal((byte) (good_counter + bad_counter));
        System.out.println(history.toString());
        historyRepository.save(history);
    }
}
