package eu.lemondreams.security.RSA.enc_dec;

import java.util.Scanner;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class RSA_enc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("RSA - enc decrypt");
        System.out.println("E for enc, D for decrypt");

        boolean encypt =false;

        String val = sc.nextLine();
        val = val.toUpperCase();

        switch (val){
            case "E" ->  encypt = true;

        }


        if(encypt){
            System.out.println("Input Private key modulus 'n' (?,e)");
            int n = sc.nextInt();

            System.out.println("Input Private key 'e' (n,?)");
            int e = sc.nextInt();


            KeyPair priv = new KeyPair(n,e);

            System.out.println("What is the message?");
            int message = sc.nextInt();

            encrypt(priv,message);



        }else{
            System.out.println("Input Public key modulus 'n' (?,e)");
            int n = sc.nextInt();

            System.out.println("Input Public key 'd'(n,?)");
            int d = sc.nextInt();

            KeyPair pub = new KeyPair(n,d);

            System.out.println("What is the message?");
            int message = sc.nextInt();

            System.out.println(pub);
            decrypt(pub,message);
        }




    }


    public  record KeyPair (int  n , int var){

    }

    private static int decrypt(KeyPair kp, int cypherText){
        System.out.println("M = (C to pow D) MOD n");
        int M =(int) (Math.pow(cypherText,kp.var()) % kp.n);
        System.out.println(""+M+" = "+cypherText+ " POW "+kp.var() + " % "+kp.n);
        return M;



    }


    private static int encrypt(KeyPair kp, int plainText){
        System.out.println("C = (M to pow E) MOD n");
        int C =(int) (Math.pow(plainText,kp.var()) % kp.n);
        System.out.println(""+C+" = "+plainText+ " POW "+kp.var() + " % "+kp.n);
        return C;



    }



}