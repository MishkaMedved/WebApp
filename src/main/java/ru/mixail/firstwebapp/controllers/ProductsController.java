package ru.mixail.firstwebapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mixail.firstwebapp.entity.Product;
import ru.mixail.firstwebapp.payload.NewProductPayload;
import ru.mixail.firstwebapp.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping("/list")
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("/create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("/create")
    public String createProduct(NewProductPayload payload) {
        Product product = this.productService.createProduct(payload.title(), payload.details());
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }

}
