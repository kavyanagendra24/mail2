package com.example.springapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.springapp.Repository.EmployeeRepository;
import com.example.springapp.Repository.EmployeeRepositoryImpl;
import com.example.springapp.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

@Controller
public class EmployeeController {
	private final EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeRepositoryImpl employeeRepositoryimpl;

 

	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/employee")
	public String form(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "login";

	}


	@PostMapping("/Login")
	public String Form(@ModelAttribute("employee") Employee employee, @RequestParam("file") MultipartFile file,
			@RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
		if (!file.isEmpty()) {
			try {
				byte[] content = file.getBytes();

				employee.setContent(content);
			} catch (Exception e) {
				// Handle file saving error
			}
		}
		
		 employee.setDatetimeField(dateTime);
		employeeRepository.save(employee);
		
		return "success";
	}

	@RequestMapping("/employee")
	public String getAllEmployee(Model model) {
		 List<Employee> employees = employeeRepository.findAll();
		 model.addAttribute("employees", employees);
		 return "employee-list";
		 
	}

      
	 @RequestMapping("/employee-list")
	  public String getAllEmployees(
	      @RequestParam(value = "page", defaultValue = "1") int page,
	      Model model)
	   {
	      int pageSize = 2;
	      Pageable pageable = PageRequest.of(page - 1, pageSize);
	      Page<Employee> employeePage = employeeRepository.findAll(pageable);

	      int totalPages = employeePage.getTotalPages();
	      List<Employee> employees = employeePage.getContent();
	     

	      model.addAttribute("employees", employees);
	      model.addAttribute("totalPages", totalPages);
	      model.addAttribute("currentPage", page);

	      return "employee-list";
	  
	    }

	@GetMapping("/edit")
	public String editEmployee(@RequestParam("id") int id, Model model) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid employee id: " + id));
		model.addAttribute("employee", employee);
		return "edit-employee";
	}

	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute("employee") Employee updatedEmployee) {
		employeeRepository.save(updatedEmployee);
		return "update";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") int id) {
		employeeRepository.deleteById(id);
		return "edit";
	}

	@GetMapping("/downloadFile")
	public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam("id") int id) {
		// Find the employee by ID
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid employee id: " + id));

		// Create a ByteArrayResource from the employee's content
		ByteArrayResource resource = new ByteArrayResource(employee.getContent());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + employee.getName())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(employee.getContent().length)
				.body(resource);
	}

//	@GetMapping("/employees")
//	public String getUsersList(@RequestParam(value = "search", required = false) String search, Model model) {
//		List<Employee> employees;
//
//		if ((search != null && !search.isEmpty())) {
//			employees = employeeRepository.searchUsersByName(search);
//		} else {
//			employees = employeeRepository.findAll();
//		}
//		model.addAttribute("employees", employees);
//		return "employee-list";
//	}

	@GetMapping("/employees")
    public String getUsersList(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "fromDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime,
            @RequestParam(value = "toDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime,
            Model model
    ) {
        List<Employee> employees;

        if ((search != null && !search.isEmpty()) || (fromDateTime != null && toDateTime != null)) {
            employees = employeeRepository.searchUsersByNameAndDateTimeRange(search, fromDateTime, toDateTime);
        } else {
            employees = employeeRepository.findAll();
        }

        model.addAttribute("employees", employees);
        return "employee-list";
    }
	
	@GetMapping("/suggestions")
	@ResponseBody
	public List<String> getSuggestions(@RequestParam("search") String search) {
		List<String> suggestions = employeeRepository.findSuggestionsByName(search);
		return suggestions;
	}

	
	@RequestMapping("/addColumn")
	public ModelAndView addColumn(String columnName, String dataType) {
		employeeRepositoryimpl.addColumn(columnName, dataType);
	
		ModelAndView modelAndView = new ModelAndView("addColumn");
		modelAndView.addObject("message", "Column added successfully!");
		return modelAndView;
	}
	 

}
