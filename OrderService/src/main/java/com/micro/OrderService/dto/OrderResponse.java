package com.micro.OrderService.dto;


import com.micro.OrderService.model.SkuCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class OrderResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SkuCode skuCode;
    private BigDecimal price;
    private Integer quantity;

    public OrderResponse() {

    }
}
