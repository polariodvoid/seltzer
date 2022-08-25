package com.polariodvoid.one_eighty.product;

import com.polariodvoid.one_eighty.Model.Product;
import com.polariodvoid.one_eighty.OneEightyApplication;
import com.polariodvoid.one_eighty.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = OneEightyApplication.class)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindById() {
        Product product = getProduct();
        product = productRepository.save(product);
        Product result = productRepository.findById(product.getId()).get();
        assertEquals(product.getId(), result.getId());
    }


    private Product getProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Apple iPhone 14");
        product.setAlias("iPhone 14");
        product.setShortDescription("The new apple iphone");
        product.setFullDescription("Bundling with superb functions and next-gen features, you can buy Apple iPhone 14 online. It comes with Apple’s latest A16 Bionic chip that offers the best CPU performance in a smartphone. It is a 6 core CPU that has 2 performance cores that boosts up the phone’s maximum performance potential and 4 efficiency cores that optimize the phone’s overall performance.");
        product.setMainImage("iphone14.jpeg");
        return product;
    }
}