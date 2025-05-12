package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.service.modelos.Customer;

@FeignClient(name = "customer-service",url = "http://localhost:8002")
@RequestMapping("/customer")
public interface CustomerFeignClient {

	@PostMapping()
	public Customer save(@RequestBody Customer customer);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<Customer> getCustomer(@PathVariable("usuarioId") int usuarioId);
}
