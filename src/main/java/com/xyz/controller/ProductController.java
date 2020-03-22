package com.xyz.controller;

import com.xyz.pojo.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/{id}")
    public Product getProductInfo( @PathVariable("id")  Long productId){
        // TODO
        return null;
    }

    @PutMapping("/{id}")
    public Product updateProductInfo(
            @PathVariable("id") Long productId,
            @RequestBody Product newProduct) {
        // TODO
        return null;
    }
}
