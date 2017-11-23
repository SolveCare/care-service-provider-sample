package care.service.provider.service;

import care.service.provider.dto.ScheduleSlotDto;
import care.service.provider.entity.Doctor;
import care.service.provider.entity.ScheduleSlot;
import care.service.provider.repository.DoctorRepository;
import care.service.provider.repository.ScheduleRepository;
import care.service.provider.transformer.DtoToScheduleTransformer;
import care.service.provider.transformer.ScheduleToDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClinicService {

    private DoctorRepository doctorRepository;
    private ScheduleRepository scheduleRepository;
    private ScheduleToDtoTransformer scheduleToDtoTransformer;
    private DtoToScheduleTransformer dtoToScheduleTransformer;

    @Autowired
    public ClinicService(DoctorRepository doctorRepository, ScheduleRepository scheduleRepository, ScheduleToDtoTransformer scheduleToDtoTransformer, DtoToScheduleTransformer dtoToScheduleTransformer) {
        this.doctorRepository = doctorRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleToDtoTransformer = scheduleToDtoTransformer;
        this.dtoToScheduleTransformer = dtoToScheduleTransformer;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<ScheduleSlotDto> getDoctorFreeSchedule(String doctorId) {
        List<ScheduleSlot> slots = scheduleRepository.findAllByDoctorId(doctorId);

        return scheduleToDtoTransformer.transformList(slots);
    }

    public List<ScheduleSlotDto> getPatientSchedule(String patientId, String doctorId) {
        List<ScheduleSlot> slots = scheduleRepository.findAllByDoctorIdAndPatientId(doctorId, patientId);

        return scheduleToDtoTransformer.transformList(slots);
    }

    public ScheduleSlotDto addScheduleSlot(ScheduleSlotDto scheduleSlotDto) {
        ScheduleSlot scheduleSlot = dtoToScheduleTransformer.transform(scheduleSlotDto);
        scheduleSlot = scheduleRepository.save(scheduleSlot);

        return scheduleToDtoTransformer.transform(scheduleSlot);
    }

    public ScheduleSlotDto bookScheduleSlot(String scheduleId, String patientId) {
        Integer modifiedRows = scheduleRepository.setPatientFor(scheduleId, patientId);

        if (modifiedRows.equals(0)) {
            throw new RuntimeException(String.format("There are no free slots by id: %s", scheduleId));
        }

        ScheduleSlot scheduleSlot = scheduleRepository.getOne(scheduleId);

        return scheduleToDtoTransformer.transform(scheduleSlot);
    }

}
