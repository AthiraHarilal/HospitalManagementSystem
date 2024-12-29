package com.hospitals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hospitals.entity.DoctorEntity;
import com.hospitals.service.DoctorService;

@Controller
@RequestMapping("/admin/doctors")
public class AdminDoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "admin_doctor_list";
    }

    @GetMapping("/form")
    public String doctorForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        DoctorEntity doctor = id != null ? doctorService.getDoctorById(id) : new DoctorEntity();
        model.addAttribute("doctor", doctor);
        return "admin_doctor_form";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute DoctorEntity doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/admin/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return "redirect:/admin/doctors";
    }
}
