package Employee.project.Employee.service;

import Employee.project.Employee.Request.EmployeeDTO;
import Employee.project.Employee.Response.BaseResponse;
import Employee.project.Employee.Response.EmployeeTaxDTO;
import java.util.List;

public interface EmployeeService {

    BaseResponse SaveEmployee(EmployeeDTO employeeDTO);

    List<EmployeeTaxDTO> calculateTaxForCurrentFinancialYear();
}
