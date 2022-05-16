package com.getir.readingisgood.persist.model.builder;

import com.getir.readingisgood.persist.model.Book;
import com.getir.readingisgood.persist.model.Order;
import com.getir.readingisgood.persist.model.OrderDetail;
import com.getir.readingisgood.persist.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class OrderBuilder {
    private Order order;

    public OrderBuilder(User user) {
        this.order = new Order();
        this.order.setUserId(user.getId());
        this.order.setAmount(BigDecimal.ZERO);
        this.order.setCreationDate(new Date());
        this.order.setOrderDetails(new ArrayList<>());
    }

    public OrderBuilder add(Book book, Integer quantity) {
        OrderDetail detail = new OrderDetail(book.getId(), quantity);
        order.getOrderDetails().add(detail);
        order.setAmount(order.getAmount().add(book.getPrice().multiply(new BigDecimal(quantity))));
        return this;
    }

    public Order build() {
        return order;
    }
}
