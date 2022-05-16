package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.exception.BaseException;
import com.getir.readingisgood.persist.model.Book;
import com.getir.readingisgood.persist.model.Order;
import com.getir.readingisgood.persist.model.OrderDetail;
import com.getir.readingisgood.persist.model.User;
import com.getir.readingisgood.persist.model.builder.OrderBuilder;
import com.getir.readingisgood.persist.repository.IOrderRepository;
import com.getir.readingisgood.service.IBookService;
import com.getir.readingisgood.service.IOrderService;
import com.getir.readingisgood.service.IStatisticService;
import com.getir.readingisgood.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final IBookService bookService;
    private final IUserService userService;
    private final IStatisticService statisticService;
    private final IOrderRepository orderRepository;


    @Override
    public Order createOrder(List<OrderDetail> orderDetails) throws BaseException {
        User user = userService.getCurrentUser();
        OrderBuilder orderBuilder = new OrderBuilder(user);
        for (OrderDetail detail : orderDetails) {
            Book book = bookService.sellBook(detail.getBookId(), detail.getQuantity());
            orderBuilder.add(book, detail.getQuantity());
        }
        Order order = orderRepository.save(orderBuilder.build());
        statisticService.orderCreated(order);
        return order;
    }

    @Override
    public List<Order> findAll() throws BaseException {
        User user = userService.getCurrentUser();
        return orderRepository.findAllByUserId(user.getId());
    }
}
