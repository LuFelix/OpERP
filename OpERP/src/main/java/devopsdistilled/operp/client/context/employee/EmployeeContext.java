package devopsdistilled.operp.client.context.employee;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ EntityModelContext.class, EntityControllerContext.class,
		MvcContext.class })
public class EmployeeContext {

}
