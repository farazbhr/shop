package de.shop.shop.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@SessionScope
@Data
@AllArgsConstructor
@Component
public class SessionBasket {
    private Multimap<Long, List<String>> basketItems;
    private Order order;

    public SessionBasket(){
        this.basketItems = ArrayListMultimap.create();
    }

    public void addItem(Long id, int number, String type){
        this.basketItems.put(id, Arrays.asList(String.valueOf(number),type));
    }
}
