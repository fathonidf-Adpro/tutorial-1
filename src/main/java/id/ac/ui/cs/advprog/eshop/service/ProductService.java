package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public Product getProduct(String id);
    public Product updateProduct(Product product);
    public void delete(String id);
    public List<Product> findAll();
}