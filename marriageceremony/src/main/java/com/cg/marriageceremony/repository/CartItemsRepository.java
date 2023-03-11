package com.cg.marriageceremony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.marriageceremony.model.CartItems;


@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Integer>{
	
	@Query(value="select * from cart_items where customer_id=  :c",nativeQuery=true)
	List<CartItems> findAllItemsInCart(@Param("c") int custId);
	
	@Query(value="delete from Cart_Items where v_id =:p AND customer_id=:c",nativeQuery=true)
	@Modifying
	int deleteItemFromCart(@Param("p") int prodId,@Param("c") int custId);
}