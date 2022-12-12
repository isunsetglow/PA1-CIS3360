/*=============================================================================
| Assignment: pa01 - Encrypting a plaintext file using the Vigenere cipher
|
| Author: Maria Hernandez
| Language: Java
|
| To Compile: javac pa01.java
|
| To Execute: java -> java pa01 kX.txt pX.txt
| where kX.txt is the keytext file
| and pX.txt is plaintext file
|
| Note: All input files are simple 8 bit ASCII input
|
| Class: CIS3360 - Security in Computing - Fall 2022
| Instructor: McAlpin
| Due Date: per assignment
|
+=============================================================================*/

import java.io.*;
import java.io.FileNotFoundException;
// aka pa01 - CIS3360
public class CSVfile {
    public static void main(String[] args) {
        String s1 = args[0]; // key
        String s2 = args[1]; // plaintext

        char pX[] = new char[512]; // plaintext array length == 512
        int msgLen = pX.length; // message text == 512
        char kX[] = new char[msgLen]; // key length == 512

        String key = String.valueOf(kX); // converting key array into string
        String pt = String.valueOf(pX); // converting plaintext array into string

        key = fileToString(s1); // key is stored
        pt = fileToString(s2); // plaintext is stored

        // patching with 'x'
        for (int i = pt.length(); i < msgLen; i++) {
            if (i < 512) {
                pt += 'x'; // plaintext patches x to the remainder of the characters
            }
        }

        System.out.println("\n\nVigenere Key: \n");
        newLine(key, key.length());

        System.out.println("\n\nPlaintext: \n");
        newLine(pt, msgLen);

        key = key.toUpperCase();
        pt = pt.toUpperCase();

        encrypt(pt, key, msgLen);
    }

    // method outputs encryption of the plaintext
    public static void encrypt(String pt, String key, int length) {
        // int length is 512
        char msg[] = pt.toCharArray(); // plaintext string to array

        char kX[] = key.toCharArray(); // key string to array
        char newK[] = new char[length]; // key length converted to plaintext length

        char ctxt[] = new char[length]; // ciphertext, length 512

        String newKey = newK.toString(); // new key to string

        // cycling
        for (int i = 0, j = 0; i < length; i++, j++) {
            if (j == kX.length)
                j = 0;
            newK[i] = kX[j];
        }

        // encrypting
        for (int i = 0; i < length; i++) {
            ctxt[i] = (char) (((msg[i] + newK[i]) % 26) + 'A');
        }

        // converting cipher text to lowercase
        newKey = newKey.toLowerCase();
        pt = pt.toLowerCase();

        // converting ciphertext array into string
        String cX = String.valueOf(ctxt);
        cX = cX.toLowerCase();

        System.out.println("\n\nCiphertext: \n");
        newLine(cX, length);
    }

    // method outputs message within 80 characters per line
    public static void newLine(String message, int length) {
        // int length is 512
        for (int i = 0, size = length; i < size; i += 80) {
            System.out.println(message.substring(i, Math.min(i + 80, size)));
        }
    }

    // method that opens files and converts them to lowercase if necessary
    public static String fileToString(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            int line = br.read();
            String plaintext = "";
            while (line != -1) {
                // plaintext += line; // plaintext stores file
                if (Character.isAlphabetic((char) line)) {
                    plaintext += (char) line;
                }
                // System.out.println(line+"\t");
                line = br.read();

            }
            plaintext = plaintext.toLowerCase();
            br.close();
            return plaintext;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

/*
 * =============================================================================
 * | I Maria Hernandez (5336239) affirm that this program is
 * | entirely my own work and that I have neither developed my code together
 * with
 * | any another person, nor copied any code from any other person, nor
 * permitted
 * | my code to be copied or otherwise used by any other person, nor have I
 * | copied, modified, or otherwise used programs created by others. I
 * acknowledge
 * | that any violation of the above terms will be treated as academic
 * dishonesty.
 * +============================================================================
 * =
 */