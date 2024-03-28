package br.com.fundatec.CosItems.repository;

import br.com.fundatec.CosItems.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductModel, String> {
}
