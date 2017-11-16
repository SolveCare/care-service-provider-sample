package care.service.provider.repository;

import care.service.provider.entity.Doctor;
import care.service.provider.entity.ScheduleSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleSlot, String> {

    List<ScheduleSlot> findAllByDoctorIdAndPatientId(String doctorId, String patientId);

    @Modifying
    @Transactional
    @Query("UPDATE ScheduleSlot slot SET slot.patientId = :patientId WHERE slot.id = :scheduleId AND slot.patientId IS NULL")
    int setFixedPatientFor(@Param("scheduleId") String scheduleId,
                           @Param("patientId") String patientId);
}