package com.hospitals.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitals.entity.BillEntity;
import com.hospitals.entity.PatientEntity;
import com.hospitals.repository.BillRepository;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PatientService patientService;

    @Override
    public List<BillEntity> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public BillEntity getBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public void generateBill(Long patientId, double amount, String description) {
        PatientEntity patient = patientService.getPatientById(patientId);
        if (patient != null) {
            BillEntity bill = new BillEntity();
            bill.setPatient(patient);
            bill.setAmount(amount);
            bill.setDescription(description);
            bill.setStatus(BillEntity.PaymentStatus.UNPAID);
            bill.setGeneratedDate(new Date());
            billRepository.save(bill);
        }
    }

    @Override
    public void updatePaymentStatus(Long billId, BillEntity.PaymentStatus status) {
        BillEntity bill = getBillById(billId);
        if (bill != null) {
            bill.setStatus(status);
            billRepository.save(bill);
        }
    }

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public List<BillEntity> getBillsByPatient(Long patientId) {
        return billRepository.findByPatientId(patientId);
    }
}
