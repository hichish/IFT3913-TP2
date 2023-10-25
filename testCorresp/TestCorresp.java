import java.io.File;

public class TestCorresp {
    static File testFolder;
    public static void main(String[] args) {
      

        String projectPath = args[0];
        
        File mainFolder = new File(projectPath + "/src/main/java");
        File testFolder = new File(projectPath + "/src/test");

        int totalClasses = 0;
        int classesWithTests = 0;

        totalClasses = countJavaFiles(mainFolder);

        classesWithTests = countCorrespondingTestFiles(testFolder, mainFolder);
        double percentage = totalClasses == 0 ? 100 : (double) classesWithTests / totalClasses * 100;
        System.out.printf("Pourcentage des classes du code sources avec des class tests correspondantes: %.2f%%\n", percentage);
    }

    private static int countJavaFiles(File directory) {
        File[] files = directory.listFiles();
        int count = 0;
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".java")) {
                    count++;
                } else if (file.isDirectory()) {
                    count += countJavaFiles(file);
                }
            }
        }
        return count;
    }

  

    public static boolean hasCorrespondingTestClass(String fileName, File mainDirectory) {
        
        File[] files = mainDirectory.listFiles();
       Boolean foundRecursively = false;
        for (File file : files ) {
            
            
                if (file.isFile()) {
                    if (file.getName().contains(fileName)) {
                        foundRecursively = true;
                    };
                } else if (file.isDirectory()) {
                    foundRecursively = hasCorrespondingTestClass(fileName, file);
                }
            
        }

        return foundRecursively;
    }

     public static int countCorrespondingTestFiles(File testDirectory, File mainDirectory) {
        File[] files = mainDirectory.listFiles();
        int count = 0;
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".java")) {
                    int lastIndex = file.getName().lastIndexOf(".");
                    String fileNameWithoutExtension = (lastIndex == -1) ? file.getName() : file.getName().substring(0, lastIndex);
                    if (hasCorrespondingTestClass(fileNameWithoutExtension, testDirectory)) count++;
                } else if (file.isDirectory()) {
                    count += countCorrespondingTestFiles(testDirectory, file);
                }
            }
        }
        return count;
    }
}
