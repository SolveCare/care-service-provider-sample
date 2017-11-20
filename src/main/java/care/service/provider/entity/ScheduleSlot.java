package care.service.provider.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "schedule", schema = "clinic")
public class ScheduleSlot {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(name = "schedule_id")
    private String id;

    @Column
    private String doctorId;

    @Column
    private String patientId;

    @Column
    private Instant timeStart;

    @Column
    private Instant timeFinish;

}
