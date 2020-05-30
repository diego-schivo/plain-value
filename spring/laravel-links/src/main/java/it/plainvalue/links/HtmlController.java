package it.plainvalue.links;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HtmlController {

	@Autowired
	protected LinkRepository links;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("links", links.findAll());
		return "welcome";
	}

	@GetMapping("/submit")
	public String submit(Model model) {
		model.addAttribute("link", new Link());
		return "submit";
	}

	@PostMapping("/submit")
	public String submit(@Valid @ModelAttribute Link link, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "submit";
		}

		links.save(link);
		return "redirect:/";
	}

}
