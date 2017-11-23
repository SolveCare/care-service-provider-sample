package care.service.provider.controller;

import care.service.provider.dto.ScheduleSlotDto;
import care.service.provider.entity.Doctor;
import care.service.provider.entity.ScheduleSlot;
import care.service.provider.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinic")
public class ClinicController {

    private ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return clinicService.getAllDoctors();
    }

    @GetMapping("/schedule/doctor")
    public List<ScheduleSlotDto> getDoctorFreeSchedule(@RequestParam String doctorId) {
        return clinicService.getDoctorFreeSchedule(doctorId);
    }

    @GetMapping("/schedule/patient")
    public List<ScheduleSlotDto> getPatientSchedule(@RequestParam String patientId,
                                                    @RequestParam(required = false) String doctorId) {
        return clinicService.getPatientSchedule(patientId, doctorId);
    }

    @PostMapping("/schedule")
    public ScheduleSlotDto createScheduleSlot(@RequestBody ScheduleSlotDto scheduleSlotDto) {
        return clinicService.addScheduleSlot(scheduleSlotDto);
    }

    @PatchMapping("/schedule")
    public ScheduleSlotDto bookScheduleSlot(@RequestParam String scheduleId,
                                            @RequestParam String patientId) {
        return clinicService.bookScheduleSlot(scheduleId, patientId);
    }

}
