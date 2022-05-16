package com.getir.readingisgood.persist.repository;

import com.getir.readingisgood.persist.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IOrderRepository extends MongoRepository<Order, String> {

    List<Order> findAllByUserId(String userId);

}
