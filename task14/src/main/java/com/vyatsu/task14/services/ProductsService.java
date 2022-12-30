package com.vyatsu.task14.services;

import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }
    public List<Product> getFilterProducts(int min, int max, String text){
        List<Product> temp = getAllProducts();
        if (!text.equals("")) temp = temp.stream().
                filter(product -> product.getTitle().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))).
                collect(Collectors.toList());
        if (max!= 0) temp =  temp.stream().
                filter(product -> product.getPrice() <= max).
                collect(Collectors.toList());
        if (min!= 0) temp =  temp.stream().
                filter(product -> product.getPrice() >= min).
                collect(Collectors.toList());
        return temp;
    }
    public void remove(Product product) {
        productRepository.del(product);
    }
    public void save(Product product){productRepository.ed(product);}
}