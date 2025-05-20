package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
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
                        () -> { throw new IllegalArgumentException("Product with id " + id + " is not found"); });
    }

    public UserBasket getUserBasket() {
        ArrayList <BasketItem> tempList = new ArrayList<>();
        int tempTotal;

        tempList = (ArrayList<BasketItem>) productBasket.getBasket().entrySet()
                .stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    Integer quantity = entry.getValue();
                    Optional<Product> prod = storageService.getProductById(productId);
                    return new BasketItem(prod.orElse(null), quantity);
                })
                .toList();

        tempTotal = tempList.stream()
                .map(BasketItem::getProduct)
                .mapToInt(entry ->  entry.getPrice() * productBasket.getBasket().get(entry.getId()))
                .sum();
        return new UserBasket(tempList, tempTotal);



    }
}
