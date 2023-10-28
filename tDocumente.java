import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class tDocumente {
    static HashMap<String, Double> hashComments = new HashMap<>();
    public static double tcomment(String path){
        if (!path.contains(".java")) {
            return -1;
        }
        int seuil =10;

        int i = 0; // nombre de ligne
        String h= "";
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (seuil>0){
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
                seuil--;

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
             if (f.getAbsolutePath().contains(".java") && f.getAbsolutePath().contains("test")) {
                 double pourcentage = tcomment(f.getAbsolutePath());
                 hashComments.put(f.getName(),pourcentage);
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
        int nombreFichierProject=359;

        for (String key: hashComments.keySet()) {
//            i++;
//            System.out.println(i+". The class "+key+"has "+hashComments.get(key)+"% comments");
            if (hashComments.get(key) == 10) {
                i++;
                //System.out.println(i+". The class "+key+"has "+hashComments.get(key)+"% comments");
            }
        }
        double pourcentage = ((double)i/nombreFichierProject)*100;
        System.out.println("testDocumente: ");
        System.out.println(i+" classes ce qui represente "+(int)pourcentage+"% des classes du projet");
    }
}
