package com.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.usuario.service.modelos.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.CustomerFeignClient;
import com.usuario.service.feignclients.ReviewFeignClient;
import com.usuario.service.modelos.Customer;
import com.usuario.service.repositorio.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CustomerFeignClient customerFeignClient;

	@Autowired
	private ReviewFeignClient reviewFeignClient;

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
	}

	public List<Customer> getCustomer(int usuarioId) {
		List<Customer> customers = restTemplate.getForObject("http://localhost:8002/customer/usuario/" + usuarioId, List.class);
		return customers;
	}

	public List<Review> getReviews(int usuarioId) {
		List<Review> reviews = restTemplate.getForObject("http://localhost:8003/review/usuario/" + usuarioId, List.class);
		return reviews;
	}

	public Customer saveCustomer(int usuarioId, Customer customer) {
		customer.setUsuarioId(usuarioId);
		Customer nuevoCustomer = customerFeignClient.save(customer);
		return nuevoCustomer;
	}
	
	public Review saveReview(int usuarioId,Review review) {
		review.setUsuarioId(usuarioId);
		Review nuevaReview = reviewFeignClient.save(review);
		return nuevaReview;
	}
	
	public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
		Map<String,Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		
		if(usuario == null) {
			resultado.put("Mensaje", "El usuario no existe");
			return resultado;
		}
		
		resultado.put("Usuario",usuario);
		List<Customer> customers = customerFeignClient.getCustomer(usuarioId);
		if(customers.isEmpty()) {
			resultado.put("Customers", "El usuario no tiene Customers asociados");
		}
		else {
			resultado.put("Customers", customers);
		}
		
		List<Review> reviews = reviewFeignClient.getReviews(usuarioId);
		if(reviews.isEmpty()) {
			resultado.put("Reviews", "El usuario no tiene reviews");
		}		
		else {
			resultado.put("Reviews", reviews);
		}
		return resultado;
	}

}