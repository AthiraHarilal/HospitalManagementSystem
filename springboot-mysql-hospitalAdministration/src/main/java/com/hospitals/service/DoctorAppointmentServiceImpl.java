package com.hospitals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitals.entity.AppointmentEntity;
import com.hospitals.repository.AppointmentRepository;

@Service
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentEntity> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
}

