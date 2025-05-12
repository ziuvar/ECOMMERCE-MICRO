package com.usuario.service.controlador;

import java.util.List;
import java.util.Map;

import com.usuario.service.modelos.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Customer;
import com.usuario.service.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> usuarios = usuarioService.getAll();
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}
	
	@GetMapping("/customers/{usuarioId}")
	public ResponseEntity<List<Customer>> listarCustomers(@PathVariable("usuarioId") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Customer> customers = usuarioService.getCustomer(id);
		return ResponseEntity.ok(customers);
	}
	
	@GetMapping("/reviews/{usuarioId}")
	public ResponseEntity<List<Review>> listarReviews(@PathVariable("usuarioId") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Review> reviews = usuarioService.getReviews(id);
		return ResponseEntity.ok(reviews);
	}
	
	@PostMapping("/customer/{usuarioId}")
	public ResponseEntity<Customer> guardarCustomer(@PathVariable("usuarioId") int usuarioId,@RequestBody Customer customer){
		Customer nuevoCustomer = usuarioService.saveCustomer(usuarioId, customer);
		return ResponseEntity.ok(nuevoCustomer);
	} 
	
	@PostMapping("/review/{usuarioId}")
	public ResponseEntity<Review> guardarReview(@PathVariable("usuarioId") int usuarioId,@RequestBody Review review){
		Review nuevaReview = usuarioService.saveReview(usuarioId, review);
		return ResponseEntity.ok(nuevaReview);
	}
	
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId){
		Map<String,Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioId);
		return ResponseEntity.ok(resultado);
	}
}