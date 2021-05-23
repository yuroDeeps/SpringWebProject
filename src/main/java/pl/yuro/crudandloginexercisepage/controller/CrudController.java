package pl.yuro.crudandloginexercisepage.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.yuro.crudandloginexercisepage.dto.EmployeeDTO;
import pl.yuro.crudandloginexercisepage.dto.UserDTO;
import pl.yuro.crudandloginexercisepage.entity.Employee;
import pl.yuro.crudandloginexercisepage.repository.EmployeeRepository;
import pl.yuro.crudandloginexercisepage.repository.UserRepository;
import pl.yuro.crudandloginexercisepage.service.EmployeeService;
import pl.yuro.crudandloginexercisepage.utilities.NotNullForRegistration;

@Controller
@Validated
@RequestMapping("/crud")
public class CrudController {
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrmmer = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrmmer);
		
	}
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private NotNullForRegistration notNullForRegistration;
	
	
	@GetMapping("/list")
	public String showList(Model model) {
		model.addAttribute("employees",employeeRepository.findAll());
		return "crud-list";
	}
	
	@GetMapping("/add")
	public String addEmployee(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,Model model) {
		model.addAttribute("employeeDTO",employeeDTO);
		return "save-employee";
	}
	
	@PostMapping("/add")
	public String save(@Validated @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO, BindingResult bindingResult) {
		notNullForRegistration.NotNullEmployeeDTO(employeeDTO, bindingResult);
		if(bindingResult.hasErrors()) {
			return "save-employee";
		}
		
		Employee employee = new Employee();
		
		employeeService.employeeSave(employeeDTO, employee);
		
		
		
		return "redirect:/crud/list";
		
	}
	
	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model ) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Niepoprawne Id pracownika: " + id));
		model.addAttribute("employee",employee);
		return "update-form";
		
	}
	
	@PostMapping("/update/{id}")
	public String updateEmployee(Employee employee,Model model) {
		employeeRepository.save(employee);
		return "redirect:/crud/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model ) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Niepoprawne Id pracownika: " + id));
		employeeRepository.delete(employee);
		return "redirect:/crud/list";
		
	}

}
