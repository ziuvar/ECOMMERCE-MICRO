package com.review.service.controladores;

import java.util.List;

import com.review.service.entidades.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.review.service.servicios.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@GetMapping
		public ResponseEntity<List<Review>> listarReviews(){
		List<Review> reviews = reviewService.getAll();
		if(reviews.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(reviews);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Review> obtenerReview(@PathVariable("id") int id){
		Review review = reviewService.getReviewById(id);
		if(review == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(review);
	}
	
	@PostMapping
	public ResponseEntity<Review> guardarReview(@RequestBody Review review){
		Review nuevaReview = reviewService.save(review);
		return ResponseEntity.ok(nuevaReview);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Review>> listarReviewsPorUsuarioId(@PathVariable("usuarioId") int id){
		List<Review> reviews = reviewService.byUsuarioId(id);
		if(reviews.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(reviews);
	}
	
}
