import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        String name = "";
        String typ = "";
        double xCord = 0.0;
        double yCord = 0.0;
        System.out.println("Test");
        List naivList = new List();
        try(Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/data/junctions.csv"), "UTF-8")) {
            scanner.useDelimiter(";|\\n");
            int counter = 0;
            
            while(scanner.hasNext()){
                //System.out.print(scanner.next()+"|");
                //scanner.next();
               // System.out.println();
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
        System.out.println(naivList.getLengthOfList());
        naivList.print();


    }
}
