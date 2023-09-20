package Monkeys;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Monkeys.Model.page;

@Controller
public class MonkeysController {

    
    ArrayList<page> technGrundlagen;
    ArrayList<page> sicherheitVerfahren;
    ArrayList<page> komplexeVerfahren;
    ArrayList<page> angriffe;
    public MonkeysController() {
        setTechnGrundlagen(new ArrayList<>());
        setSicherheitVerfahren(new ArrayList<>());
        setAngriffe(new ArrayList<>());
        setKomplexeVerfahren(new ArrayList<>());
    }
    
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("activePage", "home");

        return "index.html";
    }

    @GetMapping("/einrichten")
    public String einrichten(Model model) {
        model.addAttribute("activePage", "einrichten");
        return "index.html";
    }

    @PostMapping(path = "/einrichten/do")
    public String einrichtenDo(@RequestParam(name = "thema", required = true) String thema, @RequestParam(name = "titel", required = true) String titel, @RequestParam(name = "Text1", required = true) String text1, @RequestParam(name = "ImgLink1", required = true) String ImgLink1, @RequestParam(name = "Text2", required = false) String test2, @RequestParam(name = "ImgLink2", required = false) String ImgLink2, @RequestParam(name = "Text3", required = false) String text3, @RequestParam(name = "ImgLink3", required = false) String ImgLink3) {

        String t = thema;
        switch(t) {
            case "technGrundlagen":
                technGrundlagen.add(new page(technGrundlagen.size(), titel, ImgLink1, text1));
                break;
            case "sicherheitVerfahren":
                sicherheitVerfahren.add(new page(sicherheitVerfahren.size(), titel, ImgLink1, text1));
                break;
            case "komplexeVerfahren":
                komplexeVerfahren.add(new page(komplexeVerfahren.size(), titel, ImgLink1, text1));
                break;
            case "angriffe":
                angriffe.add(new page(angriffe.size(), titel, ImgLink1, text1));
                break;
        }
        
        return("redirect:/home");
    }

    @GetMapping("/technGrundlagen")
    public String technGrundlagen(Model model) {
        model.addAttribute("activePage", "technGrundlagen");
        model.addAttribute("technGrundlagen", technGrundlagen);
        return "index.html";
    }

    @GetMapping("/sicherheitVerfahren")
    public String sicherheitVerfahren(Model model) {
        model.addAttribute("activePage", "sicherheitVerfahren");
        model.addAttribute("sicherheitVerfahren", sicherheitVerfahren);
        return "index.html";
    }

    @GetMapping("/komplexeVerfahren")
    public String komplexeVerfahren(Model model) {
        model.addAttribute("activePage", "komplexeVerfahren");
        model.addAttribute("komplexeVerfahren", komplexeVerfahren);
        return "index.html";
    }

    @GetMapping("/angriffe")
    public String angriffe(Model model) {
        model.addAttribute("activePage", "angriffe");
        model.addAttribute("angriffe", angriffe);
        return "index.html";
    }

    @GetMapping("/infoTech")
    public String infoTech(@RequestParam(name = "id", required = true, defaultValue = "0") int id, Model model) {
        model.addAttribute("activePage", "infoTech");
        model.addAttribute("infos", technGrundlagen.get(id));
        return "index.html";
    }


    public void showTechnGrundlagen() {
        
        System.out.println(technGrundlagen.get(0).getTitel());
    }

    public void setTechnGrundlagen(ArrayList<page> technGrundlagen) {
        this.technGrundlagen = technGrundlagen;
    }
    public void setSicherheitVerfahren(ArrayList<page> sicherheitVerfahren) {
        this.sicherheitVerfahren = sicherheitVerfahren;
    }
    public void setAngriffe(ArrayList<page> angriffe) {
        this.angriffe = angriffe;
    }
    public void setKomplexeVerfahren(ArrayList<page> komplexeVerfahren) {
        this.komplexeVerfahren = komplexeVerfahren;
    }
    public ArrayList<page> getAngriffe() {
        return angriffe;
    }
    public ArrayList<page> getKomplexeVerfahren() {
        return komplexeVerfahren;
    }
    public ArrayList<page> getSicherheitVerfahren() {
        return sicherheitVerfahren;
    }

    public ArrayList<page> getTechnGrundlagen() {
        return technGrundlagen;
    }



}
