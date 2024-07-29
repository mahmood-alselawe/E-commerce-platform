package com.takarub.ecommerce.repository;

import com.takarub.ecommerce.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    List<Products> findAllByIdInOrderById(List<Integer> productId);
}
