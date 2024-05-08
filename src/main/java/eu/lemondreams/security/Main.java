package eu.lemondreams.security;

import eu.lemondreams.MenuMaker.MenuMaker.Menu;
import eu.lemondreams.MenuMaker.MenuMaker.MenuItem;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Menu mainMenu = new Menu(Menu.MenuType.MAIN);

        MenuItem FeistalCipherMenu = new MenuItem("Feistal Cipher",()->{eu.lemondreams.security.Feistal.Main.main(args);});

        MenuItem VigenereCipherMenu = new MenuItem("Vinenere Cipher",()->eu.lemondreams.security.Vigenere.VigenereCipher.main(args));

        MenuItem RSA_enc = new MenuItem("RSA - encypt/decrypt based on public private keys",()->{eu.lemondreams.security.RSA.enc_dec.RSA_enc.main(args);});

        MenuItem RSA_dh = new MenuItem("RSA -Diffie Helman",()->{eu.lemondreams.security.RSA.DiffieHelman.Main.main(args);});

        MenuItem RSA_Full = new MenuItem("RSA - Full",()->{eu.lemondreams.security.RSA.base.RSA_Simple.main(args);});



        mainMenu.addMenuItem(FeistalCipherMenu);
        mainMenu.addMenuItem(VigenereCipherMenu);
        mainMenu.addMenuItem(RSA_enc);
        mainMenu.addMenuItem(RSA_dh);
        mainMenu.addMenuItem(RSA_Full);


        mainMenu.run();


    }
}