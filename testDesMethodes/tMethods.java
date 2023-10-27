package testDesMethodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class tMethods {
    static HashMap<String, Double> hashTmethods = new HashMap<>();
    public static int tassert(String path){
        // make sure that the file has the right extension
        if (!path.contains(".java")) {
            return -1;
        }

        int i = 0; // nombre de ligne
        String h;
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                h= scanner.nextLine();
                //enleve toute les espace comme ca les tabulation avant les commentaire ne pose pas prob
                h=h.replaceAll(" ","");
                // if h contains assert that means that there is either a assertEquals,assertFalse,assertThrows
                // and fail is the last case
                if (h.contains("assert") || h.contains("fail")){
                    i++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        return i;
    }
    public static int tmethods(String path){
        if (!path.contains(".java")) {
            return -1;
        }
        int i = 0; // nombre de ligne
        String h;
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                h= scanner.nextLine();
                //enleve toute les espace comme ca les tabulation avant les commentaire ne pose pas prob
                h=h.replaceAll(" ","");
                // if h contains assert that means that there is either a assertEquals,assertFalse,assertThrows
                // and fail is the last case
                if (h.contains("publicvoidtest")){

                    i++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        return i;
    }
    public static String interTropComp(File f) throws ClassNotFoundException {
         File files[];
         if(f.isFile()) {
             if (f.getAbsolutePath().contains(".java") && f.getAbsolutePath().contains("test")) {
                 double tAssertMethod=(double) tassert(f.getAbsolutePath())/tmethods(f.getAbsolutePath());
                 hashTmethods.put(f.getName(),tAssertMethod);
             }
         }
         else{
             files = f.listFiles();
             for (File file : files) {
                 interTropComp(file);
             }
         }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //File mainFolder = new File(args[0]);
        File mainFolder = new File("C:/Users/hiche/OneDrive/Bureau/IFT3913-TP2/jfreechart-master");
        interTropComp(mainFolder);
        int i =0;
        int seuil = 5;
        for (String key: hashTmethods.keySet()) {

            //System.out.println(i+". The class "+ key + " has a "+ hashTmethods.get(key)+" density of assert per method");
            if (hashTmethods.get(key) >= seuil) {
                i++;
                System.out.println(i+". The class "+ key + " has a "+ hashTmethods.get(key)+" density of assert per method");
            }
        }
        double pourcentage = ((double)i/360)*100;
        System.out.println(pourcentage +"% des test on des methods quis ont "+ seuil +" assert par methode ou plus");
    }
}
