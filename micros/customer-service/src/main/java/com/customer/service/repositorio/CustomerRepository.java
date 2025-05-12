package com.customer.service.repositorio;

import java.util.List;

import com.customer.service.entidades.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	List<Customer> findByUsuarioId(int usuarioId);
	
}
