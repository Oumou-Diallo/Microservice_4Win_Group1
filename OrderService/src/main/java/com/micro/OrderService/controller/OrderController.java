package com.micro.OrderService.controller;

import com.micro.OrderService.dto.OrderRequest;
import com.micro.OrderService.dto.OrderResponse;
import com.micro.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();

    }


    @PutMapping("/{Id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrder(@PathVariable Long Id, @RequestBody OrderRequest orderRequest) {
        orderService.updateOrder(Id, orderRequest);
    }

    @DeleteMapping("/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long Id) {
        orderService.deleteOrder(Id);
    }
}
