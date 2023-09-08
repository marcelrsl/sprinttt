package Monkeys;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonkeysController {

    public MonkeysController() {

    }
    
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("activePage", "home");

        return "index.html";
    }

    @GetMapping("/techGrundlagen")
    public String techGrundlagen(Model model) {
        model.addAttribute("activePage", "techGrundlagen");

        return "index.html";
    }
}
