package de.shop.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;

@SessionScope
@Data
@AllArgsConstructor
@Component
public class SessionBasket {
    private HashMap<Long, Integer> basketItems;

    public SessionBasket(){
        this.basketItems = new HashMap<Long, Integer>();
    }

    public void addItem(Long id, int number){
        this.basketItems.put(id, number);
    }
}
