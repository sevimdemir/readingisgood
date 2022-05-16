package com.getir.readingisgood.service;

import com.getir.readingisgood.exception.BaseException;
import com.getir.readingisgood.persist.model.Order;
import com.getir.readingisgood.persist.model.OrderDetail;

import java.util.List;

public interface IOrderService {
    Order createOrder(List<OrderDetail> orderDetails) throws BaseException;

    List<Order> findAll() throws BaseException;
}
