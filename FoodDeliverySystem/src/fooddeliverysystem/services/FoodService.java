package fooddeliverysystem.services;

import java.util.ArrayList;
import java.util.List;

import fooddeliverysystem.entities.FoodItem;
import fooddeliverysystem.entities.Restaurant;

public class FoodService {
    private final List<Restaurant> restaurants = new ArrayList<>();

    
    public void addRestaurant(Restaurant r) {
        restaurants.add(r);
    }

    public Restaurant findRestaurantById(int id) {
        return restaurants.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    
    public void addFoodItemToRestaurant(int restaurantId, FoodItem foodItem) {
        Restaurant r = findRestaurantById(restaurantId);
        if (r != null) {
            r.addFoodItem(foodItem);
        }
    }

    public void removeFoodItemFromRestaurant(int restaurantId, int foodItemId) {
        Restaurant r = findRestaurantById(restaurantId);
        if (r != null) {
            r.removeFoodItem(foodItemId);
        }
    }
}
