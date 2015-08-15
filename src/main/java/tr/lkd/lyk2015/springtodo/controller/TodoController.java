package tr.lkd.lyk2015.springtodo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.lkd.lyk2015.springtodo.model.Todo;
import tr.lkd.lyk2015.springtodo.service.TodoService;

@Controller					
@RequestMapping("/todo")
public class TodoController {

	@Autowired // dependency injected ?
	private TodoService todoService;

	@RequestMapping("")
	public String list(Model model) {
		List<Todo> todos = todoService.getAll();

		model.addAttribute("todoList", todos);
		return "todoList";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm(@ModelAttribute Todo todo) {

		return "createForm";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute @Valid Todo todo, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			
			return "create";
			
		}
		
		
		todoService.create(todo);

		return "redirect:/todo"; // ?? recursive
	}

	@RequestMapping(value = "/mark", method = RequestMethod.POST)
	public String mark(@RequestParam("id") Long id, @RequestParam(value = "done", required = false) boolean done) {

		Todo todo = todoService.getById(id);

		if (todo.isDone()) {
			todo.setDone(false);
		} else if (!todo.isDone()) {
			todo.setDone(true);
		}

		todoService.update(todo);

		return "redirect:/todo"; // ?? recursive
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = { "id" })
	public String edit(@ModelAttribute Todo todo, Model model,
			@RequestParam(value = "message", required = false) String message) {

		// Long num = Long.valueOf(id).longValue();
		// Todo todo = todoService.getById(num);
		// model.addAttribute("todo", todo);
		//
		//
		Todo todoNew = todoService.getById(todo.getId());

		model.addAttribute("todo", todoNew);
		model.addAttribute("message", message);

		return "edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute Todo todo, Model model) {
		// todoService.update(todo);
		System.out.println(todo);
		todoService.update(todo);

		model.addAttribute("message", "success");

		return "redirect:/todo/edit?id=" + todo.getId();
	}
}
