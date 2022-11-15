package empapp;

import empapp.dto.CreateEmployeeCommand;
import empapp.dto.EmployeeDto;
import empapp.dto.UpdateEmployeeCommand;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> employees() {
        return employeeService.listEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") long id) {
        var employee = employeeService.findEmployeeById(id);
        return ResponseEntity
                .ok()
                .header("Request-Id", UUID.randomUUID().toString())
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .eTag(Integer.toString(employee.hashCode()))
                .body(employee);
    }

    @PostMapping // nem idempotens
    @Operation(description = "creates an employee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody CreateEmployeeCommand command) {
        EmployeeDto employeeDto = employeeService.createEmployee(command);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeDto.getId()).toUri()).body(employeeDto);
    }

    @PutMapping("/{id}") // idempotens
    public EmployeeDto updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand command) {
        return employeeService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
    }

}
