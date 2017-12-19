package com.learning.controllers;

import com.learning.models.Dictionary;
import com.learning.utils.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/dictionary")
public class DictionaryController {
	@Autowired
	private DictionaryRepository DictionaryRepository;

	@GetMapping(path="/add")
	public String createNewWord(Model model) {
		model.addAttribute("word", new Dictionary());
		return "dictionary_add";
	}
	
	@PostMapping(path="/add")
	public String addNewWord(@ModelAttribute Dictionary word) {
		DictionaryRepository.save(word);
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
		return "dictionary_list";
	}
}
