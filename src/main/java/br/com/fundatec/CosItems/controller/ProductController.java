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
    ProductService productService;

    @GetMapping
    public String index() {
        return "product/index";
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel productModel) {
        ProductModel createdProduct = productService.create(productModel);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{productId}")
    public String index(Model model, @PathVariable String productId) {
        ProductModel product = productService.findById(productId);
        if (product == null) {
            return null;
        }

        model.addAttribute("product", product);
        return "product/index";
    }

    @PutMapping("/{productId}")
    @ResponseBody
    public ProductModel updateProduct(@PathVariable String productId, @RequestBody ProductModel productModel){
        return productService.update(productId, productModel);
    }



    @GetMapping("/all")
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
