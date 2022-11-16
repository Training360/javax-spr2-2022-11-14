package empapp;

import empapp.dto.EmployeeWdto;
import empapp.dto.FindEmployeeByIdWdto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@Component
@WebService
@AllArgsConstructor
public class EmployeesWebService {

    private EmployeeService employeeService;

    public EmployeeWdto findEmployeeById(FindEmployeeByIdWdto findEmployee) {
        var employee = employeeService.findEmployeeById(findEmployee.getId());
        return new EmployeeWdto(employee.getId(), employee.getName());
    }
}
