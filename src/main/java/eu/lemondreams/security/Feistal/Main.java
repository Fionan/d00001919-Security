package eu.lemondreams.security.feistal;


import eu.lemondreams.security.feistal.Block;
import eu.lemondreams.security.feistal.Key;
import eu.lemondreams.security.feistal.SubKey;

import java.util.*;

public class Main {

 static    ArrayList<SubKey> subkeys = new ArrayList<>();


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);


        System.out.println("Welcome to Feistal viewer");
        System.out.println("Enter the plaintext");
        //get message form user

        String plainTextString = sc.nextLine();
        //Block plaintext = new Block("01011100");
        Block plaintext = new Block(plainTextString);


        System.out.println("Enter number of rounds");
        int numberOfRounds = sc.nextInt();
        sc.nextLine();

        // get key from user
        System.out.println("Enter the Key");
        String keyAsString = sc.nextLine();

        Key masterKey = new Key(keyAsString,numberOfRounds);

       // Key masterKey = new Key("110101",4);

        int rounds = numberOfRounds;
        //get rounds from user

        //assume default key mix

        generateSubkeys(masterKey);
        //return key list

        printKeys();


        Block current = plaintext;

        System.out.println("Encoding...");
        for (int i = 0; i < rounds; i++) {

            current = round(current,subkeys.get(i),i);

            System.out.println("");
        }

        current= current.rotate();

        System.out.println("\t**** Cypher text is therefore: "+current+" ****\n");

        System.out.println("Press enter to decode:");
        sc.nextLine();

        System.out.println("Decoding...");
        for (int i = rounds-1;i >=0; i--) {


            current = round(current,subkeys.get(i),i+1);
            System.out.println("");


        }
       current= current.rotate();
        System.out.println("Original plaintext  "+plaintext);
        System.out.println("PLain text is therefore: "+current);




        //perform encode



    }

    private static void generateSubkeys(Key masterKey) {
    System.out.println("Assuming default key arrangement");

        SubKey k1 = new SubKey(masterKey,"1234");
        SubKey k2 = new SubKey(masterKey,"3456");
        SubKey k3 = new SubKey(masterKey,"2345");
        SubKey k4  =new SubKey(masterKey,"1256");

        subkeys.add(k1);
        subkeys.add(k2);
        subkeys.add(k3);
        subkeys.add(k4);

    }


    private  static void printKeys(){
        System.out.println("SubKeys :");

        int cout = 1;
     for(SubKey sk : subkeys){
         System.out.println("\tk"+getSubscript(cout)+sk);
         cout++;
     }
    }

    private static void log(String message,Block b){

        System.out.println("\t"+message + " : "+b);

    }

    private static Block round (Block b, SubKey sk,int roundNumber){

        HashMap<String,Block> valuesb = new HashMap<>();

        System.out.println("Round : "+roundNumber);
       log("Round start block",b);
       log("Sub key ",sk);
       valuesb.put("Block",b);
       valuesb.put("Subkey",sk);





        //split
        Block leftside = b.left();
       log("Left side ",leftside);
       valuesb.put("Left",leftside);

        Block rightside = b.right();
      log("Right side",rightside);
       valuesb.put("Right",rightside);

        //perform xor on rightside
        Block XORd = rightside.XOR(sk);
        log("Perform XOR on Right side",XORd);
       valuesb.put("XORd",XORd);

        //perform shift
        Block shifted = XORd.shift(1, Block.Direction.LEFT);
       log("Perform left shift ",shifted);
       valuesb.put("Shifted",shifted);


        Block merge = leftside.XOR(shifted);
       log("Perform XOR with modded right to leftside ",merge);
       valuesb.put("merge",merge);

        // join back
        Block joined = Block.join(merge,rightside);
       log("Joining block ",joined);

        Block rotate = joined.rotate();
      log("rotate left and right",rotate);
       valuesb.put("Rotate",rotate);


     // printRound(roundNumber,valuesb);
        return rotate;

    }

    public static String getSubscript(int digit) {
        if (digit >= 0 && digit <= 9) {
            int codePoint = 0x2080 + digit;
            return new String(Character.toChars(codePoint));
        } else {
            throw new IllegalArgumentException("Digit must be between 0 and 9");
        }
    }


    public static void printRound(int roundNumber, Map<String,Block> v){


        System.out.println("Round "+roundNumber);
        String line1 ="\tStarting block :"+v.get("Block")+"\tSubkey : \t"+v.get("Subkey");
        String line2 = "\t\t\t\t\t\u2199\t\t\t \u2198";
        String line4 = "\tLeft-Side : \t"+v.get("Left")+"\t\tRight-Side : \t"+v.get("Right");
        String line5 = "\tXORd: \t\t\t"+v.get("Shifted")+"\t<--shift \u265C\tK"+getSubscript(roundNumber)+" : \t"+v.get("Subkey");
        String line3 = "\t\t\t\t\t\u0305\u0305\u0305\u0305\t\t\t\t\t\t\u0305\u0305\u0305\u0305 \u21A9";
        String line6 = "\tLeft-side : \t"+v.get("merge")+"\t\t\t"+"XORd\t:\t"+v.get("XORd");
        String line7 = "\t\t\u2192\t\u2192\t\u2192\t\u2198\t\u2198\t"+"\t\u2199\t\u2199\t\u2190\t\u2190\t\u2190";
        String line8 = "\t\t\t\t\t\t\t\u2198 \u2199\t";

        System.out.println(line1);
        System.out.println(line2);


        System.out.println(line4);
        System.out.println(line5);
        System.out.println(line3);
        System.out.println(line6);
        System.out.println(line7);
        System.out.println(line8);
    }


    public static String addOverline(String text) {
        StringBuilder builder = new StringBuilder();
        builder.append(text);

        // Add combining overline character after each character in the text
        for (int i = 0; i < text.length(); i++) {
            builder.append("\u0305"); // U+0305: Combining Overline
        }

        return builder.toString();
    }
}