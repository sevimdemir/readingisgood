package com.getir.readingisgood.persist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {

    @Id
    private String id;

    private Long orderCount;

    private Long soldBookCount;

    private BigDecimal revenue;

    private Integer month;

    private Integer year;
}
