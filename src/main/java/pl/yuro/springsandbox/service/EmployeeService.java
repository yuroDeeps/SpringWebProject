package pl.yuro.springsandbox.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.yuro.springsandbox.dto.EmployeeDTO;
import pl.yuro.springsandbox.entity.Employee;
import pl.yuro.springsandbox.repository.EmployeeRepository;

@Service
public class EmployeeService  {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Transactional
	public void employeeSave(EmployeeDTO employeeDTO, Employee employee) {
		modelMapper.map(employeeDTO, employee);
		
		employeeRepository.save(employee);
	}
	
	
}
