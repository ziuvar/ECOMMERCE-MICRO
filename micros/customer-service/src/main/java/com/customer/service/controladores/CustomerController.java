package com.customer.service.controladores;

import java.util.List;

import com.customer.service.entidades.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.service.servicios.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<Customer>> listarCustomers(){
		List<Customer> customers = customerService.getAll();
		if(customers.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(customers);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> obtenerCustomer(@PathVariable("id") int id){
		Customer customer = customerService.getCustomerById(id);
		if(customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customer);
	}
	
	@PostMapping
	public ResponseEntity<Customer> guardarCustomer(@RequestBody Customer customer){
		Customer nuevoCustomer = customerService.save(customer);
		return ResponseEntity.ok(nuevoCustomer);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Customer>> listarCustomersPorUsuarioId(@PathVariable("usuarioId") int id){
		List<Customer> customers = customerService.byUsuarioId(id);
		if(customers.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(customers);
	}
}