package Employee.project.Employee.Repo;

import Employee.project.Employee.DAO.EmployeeDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeDAO,Long> {

}
