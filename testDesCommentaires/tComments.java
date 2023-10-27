package testDesCommentaires;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class tComments {
    static HashMap<String, Double> hashComments = new HashMap<>();
    public static double tloc(String path) {
        if (!path.contains(".java")) {
            return -1;
        }

        int i = 0; // nombre de ligne
        String h= "";
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                h= scanner.nextLine();
                //enleve toute les espace comme ca les tabulation avant les commentaire ne pose pas prob
                h=h.replaceAll(" ","");
                // if h.indexOf("//")== 0 that means this line is a comment, because it starts with "//"
                // if the line is equal to "" that means it's empty, because we removed all the spaces
                // if h.indexOf("/*")== 0 it starts with a long comment
                // if h.indexOf("*") == 0 it's also a comment
                if (h.indexOf("//") != 0 && h.indexOf("/*") != 0 && !Objects.equals(h, "") && h.indexOf("*") != 0) {
                    i++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        return i;
    }
    public static double tcomment(String path){
        if (!path.contains(".java")) {
            return -1;
        }

        int i = 0; // nombre de ligne
        String h= "";
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                h= scanner.nextLine();
                //enleve toute les espace comme ca les tabulation avant les commentaire ne pose pas prob
                h=h.replaceAll(" ","");
                // if h.indexOf("//")== 0 that means this line is a comment, because it starts with "//"
                // if the line is equal to "" that means it's empty, because we removed all the spaces
                // if h.indexOf("/*")== 0 it starts with a long comment
                // if h.indexOf("*") == 0 it's also a comment
                if (h.indexOf("//") == 0 || h.indexOf("/*") == 0 || h.indexOf("*") == 0) {
                    i++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        return i;
    }
    public static String intermediaire(File f) throws ClassNotFoundException {
         File files[];
         if(f.isFile()) {
             if (f.getAbsolutePath().contains(".java") && f.getAbsolutePath().contains("src/test")) {
                 double pourcentage = tcomment(f.getAbsolutePath())/(tcomment(f.getAbsolutePath())+tloc(f.getAbsolutePath()));
                 hashComments.put(f.getName(),pourcentage*100);
             }
         }
         else{
             files = f.listFiles();
             for (File file : files) {
                 intermediaire(file);
             }
         }
        return null;
    }
    public static void main(String[] args) throws ClassNotFoundException {
        File mainFolder = new File(args[0]);
        intermediaire(mainFolder);
        int i=0;
        int seuil = 25;

        for (String key: hashComments.keySet()) {
//            i++;
//            System.out.println(i+". The class "+key+"has "+hashComments.get(key)+"% comments");
            if (hashComments.get(key) < seuil) {
                i++;
                System.out.println(i+". The class "+key+"has "+hashComments.get(key)+"% comments");
            }
        }
        double pourcentage = ((double)i/360)*100;
        System.out.println("There are "+i+" classes that less than 25% of their code " +
                "are comments that means that they are not well documented, that means that" +
                " "+pourcentage+"% of classes inside the project are not well documented");
    }
}
