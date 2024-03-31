package org.pro.productservice.controllers;

import org.pro.productservice.entities.Product;
import org.pro.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products") //entites which we are creating should revolve around the particular entity
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Create a product
    @PostMapping("/")
    public Product createproduct(@RequestBody Product product){
        return new Product();
    }

    //get product by its id
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id){
        //call the FakeStore API to get the pproduct by given ID
        return productService.getProductById(id);
    }

    //get all products
    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //Partial update
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return new Product();
    }

    @PostMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){

    }
}