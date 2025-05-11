package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

abstract public class Product implements Searchable {
    private final String name;
    private UUID id;

    public UUID getId() {
        return this.id;
    }

    public Product(String name, UUID id) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Поле name не может быть пустым.");
        }
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    abstract public int getPrice();

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }

    abstract public boolean isSpecial();

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return getName();
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
