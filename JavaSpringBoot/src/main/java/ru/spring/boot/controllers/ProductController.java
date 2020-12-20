package ru.spring.boot.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spring.boot.model.Product;
import ru.spring.boot.serviсes.ProductServices;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices productServices;

    @GetMapping("/all")
    public String showAll (Model model) {
        model.addAttribute("products", productServices.findAll());
        return "all_product";
    }

    @GetMapping("/remove/{id}")
    public String removeProductById (@PathVariable Long id) {
        productServices.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String add (@ModelAttribute Product product) {
        productServices.saveOrUpdate(product);
        return "redirect:/products/all";
    }





}
