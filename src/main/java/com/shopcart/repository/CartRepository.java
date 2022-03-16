package com.shopcart.repository;

import com.shopcart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByCustomerId(Long id);

    @Query(value = "SELECT * FROM CART WHERE CUSTOMER_ID = ?1 AND PRODUCT_ID = ?2", nativeQuery = true)
    Cart getCartByCustomerIdAndProductId(Long customerId, Long productId);
}