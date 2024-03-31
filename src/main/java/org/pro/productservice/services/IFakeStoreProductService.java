package org.pro.productservice.services;

import org.pro.productservice.dtos.FakeStoreProductDto;
import org.pro.productservice.entities.Category;
import org.pro.productservice.entities.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class IFakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    IFakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        //call the fakestore Api to get the product with given ID
        //RestTemplate restTemplate = new RestTemplate(); // this is not the good way to create object like this
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" +id, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product updateproduct() {
        return null;
    }

    @Override
    public Product replaceProduct() {
        return null;
    }
}