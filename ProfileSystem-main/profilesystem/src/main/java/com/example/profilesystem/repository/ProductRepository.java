package com.example.profilesystem.repository;

import org.springframework.stereotype.Repository;

import com.example.profilesystem.pojo.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

	Optional<Product> findById(Integer productId);
	
	
	
	@Query(value="Select * from Product where subscribed=true and subscriberId = subscriberId", nativeQuery=true)
	List<Product> findProductSubscribedByCustomer(@Param("subscriberId")Integer subscriberId);
	

}
