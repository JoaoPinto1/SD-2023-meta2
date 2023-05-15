package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.demo.Forms.user;
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
    public String home(){
        return "home";
    }

    @GetMapping("/informacoes_gerais")
    public String informacoes(){
        return "/websocket";
    }

    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("login", new user());
        return "login";
    }

    @PostMapping("/success_login")
    public String sucess_login(@ModelAttribute user login){

        //mandar para o server o username e o login recevido

        System.out.println(login.toString());
        //esperar pela resposta para saber se foi um sucesso ou não.

        return "home";
    }

    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute("register", new user());
        return "register";
    }


    @PostMapping("/register_sucess")
    public String register_sucess(@ModelAttribute user register){


        //mandar resultados para o server


        //recever resposta do server

        return "home";
    }


    @GetMapping("/pesquisa_termos")
    public String pesquisar_termos(Model model){

        model.addAttribute("Termos" , new termos());

        return "pesquisa_termos";

    }


    @PostMapping("/termos_pesquisados")
    public String termos_pesquisados(@ModelAttribute termos Termos){

        System.out.println(Termos.toString());

        return "home";
    }
    @GetMapping("/indexar_url")
    public String indexar_url(Model model){
        model.addAttribute("Url" , new url());
        return "indexar";
    }

    @PostMapping("/indexado")
    public String Verifica_Indexar(@ModelAttribute url Url){

        String[] results = {"1" , "resultado" , "3"};
        //mandar url para o server
        Url.setResults(results);
        //receber mensagem


        return "resultados";
    }


}


