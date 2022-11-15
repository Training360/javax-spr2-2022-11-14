package empapp.dto;

import lombok.Value;

@Value
public class EmployeeHasBeenCreatedEvent {

    private Long employeeId;

    private String employeeName;
}
