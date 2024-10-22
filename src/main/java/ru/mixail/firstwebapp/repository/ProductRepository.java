package ru.mixail.firstwebapp.repository;


import org.springframework.stereotype.Repository;
import ru.mixail.firstwebapp.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository {
    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(Integer productId);

    void deleteById(Integer id);
}
