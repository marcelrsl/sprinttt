package Monkeys;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller 
public class RSAVerfahrenController {
    int p;
    int q;
    int n;
    int f;
    int e;
    int d;

    @GetMapping("/rsaverfahren")
    public String rsaverfahren(@RequestParam(name="activePage", required = false, defaultValue = "rsaverfahren") String activePage, Model model){
        model.addAttribute("activePage", "rsaverfahren");
        return "index.html";
    }

}