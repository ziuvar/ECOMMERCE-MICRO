package com.usuario.service.feignclients;

import java.util.List;

import com.usuario.service.modelos.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "review-service",url = "http://localhost:8003")
@RequestMapping("/review")
public interface ReviewFeignClient {

	@PostMapping()
	public Review save(@RequestBody Review review);

	@GetMapping("/usuario/{usuarioId}")
	public List<Review> getReviews(@PathVariable("usuarioId") int usuarioId);
}
