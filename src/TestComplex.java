import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestComplex {
    public static void main(String[] args) {
        System.out.println("test of complex data structure. 2d Grid:");
        String name = "";
        String typ = "";
        double xCord = 0.0;
        double yCord = 0.0;
        List naivList = new List();
        try(Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/data/junctions.csv"), "UTF-8")) {
            scanner.useDelimiter(";|\\n");
            int counter = 0;
            int i = 0;
            while(scanner.hasNext()){
                //Initialisiere den Datensatz, wenn alle 4 Variablen belegt sind, wird ein neuer Datensatz erzeugt
                switch (counter){
                    case 0: name = scanner.next();
                        break;
                    case 1: xCord = Double.parseDouble(scanner.next());
                        break;
                    case 2: yCord = Double.parseDouble(scanner.next());
                        break;
                    case 3: typ = scanner.next();
                        break;
                }
                if (counter == 3){
                    naivList.add(new Data(name,xCord,yCord,typ));
                    counter =0;
                    i++;
                }
                else {
                    counter++;
                }
            }

            scanner.close();

        } catch(FileNotFoundException e) {
// junctions.csv wurde nicht gefunden
            System.exit(1);
        }
        Grid g1 = new Grid(naivList);
        g1.analyzeBuckets();
        int[] test1 = g1.numberOfPointsinRadius(0,0,10000);
        System.out.println("  > Airports: " + test1[0]+ "   Trainstations: " + test1[1]);
        int[] test2 = g1.numberOfPointsinRadius(1818.54657,5813.29982,100);
        System.out.println("  > Airports: " + test2[0]+ "   Trainstations: " + test2[1]);
        System.out.println("-----------------Test zweite Abfrage--------------------------");
        long startTime = System.currentTimeMillis();
        g1.findNumberOfTrainssationsAroundAirport(naivList,20,15);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Dauer der Abfrage in Sekunden: " + elapsedTime/1000.0);


    //Liste auf airports filtern, return einfügen und kopierte Methode löschen, Typ als enum, nur auf Intersection buckets prüfen
    }
}
