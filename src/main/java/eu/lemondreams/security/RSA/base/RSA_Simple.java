package eu.lemondreams.security.RSA.base;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class RSA_Simple {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("RSA - tool\n");


       try {

        //1
        System.out.println("Enter Value for P");
        int P = sc.nextInt();
        System.out.println();

        System.out.println("Enter Value for Q");
        int Q = sc.nextInt();
        System.out.println();
        //2
        System.out.println("N is P * Q");
        int N = P*Q;
        System.out.println("N = "+N);
        System.out.println();
        //3
        System.out.println("int T = ((P-1) *(Q-1) )");
        int T = ((P-1) *(Q-1) );
        System.out.println("T = "+T);
        System.out.println();


        //4 select public key
        // Must be prime, must be less than Totient
        //Must NOT be a factor of the Totient

        List<Integer> possibleValuesOfE = findPossibleValuesOfE(T);

        if (possibleValuesOfE.isEmpty()) {
            System.out.println("No possible values of E found for the given T.");
        } else {
            System.out.println("Possible values of E: " + possibleValuesOfE);
        }

        System.out.println("Input the value for E");
        int E = sc.nextInt();
        System.out.println();

        List<Integer> possibleValuesOfD = findPossibleValuesOfD(E, T);

        if (possibleValuesOfD.isEmpty()) {
            System.out.println("No possible values of D found for the given E and T.");
        } else {
            System.out.println("Possible values of D: " + possibleValuesOfD);
        }


        //5 Select Private Key
        // We will call D
        //(D * E) MOD T = 1;

        System.out.println("(D * E) MOD T = 1");
        System.out.println("Input the value for D");
        int D = sc.nextInt();
        System.out.println();


        System.out.println("Public Key(n,e) : ("+N+","+E+")");
        System.out.println("Private Key(n,d) : ("+N+","+D+")");
        System.out.println();

        System.out.println("Whats the (M)essage?");
        int M = sc.nextInt();
        System.out.println();
        // Encrpytion is M^E MOD N
       
    
        System.out.println("En(C)ryption: M^E MOD N");
        double C = (Math.pow(M,E)) %N;
        System.out.println("C = "+C);


        System.out.println("Decryption of C to Original (M)essage: ");
        double Md = (Math.pow(C,D)) %N;
        System.out.println("M = "+Md);

        System.out.println("\n Summary:");
        System.out.println("P "+P);
        System.out.println("Q "+Q);
        System.out.println("N "+N);
        System.out.println("T "+T);
        System.out.println("E "+E);
        System.out.println("M "+M);
        System.out.println("D "+D);
        System.out.println("Public Key (n,e) : ("+N+","+E+")");
        System.out.println("Private Key (n,d) : ("+N+","+D+")");
        System.out.println("");

       } catch (Exception e) {
        // TODO: handle exception
       }


        
    }



    public static List<Integer> findPossibleValuesOfD(int E, int T) {
        List<Integer> possibleValuesOfD = new ArrayList<>();

        for (int D = 1; D < T; D++) {
            if ((D * E) % T == 1) {
                possibleValuesOfD.add(D);
            }
        }

        return possibleValuesOfD;
    }



 // Must be prime, must be less than T
 //Must NOT be a factor of the T
    
    public static List<Integer> findPossibleValuesOfE(int T) {
        List<Integer> possibleValuesOfE = new ArrayList<>();

        for (int E = 2; E < T; E++) {
            if (isPrime(E)) {
                
                if(T % E==1){
                    continue;
                }possibleValuesOfE.add(E);


            }
        }

        return possibleValuesOfE;
    }

    // Function to check if a number is prime
    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}