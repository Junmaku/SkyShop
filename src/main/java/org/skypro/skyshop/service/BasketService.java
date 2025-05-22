package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.ProductNotFoundException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        storageService.getProductById(id)
                .ifPresentOrElse(product -> productBasket.addProduct(product.getId()),
                        () -> {
                            throw new IllegalArgumentException("Product with id " + id + " is not found");
                        });
    }

    public UserBasket getUserBasket() {
        List<BasketItem> tempList = productBasket.getBasket().entrySet()
                .stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    Product prod = storageService.getProductById(productId)
                            .orElseThrow(() -> new ProductNotFoundException(productId));
                    return new BasketItem(prod, entry.getValue());
                })
                .collect(Collectors.toList());

        int tempTotal = tempList.stream()
                .mapToInt(entry -> entry.getProduct().getPrice() * entry.getQuantity())
                .sum();
        return new UserBasket(tempList, tempTotal);
    }

    public StorageService getStorageService() {
        return storageService;
    }
}
