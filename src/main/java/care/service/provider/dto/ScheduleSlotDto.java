package care.service.provider.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.Instant;

@Data
@Builder
public class ScheduleSlotDto {

    private String id;
    private String doctorId;
    private String patientId;
    private Long timeStart;
    private Long timeFinish;

}
