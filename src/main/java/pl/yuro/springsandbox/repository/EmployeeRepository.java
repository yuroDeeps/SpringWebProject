package pl.yuro.springsandbox.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.yuro.springsandbox.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {s
}
