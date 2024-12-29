package com.hospitals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleController {

	@PostMapping("/role/redirect")
	public String redirectBasedOnRole(@RequestParam String name, @RequestParam String role, Model model) {
		model.addAttribute("name", name);

		switch (role.toUpperCase()) {
		case "ADMIN":
			return "redirect:/admin/dashboard";
		case "DOCTOR":
			return "redirect:/doctor/dashboard";
		case "PATIENT":
			return "redirect:/patient/dashboard";
		default:
			model.addAttribute("error", "Invalid role selected");
			return "role_selection";
		}
	}
	
	@GetMapping("/role/select")
	public String showRoleSelectionPage() {
	    return "role_selection";
	}

}
