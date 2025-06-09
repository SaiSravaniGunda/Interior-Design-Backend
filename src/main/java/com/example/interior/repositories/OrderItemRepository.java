package com.example.interior.repositories;

import com.example.interior.dto.OrderItemResponse;
import com.example.interior.models.OrderItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//	 @Query("SELECT oi FROM OrderItem oi WHERE oi.product.shop.vendor.id = :vendorId")
//	    List<OrderItem> findByVendorId(@Param("vendorId") Long vendorId);
	
//	@Query("SELECT oi FROM OrderItem oi " +
//	           "JOIN FETCH oi.product p " +
//	           "JOIN FETCH p.shop s " +
//	           "WHERE s.vendor.id = :vendorId")
//	    List<OrderItem> findByVendorId(@Param("vendorId") Long vendorId);
//	@Query("SELECT oi FROM OrderItem oi " +
//		       "JOIN FETCH oi.product p " +
//		       "JOIN FETCH p.shop s " +
//		       "JOIN FETCH oi.order o " +  // ✅ Join the Order entity to fetch orderId
//		       "WHERE s.vendor.id = :vendorId")
//		List<OrderItem> findByVendorId(@Param("vendorId") Long vendorId);
	@Query("SELECT new com.example.interior.dto.OrderItemResponse(oi) " +
	           "FROM OrderItem oi " +
	           "JOIN oi.product p " +
	           "JOIN p.shop s " +
	           "WHERE s.vendor.id = :vendorId")
	    List<OrderItemResponse> findByVendorId(@Param("vendorId") Long vendorId);
	
	 

}
