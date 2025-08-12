package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {
    private BasketService basketService;
    private SearchService searchService;
    private StorageService storageService;

    public ShopController(SearchService searchService, BasketService basketService, StorageService storageService) {
        this.searchService = searchService;
        this.basketService = basketService;
        this.storageService = storageService;
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getArticlesStorage();
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProductsStorage();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam("pattern") String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addProduct(id);
        return "Продукт " + storageService.getProductById(id).get().getName() + " добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }

}
