package org.pro.productservice.services;

import org.pro.productservice.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

//why product service interface is we can have different implementation for it like through db  or like fakestore
@Service
public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateproduct();
    Product replaceProduct();
}