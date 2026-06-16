package com.denisar5.perfumehub.repository;

import com.denisar5.perfumehub.model.entity.Order;
import com.denisar5.perfumehub.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByUser(UserEntity user);
}