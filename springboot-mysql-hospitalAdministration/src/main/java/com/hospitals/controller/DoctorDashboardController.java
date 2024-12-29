package com.hospitals.controller;

import com.hospitals.entity.DoctorEntity;
import com.hospitals.entity.AppointmentEntity;
import com.hospitals.service.DoctorProfileService;
import com.hospitals.service.DoctorAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class DoctorDashboardController {

    @Autowired
    private DoctorProfileService doctorProfileService;

    @Autowired
    private DoctorAppointmentService doctorAppointmentService;

    // Assuming the doctorId is stored in the session or URL (you can adjust this as needed)
    @GetMapping("/doctor/dashboard/{doctorId}")
    public String viewDashboard(@PathVariable Long doctorId, Model model) {
        // Get doctor profile
        DoctorEntity doctor = doctorProfileService.getDoctorProfile(doctorId);

        // Get doctor's appointments
        List<AppointmentEntity> appointments = doctorAppointmentService.getAppointmentsByDoctor(doctorId);

        model.addAttribute("doctor", doctor);
        model.addAttribute("appointments", appointments);

        return "doctor_dashboard"; // Display doctor dashboard view
    }

    @GetMapping("/doctor/profile/edit/{doctorId}")
    public String editProfile(@PathVariable Long doctorId, Model model) {
        DoctorEntity doctor = doctorProfileService.getDoctorProfile(doctorId);
        model.addAttribute("doctor", doctor);
        return "doctor_profile_edit"; // Form for editing doctor profile
    }

    @PostMapping("/doctor/profile/save")
    public String saveProfile(@ModelAttribute DoctorEntity doctor) {
        doctorProfileService.updateDoctorProfile(doctor);
        return "redirect:/doctor/dashboard/" + doctor.getId(); // Redirect to dashboard after saving profile
    }
}
