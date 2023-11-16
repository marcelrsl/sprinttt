package Monkeys;

//Imports
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

    //Initialisieren der Variabeln
    //Die Variabel editModus wird in (fast) jeden Mapping abgefragt, um das richtige anzuzeigen und die Möglichkeit zu bieten Inhalte zu verändern
    boolean editModus;
    boolean benachrichtigung1;
    boolean benachrichtigung2;
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
        benachrichtigung1 = false;
        benachrichtigung2 = false; 
        test();
    }

    public void test() {
        System.out.println(editModus);
    }
    
    //Hier befindet sich das Mapping für die Startseite
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("activePage", "home");
        System.out.println(editModus);
        model.addAttribute("editMode", getEditModus());
        model.addAttribute("benachrichtigung1", getBenachrichtigung1());
        model.addAttribute("benachrichtigung2", getBenachrichtigung2());
        return "index.html";
    }

    //Hier befindet sich das Mapping um eine neue Seite zu erstellen
    @GetMapping("/einrichten")
    public String einrichten(Model model) {
        model.addAttribute("activePage", "einrichten");
        model.addAttribute("editMode", getEditModus());
        return "index.html";
    }

    //In diese PostMapping werden die Daten der neuen Seite zum DatabaseController weitergegeben
    @PostMapping(path = "/einrichten/do")
    public String einrichtenDo(@RequestParam(name = "thema", required = true) String thema, @RequestParam(name = "titel", required = true) String titel, @RequestParam(name = "Text1", required = true) String text1, @RequestParam(name = "ImgLink1", required = true) String ImgLink1, @RequestParam(name = "Text2", required = false) String text2, @RequestParam(name = "ImgLink2", required = false) String ImgLink2, @RequestParam(name = "Text3", required = false) String text3, @RequestParam(name = "ImgLink3", required = false) String ImgLink3) {

        //Hier wird ein neues Objekt erstellt und die Inhalte hinzugefügt
        page page = new page( titel, ImgLink1, text1, ImgLink2, text2, ImgLink3, text3);

        //Damit die neue Seite auch dem richtigen Thema zugeordnet wird, wird in der Switch-Case-Methode die Variabel Thema dem richtigen Case zugeordnet und dann an die rcihtige Methode in der Datenbank weitergeleitet.
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
        //Danach wird man zur Startseite weitergeleitet
        return("redirect:/home");
    }

    //Hier befindet sich das Mapping für die Übersicht der Themen
    @GetMapping("/uebersicht")
    public String uebersicht(@RequestParam(name = "thema", required = true, defaultValue = ".") String thema, Model model) throws SQLException {
        
        /*Dies soll das einzige Mapping einer Übersicht für alle 4 Themen sein. Dies funktioniert wie folgt:
            Man bekommt an Anfang das Thema vom Button/ aus der Navabar übergeben. Dieses wird dann wieder in der Switch-Case-Methode eingestetzt und dem richtigen Case zugeordnet.
        */
        DatabaseController db = new DatabaseController();
        model.addAttribute("activePage", "uebersicht");
        model.addAttribute("editMode", getEditModus());
        
        String t = thema;
        switch(t) {
            case "technGrundlagen":
                model.addAttribute("uebersicht", db.getAllTechnGrundlagen());
                model.addAttribute("titel", "Technische Grundlagen");
                //Dies ist nur noch für den nächsten Button zu den Infos wichtig
                model.addAttribute("thema", "technGrundlagen");
                break;
            case "sicherheitVerfahren":
                model.addAttribute("uebersicht", db.getAllSicherheitVerfahren());
                model.addAttribute("titel", "Sicherheit & Verfahren");
                //Dies ist nur noch für den nächsten Button zu den Infos wichtig
                model.addAttribute("thema", "sicherheitVerfahren");
                break;
            case "komplexeVerfahren":
                model.addAttribute("uebersicht", db.getAllKomplexeVerfahren());
                model.addAttribute("titel", "Komplexe Verfahren");
                //Dies ist nur noch für den nächsten Button zu den Infos wichtig
                model.addAttribute("thema", "komplexeVerfahren");
                break;
            case "angriffe":
                model.addAttribute("uebersicht", db.getAllAngriffe());
                model.addAttribute("titel", "Angriffe");
                //Dies ist nur noch für den nächsten Button zu den Infos wichtig
                model.addAttribute("thema", "angriffe");
                break;
            case ".":
                break;
        
            
        }
        return "index.html";
    }

    //Hier befindet sich das Mapping für das schullendlich Anzeigen der Informationen
    @GetMapping("/infoTech")
    public String infoTech(@RequestParam(name = "id", required = true, defaultValue = "0") int id, @RequestParam(name = "thema", required = true) String thema, Model model) throws SQLException{
        model.addAttribute("activePage", "infoTech");
        model.addAttribute("editMode", getEditModus());

        //Hier passsiert das selbe wie beim Mapping davor. Es wird aber noch zusätzlich die Id des Themas übergeben.

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

    //Hier befindet sich das Mapping, um eine Seite zu ändern.
    @GetMapping("/editPage") 
    public String editPage(@RequestParam(name = "id", required = true, defaultValue = "0") int id, @RequestParam(name = "thema", required = true) String thema, Model model) throws SQLException {

        model.addAttribute("activePage", "editPage");
        model.addAttribute("editMode", getEditModus());
        
        //Hier wird das abgespeicherte Objekt aus der Datenbank geholt. Damit es das richtige ist, wird das Thema und die Id übergeben

        DatabaseController db = new DatabaseController();
        String t = thema;
        switch(t) {
            case "technGrundlagen":
                model.addAttribute("info", db.getTechnGrundlagen(id));
                model.addAttribute("thema", "technGrundlagen");
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

    //Hier ist das PostMapping, welches das ändern des Objekts durchführt.
    @PostMapping(path="/editPage/do")
    public String editPageDo(@RequestParam(name = "thema", required = true) String thema, @RequestParam(name = "titel", required = true) String titel, @RequestParam(name = "Text1", required = true) String text1, @RequestParam(name = "ImgLink1", required = true) String ImgLink1, @RequestParam(name = "Text2", required = false) String text2, @RequestParam(name = "ImgLink2", required = false) String ImgLink2, @RequestParam(name = "Text3", required = false) String text3, @RequestParam(name = "ImgLink3", required = false) String ImgLink3, @RequestParam(name = "id", required = true) int id ) throws SQLException {

        //Hier passiert genau das selbe wie bei /einrichten

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

    //Hier befindet sich das Mapping, um eine Seite zu löschen.
    @GetMapping("/deletePage")
    public String deletePage(@RequestParam(name = "id", required = true, defaultValue = "0") int id, @RequestParam(name = "thema", required = true) String thema, Model model) throws SQLException {

        //Hier wird nur das Thema und die Id an die Methode im DatabaseController übergeben, der sie dann löscht.
        try {
            DatabaseController db = new DatabaseController();
            db.deletePage(thema, id);
        }
        catch(Exception e){
            System.out.println("Error! Player data not valid or parsing went wrong :( !");
            System.out.println(e);
        } 
        //weiterleitung zur Startseite
        return("redirect:/home");
    }

    //Hier befindet sich das Mapping um in den Edit Modus zu kommen
    @GetMapping("/navEdit/start")
    public String navEditStart() {
        setEditModus(true);
        setBenachrichtigung1(true);
        return("redirect:/home");
    }

    //Hier befindet isch das Mapping um in den Public Modus zu kommen
    @GetMapping("/navEdit/ende")
    public String navEditEnde() {
        setEditModus(false);
        setBenachrichtigung2(true);
        return("redirect:/home");
    }

    //Setter und Getter
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
    public void setBenachrichtigung1(boolean benachrichtigung1) {
        this.benachrichtigung1 = benachrichtigung1;
    }
    public boolean getBenachrichtigung1() {
        return benachrichtigung1;
    }
    public void setBenachrichtigung2(boolean benachrichtigung2) {
        this.benachrichtigung2 = benachrichtigung2;
    }
    public boolean getBenachrichtigung2() {
        return benachrichtigung2;
    }
}
