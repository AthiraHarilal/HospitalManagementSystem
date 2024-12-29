package com.hospitals.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospitals.entity.AppointmentEntity;
import com.hospitals.entity.PatientEntity;
import com.hospitals.service.AppointmentService;
import com.hospitals.service.PatientService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    // Patient Dashboard: View Patient's Appointments
    @GetMapping("/dashboard")
    public String getDashboard(Model model, Principal principal) {
        // Check if the principal (user) is authenticated
        if (principal == null) {
            return "redirect:/login"; // Redirect to login page if not authenticated
        }

      String username = principal.getName(); // Get logged-in patient's username
       PatientEntity patient = patientService.findByUsername(username); // Fetch patient data

        // If patient is not found, redirect to login
        if (patient == null) {
            return "redirect:/login"; // Redirect to login page if patient not found
        }

        // Fetch appointments for the logged-in patient
        List<AppointmentEntity> appointments = appointmentService.getAppointmentsByPatientId(patient.getId()); 

        // Add patient and appointments to the model
        model.addAttribute("patient", patient);
        model.addAttribute("appointments", appointments);

        // Return the view for the patient dashboard
        return "patient/dashboard"; // Redirect to patient dashboard page
    }

    // View Patient Profile
    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        // Check if the principal (user) is authenticated
        if (principal == null) {
            return "redirect:/login"; // Redirect to login page if not authenticated
        }

        String username = principal.getName(); // Get logged-in patient's username
        PatientEntity patient = patientService.findByUsername(username); // Fetch patient data

        // If patient is not found, redirect to login
        if (patient == null) {
            return "redirect:/login"; // Redirect to login page if patient not found
        }

        // Add patient to the model for profile page
        model.addAttribute("patient", patient);

        // Return the view for patient profile
        return "patient/profile"; // Return the profile page
    }

    // Update Patient Profile
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("patient") PatientEntity updatedPatient, Principal principal) {
        // Check if the principal (user) is authenticated
        if (principal == null) {
            return "redirect:/login"; // Redirect to login page if not authenticated
        }

        String username = principal.getName(); // Get logged-in patient's username
        PatientEntity patient = patientService.findByUsername(username); // Fetch patient data

        // If patient is not found, redirect to login
        if (patient == null) {
            return "redirect:/login"; // Redirect to login page if patient not found
        }

        // Update allowed fields
        patient.setFirstName(updatedPatient.getFirstName());
        patient.setContact(updatedPatient.getContact());
        // You can add more fields for update, like address
        // patient.setAddress(updatedPatient.getAddress());

        patientService.savePatient(patient); // Save updated patient data

        // Redirect back to profile page with success message
        return "redirect:/patient/profile?success"; // Redirect back to profile page
    }
}
