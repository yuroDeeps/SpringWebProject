package pl.yuro.crudandloginexercisepage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.yuro.crudandloginexercisepage.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	
}
