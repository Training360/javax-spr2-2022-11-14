package empapp.dto;

import empapp.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CreateEmployeeCommand {

    @Schema(description = "the name of the new employee", example = "John Doe")
    private String name;

    private List<Address> addresses;
}
