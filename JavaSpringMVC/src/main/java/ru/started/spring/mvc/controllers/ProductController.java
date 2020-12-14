package ru.started.spring.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.started.spring.mvc.model.Product;
import ru.started.spring.mvc.services.ProductService;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService (ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProduct (Model model) {
        System.out.println(productService.getAllProduct().size());
        model.addAttribute("frontProduct", productService.getAllProduct());
        return "all_product";
    }

    @GetMapping("/remove/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/product/all";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/product/all";
    }

}
