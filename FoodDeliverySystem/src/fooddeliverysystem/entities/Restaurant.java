package fooddeliverysystem.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Restaurant {
    private int id;                 
    private String name;            
    private final List<FoodItem> menu = new ArrayList<>(); // Read

    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }

  
    public int getId() { return id; }
    public String getName() { return name; }
    public List<FoodItem> getMenu() { 
    	return Collections.unmodifiableList(menu); 
    }

    public void addFoodItem(FoodItem item) {
        if (item == null) return;
        
        for (FoodItem fi : menu) {
            if (fi.getId() == item.getId()) return;
        }
        menu.add(item);
    }

    public void removeFoodItem(int foodItemId) {
        Iterator<FoodItem> it = menu.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == foodItemId) {
                it.remove();
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Restaurant{id=" + id + ", name='" + name + "', menuSize=" + menu.size() + "}";
    }
}
