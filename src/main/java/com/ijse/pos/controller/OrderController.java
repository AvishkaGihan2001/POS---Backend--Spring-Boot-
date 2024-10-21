package com.ijse.pos.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.pos.dto.OrderDto;
import com.ijse.pos.entity.Item;
import com.ijse.pos.entity.Order;
import com.ijse.pos.service.ItemService;
import com.ijse.pos.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/order")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto) {
        try {
            Order order = new Order();
            List<Long> itemIDs = orderDto.getItemIDs();
            List<Integer> quantities = orderDto.getQuantities();

            order.setOrderTotal(0.0);
            List<Item> orderedItems = new ArrayList<>();

            itemIDs.forEach(id -> {
                Item item = itemService.getItem(id);
                if (item != null) {
                    orderedItems.add(item);
                    order.setOrderTotal(
                            order.getOrderTotal() + (item.getPrice() * quantities.get(itemIDs.indexOf(id))));
                }
            });

            order.setOrderItems(orderedItems);
            order.setCustomerName(orderDto.getCustomerName());

            Order savedOrder = orderService.createOrder(order);

            if (savedOrder != null) {
                itemIDs.forEach(id -> {
                    Item item = itemService.getItem(id);
                    if (item != null) {
                        item.setQuantity(item.getQuantity() - quantities.get(itemIDs.indexOf(id)));
                        itemService.updateItem(item);
                    }
                });
                return ResponseEntity.ok(savedOrder);
            } else {
                return ResponseEntity.badRequest().build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
