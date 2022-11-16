package empapp;

import empapp.dto.AddressDto;
import empapp.dto.CreateAddressDto;
import empapp.dto.CreateEmployeeCommand;
import empapp.dto.EmployeeDto;
import empapp.entity.Address;
import empapp.entity.Employee;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toEmployeeDto(Employee employee);

    List<EmployeeDto> toEmployeesDto(List<Employee> employees);

    AddressDto toAddressDto(Address address);

    List<AddressDto> toAddressesDto(List<Address> addresses);

    Employee toEmployee(CreateEmployeeCommand command);

    Address toAddress(CreateAddressDto createAddressDto);

    @AfterMapping
    default void setShoppingCartSampleParent(@MappingTarget Employee employee){
        for(Address address: employee.getAddresses()){
            address.setEmployee(employee);
        }
    }
}
