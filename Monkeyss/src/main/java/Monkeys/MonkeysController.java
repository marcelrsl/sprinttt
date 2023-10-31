package Monkeys;


import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Monkeys.Model.page;

@Controller
public class MonkeysController {

    boolean editModus;
    ArrayList<page> technGrundlagen;
    ArrayList<page> sicherheitVerfahren;
    ArrayList<page> komplexeVerfahren;
    ArrayList<page> angriffe;

    public MonkeysController() {
        setTechnGrundlagen(new ArrayList<>());
        setSicherheitVerfahren(new ArrayList<>());
        setAngriffe(new ArrayList<>());
        setKomplexeVerfahren(new ArrayList<>());
        editModus = false;   
        test();
    }

    public void test() {
        System.out.println(editModus);
    }
    
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("activePage", "home");
        System.out.println(editModus);
        model.addAttribute("editMode", getEditModus());
        return "index.html";
    }

    @GetMapping("/einrichten")
    public String einrichten(Model model) {
        model.addAttribute("activePage", "einrichten");
        model.addAttribute("editMode", getEditModus());
        return "index.html";
    }

    @PostMapping(path = "/einrichten/do")
    public String einrichtenDo(@RequestParam(name = "thema", required = true) String thema, @RequestParam(name = "titel", required = true) String titel, @RequestParam(name = "Text1", required = true) String text1, @RequestParam(name = "ImgLink1", required = true) String ImgLink1, @RequestParam(name = "Text2", required = false) String text2, @RequestParam(name = "ImgLink2", required = false) String ImgLink2, @RequestParam(name = "Text3", required = false) String text3, @RequestParam(name = "ImgLink3", required = false) String ImgLink3) {

        page page = new page( titel, ImgLink1, text1, ImgLink2, text2, ImgLink3, text3);

        String t = thema;
        switch(t) {
            case "technGrundlagen":
                technGrundlagen.add(new page(titel, ImgLink1, text1, ImgLink2, text2, ImgLink3, text3));
                
                try {
                    DatabaseController db = new DatabaseController();
                    db.addTechnGrundlagen(page);
                }
                catch(Exception e){
                    System.out.println("Error! Player data not valid or parsing went wrong :( !");
                    System.out.println(e);
                }
                break;

            case "sicherheitVerfahren":
                sicherheitVerfahren.add(new page( titel, ImgLink1, text1, ImgLink2, text2, ImgLink3, text3));

                try {
                    DatabaseController db = new DatabaseController();
                    db.addSicherheitVerfahren(page);
                }
                catch(Exception e){
                    System.out.println("Error! Player data not valid or parsing went wrong :( !");
                    System.out.println(e);
                }
                break;

            case "komplexeVerfahren":
                komplexeVerfahren.add(new page(titel, ImgLink1, text1, ImgLink2, text2, ImgLink3, text3));

                try {
                    DatabaseController db = new DatabaseController();
                    db.addKomplexeVerfahren(page);
                }
                catch(Exception e){
                    System.out.println("Error! Player data not valid or parsing went wrong :( !");
                    System.out.println(e);
                }
                break;

            case "angriffe":
                angriffe.add(new page(titel, ImgLink1, text1, ImgLink2, text2, ImgLink3, text3));

                try {
                    DatabaseController db = new DatabaseController();
                    db.addAngriffe(page);
                }
                catch(Exception e){
                    System.out.println("Error! Player data not valid or parsing went wrong :( !");
                    System.out.println(e);
                }
                break;
        }
        
        return("redirect:/home");
    }

    @GetMapping("/uebersicht")
    public String uebersicht(@RequestParam(name = "thema", required = true, defaultValue = ".") String thema, Model model) throws SQLException {
        DatabaseController db = new DatabaseController();
        model.addAttribute("activePage", "uebersicht");
        model.addAttribute("editMode", getEditModus());
        
        String t = thema;
        switch(t) {
            case "technGrundlagen":
                model.addAttribute("uebersicht", db.getAllTechnGrundlagen());
                model.addAttribute("titel", "Technische Grundlagen");
                model.addAttribute("thema", "technGrundlagen");
                break;
            case "sicherheitVerfahren":
                model.addAttribute("uebersicht", db.getAllSicherheitVerfahren());
                model.addAttribute("titel", "Sicherheit & Verfahren");
                model.addAttribute("thema", "sicherheitVerfahren");
                break;
            case "komplexeVerfahren":
                model.addAttribute("uebersicht", db.getAllKomplexeVerfahren());
                model.addAttribute("titel", "Komplexe Verfahren");
                model.addAttribute("thema", "komplexeVerfahren");
                break;
            case "angriffe":
                model.addAttribute("uebersicht", db.getAllAngriffe());
                model.addAttribute("titel", "Angriffe");
                model.addAttribute("thema", "angriffe");
                break;
            case ".":
                break;
        
            
        }
        return "index.html";
    }

    @GetMapping("/infoTech")
    public String infoTech(@RequestParam(name = "id", required = true, defaultValue = "0") int id, @RequestParam(name = "thema", required = true) String thema, Model model) throws SQLException{
        model.addAttribute("activePage", "infoTech");
        model.addAttribute("editMode", getEditModus());

        DatabaseController db = new DatabaseController();
        String t = thema;
        switch(t) {
            case "technGrundlagen": 
                model.addAttribute("info", db.getTechnGrundlagen(id));
                model.addAttribute("thema", "technGrundlagen");
                //System.out.println(db.getTechnGrundlagen(id));
                break;
            case "sicherheitVerfahren":
                model.addAttribute("info", db.getSicherheitVerfahren(id));
                model.addAttribute("thema", "sicherheitVerfahren");
                break;
            case "komplexeVerfahren":
                model.addAttribute("info", db.getKomplexeVerfahren(id));
                model.addAttribute("thema", "komplexeVerfahren");
                break;
            case "angriffe":
                model.addAttribute("info", db.getAngriffe(id));
                model.addAttribute("thema", "angriffe");
                break;
            
        }
        return "index.html";
    }

    @GetMapping("/editPage") 
    public String editPage(@RequestParam(name = "id", required = true, defaultValue = "0") int id, @RequestParam(name = "thema", required = true) String thema, Model model) throws SQLException {

        model.addAttribute("activePage", "editPage");
        model.addAttribute("editMode", getEditModus());
        
        DatabaseController db = new DatabaseController();
        String t = thema;
        switch(t) {
            case "technGrundlagen":
                model.addAttribute("info", db.getTechnGrundlagen(id));
                
                model.addAttribute("thema", "technGrundlagen");
                //System.out.println(db.getTechnGrundlagen(id));
                break;
            case "sicherheitVerfahren":
                model.addAttribute("info", db.getSicherheitVerfahren(id));
                model.addAttribute("thema", "sicherheitVerfahren");
                break;
            case "komplexeVerfahren":
                model.addAttribute("info", db.getKomplexeVerfahren(id));
                model.addAttribute("thema", "komplexeVerfahren");
                break;
            case "angriffe":
                model.addAttribute("info", db.getAngriffe(id));
                model.addAttribute("thema", "angriffe");
                break;
            
        }
        
        return "index.html";
    }

    @PostMapping(path="/editPage/do")
    public String editPageDo(@RequestParam(name = "thema", required = true) String thema, @RequestParam(name = "titel", required = true) String titel, @RequestParam(name = "Text1", required = true) String text1, @RequestParam(name = "ImgLink1", required = true) String ImgLink1, @RequestParam(name = "Text2", required = false) String text2, @RequestParam(name = "ImgLink2", required = false) String ImgLink2, @RequestParam(name = "Text3", required = false) String text3, @RequestParam(name = "ImgLink3", required = false) String ImgLink3, @RequestParam(name = "id", required = true) int id ) throws SQLException {

         page page = new page( titel, ImgLink1, text1, ImgLink2, text2, ImgLink3, text3);

        String t = thema;
        switch(t) {
            case "technGrundlagen":
                technGrundlagen.add(new page(titel, ImgLink1, text1, ImgLink2, text2, ImgLink3, text3));
                
                try {
                    DatabaseController db = new DatabaseController();
                    db.edit(t, id, page);
                }
                catch(Exception e){
                    System.out.println("Error! Player data not valid or parsing went wrong :( !");
                    System.out.println(e);
                }
                break;

            case "sicherheitVerfahren":
                sicherheitVerfahren.add(new page( titel, ImgLink1, text1, ImgLink2, text2, ImgLink3, text3));

                try {
                    DatabaseController db = new DatabaseController();
                    db.edit(t, id, page);
                }
                catch(Exception e){
                    System.out.println("Error! Player data not valid or parsing went wrong :( !");
                    System.out.println(e);
                }
                break;

            case "komplexeVerfahren":
                komplexeVerfahren.add(new page(titel, ImgLink1, text1, ImgLink2, text2, ImgLink3, text3));

                try {
                    DatabaseController db = new DatabaseController();
                    db.edit(t, id, page);
                }
                catch(Exception e){
                    System.out.println("Error! Player data not valid or parsing went wrong :( !");
                    System.out.println(e);
                }
                break;

            case "angriffe":
                angriffe.add(new page(titel, ImgLink1, text1, ImgLink2, text2, ImgLink3, text3));

                try {
                    DatabaseController db = new DatabaseController();
                    db.edit(t, id, page);
                }
                catch(Exception e){
                    System.out.println("Error! Player data not valid or parsing went wrong :( !");
                    System.out.println(e);
                }
                break;
        }
        
        return("redirect:/home");
    }

    @GetMapping("/navEdit/start")
    public String navEditStart() {
        setEditModus(true);
        return("redirect:/home");
    }

    @GetMapping("/navEdit/ende")
    public String navEditEnde() {
        setEditModus(false);
        return("redirect:/home");
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
    public void setEditModus(boolean editModus) {
        this.editModus = editModus;
    }
    public boolean getEditModus() {
        return editModus;
    }



}
