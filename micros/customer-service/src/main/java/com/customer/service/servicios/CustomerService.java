package com.customer.service.servicios;

import java.util.List;

import com.customer.service.entidades.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.service.repositorio.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getAll(){
		return customerRepository.findAll();
	}
	
	public Customer getCustomerById(int id) {
		return customerRepository.findById(id).orElse(null);
	}
	
	public Customer save(Customer customer) {
		Customer nuevoCustomer = customerRepository.save(customer);
		return nuevoCustomer;
	}
	
	public List<Customer> byUsuarioId(int usuarioId){
		return customerRepository.findByUsuarioId(usuarioId);
	}
}
