package PasswordEncryption;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Encrypter {
    public static void main(String[] args) {
        try {
            File file = new File("EncryptedMessage.txt");

            if (file.createNewFile()) {
                System.out.println("Success: file at " + file.getAbsolutePath());
            }

            final String KEY = "secretkey";

            Scanner scanner = new Scanner(System.in);

            if (YN("Would you like to see the secret message? (y/n): ", scanner)) {
                String fileText = new String(Files.readAllBytes(file.toPath()));
                System.out.println(encryptDecryptMessage(fileText, KEY));
            }

            if (YN("Would you like to encrypt a new message? (y/n): ", scanner)) {
                System.out.println("What is the new message?");

                String encryptedMessage = encryptDecryptMessage(scanner.nextLine(), KEY);

                FileWriter fw = new FileWriter(file);
                fw.write(encryptedMessage, 0, encryptedMessage.length());
                fw.close();
            }

            scanner.close();

        } catch (IOException e) {
            System.out.println("An Error Accured: " + e.getMessage());
        }

    }

    //This is where the magic happens
    public static String encryptDecryptMessage(String message, String key) {
        String encryptedMessage = "";
        for (int i = 0; i < message.length(); i++) {
            char c = (char) (message.charAt(i) ^ key.charAt(i % key.length()));
            encryptedMessage += c;
        }
        return encryptedMessage;
    }

    //This function will return t/f depending on if the user input is "y" or "n"
    public static boolean YN(String prompt, Scanner input) {
        System.out.print(prompt);
        String yn = input.nextLine();
        while (!(yn.equalsIgnoreCase("y") || yn.equalsIgnoreCase("n"))) {
            System.out.print(prompt);
            yn = input.nextLine();
        }
        return yn.equalsIgnoreCase("y");
    }
}
