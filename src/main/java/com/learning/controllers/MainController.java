package com.learning.controllers;

import com.learning.models.Dictionary;
import com.learning.utils.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private DictionaryRepository DictionaryRepository;
	
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewWord(@RequestParam String word
			, @RequestParam String explanation) {
		// @ResponseBody means the returned String is the response, not a view word
		// @RequestParam means it is a parameter from the GET or POST request
		
		Dictionary n = new Dictionary();
		n.setWord(word);
		n.setExplanation(explanation);
		DictionaryRepository.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Dictionary> getAllWords() {
		// This returns a JSON or XML with the users
		return DictionaryRepository.findAll();
	}

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
}
