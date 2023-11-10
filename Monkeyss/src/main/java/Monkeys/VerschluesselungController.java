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
            //Buchstaben verschieben und an Geheimtext haengen
            char next = plaintext.charAt(i);
            ciphertext = ciphertext + shift(next, key);
        }
        return ciphertext;
    }

    //Verschiebt den Buchstaben um die angegebene Verschiebung (key)
    private char shift(char letter, int shift){
        //Buchstaben als Zahl behandeln
        if(letter>='a' && letter <='z'){ //Sonderzeichen nicht veraendern
            letter += shift;
            while(letter > 'z'){
                letter -= 26;
            }
        }
        return letter;
    }

    //Entschluesselt den Geheimtext mit dem angegebenen Schluessel 
    private String decode(String ciphertext, int key){
        String decoded = "";
        ciphertext = ciphertext.toLowerCase(); //Alles in Kleinbuchstaben umwandeln
        for(int i=0; i<ciphertext.length(); i++){
            //Jeden Buchstaben verschieben und zwar um (26-Schluessel(key)) Stellen
            //und an entschluesselten Text anhaengen
           char next = ciphertext.charAt(i);
           decoded = decoded + shift(next, 26-key);
        }
        return decoded;
    }

    @GetMapping("/caesar")
    public String caesar(Model model) {
        model.addAttribute("activePage", "caesar");
        return "index.html";
    }

    @PostMapping(path = "/caesar/do") 
    public String caesarDo(@RequestParam(name="klartext", required = true, defaultValue = "") String klartext,
                            @RequestParam(name="key", required = true, defaultValue = "0") int key,
                            RedirectAttributes redirectAttributes) {
        String encryptedText = code(klartext, key);
        redirectAttributes.addFlashAttribute("klartext", encryptedText);
        redirectAttributes.addFlashAttribute("encrypt", klartext.length() > 0);

        return "redirect:/caesar";
    }


}
