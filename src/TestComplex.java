import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestComplex {
    public static void main(String[] args) {
        System.out.println("test of complex data structure. 2d Tree:");
        String name = "";
        String typ = "";
        double xCord = 0.0;
        double yCord = 0.0;
        Tree2D t1 = new Tree2D();
        try(Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/data/junctions.csv"), "UTF-8")) {
            scanner.useDelimiter(";|\\n");
            int counter = 0;
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
                    t1.add(new Node(name,xCord,yCord,typ));
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

    }
}
