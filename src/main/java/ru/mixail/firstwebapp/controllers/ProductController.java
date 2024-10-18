package ru.mixail.firstwebapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mixail.firstwebapp.entity.Product;
import ru.mixail.firstwebapp.payload.UpdateProductPayload;
import ru.mixail.firstwebapp.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/{productId:\\d+}")
public class ProductController {

    private final ProductService productService;

    @ModelAttribute("product")
    public Product Product(@PathVariable("productId") int productId) {
        return this.productService.findProduct(productId).orElseThrow();
    }

    @GetMapping
    public String getProductPage() {
        return "catalogue/products/product";
    }

    @GetMapping("/edit")
    public String getProductEditPage() {
        return "catalogue/products/edit_product";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute("product") Product product,UpdateProductPayload payload) {
        this.productService.updateProduct(product.getId(),payload.title(),payload.details());
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }

}
