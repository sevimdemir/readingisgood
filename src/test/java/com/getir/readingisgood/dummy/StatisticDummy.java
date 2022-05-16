package com.getir.readingisgood.dummy;

import com.getir.readingisgood.persist.model.Statistic;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatisticDummy {

    public static Statistic singleStatistic() {
        return singleStatisticWithId(1);
    }

    public static Optional<Statistic> singleStatisticOpt() {
        return Optional.of(singleStatisticWithId(1));
    }

    private static Statistic singleStatisticWithId(int id) {
        return new Statistic("id_" + id,
                Faker.instance().number().randomNumber(),
                Faker.instance().number().randomNumber(),
                BigDecimal.TEN,
                Faker.instance().number().randomDigit(),
                Faker.instance().number().randomDigit()
        );
    }

    public static List<Statistic> listOfStatistics() {
        return new ArrayList<>() {{
            add(singleStatisticWithId(1));
            add(singleStatisticWithId(2));
            add(singleStatisticWithId(3));
        }};

    }
}
