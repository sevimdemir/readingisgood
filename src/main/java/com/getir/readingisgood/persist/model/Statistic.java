package com.getir.readingisgood.persist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "statistics")
public class Statistic {

    @Id
    private String id;

    @JsonProperty("order_count")
    private Long orderCount;

    @JsonProperty("sold_book_count")
    private Long soldBookCount;

    private BigDecimal revenue;

    private Integer month;

    private Integer year;
}
