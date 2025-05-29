package org.skypro.skyshop.exceptions;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(UUID productId) {
        super("No product found with id: " + productId);
    }
}
