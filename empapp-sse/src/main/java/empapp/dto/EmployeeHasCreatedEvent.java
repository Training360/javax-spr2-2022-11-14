package empapp.dto;

import lombok.Value;

@Value
public class EmployeeHasCreatedEvent {

    private long employeeId;

    private String employeeName;
}
