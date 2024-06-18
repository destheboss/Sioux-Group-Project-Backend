package sioux.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private Long id;
    private String subject;
    private Date startTime;
    private Date endTime;
    private Boolean isAllDay;
    private Long employeeId;
}
