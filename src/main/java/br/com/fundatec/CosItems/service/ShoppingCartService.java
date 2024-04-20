package br.com.fundatec.CosItems.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fundatec.CosItems.model.ProductModel;
import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.repository.UserRepository;

@Service
public class ShoppingCartService {
    @Autowired
    UserRepository userRepository;

    public List<ProductModel> getCartList(UserModel userModel) {
        return userRepository.findShoppingCartById(userModel.getId());
    }

}
