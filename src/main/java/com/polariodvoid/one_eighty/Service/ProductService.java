package com.polariodvoid.one_eighty.Service;

import com.polariodvoid.one_eighty.Exceptions.ProductNotFoundException;
import com.polariodvoid.one_eighty.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.polariodvoid.one_eighty.Model.Product;

import java.util.List;

@Service

public class ProductService {
    public static final int PRODUCTS_PER_PAGE = 10;
    public static final int SEARCH_RESULTS_PER_PAGE = 10;

    @Autowired public ProductRepository productRepository;

    public Page<Product> listByCategory(int pageNum, Integer categoryId) {
        String categoryIdMatch = "-" + String.valueOf(categoryId) + "-";
        Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);

        return productRepository.listByCategory(categoryId, categoryIdMatch, pageable);
    }

    public Product getProduct(String alias) throws ProductNotFoundException {
        Product product = productRepository.findByAlias(alias);
        if (product == null) {
            throw new ProductNotFoundException("Could not find any product with alias " + alias);
        }

        return product;
    }

    public Product getProduct(Integer id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).get();
        if (product == null){
            throw new ProductNotFoundException("Could not find any product with ID " + id);
        }
        return product;
    }

    public List<Product> search(String keyword) {
        return productRepository.search(keyword);

    }
}
