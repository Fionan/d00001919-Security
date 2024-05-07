import java.util.Scanner;


public class VigenereCipher {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("What is the mesage?");
        String message = sc.nextLine();
        System.out.println("What is the key?");
        String key = sc.nextLine();
        
        System.out.println("Original Message: " + message);
        System.out.println("Key: " + key);
        
        String encryptedMessage = encrypt(message, key);
        System.out.println("Encrypted Message: " + encryptedMessage);
    }
    
    public static String encrypt(String message, String key) {
        StringBuilder result = new StringBuilder();
        
        // Convert message and key to uppercase for simplicity
        message = message.toUpperCase();
        key = key.toUpperCase();
        
        // Repeat the key to match the length of the message
        StringBuilder repeatedKey = new StringBuilder();
        int keyIndex = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                repeatedKey.append(key.charAt(keyIndex));
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                repeatedKey.append(c);
            }
        }
        
        System.out.println("Repeated Key: " + repeatedKey.toString());
        
        // Encrypt each letter of the message using the Vigenère cipher
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                int messageIndex = c - 'A';
                int keyCharIndex = repeatedKey.charAt(i) - 'A';
                char encryptedChar = (char) (((messageIndex + keyCharIndex) % 26) + 'A');
                result.append(encryptedChar);
                System.out.println("Encrypting '" + c + "' with key '" + repeatedKey.charAt(i) + "': " + encryptedChar);
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
}

