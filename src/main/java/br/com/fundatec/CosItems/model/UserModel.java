package br.com.fundatec.CosItems.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class UserModel {

    @Id
    private String id;
    private String email;
    private String password;
    private List<ProductModel> shoppingCart = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ProductModel> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<ProductModel> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
