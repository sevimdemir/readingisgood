package com.getir.readingisgood.persist.model.builder;

import com.getir.readingisgood.persist.model.Statistic;

import java.math.BigDecimal;

public class StatisticBuilder {
    private Statistic stat;

    public StatisticBuilder(Integer year, Integer month) {
        stat = new Statistic();
        stat.setYear(year);
        stat.setMonth(month);
        stat.setOrderCount(0L);
        stat.setRevenue(BigDecimal.ZERO);
        stat.setSoldBookCount(0L);
    }

    public Statistic build() {
        return stat;
    }
}
