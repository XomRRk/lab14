package com.vyatsu.task14.repositories;

import com.vyatsu.task14.entities.Product;

import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", 40));
        products.add(new Product(2L, "Milk", 90));
        products.add(new Product(3L, "Cheese", 200));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
    }
public  void del (Product product){products.remove(product);}
    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    public void save(Product product) {
        products.add(product);
    }
    public void ed(Product product){
        if(product.getId()>3){
            products.add(product);
        }
        else {
        for (Product p : products) {
            if (p.getId().equals(product.getId())){
                p.setPrice(product.getPrice());
                p.setTitle(product.getTitle());
                return;
            }
        }}
    }
}

