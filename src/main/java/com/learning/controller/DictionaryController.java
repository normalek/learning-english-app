package com.learning.controller;

import com.learning.models.Dictionary;
import com.learning.models.Gradations;
import com.learning.models.History;
import com.learning.models.Timers;
import com.learning.service.UsersHelper;
import com.learning.utils.DictionaryRepository;
import com.learning.utils.GradationsRepository;
import com.learning.utils.TimersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@Controller
@RequestMapping(path="/dictionary")
public class DictionaryController {
	@Autowired
	private DictionaryRepository DictionaryRepository;
	@Autowired
	private TimersRepository TimersRepository;
//	@Autowired
//	private Timers timers;
	@Autowired
	private GradationsRepository gradationsRepository;
	@Autowired
	private UsersHelper usersHelper;

	@GetMapping(path="/add")
	public String createNewWord(Model model) {
		model.addAttribute("word", new Dictionary());
		return "dictionary_add";
	}
	
	@PostMapping(path="/add")
	public String addNewWord(@ModelAttribute Dictionary word) {
		DictionaryRepository.save(word);
		Byte days = usersHelper.getCurrentUser().getRepetition_period();
		Timers timers = new Timers();
		timers.setDictionaryItem(word);
		timers.setGradationItem(gradationsRepository.findOne(1));
		timers.setDate_learned(LocalDate.now().minus(Period.ofDays(days)).toString());
		timers.setCounter(0);
		timers.setNeed_repeat(0);
		TimersRepository.save(timers);
		return "redirect:list";
	}

	@RequestMapping("/view")
	public @ResponseBody Dictionary getDictionaryId(@RequestParam Integer id) {
		return DictionaryRepository.findOne(id);
	}

	@RequestMapping("/delete")
	public String deleteWord(@RequestParam Integer id) {
		DictionaryRepository.delete(id);
		return "redirect:list";
	}

	@GetMapping("/edit")
	public String editWord(Model model, @RequestParam Integer id) {
		model.addAttribute("word", DictionaryRepository.findOne(id));
		return "dictionary_edit";
	}

	@PostMapping("/edit")
	public String saveWord(@ModelAttribute Dictionary word) {
		DictionaryRepository.save(word);
		return "redirect:list";
	}

	@RequestMapping("/list")
	public String getDictionaryList(Model model) {
		model.addAttribute("dictionary", DictionaryRepository.findWithOrder());
        model.addAttribute("total", DictionaryRepository.count());
		return "dictionary_list";
	}
}
