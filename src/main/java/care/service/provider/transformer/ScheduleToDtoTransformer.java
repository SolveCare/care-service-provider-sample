package care.service.provider.transformer;

import care.service.provider.dto.ScheduleSlotDto;
import care.service.provider.entity.ScheduleSlot;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ScheduleToDtoTransformer implements Transformer <ScheduleSlot, ScheduleSlotDto> {

    @Override
    public ScheduleSlotDto transform(ScheduleSlot slot) {
        ScheduleSlotDto slotDto = ScheduleSlotDto.builder().build();
        BeanUtils.copyProperties(slot, slotDto);

        final Instant timeStart = slot.getTimeStart();
        final Instant timeFinish = slot.getTimeFinish();
        slotDto.setTimeStart(timeStart.getEpochSecond());
        slotDto.setTimeFinish(timeFinish.getEpochSecond());

        return slotDto;
    }
}
