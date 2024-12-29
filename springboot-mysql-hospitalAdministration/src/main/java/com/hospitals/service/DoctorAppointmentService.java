package com.hospitals.service;

import com.hospitals.entity.AppointmentEntity;
import java.util.List;

public interface DoctorAppointmentService {
    List<AppointmentEntity> getAppointmentsByDoctor(Long doctorId);
}

