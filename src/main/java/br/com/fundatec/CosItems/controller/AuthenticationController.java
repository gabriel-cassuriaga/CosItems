package br.com.fundatec.CosItems.controller;

import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String registerUser(Model model, UserModel userModel) {

        try {
            userService.createUser(userModel);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorUserExists", "Usuario já cadastrado!");
            return "signin/index";
        }

    }

    @PostMapping("/login")
    public RedirectView loginUser(RedirectAttributes redirectAttributes, Model model, UserModel userModel) {
        UserModel userLogin = userService.authenticate(userModel.getEmail(), userModel.getPassword());
        if (userLogin != null) {
            model.addAttribute("userId", userLogin.getId());

            redirectAttributes.addFlashAttribute("userId", userLogin.getId());
            return new RedirectView("/", true);
        }
        model.addAttribute("errorUserNotFound", "Usuario ou senha inválidos!");
        return new RedirectView("/auth/login", true);
    }

    @GetMapping
    public String signin(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "signin/index";
    }

}
