package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private List<Order> orders;

    public OrderRepository(){
        this.orders = new ArrayList<>();
    }

    public Order save(Order order){
        orders.add(order);
        return order;
    }

    public List<Order> listOrders(){
        return orders;
    }
}
