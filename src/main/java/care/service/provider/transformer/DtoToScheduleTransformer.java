package care.service.provider.transformer;

import care.service.provider.dto.ScheduleSlotDto;
import care.service.provider.entity.ScheduleSlot;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DtoToScheduleTransformer implements Transformer <ScheduleSlotDto, ScheduleSlot> {

    @Override
    public ScheduleSlot transform(ScheduleSlotDto scheduleSlotDto) {
        return ScheduleSlot.builder()
                .doctorId(scheduleSlotDto.getDoctorId())
                .timeStart(Instant.ofEpochSecond(scheduleSlotDto.getTimeStart()))
                .timeFinish(Instant.ofEpochSecond(scheduleSlotDto.getTimeFinish()))
                .build();
    }

}
