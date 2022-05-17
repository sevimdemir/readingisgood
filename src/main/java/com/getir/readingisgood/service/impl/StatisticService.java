package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.persist.model.Order;
import com.getir.readingisgood.persist.model.OrderDetail;
import com.getir.readingisgood.persist.model.Statistic;
import com.getir.readingisgood.persist.model.builder.StatisticBuilder;
import com.getir.readingisgood.persist.repository.IStatisticRepository;
import com.getir.readingisgood.service.IStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService implements IStatisticService {

    private final IStatisticRepository statisticRepository;

    /**
     * After order created it increases the stats of the related month/year
     * @param order
     */
    @Override
    public void orderCreated(Order order) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(order.getCreationDate());

        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1;

        Statistic stat = statisticRepository.findByYearAndMonth(year, month).orElse(new StatisticBuilder(year, month).build());
        stat.setOrderCount(stat.getOrderCount() + 1);
        stat.setRevenue(stat.getRevenue().add(order.getAmount()));
        Integer sum = order.getOrderDetails().stream().map(OrderDetail::getQuantity)
                .reduce(0, Integer::sum);
        stat.setSoldBookCount(stat.getSoldBookCount() + sum);
        statisticRepository.save(stat);
    }

    @Override
    public List<Statistic> findAll() {
        return statisticRepository.findAll();
    }
}
