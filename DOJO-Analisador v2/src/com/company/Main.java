package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException   {

        File file = new File(System.getProperty("user.dir")+"\\jquery-3.4.1.js");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String entry;
        String fullEntry = "";
        while ((entry = br.readLine()) != null) {
            String stTrim = entry.trim();
            if(stTrim.startsWith("//")) continue;
            if(stTrim.contains("//")) {
                stTrim = stTrim.substring(0, stTrim.indexOf("//")-1);
            }
            fullEntry += stTrim;
        }

        fullEntry = fullEntry.replaceAll("\\/\\*[\\s\\S]*?\\*\\/|([^\\\\:]|^)\\/\\/.*|<!--[\\s\\S]*?-->$", "");

        //System.out.println(full);
        List<String> allLines = Arrays.asList(fullEntry);
        Path fullFile = Paths.get(System.getProperty("user.dir")+"\\minifiedJquery.js");
        Files.write(fullFile, allLines, StandardCharsets.UTF_8);

        try {
            System.out.println("-------------entering  readMethod-------------");
            String readFile = readConvertedFile(System.getProperty("user.dir")+"\\minifiedJquery.js");

            Lexer lexer = new Lexer();
            System.out.println("-------------entering    tokenize-------------");
            lexer.tokenize(readFile);

            System.out.println("-------------entering   getTokens-------------");
            List<Token> allTokens = lexer.getTokens();

            System.out.println("-------------entering writeMethod-------------");
            writeConvertedFile(allTokens);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    private static String readConvertedFile(String stringPath) throws IOException {
        StringBuffer fullFile = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(stringPath));
        char[] buf = new char[1024];
        int count = 0;

        while ((count = reader.read(buf)) != -1) {
            String fileRead = String.valueOf(buf, 0, count);
            fullFile.append(fileRead);
            buf = new char[1024];
        }

        reader.close();
        return fullFile.toString();
    }

    private static void writeConvertedFile(List<Token> tokens) throws IOException {
        FileWriter writer = new FileWriter("lexicalAnalysis.txt");

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            writer.write(token.toString() + "\r\n");
        }

        writer.close();
    }
}
