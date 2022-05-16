package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.dummy.OrderDummy;
import com.getir.readingisgood.dummy.StatisticDummy;
import com.getir.readingisgood.persist.model.Statistic;
import com.getir.readingisgood.persist.repository.IStatisticRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticServiceTest {

    @Mock
    IStatisticRepository statisticRepository;

    @InjectMocks
    StatisticService statisticService;

    @Test
    void orderCreated() {
        when(statisticRepository.findByYearAndMonth(Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .thenReturn(StatisticDummy.singleStatisticOpt());
        //
        statisticService.orderCreated(OrderDummy.singleOrder());
        //
        verify(statisticRepository, times(1)).save(Mockito.any(Statistic.class));
    }

    @Test
    void findAll() {
        statisticService.findAll();
        verify(statisticRepository, times(1)).findAll();
    }
}