package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.dummy.BookDummy;
import com.getir.readingisgood.dummy.OrderDummy;
import com.getir.readingisgood.dummy.UserDummy;
import com.getir.readingisgood.persist.exception.BaseException;
import com.getir.readingisgood.persist.exception.OrderNotFoundException;
import com.getir.readingisgood.persist.model.Order;
import com.getir.readingisgood.persist.repository.IOrderRepository;
import com.getir.readingisgood.service.IBookService;
import com.getir.readingisgood.service.IStatisticService;
import com.getir.readingisgood.service.IUserService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    IBookService bookService;
    @Mock
    IUserService userService;
    @Mock
    IStatisticService statisticService;
    @Mock
    IOrderRepository orderRepository;
    @Mock
    MongoTemplate mongoTemplate;

    @Mock
    ReentrantLock locker;

    @InjectMocks
    OrderService orderService;


    @Test
    void createOrder() {
        try {
            when(locker.tryLock(Mockito.anyLong(), Mockito.any(TimeUnit.class))).thenReturn(Boolean.TRUE);
            when(userService.getCurrentUser()).thenReturn(UserDummy.singleUser());
            when(bookService.sellBook(Mockito.anyString(), Mockito.any(Integer.class))).thenReturn(BookDummy.singleBook());
            when(orderRepository.save(Mockito.any(Order.class))).thenReturn(OrderDummy.singleOrder());
            //
            orderService.createOrder(OrderDummy.listOfOrderDetail());
            //
            verify(userService, times(1)).getCurrentUser();
            verify(bookService, times(OrderDummy.listOfOrderDetail().size())).sellBook(Mockito.anyString(), Mockito.any(Integer.class));
            verify(orderRepository, times(1)).save(Mockito.any(Order.class));
            verify(statisticService, times(1)).orderCreated(Mockito.any(Order.class));
            verify(locker, times(1)).tryLock(Mockito.anyLong(), Mockito.any(TimeUnit.class));
            verify(locker, times(1)).unlock();
        } catch (Exception e) {
            fail("unexpected exception");
        }

    }

    @Test
    void findAll() {
        try {
            when(userService.getCurrentUser()).thenReturn(UserDummy.singleUser());
            orderService.findAll();
            verify(orderRepository, times(1)).findAllByUserId(Mockito.anyString());
        } catch (BaseException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    void testFindAll() {
        Pageable pageable = Mockito.mock(Pageable.class);
        when(pageable.isUnpaged()).thenReturn(Boolean.TRUE);
        try {
            when(userService.getCurrentUser()).thenReturn(UserDummy.singleUser());
            when(mongoTemplate.find(Mockito.any(Query.class), Mockito.any(Class.class))).thenReturn(OrderDummy.listOfOrders());
            //
            orderService.findAll(pageable);
            //
            verify(mongoTemplate, times(1)).find(Mockito.any(Query.class), Mockito.any(Class.class));

        } catch (BaseException e) {
            fail("Unexpected exception");
        }

    }

    @Test
    void findById() {
        when(orderRepository.findById(Mockito.anyString())).thenReturn(OrderDummy.singleOrderOpt());
        //
        try {
            orderService.findById(Faker.instance().lorem().word());
            //
            verify(orderRepository, times(1)).findById(Mockito.anyString());
        } catch (BaseException e) {
            fail("unexpected exception");
        }
    }

    @Test
    void findById_withException() {
        when(orderRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        //
        try {
            orderService.findById(Faker.instance().lorem().word());
            //
            fail("unexpected return");
        } catch (BaseException e) {
            assertEquals(OrderNotFoundException.class, e.getClass());
        }
    }

    @Test
    void findByDateInterval() {
        when(mongoTemplate.find(Mockito.any(Query.class), Mockito.any(Class.class))).thenReturn(null);
        //
        orderService.findByDateInterval(Faker.instance().date().birthday(), Faker.instance().date().birthday());
        //
        verify(mongoTemplate, times(1)).find(Mockito.any(Query.class), Mockito.any(Class.class));
    }
}