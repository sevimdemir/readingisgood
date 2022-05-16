package com.getir.readingisgood.service;

import com.getir.readingisgood.persist.exception.BaseException;
import com.getir.readingisgood.persist.model.Order;
import com.getir.readingisgood.persist.model.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IOrderService {
    Order createOrder(List<OrderDetail> orderDetails) throws BaseException;

    List<Order> findAll() throws BaseException;

    Page<Order> findAll(Pageable pageable) throws BaseException;

    Order findById(String id) throws BaseException;

    List<Order> findByDateInterval(Date from, Date to);
}
