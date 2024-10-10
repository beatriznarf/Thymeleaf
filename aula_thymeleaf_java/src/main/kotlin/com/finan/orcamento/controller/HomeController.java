package com.finan.orcamento.controller;


import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String redirectToHome() {
        return "redirect:/home"; // Redireciona para a página home
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // Retorna o template home
    }
}