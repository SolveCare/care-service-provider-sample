package care.service.provider.service;

import care.service.provider.entity.Doctor;
import care.service.provider.entity.ScheduleSlot;
import care.service.provider.repository.DoctorRepository;
import care.service.provider.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClinicService {

    private DoctorRepository doctorRepository;
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ClinicService(DoctorRepository doctorRepository, ScheduleRepository scheduleRepository) {
        this.doctorRepository = doctorRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<ScheduleSlot> getDoctorSchedule(String doctorId, String patientId) {
        return scheduleRepository.findAllByDoctorIdAndPatientId(doctorId, patientId);
    }

    @Transactional(rollbackFor=Exception.class)
    public boolean bookScheduleSlot(String scheduleId, String patientId) {
        Integer modifiedRows = scheduleRepository.setFixedPatientFor(scheduleId, patientId);

        if (modifiedRows.equals(0)) {
            throw new RuntimeException(String.format("There are no free slots by id: %s", scheduleId));
        }

        return true;
    }
}
