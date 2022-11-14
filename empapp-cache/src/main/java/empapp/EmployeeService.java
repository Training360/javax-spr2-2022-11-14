package empapp;

import empapp.dto.CreateEmployeeCommand;
import empapp.dto.EmployeeDto;
import empapp.dto.UpdateEmployeeCommand;
import empapp.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper;

    @CacheEvict(value = "employees", allEntries = true)
    @CachePut(key = "#result.id", value = "employee")
    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = employeeMapper.toEmployee(command);
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeDto(employee);
    }

    @Cacheable("employees") // <Void, List<EmployeeDto>>
    public List<EmployeeDto> listEmployees() {
        return employeeMapper.toEmployeesDto(employeeRepository.findAllWithAddresses());
    }

    @Cacheable("employee") // <Long, EmployeeDto>
    public EmployeeDto findEmployeeById(long id) {
        return employeeMapper.toEmployeeDto(employeeRepository.findByIdWithAddresses(id)
                        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id)));
    }

    @Transactional
    @Caching(
            put = {
                    @CachePut(value = "employee", key = "#id")
            },
            evict = {
            //@CacheEvict(value = "employee", key = "#id"),
            @CacheEvict(value = "employees", allEntries = true)
    })
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employeeToModify = employeeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeToModify.setName(command.getName());
        return employeeMapper.toEmployeeDto(employeeToModify);
    }

    @Caching( evict = {
            @CacheEvict(value = "employee", key = "#id"),
            @CacheEvict(value = "employees", allEntries = true)
    })
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findByIdWithAddresses(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
}
