
package com.hospitals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospitals.entity.AppointmentEntity;
import com.hospitals.service.AppointmentService;
import com.hospitals.service.DoctorService;
import com.hospitals.service.PatientService;

@Controller
@RequestMapping("/admin/appointments")
public class AdminAppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "admin_appointment_list";
    }

    @GetMapping("/add")
    public String showAddAppointmentForm(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("appointment", new AppointmentEntity());
        return "admin_appointment_form";
    }

    @PostMapping("/add")
    public String addAppointment(@ModelAttribute AppointmentEntity appointment) {
    	System.out.println(appointment);
    	appointmentService.saveAppointment(appointment);
        return "redirect:/admin/appointments";
    }

    @GetMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "redirect:/admin/appointments";
    }
}
