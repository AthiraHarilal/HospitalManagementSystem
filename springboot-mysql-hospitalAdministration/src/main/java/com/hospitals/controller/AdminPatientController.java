package com.hospitals.controller;




import com.hospitals.entity.PatientEntity;
import com.hospitals.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/patients")
public class AdminPatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "admin_patient_list";
    }

    @GetMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new PatientEntity());
        return "admin_patient_form";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute PatientEntity patient) {
        patientService.savePatient(patient);
        return "redirect:/admin/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditPatientForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "admin_patient_form";
    }

    @PostMapping("/edit/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute PatientEntity patient) {
        patient.setId(id);
        patientService.savePatient(patient);
        return "redirect:/admin/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/admin/patients";
    }
}

