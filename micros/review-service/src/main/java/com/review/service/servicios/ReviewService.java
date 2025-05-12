package com.review.service.servicios;

import java.util.List;

import com.review.service.entidades.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.review.service.repositorio.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	public List<Review> getAll() {
		return reviewRepository.findAll();
	}

	public Review getReviewById(int id) {
		return reviewRepository.findById(id).orElse(null);
	}

	public Review save(Review review) {
		Review nuevaReview = reviewRepository.save(review);
		return nuevaReview;
	}

	public List<Review> byUsuarioId(int usuarioId) {
		return reviewRepository.findByUsuarioId(usuarioId);
	}
}
