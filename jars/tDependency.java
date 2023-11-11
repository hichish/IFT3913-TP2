import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class tDependency {

    static HashMap<String, Double> dependency = new HashMap<>();

    public static int tdepend(String path){
        String nameDependency;
        double occurence;
        if (!path.contains(".java")) {
            return -1;
        }
        int i = 0; // nombre de ligne
        String h;
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()){
                occurence = 0;
                h= scanner.nextLine();
                //enleve toute les espace comme ca les tabulation avant les commentaire ne pose pas prob
                h=h.replaceAll(" ","");
                if (h.contains("import") && h.contains("org")){
                    nameDependency = h.replaceAll("import","");
                    if (dependency.get(nameDependency) != null) {
                        occurence = dependency.get(nameDependency);
                        dependency.put(nameDependency,occurence+1);
                    }
                    else {
                        dependency.put(nameDependency,occurence+1);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        return i;
    }
    public static String testDependencies(File f) throws ClassNotFoundException {
         File files[];
         if(f.isFile()) {
             if (f.getAbsolutePath().contains(".java") && f.getAbsolutePath().contains("test")) {
                 tdepend(f.getAbsolutePath());
             }
         }
         else{
             files = f.listFiles();
             for (File file : files) {
                 testDependencies(file);
             }
         }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        File mainFolder = new File(args[0]);
        //File mainFolder = new File("/Users/ahmadbary/IFT3913-TP2/jfreechart-master");
        testDependencies(mainFolder);
        int i =0;
        int seuil = 10;
        int nombreFichierProject=359;
        for (String key: dependency.keySet()) {
            if (dependency.get(key) >= seuil) {
                i++;
                //System.out.println("the dependancy "+key.substring(0,key.length()-1) +" is used "+dependency.get(key)+" times" );
            }
            //System.out.println("the dependancy "+key.substring(0,key.length()-1) +" is used "+dependency.get(key)+" times" );
        }
        //System.out.println("Il y a "+i+" classe qui sont utiliser plus que "+seuil+" fois ou plus");
        double pourcentage =  ((double)i/nombreFichierProject)*100;
        //System.out.println((int)pourcentage+"% des classes du projet");
        //System.out.println("Test de Dependence: "+(int)pourcentage+"%");
        System.out.println("testDependency: ");
        System.out.println(i+" classes ce qui represente "+(int)pourcentage+"% des classes du projet");
    }
}
