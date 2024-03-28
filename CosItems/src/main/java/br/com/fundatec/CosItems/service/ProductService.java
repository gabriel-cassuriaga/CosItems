package br.com.fundatec.CosItems.service;

import br.com.fundatec.CosItems.model.ProductModel;
import br.com.fundatec.CosItems.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductModel create(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public ProductModel update(String id, ProductModel productModel) {
        ///////AJUSTAR EXCEÇÃO/////////////////////////////////////////////////
        ProductModel existingProduct = productRepository.findById(id).
                orElseThrow(() -> new Error("User not found with id: " + id));
        ///////////////////////////////////////////////////////////////////////

        existingProduct.setName(productModel.getName());
        existingProduct.setPrice(productModel.getPrice());
        existingProduct.setDescription(productModel.getDescription());
        existingProduct.setQuantity(productModel.getQuantity());

        return productRepository.save(existingProduct);
    }

    public ProductModel findById(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(String productId) {
        productRepository.deleteById(productId);
    }

}
