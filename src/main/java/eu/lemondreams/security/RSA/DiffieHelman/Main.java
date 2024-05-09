package eu.lemondreams.security.RSA.DiffieHelman;

import java.util.Scanner;

public class Main {

//(c)	Determine the value of the symmetric key found using the Diffie-Hellman protocol if g=7, p=23, a=4 and b=6.

    public static void main(String[] args) {
        System.out.println("Diffie hellman world!");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter value for (g) :");
        int g = sc.nextInt();

        System.out.println("Enter the value for (p) :");
        int p = sc.nextInt();

        System.out.println("Enter alice's secert (a) :");
        int a = sc.nextInt();

        System.out.println("Enter bobs's secert (b) :");
        int b = sc.nextInt();

        // User(String name, double g, double p, long secret) {

        User alice = new User("Alice",g,p,a);
        User bob = new User("Bob",g,p,b);
        
        double astep1 =  alice.diffie_hellman();
        double bstep1 = bob.diffie_hellman();

        System.out.println("(g ^ Secert) MOD p");
        System.out.println("Alice calcs " + astep1);
        

        System.out.println("bob calcs " +bstep1);
        System.out.println("");

        System.out.println("Alice send to bob, and bob sends this to alice");
        alice.sendToUser(bob,astep1);
        bob.sendToUser(alice,bstep1);

        System.out.println("Alice checks bobs work...");


    if(alice.checkDiffie_hellman()== bob.checkDiffie_hellman()){




        System.out.println("Alice had "+alice.checkDiffie_hellman());
        System.out.println("Bob had "+bob.checkDiffie_hellman());

        System.out.println("Bob and Alice agree");


        System.out.println( "They agreed on "+alice.checkDiffie_hellman());


    }else {
        System.out.println("Alice and Bob disagree");
        System.out.println("Alice had "+alice.checkDiffie_hellman());
        System.out.println("Bob had "+bob.checkDiffie_hellman());

    }







    }








}

