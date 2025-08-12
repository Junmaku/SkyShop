package org.skypro.skyshop.model.basket;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.Objects;

public class UserBasket {
    private final Collection<BasketItem> userProducts;
    private final int total;

    public UserBasket(Collection<BasketItem> userProducts, int total) {
        this.userProducts = userProducts;
        this.total = total;
    }

    public Collection<BasketItem> getUserProducts() {
        return userProducts;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserBasket that = (UserBasket) o;
        return getTotal() == that.getTotal() && Objects.equals(getUserProducts(), that.getUserProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserProducts(), getTotal());
    }

    @Override
    public String toString() {
        return "UserBasket{" +
                "userProducts=" + userProducts +
                ", total=" + total +
                '}';
    }
}
