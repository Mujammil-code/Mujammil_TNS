package fooddeliverysystem.entities;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private int orderId;                              
    private Customer customer;                        
    private final Map<FoodItem, Integer> items;       
    private String status = "Pending";                
    private DeliveryPerson deliveryPerson;            
    private String deliveryAddress;                   

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new LinkedHashMap<>();
    }

    
    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Map<FoodItem, Integer> getItems() { 
    	return Collections.unmodifiableMap(items); 
    }

   
    public String getStatus() { return status; }
    public void setStatus(String status) {
        if (status != null && !status.isBlank()) this.status = status;
    }

    public DeliveryPerson getDeliveryPerson() { return deliveryPerson; }
    public void setDeliveryPerson(DeliveryPerson deliveryPerson) { 
    	this.deliveryPerson = deliveryPerson; 
    }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { 
    	this.deliveryAddress = deliveryAddress; 
    }

    
    public void addItem(FoodItem item, int quantity) {
        if (item == null || quantity <= 0) return;
        items.merge(item, quantity, Integer::sum);
    }

    @Override
    public String toString() {
        String dp = (deliveryPerson == null) ? "Not Assigned" : deliveryPerson.getName();
        return "Order{orderId=" + orderId +
               ", customer=" + (customer != null ? customer.getUsername() : "null") +
               ", items=" + items +
               ", status='" + status + '\'' +
               ", deliveryPerson=" + dp +
               ", deliveryAddress=" + (deliveryAddress == null ? "-" : deliveryAddress) +
               '}';
    }
}
