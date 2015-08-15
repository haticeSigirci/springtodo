package tr.lkd.lyk2015.springtodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/path")
public class PathController {

	@RequestMapping("/test")
	public String param(@RequestParam(value = "variable", defaultValue = "default value") String variable,
			Model model) {

		model.addAttribute("variable", variable);

		return "path";
	}

	@RequestMapping("/test/{variable}")
	public String test(@PathVariable("variable") String variable, Model model) {

		model.addAttribute("variable", variable);

		return "path";
	}

}
