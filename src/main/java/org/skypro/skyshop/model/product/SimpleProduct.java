package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        if (price < 1) {
            throw new IllegalArgumentException("Цена не может быть меньше 1");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("%s: %d", super.getName(), getPrice());
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
