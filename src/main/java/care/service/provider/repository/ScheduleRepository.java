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

    ScheduleSlot save(ScheduleSlot slot);

    @Modifying
    @Transactional
    @Query("UPDATE ScheduleSlot scheduleSlot SET scheduleSlot.patientId = :patientId WHERE scheduleSlot.id = :scheduleId AND scheduleSlot.patientId IS NULL")
    int setPatientFor(@Param("scheduleId") String scheduleId,
                      @Param("patientId") String patientId);
}