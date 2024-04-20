package br.com.fundatec.CosItems.controller;

import br.com.fundatec.CosItems.config.TokenService;
import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.model.DTO.UserDTO;
import br.com.fundatec.CosItems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping("/register")
    public String registerUser(Model model, UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorUserExists", "Usuario já cadastrado!");
            return "signin/index";
        }

    }

    @PostMapping("/login")
    public String loginUser(Model model, UserDTO userLoginDTO) {

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(userLoginDTO.email(),
                    userLoginDTO.password());

            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((UserModel) auth.getPrincipal());

            // return ResponseEntity.ok(new LoginResponseDTO(token));
            return "redirect:/";
        } catch (AuthenticationException e) {
            model.addAttribute("errorUserNotFound", "Usuario ou senha inválidos!");
            return "signin/index";
        }
    }

    @GetMapping
    public String signin(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "signin/index";
    }

}
