package com.example.demo;


import com.example.demo.RMIClient.Hello_S_I;
import com.example.demo.RMIClient.Hello_C_I;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.demo.Forms.User;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.Forms.Url;
import com.example.demo.Forms.Termos;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class webpage extends UnicastRemoteObject implements Hello_C_I {
    private Hello_S_I h;
    private webpage c;
    public List<String> searchs = new ArrayList<>();
    public boolean logged_on = false;
    public boolean success = false;


    public webpage() throws RemoteException {
        super();
        try {
            h = (Hello_S_I) LocateRegistry.getRegistry(7000).lookup("XPTO");
            c = this;
            h.subscribe("cliente", (Hello_C_I) c);
        } catch (Exception e) {
            System.err.println("Error connecting to search module: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /* private ProgramInfoSender programInfoSender;

     @Autowired
     public Webpage(ProgramInfoSender programInfoSender) {
         this.programInfoSender = programInfoSender;
     }
    */


    private static Boolean verify_value(String username) {
        return !username.contains("|") && !username.contains(";") && !username.contains("\\n") && !username.contains(" ");

    }

    @GetMapping("/")
    public String redirect() throws RemoteException, NotBoundException {

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


    @GetMapping("/logout")
    public String logout(Model model) throws Exception {
        // Clear session or perform any other logout operations

        // Set logout message in the session
        if (logged_on) {

            model.addAttribute("errorMessage", "Logout realizado com sucesso!");
            logged_on = false;
            String msg = "type | logout;";
            h.print_on_server(msg,(Hello_C_I) c);

        } else {
            model.addAttribute("errorMessage", "Necessita ter login realizado para poder fazer logout!");
        }

        return "/home"; // Redirect to the login page
    }


    @PostMapping("/check_login")
    public String success_login(@ModelAttribute User user, Model model) throws Exception {

        //mandar para o server o username e o login recevido
        String msg = "type | login; username | " + user.getUsername() + "; password | " + user.getPassword();

        if (!verify_value(user.getUsername()) || !verify_value(user.getPassword())) {
            model.addAttribute("errorMessage", "Username ou password errada!");
            return "login";
        } else {
            h.print_on_server(msg, (Hello_C_I) c);

            if(!logged_on){
                model.addAttribute("errorMessage", "Username ou password errada!");
                return "login";
            }

            model.addAttribute("errorMessage", "Login realizado com sucesso!");
            return "home";
        }

    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/check_register")
    public String register_sucess(@ModelAttribute User user, Model model) throws Exception {


        if (!verify_value(user.getUsername()) || !verify_value(user.getPassword())) {
            model.addAttribute("errorMessage", "Username ou password invalidos!\n\"Nao pode conter os carateres '|' , ';' , ' ' e '\\\\n'\\n\"");

            return "register";
        } else {
            String msg = "type | regist; username | " + user.getUsername() +"; password | "+ user.getPassword();
            h.print_on_server(msg,(Hello_C_I) c);

            if(!success){
                model.addAttribute("errorMessage", "Username ja se encontra utilizado...");

                return "register";
            }

            model.addAttribute("errorMessage", "Registo realizado com sucesso!");
            return "home";
        }

    }


    @GetMapping("/indexar_stories")
    public String indexar_stories(Model model){

        model.addAttribute("user" , new User());

        return "index_user_stories";
    }

    @PostMapping("/check_stories")
    public String check_stories(@ModelAttribute User user) throws Exception {

        String msg = "type | search2; username | " + user.getUsername();
        h.print_on_server(msg,(Hello_C_I) c);
        return "redirect:/home";
    }


    @GetMapping("/pesquisa_termos")
    public String pesquisar_termos(Model model) {

        model.addAttribute("Termos", new Termos());

        return "pesquisa_termos";

    }


    @PostMapping("/termos_pesquisados")
    public String termos_pesquisados(@ModelAttribute Termos termos, Model model) throws Exception {

        for (int i = 0; i < termos.getTermos().toArray().length; i++) {
            if (!verify_value(termos.getTermos().get(i))) {
                model.addAttribute("errorMessage", "Termo invalido!\n\"Nao pode conter os carateres '|' , ';' , ' ' e '\\\\n'\\n\"");
                return "pesquisa_termos";
            }
        }

        String aux = String.join(",", termos.getTermos());
        String msg = "type | search; "+ termos.getChecked()+" | " + aux;
        h.print_on_server(msg,(Hello_C_I) c);

        termos.setResults(searchs);

        return "resultados_termos";
    }

    @GetMapping("/pesquisa_url")
    public String pesquisa_url(Model model) {
        model.addAttribute("url", new Url());

        return "pesquisa_url";
    }


    @GetMapping("/indexar_url")
    public String indexar_url(Model model) {

        model.addAttribute("url", new Url());
        return "indexar";

    }

    @PostMapping("/check_pesquisa")
    public String check_pesquisa(@ModelAttribute Url url, Model model) throws Exception {

        if (!verify_value(url.getUrl())) {
            model.addAttribute("errorMessage", "Url invalido!\n\"Nao pode conter os carateres '|' , ';' , ' ' e '\\\\n'\\n\"");
            return "pesquisa_url";
        } else {
            String msg = "type | search1; pesquisa | " + url.getUrl();
            h.print_on_server(msg,(Hello_C_I) c);

            url.setResults(searchs);

            return "resultados";
        }
    }

    @GetMapping("/informacoes_gerais")
    public String informacoes() throws Exception {
        String msg = "type | information;";
        h.print_on_server(msg,(Hello_C_I) c);
        return "websocket1";
    }


    @PostMapping("/check_indexacao")
    public String check_indexacao(@ModelAttribute Url url, Model model) throws Exception {

        if (!verify_value(url.getUrl())) {
            model.addAttribute("errorMessage", "Url invalido!\n\"Nao pode conter os carateres '|' , ';' , ' ' e '\\\\n'\\n\"");
            return "indexar";
        } else {
            String msg = "type | url; url | "+url.getUrl();
            h.print_on_server(msg,(Hello_C_I) c);
            return "redirect:/home";
        }
    }

    public void print_on_client(String s) throws Exception {

        String[] msg_received = s.split(" ", 0);
        System.out.println(s);


        if (msg_received[3].equals("search") || msg_received[3].equals("search1")) {

            searchs.clear();
            String search_results = msg_received[6];
            String[] separate_results = search_results.split(";");

            System.out.println(Arrays.toString(separate_results));

            if (separate_results[0].equals("nada")) {


                searchs.add("Nenhum resultado foi encontrado...");

                System.out.println(searchs);

            }else{
                searchs.addAll(Arrays.asList(separate_results));

            }


        }else if(msg_received[3].equals("logged")) {

            //fica com o valor do login, true se sucedeu e false se falhou
            logged_on = msg_received[5].equals("on;");

        }else if(msg_received[3].equals("register")){
            success = msg_received[5].equals("sucess;");
        }
    }

    @Override
    public void ping() throws RemoteException {

    }
}


