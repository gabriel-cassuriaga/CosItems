package br.com.fundatec.CosItems.controller;

import br.com.fundatec.CosItems.model.ProductModel;
import br.com.fundatec.CosItems.service.ShoppingCartService;
import br.com.fundatec.CosItems.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserService userService;

    @GetMapping
    public String index(Model model, HttpServletRequest request) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            String id = (String) inputFlashMap.get("userId");
            List<ProductModel> products = userService.getById(id).getShoppingCart();
    
            model.addAttribute("products", products);
            return "shoppingcart/index";
        } else {
            return "redirect:/auth";
        }


    }

}
