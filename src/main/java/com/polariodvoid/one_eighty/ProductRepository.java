package com.polariodvoid.one_eighty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.polariodvoid.one_eighty.Model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {

    @Query("SELECT p FROM Product p WHERE p.enabled = true "
            + "AND (p.category = ?1 OR p.category LIKE %?2%)"
            + " ORDER BY p.name ASC")
    public Page<Product> listByCategory(Integer categoryId, String categoryIDMatch, Pageable pageable);

    public Product findByAlias(String alias);

    @Query(value = "SELECT * FROM products WHERE enabled = true AND "
            + "MATCH(name, short_description, full_description) AGAINST (?1)",
            nativeQuery = true)
    public List<Product> search(String keyword);
}
