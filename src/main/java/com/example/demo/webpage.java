package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.demo.Forms.User;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.Forms.url;
import com.example.demo.Forms.termos;


@Controller
public class webpage {

    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }


    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/success_login")
    public String success_login(@ModelAttribute User user) {

        //mandar para o server o username e o login recevido

        System.out.println(user);
        //esperar pela resposta para saber se foi um sucesso ou não.

        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("register", new User());
        return "register";
    }


    @PostMapping("/register_sucess")
    public String register_sucess(@ModelAttribute User register) {


        //mandar resultados para o server


        //recever resposta do server

        return "home";
    }


    @GetMapping("/pesquisa_termos")
    public String pesquisar_termos(Model model) {

        model.addAttribute("Termos", new termos());

        return "pesquisa_termos";

    }


    @PostMapping("/termos_pesquisados")
    public String termos_pesquisados(@ModelAttribute termos Termos) {

        System.out.println(Termos);

        return "home";
    }

    @GetMapping("/indexar_url")
    public String indexar_url(Model model) {
        model.addAttribute("Url", new url());
        return "indexar";
    }

    @PostMapping("/indexado")
    public String Verifica_Indexar(@ModelAttribute url Url) {

        String[] results = {"1", "resultado", "3"};
        //mandar url para o server
        Url.setResults(results);
        //receber mensagem


        return "resultados";
    }


}


