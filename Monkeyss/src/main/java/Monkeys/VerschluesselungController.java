package Monkeys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/verschluesselung")
    public String Caesar(@RequestParam(name="klartext", required = false, defaultValue = "") String klartext,
    @RequestParam (name="key", required = false, defaultValue = "0") int key, Model model){

        model.addAttribute("klartext", code(klartext, key));
        if (klartext.length() > 0) {
            model.addAttribute("encrypt", true);
        }else{
            model.addAttribute("encrypt", false);
        }

        return "verschluesselung.html";
    }


}
