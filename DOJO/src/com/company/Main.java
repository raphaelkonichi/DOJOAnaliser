package com.company;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException  {

//        try {
//            String inputText;
//            String entry = "";
//
//            File myFile = new File("C:\\Users\\Raphael\\Desktop\\Jquery.txt");
//            Scanner myScanner = new Scanner(myFile);
//            while (myScanner.hasNextLine()){
//                entry += myScanner.nextLine();
//            }
//
//            String[] removeSpaces = entry.split("\\s+|\\t+");
//
//            for (int i = 0; i < removeSpaces.length; i++){
//                //removeSpaces[i] = removeSpaces[i].replaceAll("(?:/\\\\*(?:[^*]|(?:\\\\*+[^*/]))*\\\\*+/)|(?://.*)", "");
//                removeSpaces[i] = removeSpaces[i].replaceAll("(^(\\/\\*+[\\s\\S]*?\\*\\/)|(\\/\\*+.*\\*\\/)|\\/\\/.*?[\\r\\n])[\\r\\n]*", "");
//                removeSpaces[i] = removeSpaces[i].replaceAll("","");
//            }
//
//            String str = String.join("", removeSpaces);
//
//            FileWriter fw = new FileWriter(myFile.getAbsoluteFile());
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(str);
//            bw.close();
//
//
//        } catch (Exception ex) {
//            System.out.println("Exception: "+ ex.getMessage());
//        }

        File file = new File(System.getProperty("user.dir")+"\\src\\com\\company\\jquery-3.4.1.js");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        String full = "";
        while ((st = br.readLine()) != null) {
            String stTrim = st.trim();
            if(stTrim.startsWith("//")) continue;
            if(stTrim.contains("//")) {
                stTrim = stTrim.substring(0, stTrim.indexOf("//")-1);
            }
            full += stTrim;
        }

        full = full.replaceAll("\\/\\*[\\s\\S]*?\\*\\/|([^\\\\:]|^)\\/\\/.*|<!--[\\s\\S]*?-->$", "");

        System.out.println(full);
        List<String> lines = Arrays.asList(full);
        Path fullFile = Paths.get(System.getProperty("user.dir")+"\\src\\com\\company\\minifiedJquery.js");
        Files.write(fullFile, lines, StandardCharsets.UTF_8);
    }
}
