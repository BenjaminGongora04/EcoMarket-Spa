package com.ecomarket.controller;

import com.ecomarket.model.Product;
import com.ecomarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/list";
    }

    @GetMapping("/eco")
    public String listEcoProducts(Model model) {
        model.addAttribute("products", productService.getEcoFriendlyProducts());
        return "products/eco-list";
    }

    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "products/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam String query, Model model) {
        model.addAttribute("products", productService.searchProducts(query));
        return "products/list";
    }
}