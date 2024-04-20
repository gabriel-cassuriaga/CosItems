package br.com.fundatec.CosItems.repository;

import br.com.fundatec.CosItems.model.ProductModel;
import br.com.fundatec.CosItems.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends MongoRepository<UserModel, String> {
    @Query("{ 'email': ?0, 'password': ?1 }")
    UserModel findByEmailAndPassword(String email, String password);

   // Optional<UserModel> findByEmail(String email);

    UserDetails findByEmail(String email);

    List<ProductModel> findShoppingCartById(String userId);

}