package fooddeliverysystem.services;

import java.util.ArrayList;
import java.util.List;

import fooddeliverysystem.entities.Customer;
import fooddeliverysystem.entities.DeliveryPerson;
import fooddeliverysystem.entities.Order;

public class OrderService {
    private final List<Order> orders = new ArrayList<>();
    private int orderIdGen = 1;

    public Order placeOrder(Customer customer, String deliveryAddress) {
        if (customer == null || customer.getCart().getItems().isEmpty()) {
            return null;
        }

        Order order = new Order(orderIdGen++, customer);
        for (var entry : customer.getCart().getItems().entrySet()) {
            order.addItem(entry.getKey(), entry.getValue());
        }
        order.setDeliveryAddress(deliveryAddress);
        orders.add(order);

        
        customer.getCart().getItems().clear();
        return order;
    }

    public void assignDeliveryPerson(int orderId, DeliveryPerson dp) {
        Order o = findOrderById(orderId);
        if (o != null) {
            o.setDeliveryPerson(dp);
        }
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order findOrderById(int id) {
        return orders.stream().filter(o -> o.getOrderId() == id).findFirst().orElse(null);
    }
}
