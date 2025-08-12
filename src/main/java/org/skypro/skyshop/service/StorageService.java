package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.*;


@Service
@ApplicationScope
public class StorageService {
    private final Map<UUID, Product> productsStorage;
    private final Map<UUID, Article> articlesStorage;


    public StorageService(Map<UUID, Product> products, Map<UUID, Article> articles) {
        this.productsStorage = products;
        this.articlesStorage = articles;
        init();
    }

    public List<Product> getProductsStorage() {
        return new ArrayList<>(productsStorage.values());
    }

    public List<Article> getArticlesStorage() {
        return new ArrayList<>(articlesStorage.values());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(Optional.ofNullable(productsStorage.get(id))
                .orElseThrow(() -> new NoSuchProductException(id)));
    }

    public Collection<Searchable> getAllSearchable() {
        List<Searchable> tempList = new ArrayList<>();
        productsStorage.values()
                .stream()
                .forEach(tempList::add);
        tempList.addAll(articlesStorage.values());
        return tempList;
    }

    public static List<Article> createArticles4Test() {
        ArrayList<Article> articles = new ArrayList<>();

        Article text1 = new Article("Energy drink", "Red bull is most popular energy drink in the world.", UUID.randomUUID());
        Article text2 = new Article("Glasses", "Rayban is most popular glasses in the world.", UUID.randomUUID());
        Article text3 = new Article("Car", "One of the most popular car is Toyota from Japan.", UUID.randomUUID());

        articles.add(text1);
        articles.add(text2);
        articles.add(text3);

        return articles;
    }

    public static List<Product> createProducts4Test() {
        ArrayList<Product> newBasket = new ArrayList<>();
        Product potato = new FixPriceProduct("Potato", UUID.randomUUID());
        Product salad = new DiscountedProduct("Salad", 45, 5, UUID.randomUUID());
        Product carrot = new SimpleProduct("Carrot", 40, UUID.randomUUID());

        newBasket.add(potato);
        newBasket.add(salad);
        newBasket.add(carrot);

        return newBasket;
    }

    public void init() {
        createProducts4Test().forEach(element -> this.productsStorage.put(element.getId(), element));
        createArticles4Test().forEach(element -> this.articlesStorage.put(element.getId(), element));
    }
}
