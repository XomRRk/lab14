package com.vyatsu.task14.controllers;

import com.vyatsu.task14.entities.Filter;
import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.services.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


@Controller

@RequestMapping("/products")
public class ProductsController {
    private  ProductsService productsService;
    private Filter filter;
    @PostConstruct
    public void post(){
        filter = new Filter();
    }
   @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Model model) {
        Product product = new Product();
        model.addAttribute("products", productsService.getAllProducts());
        model.addAttribute("product", product);
        model.addAttribute("filter", filter);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product) {
        productsService.add(product);
        //return "/products";
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/filter")
    public String filterProduct(Model model,
                                @RequestParam(name = "min",required = false) int min,
                                @RequestParam(name = "max",required = false) int max,
                                @RequestParam(name = "search",required = false) String search) {
        Product product = new Product();
        filter.setMax(max);
        filter.setMin(min);
        filter.setSearch(search);
        model.addAttribute("products", productsService.getFilterProducts( min,max,search));
        model.addAttribute("product", product);
        model.addAttribute("filter", filter);
        return "products";
    }
    @GetMapping("/res")
    public String res (Model model){
        filter.setSearch("");
        filter.setMin(0);
        filter.setMax(0);
        return "redirect:/products";
    }
    @GetMapping("/remove/{id}")
    public String removeProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        productsService.remove(product);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable(value = "id") long id, Model model)
    {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }
    @GetMapping("/edit")

        public String add2(Model model, @RequestParam(required = false, defaultValue = "", name = "id") String id) {
        Product product;
        if (id == null || id.isEmpty()) product = new Product();
        else product = productsService.getById(Long.parseLong(id));
        model.addAttribute("product", product);
        return "product-edit";
    }
    @PostMapping("/ed/{id}")
    public String update(@ModelAttribute(value = "product")Product product) {
        productsService.save(product);
        return "redirect:/products";
    }
    @PostMapping("/add2")
    public String update2(@ModelAttribute(value = "product")Product product) {
        productsService.save(product);
        return "redirect:/products";
    }

}
