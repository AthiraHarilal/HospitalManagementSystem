package com.hospitals.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hospitals.entity.AppointmentEntity;
import com.hospitals.service.AppointmentService;

@Controller
public class DoctorAppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Show the doctor's appointments
    @GetMapping("/doctor/appointments")
    public String showDoctorAppointments(Model model) {
        Long doctorId = getCurrentDoctorId();  // Fetch the current doctor's ID
        List<AppointmentEntity> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        model.addAttribute("appointments", appointments);
        return "doctor_appointments";  // Template to display appointments
    }

    // This method simulates fetching the current doctor's ID
    private Long getCurrentDoctorId() {
        // Replace with actual logic to fetch the logged-in doctor's ID (e.g., from security context or session)
        return 1L;  // Example, assuming doctor with ID 1 is logged in
    }
}
