package br.com.fundatec.CosItems.repository;

import br.com.fundatec.CosItems.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {
}