package com.example.profilesystem.repository;

import org.springframework.stereotype.Repository;

import com.example.profilesystem.pojo.CustomerProfile;
import com.example.profilesystem.pojo.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerProfile, Integer> {

	Optional<CustomerProfile> findById(Integer customerId);

	@Query(value="SELECT * FROM  BusinessProfile WHERE customerId in(Select subscriberId from Product where subscribed=true and productId = productId)", nativeQuery=true)
	List<CustomerProfile> findCustomerSubscribedToProduct(@Param("productId")Integer productId);

}
