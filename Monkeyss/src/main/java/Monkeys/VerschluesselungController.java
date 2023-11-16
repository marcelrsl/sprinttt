package Monkeys;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller 
public class VerschluesselungController  {

    //Klartext mit dem Schluessel verschluesseln 
    private String code(String plaintext, int key){
        String ciphertext = "";
        plaintext = plaintext.toLowerCase(); //Alles in Kleinbuchstaben umwandeln
        for(int i=0; i<plaintext.length(); i++){
            //Buchstaben verschieben und an Geheimtext hinzufügen
            char next = plaintext.charAt(i);
            ciphertext = ciphertext + shift(next, key);
        }
        return ciphertext;
    }

    //Verschiebt den Buchstaben um die angegebene Verschiebung (key)
    private char shift(char letter, int shift){
        //Buchstaben als Zahl behandeln
        if(letter>='a' && letter <='z'){ //Sonderzeichen nicht verändern
            letter += shift;
            while(letter > 'z'){
                letter -= 26; //Es wird sicher gestellt, dass nach dem "Z", man wieder zum Beginn des Alphabets zurückkehrt
            }
        }
        return letter;
    }

    //Entschlüsselt den Geheimtext mit dem angegebenen Schlüssel 
    private String decode(String ciphertext, int key){
        String decoded = "";
        ciphertext = ciphertext.toLowerCase(); //Geheimtext in Kleinbuchstaben umwandeln
        for(int i=0; i<ciphertext.length(); i++){
            //Jeden Buchstaben verschieben, um 26 Schlüssel Positonen und zum entschlüsselten Text hinzufügen
           char next = ciphertext.charAt(i);
           decoded = decoded + shift(next, 26-key);
        }
        return decoded;
    }

    @GetMapping("/caesar") 
    public String caesar(@RequestParam(name="encrypt", required = false, defaultValue = "") String encrypt,@RequestParam(name="klartext", required = false, defaultValue = "") String klartext,Model model) {
        model.addAttribute("activePage", "caesar"); //Hinzufügen der Attribute für die active Page
        model.addAttribute("klartext", klartext); //Der Klartet wird als Attribut hinzugefügt
        model.addAttribute("encrypt", klartext.length() > 0);
    
        return "index.html";
    }

    @PostMapping(path = "/caesar/do") 
public String caesarDo(RedirectAttributes redirectAttributes, @RequestParam(name="klartext", required = true, defaultValue = "") String klartext,
    @RequestParam(name="key", required = true, defaultValue = "0") int key,
    Model model) {
    String encryptedText = code(klartext, key);
    redirectAttributes.addAttribute("klartext", encryptedText); //Verschlüsselter Text wird als Attribut weitergeleitet
    redirectAttributes.addAttribute("encrypt", klartext.length() > 0);
    return "redirect:/caesar"; // Annahme, dass deine HTML-Datei "caesar.html" ist, ändere dies entsprechend
    }
}