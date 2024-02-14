package com.example.seminar11.service;


import com.example.seminar11.model.Product;
import com.example.seminar11.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;

    @Autowired
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product addProduct(Product p) {
        return repo.save(p);
    }

    public List<Product> allFindProducts() {
        return repo.findAll();
    }

    public Product getAProductIdToAddOrUpdate(Long id, Product p) {
        Optional<Product> current = repo.findById(id);
        if (current.isPresent()) {
            Product changeProduct = current.get();
            changeProduct.setId(id);
            changeProduct.setTitle(p.getTitle());
            changeProduct.setLocalDateTime(p.getLocalDateTime());
            return repo.save(changeProduct);
        }
        return repo.save(p);
    }

    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
}
