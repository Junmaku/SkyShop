package org.skypro.skyshop.model.basket;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;

@Component
@SessionScope
public class UserBasket {
    private final Collection<BasketItem> userProducts;
    private final int total;

    public UserBasket(Collection<BasketItem> userProducts, int total) {
        this.userProducts = userProducts;
        this.total = total;
    }
}
