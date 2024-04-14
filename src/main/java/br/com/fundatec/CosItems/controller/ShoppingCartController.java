package br.com.fundatec.CosItems.controller;

import br.com.fundatec.CosItems.model.ProductModel;
import br.com.fundatec.CosItems.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class ShoppingCartController {
   @Autowired
   ShoppingCartService shoppingCartService;

    @GetMapping
    public String index(Model model){
       List<ProductModel> products = shoppingCartService.getCartList(null);
       model.addAttribute("products", products);
        return "shoppingcart/index";
    }

}
