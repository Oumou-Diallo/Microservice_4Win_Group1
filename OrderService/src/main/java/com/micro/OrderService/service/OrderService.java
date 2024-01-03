package com.micro.OrderService.service;

import com.micro.OrderService.dto.OrderRequest;
import com.micro.OrderService.dto.OrderResponse;
import com.micro.OrderService.model.Order;
import com.micro.OrderService.repository.OrderRepo;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepo orderRepo;


    public OrderResponse getOrderById(Long id) {
        // Appel à votre repository ou service pour récupérer l'utilisateur par ID
        Order order = orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));

        // Conversion de l'entité User en DTO UserResponse (ou autre objet que vous utilisez)
        return mapToDto(order);
    }


    public void createOrder(OrderRequest orderRequest){
        Order order = Order.builder()
                .skuCode(orderRequest.getSkuCode())
                .quantity(orderRequest.getQuantity())
                .price(orderRequest.getPrice())
                .build();

        orderRepo.save(order);

        log.info("Order is save");
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepo.findAll();

        return orders.stream().map(this::mapToOrderResponse).toList();
    }


    public void updateOrder(Long orderId, OrderRequest orderRequest) {
        Order existingOrder = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrder.setSkuCode(orderRequest.getSkuCode());
        existingOrder.setQuantity(orderRequest.getQuantity());
        existingOrder.setPrice(orderRequest.getPrice());

        orderRepo.save(existingOrder);

        log.info("Order is updated");
    }

    public void updateOrderUI(Order updateOrder) {
        Order existingOrder = orderRepo.findById(updateOrder.getId())
                .orElseThrow(() -> new NotFoundException("Order not found"));

        // Mettez à jour les champs de l'utilisateur existant avec les valeurs de l'utilisateur mis à jour
        existingOrder.setSkuCode(updateOrder.getSkuCode());
        existingOrder.setPrice(updateOrder.getPrice());
        existingOrder.setQuantity(updateOrder.getQuantity());

        orderRepo.save(existingOrder);
    }

    public void deleteOrder(Long Id) {
        orderRepo.deleteById(Id);

        log.info("Order is deleted");
    }


    private OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .quantity(order.getQuantity())
                .skuCode(order.getSkuCode())
                .price(order.getPrice())
                .build();
    }

    private OrderResponse mapToDto(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setSkuCode(order.getSkuCode());
        orderResponse.setPrice(order.getPrice());
        orderResponse.setQuantity(order.getQuantity());
        // Autres propriétés à mapper...

        return orderResponse;
    }
}
