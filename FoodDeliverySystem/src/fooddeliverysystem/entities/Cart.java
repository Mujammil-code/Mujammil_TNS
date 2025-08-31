package fooddeliverysystem.entities;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    
    private final Map<FoodItem, Integer> items = new LinkedHashMap<>();

    public Cart() {}

   
    public void addItem(FoodItem foodItem, int quantity) {
        if (foodItem == null || quantity <= 0) return;
        items.merge(foodItem, quantity, Integer::sum);
    }

  
    public void removeItem(FoodItem foodItem) {
        if (foodItem == null) return;
        items.remove(foodItem);
    }

    
    public Map<FoodItem, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cart{");
        double total = 0.0;
        boolean first = true;
        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
            if (!first) sb.append(", ");
            double cost = e.getKey().getPrice() * e.getValue();
            total += cost;
            sb.append(e.getKey().getName()).append(" x ").append(e.getValue())
              .append(" (").append(cost).append(")");
            first = false;
        }
        sb.append("; total=").append(total).append("}");
        return sb.toString();
    }
}
