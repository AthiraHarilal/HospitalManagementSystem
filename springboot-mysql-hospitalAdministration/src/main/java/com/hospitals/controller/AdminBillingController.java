package com.hospitals.controller;

import com.hospitals.entity.BillEntity;
import com.hospitals.service.BillService;
import com.hospitals.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/bills")
public class AdminBillingController {

    @Autowired
    private BillService billService;

    @Autowired
    private PatientService patientService;

    // List all bills
    @GetMapping
    public String listBills(Model model) {
        model.addAttribute("bills", billService.getAllBills());
        return "admin_bill_list";
    }

    // Show form to generate a new bill
    @GetMapping("/add")
    public String showGenerateBillForm(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "admin_generate_bill";
    }

    // Handle the request to generate a bill
    @PostMapping("/add")
    public String generateBill(@RequestParam Long patientId, @RequestParam double amount, @RequestParam String description) {
        billService.generateBill(patientId, amount, description);
        return "redirect:/admin/bills";
    }

    // Show form to update payment status
    @GetMapping("/edit/{id}")
    public String showEditBillForm(@PathVariable Long id, Model model) {
        model.addAttribute("bill", billService.getBillById(id));
        return "admin_edit_bill";
    }

    // Handle updating the payment status
    @PostMapping("/edit/{id}")
    public String updatePaymentStatus(@PathVariable Long id, @RequestParam BillEntity.PaymentStatus status) {
        billService.updatePaymentStatus(id, status);
        return "redirect:/admin/bills";
    }

    // Delete a bill
    @GetMapping("/delete/{id}")
    public String deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return "redirect:/admin/bills";
    }
}
