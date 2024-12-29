package com.hospitals.service;

import java.util.List;

import com.hospitals.entity.BillEntity;

public interface BillService {
    List<BillEntity> getAllBills();

    BillEntity getBillById(Long id);

    void generateBill(Long patientId, double amount, String description);

    void updatePaymentStatus(Long billId, BillEntity.PaymentStatus status);

    void deleteBill(Long id);

    List<BillEntity> getBillsByPatient(Long patientId);
}
