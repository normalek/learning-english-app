package com.learning.controllers;

import com.learning.models.History;
import com.learning.utils.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/history")
public class HistoryController {

    @Autowired
    private HistoryRepository HistoryRepository;

    @RequestMapping("/list")
    public String getHistoryList(Model model) {
        List<History> history_list = HistoryRepository.findAll();
        List<Integer> history_percentage = new ArrayList<>();
        for (History history : history_list) {
            history_percentage.add(history.getRight()*100/history.getTotal());
        }
        int average = ((int) history_percentage.stream().mapToInt(val -> val).average().getAsDouble());
        model.addAttribute("history", history_list);
        model.addAttribute("average", average);
        return "history_list";
    }
}
