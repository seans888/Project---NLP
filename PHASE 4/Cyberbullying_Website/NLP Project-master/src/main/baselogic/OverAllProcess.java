package main.baselogic;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javafx.geometry.Pos;
import main.baselogic.Post;
import main.model.Entry;
import main.util.DataConnect;

import javax.faces.bean.ManagedBean;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by Paulo on 3/19/2017.
 */

@ManagedBean
public class OverAllProcess extends Post {


    public String main() throws SQLException, ClassNotFoundException, FileNotFoundException {




      //  Scanner reader = new Scanner(System.in);
        System.out.println("Post:");
        String myString = userEntry;
       // String myString = reader.nextLine();
        // next line has some new errors pls fix
        myString = myString.replaceAll("[^a-zA-Z0-9]", " ");
        System.out.println("Cleaning Output:");
        PrintStream myString1 = new PrintStream(new FileOutputStream ("C:\\Users\\Paulo\\IdeaProjects\\NLP Project-master\\data\\data.txt"));
        System.setOut(myString1);
        System.out.println(myString);

        String delimeter = " ";
        String[] words = myString.split(delimeter);
        String[] trimmedArray = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            trimmedArray[i] = words[i].trim();
        }


        System.out.println("Tokenization Output:");
         for (int i = 0; i < words.length; i++) {
            PrintStream words1 = new PrintStream(new FileOutputStream ("C:\\Users\\Paulo\\IdeaProjects\\NLP Project-master\\data\\data.txt"));
            System.setOut(words1);
            System.out.println(words[i]);

        }

        Set<String> set = new HashSet<>();
        Collections.addAll(set, trimmedArray);
        System.out.println("List of Array:");
        PrintStream set1 = new PrintStream(new FileOutputStream ("C:\\Users\\Paulo\\IdeaProjects\\NLP Project-master\\data\\data.txt"));
        System.setOut(set1);
        System.out.println(set);

return null;
    }
    }