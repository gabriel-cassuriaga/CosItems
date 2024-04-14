package br.com.fundatec.CosItems.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class UserModel {

    @Id
    private String id;
    private String email;
    private String password;
    private List<CartItemModel> shoppingCart;

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

    public List<CartItemModel> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<CartItemModel> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
