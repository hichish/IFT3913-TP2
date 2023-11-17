import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class trpCompCsv {
    public static void main(String[] args) {
        String csvFile = "jfreechart-test-stats.csv";

        List<DataEntry> dataList = new ArrayList<>();
        List<DataEntry> dataList1 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    String classPath = parts[0].trim();
                    int tloc = Integer.parseInt(parts[1].trim());
                    int wmc = Integer.parseInt(parts[2].trim());
                    int tassert = Integer.parseInt(parts[3].trim());

                    double tcmp = (double) tloc / tassert;
//                    if (tassert > 20 ) {
//                        dataList.add(new DataEntry(classPath, tloc, wmc, tassert, tcmp));
//                    }
//                    else{
//                        dataList1.add(new DataEntry(classPath, tloc, wmc, tassert, tcmp));
//                    }
                    dataList.add(new DataEntry(classPath, tloc, wmc, tassert, tcmp));

                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tropComp(dataList);
    }
    public static void tropComp(List<DataEntry> name){
        // Sort by TLOC in ascending order
        List<DataEntry> sortedByTloc = name.stream()
                .sorted(Comparator.comparingInt(DataEntry::getTloc))
                .collect(Collectors.toList());

        // Sort by TCMP in ascending order
        List<DataEntry> sortedByTcmp = name.stream()
                .sorted(Comparator.comparingDouble(DataEntry::getTcmp))
                .collect(Collectors.toList());

        // Find the top 10% common between both
        int top10Percent = (int) (0.2 * Math.min(sortedByTloc.size(), sortedByTcmp.size()));
        int numberSkip = sortedByTloc.size()-top10Percent;
        for (int i = 0; i < numberSkip; i++) {
            sortedByTcmp.remove(0);
            sortedByTloc.remove(0);
        }


        List<DataEntry> commonEntries = new ArrayList<>();

        for (int i = 0; i < top10Percent; i++) {
            DataEntry tlocEntry = sortedByTloc.get(i);
            if (sortedByTcmp.contains(tlocEntry)) {
                commonEntries.add(tlocEntry);
            }
        }

        // Print the common entries
        //System.out.println("Top 5% common entries between TLOC and TCMP:");
        int j = 0; //+20
        int i = 0; //-20
        for (DataEntry entry : commonEntries) {
            if(entry.getTassert()>20){
                j++;
            }
            else {i++;}

        }
        System.out.println("The number of classes considered complexe that have more than 20 assertions is: "+j);
        System.out.println("The number of classes considered complexe that have less than 20 assertions is: "+i);
    }
}

class DataEntry {
    private String classPath;
    private int tloc;
    private int wmc;
    private int tassert;
    private double tcmp;

    public DataEntry(String classPath, int tloc, int wmc, int tassert, double tcmp) {
        this.classPath = classPath;
        this.tloc = tloc;
        this.wmc = wmc;
        this.tassert = tassert;
        this.tcmp = tcmp;
    }

    public String getClassPath() {
        return classPath;
    }

    public int getTloc() {
        return tloc;
    }

    public int getWmc() {
        return wmc;
    }

    public int getTassert() {
        return tassert;
    }

    public double getTcmp() {
        return tcmp;
    }
}
