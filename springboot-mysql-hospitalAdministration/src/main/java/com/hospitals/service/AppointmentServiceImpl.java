package com.hospitals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitals.entity.AppointmentEntity;
import com.hospitals.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public List<AppointmentEntity> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	@Override
	public List<AppointmentEntity> getAppointmentsByDoctor(Long doctorId) {
		return appointmentRepository.findByDoctorId(doctorId);
	}

	@Override
	public List<AppointmentEntity> getAppointmentsByDoctorId(Long doctorId) {
		return appointmentRepository.findByDoctorId(doctorId); // Assuming you have a method in the repository
	}

	@Override
	public List<AppointmentEntity> getAppointmentsByPatient(Long patientId) {
		return appointmentRepository.findByPatientId(patientId);
	}

	@Override
	public void saveAppointment(AppointmentEntity appointment) {
		appointmentRepository.save(appointment);
	}

	@Override
	public void cancelAppointment(Long id) {
		appointmentRepository.deleteById(id);
	}

	@Override
	public List<AppointmentEntity> findAppointmentsByDoctorId(Long doctorId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Patient-level method: Fetch appointments for a specific patient
    @Override
    public List<AppointmentEntity> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
}
