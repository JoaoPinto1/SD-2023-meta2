package RMIClient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import Forms.user;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/success_login")
    public String sucess_login(@ModelAttribute user login){

        //mandar para o server o username e o login recevido


        //esperar pela resposta para saber se foi um sucesso ou não.

        return "sucess_login";
    }

    @GetMapping("/register")
    public String login(Model model){

        model.addAttribute("register", new user());
        return "login";
    }


}


