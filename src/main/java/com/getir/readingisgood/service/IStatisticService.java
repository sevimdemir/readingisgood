package com.getir.readingisgood.service;

import com.getir.readingisgood.persist.model.Order;
import com.getir.readingisgood.persist.model.Statistic;

import java.util.List;

public interface IStatisticService {

    void orderCreated(Order order);

    List<Statistic> findAll();
}
