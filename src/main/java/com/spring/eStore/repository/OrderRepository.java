package com.spring.eStore.repository;

import com.spring.eStore.entity.Order;
import com.spring.eStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByUser(User user);
}
