package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;

import java.util.List;

public interface OrderService{
    Order placeOrder(String balloonColor, String clientName, String address);
    List<Order> listOrders();
}
