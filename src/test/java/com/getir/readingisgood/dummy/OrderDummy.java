package com.getir.readingisgood.dummy;

import com.getir.readingisgood.persist.model.Order;
import com.getir.readingisgood.persist.model.OrderDetail;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDummy {

    public static Order singleOrder() {
        return singleOrderWithId(1);
    }

    public static Optional<Order> singleOrderOpt() {
        return Optional.of(singleOrderWithId(1));
    }

    private static Order singleOrderWithId(int id) {
        return new Order("id_" + id,
                "id_" + id,
                BigDecimal.TEN,
                Faker.instance().date().birthday(),
                listOfOrderDetail()
        );
    }

    public static List<Order> listOfOrders() {
        return new ArrayList<>() {{
            add(singleOrderWithId(1));
            add(singleOrderWithId(2));
            add(singleOrderWithId(3));
        }};

    }

    public static List<OrderDetail> listOfOrderDetail() {
        return new ArrayList<>() {{
            add(singleOrderDetailWithId(1));
            add(singleOrderDetailWithId(2));
            add(singleOrderDetailWithId(3));
        }};

    }

    private static OrderDetail singleOrderDetailWithId(int i) {
        return new OrderDetail("id_" + i,
                Faker.instance().number().randomDigit()
        );
    }
}
