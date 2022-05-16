package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.persist.exception.BaseException;
import com.getir.readingisgood.persist.exception.LockingFailedException;
import com.getir.readingisgood.persist.exception.OrderNotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final IBookService bookService;
    private final IUserService userService;
    private final IStatisticService statisticService;
    private final IOrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;
    private final ReentrantLock locker;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(List<OrderDetail> orderDetails) throws BaseException {
        try {
            if (!locker.tryLock(20, TimeUnit.SECONDS)) {
                throw new LockingFailedException();
            }
            User user = userService.getCurrentUser();
            OrderBuilder orderBuilder = new OrderBuilder(user);
            for (OrderDetail detail : orderDetails) {
                Book book = bookService.sellBook(detail.getBookId(), detail.getQuantity());
                orderBuilder.add(book, detail.getQuantity());
            }
            Order order = orderRepository.save(orderBuilder.build());
            statisticService.orderCreated(order);
            return order;
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        } finally {
            locker.unlock();
        }
    }

    @Override
    public List<Order> findAll() throws BaseException {
        User user = userService.getCurrentUser();
        return orderRepository.findAllByUserId(user.getId());
    }

    @Override
    public Page<Order> findAll(Pageable pageable) throws BaseException {
        Query query = new Query().with(pageable);
        query.addCriteria(Criteria.where("userId").is(userService.getCurrentUser().getId()));

        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Order.class),
                pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), Order.class)
        );
    }

    @Override
    public Order findById(String id) throws BaseException {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isEmpty()) {
            throw new OrderNotFoundException(id);
        }
        return orderOpt.get();
    }

    @Override
    public List<Order> findByDateInterval(Date from, Date to) {
        Query query = new Query();
        query.addCriteria(Criteria.where("creationDate").gte(from).lt(to));
        return mongoTemplate.find(query, Order.class);
    }
}
