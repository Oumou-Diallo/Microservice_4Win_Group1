package com.micro.OrderService.dto;

import com.micro.OrderService.model.SkuCode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderRequest {
    @Enumerated(EnumType.STRING)
    private SkuCode skuCode;
    private BigDecimal price;
    private Integer quantity;

    public OrderRequest() {

    }
}
