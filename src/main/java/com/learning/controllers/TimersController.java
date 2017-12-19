package com.learning.controllers;

import com.learning.models.Gradations;
import com.learning.models.Timers;
import com.learning.utils.GradationsRepository;
import com.learning.utils.TimersRepository;
import com.learning.utils.TimersWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller 
@RequestMapping(path = "/exercises")
public class TimersController {
    @Autowired
    private TimersRepository timersRepository;
    @Autowired
    private GradationsRepository gradationsRepository;
    @Autowired
    private TimersWrapper wrapper;

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
        Pageable topTwenty = new PageRequest(0, 20);
        List<Timers> timers = timersRepository.findFirstWorst(topTwenty);
//        Collections.shuffle(timers);
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
        for (String key : map.keySet()) {
            if (key.contains("current_word")) {
                input_word = map.get(key)[0];
            } else if (key.contains("id_rec")) {
                String id_rec = map.get(key)[0];
                Timers record = timersRepository.findOne(Integer.valueOf(id_rec));
                System.out.println("id_rec: " + id_rec + ",input_word: " + input_word + ", actual_word: " + record.getDictionaryItem().getWord());
                Integer id_curr_grad = record.getGradationItem().getId_rec();
                if (record.getGradationItem().getId_rec() != 1 && !record.getDictionaryItem().getWord().equals(input_word)) {//decreasing gradation case
                    System.out.println("into decreasing" + record.getDictionaryItem().getWord());
                    Gradations bad_gradation = gradationsRepository.findOne(id_curr_grad-1);
                    record.setGradationItem(bad_gradation);
                    System.out.println("new bad gradation: " + record.getGradationItem().getName());
                    timersRepository.save(record);
                } else if (record.getGradationItem().getId_rec() != 4 && record.getDictionaryItem().getWord().equals(input_word)) {//increasing gradation case
                    Gradations good_gradation = gradationsRepository.findOne(id_curr_grad+1);
                    record.setGradationItem(good_gradation);
                    System.out.println("new good gradation: " + record.getGradationItem().getName());
                    timersRepository.save(record);
                }
            }
        }
    }
}
