package br.com.fundatec.CosItems.controller;

import br.com.fundatec.CosItems.model.ProductModel;
import br.com.fundatec.CosItems.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel productModel) {
        ProductModel createdProduct = productService.create(productModel);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{productId}")
    @ResponseBody
    public ProductModel updateProduct(@PathVariable String productId, @RequestBody ProductModel productModel){
        return productService.update(productId, productModel);
    }

    @GetMapping("/{productId}")
    @ResponseBody
    public ResponseEntity<ProductModel> getProductById(@PathVariable String productId) {
        ProductModel product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> products = productService.findAll();
        return ResponseEntity.ok(products);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }

}
