package com.micro.OrderService.controller;


import com.micro.OrderService.dto.OrderRequest;
import com.micro.OrderService.dto.OrderResponse;
import com.micro.OrderService.model.Order;
import com.micro.OrderService.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderControllerUI {

    private final OrderService orderService;


    @GetMapping("/display")
    public String getAllOrdersUI(Model model) {
        List<OrderResponse> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/add")
    public String showAddOrderPage(Model model) {
        model.addAttribute("orderRequest", new OrderRequest());
        return "add-order";
    }

    @PostMapping("/add")
    public String addOrderUI(@ModelAttribute("orderRequest") @Valid OrderRequest orderRequest, BindingResult result) {
        if (result.hasErrors()) {
            // Gérer les erreurs de validation, peut-être renvoyer à la page du formulaire avec un message d'erreur.
            return "add-order";
        }

        try {
            orderService.createOrder(orderRequest);
            return "redirect:/api/order/display";
        } catch (Exception e) {
            // Gérer l'exception (par exemple, utilisateur déjà existant) et renvoyer à la page du formulaire avec un message d'erreur.
            return "add-order";
        }
    }



    @GetMapping("/edit/{id}")
    public String showUpdateOrderPage(@PathVariable Long id, Model model) {
        // Récupérer l'utilisateur à modifier en fonction de l'ID
        OrderResponse orderToUpdate = orderService.getOrderById(id);

        // Passer l'utilisateur à la page de confirmation de modification
        model.addAttribute("orderToUpdate", orderToUpdate);

        return "edit-order";
    }

    @PostMapping("/edit/{id}")
    public String updateOrderUI(@ModelAttribute Order order) {
        orderService.updateOrderUI(order);
        return "redirect:/api/order/display";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteOrderPage(@PathVariable Long id, Model model) {
        // Récupérer l'utilisateur à supprimer en fonction de l'ID
        OrderResponse orderToDelete = orderService.getOrderById(id);

        // Passer l'utilisateur à la page de confirmation de suppression
        model.addAttribute("order", orderToDelete);

        return "delete-order";
    }



    @PostMapping("/delete/{id}")
    public String deleteOrderUI(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/api/order/display";
    }



}

