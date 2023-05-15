package com.example.demo;

import com.example.demo.Forms.Termos;
import com.example.demo.RMIClient.Hello_C_I;
import com.example.demo.RMIClient.Hello_S_I;
import com.example.demo.RMIClient.Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.demo.Forms.User;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.Forms.Url;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


@Controller
public class Webpage implements Hello_C_I {
    private Hello_S_I h;
    private Webpage c;

    public Webpage() {
        try {
            h = (Hello_S_I) LocateRegistry.getRegistry(7000).lookup("XPTO");
            c = this;
            h.subscribe("cliente", (Hello_C_I) c);
        } catch (Exception e) {
            System.err.println("Error connecting to search module: " + e.getMessage());
            e.printStackTrace();
        }
    }

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
    public String success_login(@ModelAttribute User user) throws Exception {

        //mandar para o server o username e o login recevido
        String msg = "type | login; username | "+user.getUsername()+"; password | "+user.getPassword();
        h.print_on_server(msg,(Hello_C_I) c);
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

        model.addAttribute("Termos", new Termos());

        return "pesquisa_termos";

    }


    @PostMapping("/termos_pesquisados")
    public String termos_pesquisados(@ModelAttribute Termos termos) {

        System.out.println(termos);

        return "home";
    }

    @GetMapping("/indexar_url")
    public String indexar_url(Model model) {
        model.addAttribute("Url", new Url());
        return "indexar";
    }

    @PostMapping("/indexado")
    public String Verifica_Indexar(@ModelAttribute Url Url) {

        String[] results = {"1", "resultado", "3"};
        //mandar url para o server
        Url.setResults(results);
        //receber mensagem


        return "resultados";
    }

    @GetMapping("/informacoes_gerais")
    public String subscribeToProgramStatus() {
        return "websocket";
    }

    @GetMapping("index_stories")
    public String index_stories() throws Exception {
        String msg = "type | search2; user | jl";
        h.print_on_server(msg, (Hello_C_I) c);
        return "index_stories";
    }

    @Override
    public void print_on_client(String s) throws Exception {
    }

    @Override
    public void ping() throws RemoteException {

    }
}
