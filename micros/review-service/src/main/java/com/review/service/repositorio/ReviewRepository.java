package com.review.service.repositorio;

import java.util.List;

import com.review.service.entidades.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

	List<Review> findByUsuarioId(int usuarioId);
	
}
