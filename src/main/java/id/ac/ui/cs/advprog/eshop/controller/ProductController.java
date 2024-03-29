package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model){
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model){
        model.addAttribute("product", service.getProduct(id));
        return "editProduct";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable String id, @ModelAttribute("product") Product product, Model model){
        
        Product chosenProduct = service.getProduct(id);
        chosenProduct.setProductId(id);
        chosenProduct.setProductName(product.getProductName());
        chosenProduct.setProductQuantity(product.getProductQuantity());
        
        service.updateProduct(chosenProduct); // penting apa enggak?
        return "redirect:list";
    }

    @GetMapping("/{id}")
    public String deleteProduct(@PathVariable String id){
        service.delete(id);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }
}