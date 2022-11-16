package training.empappcxfclient;

import empapp.EmployeesWebServiceService;
import empapp.FindEmployeeByIdWdto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpappCxfClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmpappCxfClientApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		var service = new EmployeesWebServiceService();
		var port = service.getEmployeesWebServicePort();
		var request = new FindEmployeeByIdWdto();
		request.setId(1);

		var response = port.findEmployeeById(request);
		System.out.println(response.getId() + " " + response.getName());

	}
}
