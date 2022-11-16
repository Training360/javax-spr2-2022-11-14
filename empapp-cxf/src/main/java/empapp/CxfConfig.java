package empapp;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.cxf.Bus;
import javax.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;

@Configuration
public class CxfConfig {

    @Bean
    public Endpoint employeeEndpoint(Bus bus, EmployeesWebService employeesWebService) {
        var endpoint = new EndpointImpl(bus, employeesWebService);
        endpoint.publish("/employees");
        return endpoint;
    }
}
