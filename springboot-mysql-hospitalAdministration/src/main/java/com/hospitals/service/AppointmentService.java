package com.hospitals.service;

import java.util.List;


import com.hospitals.entity.AppointmentEntity;

public interface AppointmentService {
    List<AppointmentEntity> getAllAppointments();
    List<AppointmentEntity> getAppointmentsByDoctor(Long doctorId);
    List<AppointmentEntity> getAppointmentsByPatient(Long patientId);
    void saveAppointment(AppointmentEntity appointment);
    void cancelAppointment(Long id);
    List<AppointmentEntity> getAppointmentsByDoctorId(Long doctorId);
	List<AppointmentEntity> findAppointmentsByDoctorId(Long doctorId);
	// Patient-level methods: Fetch appointments for a specific patient
    List<AppointmentEntity> getAppointmentsByPatientId(Long patientId);

}

