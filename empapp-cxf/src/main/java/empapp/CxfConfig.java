package empapp;


import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.cxf.Bus;
import javax.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;

import java.util.List;

@Configuration
public class CxfConfig {

    @Bean
    public Endpoint employeeEndpoint(Bus bus, EmployeesWebService employeesWebService) {
        var endpoint = new EndpointImpl(bus, employeesWebService);
        endpoint.setFeatures(List.of(loggingFeature()));
        endpoint.publish("/employees");
        return endpoint;
    }

    @Bean
    public LoggingFeature loggingFeature() {
        var loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        loggingFeature.setVerbose(true);
        loggingFeature.setLogMultipart(true);
        return loggingFeature;
    }
}
