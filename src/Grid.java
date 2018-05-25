
public class Grid {
    private static int numberOfData;
    private static int buckets;
    private List[][] grid;
    // das Grid ist um diese Werte xStart und yStart verschoben um die Rechnung zu vereinfachen,
// damit sind nur positve Koordinaten drinnen und beim Aulesen muss um diesen Faktor korrigiert werden
    private double xStart;
    private double yStart;
    //Schrittweite in den Buckets, jeweils x und y Richtung
    private double xSteps;
    private double ySteps;

    public Grid(List list) {
        //Initialisiere Varibalen im Konstruktor
        double minX = list.getMinX();
        xStart = Math.abs(minX);
        double minY = list.getMinY();
        yStart = Math.abs(minY);
        double maxX = list.getMaxX();
        double maxY = list.getMaxY();
        numberOfData = list.getLengthOfList();
        buckets = (int) Math.ceil(Math.sqrt(numberOfData));
        //buckets = 200;
        grid = new List[buckets][buckets];
        xSteps = (Math.abs(minX) + maxX) / buckets;
        ySteps = (Math.abs(minY) + maxY) / buckets;
        createBuckets();
        hashPoints(list);


    }

    private void createBuckets() {
        //create buckets
        for (int i = 0; i < buckets; i++) {
            for (int j = 0; j < buckets; j++) {
                double x1 = i * xSteps;
                double x2 = x1 + xSteps;
                double y1 = j * ySteps;
                double y2 = y1 + ySteps;
                grid[i][j] = new List(x1, x2, y1, y2);
            }
        }
    }

    private void hashPoints(List list) {
        Data n = list.getHead();
        while (n != null) {
            //Errechne Index und
            //verschiebe X und Y Koordinate ins Positive, da Array von 0 startet
            int i = (int) ((xStart + n.getxCord()) / xSteps);
            int j = (int) ((yStart + n.getyCord()) / ySteps);
            //falls die größten Elemente ausgewählt werden hätte man eine IndexOutOfBoundaryException
            //Ist bei Suche abgedeckt
            if (j == buckets) {
                j--;
            }
            if (i == buckets) {
                i--;
            }
            grid[i][j].add(n);
            n = n.getNext();
        }
    }

    public void analyzeBuckets() {
        int emptyBuckets = 0;
        int maxLenthOfBucket = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].getLengthOfList() == 0) {
                    emptyBuckets++;
                }
                if (grid[i][j].getLengthOfList() > maxLenthOfBucket) {
                    maxLenthOfBucket = grid[i][j].getLengthOfList();
                }
            }
        }
        System.out.println("Number of buckets: " + buckets * buckets);
        System.out.println("Bumber of empty buckets: " + emptyBuckets);
        System.out.println("Max length of a bucket: " + maxLenthOfBucket);
    }

    public int[] numberOfPointsinRadius(double xCordP, double yCordP, double radius) {
        int[] output = new int[2];
        output[0] = 0; // airports
        output[1] = 0; // Trainstation
        //Indizizesberechnung von Suchraster
        int xBucket = (int) ((xCordP + xStart) / xSteps);
        int yBucket = (int) ((yCordP + yStart) / ySteps);
        int iMax = (int) ((xCordP + xStart + radius) / xSteps);
        int jMax = (int) ((yCordP + yStart + radius) / ySteps);
        int xMin = (int) ((xCordP + xStart - radius) / xSteps);
        int yMin = (int) ((yCordP + yStart - radius) / ySteps);
        //Check distance for every hitted bucket
        for (int i = xMin; i <= iMax; i++) {
            for (int j = yMin; j <= jMax; j++) {
                boolean inBoundsI = (i >= 0) && (i < grid.length);
                boolean inBoundsJ = (j >= 0) && (j < grid.length);
                Data n = null;
                if (inBoundsI && inBoundsJ) {
                    n = grid[i][j].getHead();
                    switch (inRadius(xCordP, yCordP, radius, i, j)) {
                        case 1:
                            while (n != null) {
                                if (n.getTyp().equals("AIRPORT")) {
                                    output[0]++;
                                } else {
                                    output[1]++;
                                }
                                n = n.getNext();
                            }
                            break;
                        case 2:
                            while (n != null) {
                                double distance = Math.sqrt(Math.pow(yCordP - n.getyCord(), 2) + Math.pow(xCordP - n.getxCord(), 2));
                                if (Math.abs(distance) < radius) {
                                    if (n.getTyp().equals("AIRPORT")) {
                                        output[0]++;
                                    } else {
                                        output[1]++;
                                    }
                                }
                                n = n.getNext();
                            }
                    }
                }
            }
        }
        return output;
    }

    public void findNumberOfTrainssationsAroundAirport(List list, int numberTrainstations, double radius) {
        Data n = list.getHead();
        int airports = 0;
        while (n != null) {
            if (n.getTyp().equals("AIRPORT")) {
                if (numberOfPointsinRadius(n.getxCord(), n.getyCord(), radius)[1] >= numberTrainstations) {
                    airports++;
                }
            }
            n = n.getNext();
        }
        System.out.println("Airports with at least " + numberTrainstations + " Trainstations less than " + radius + " units away");
        System.out.println("    >" + airports);
    }

    public int inRadius(double xCordP, double yCordP, double radius, int i, int j) {
        int xBucket = (int) ((xCordP + xStart) / xSteps);
        int yBucket = (int) ((yCordP + yStart) / ySteps);
        //Find Intersection-Buckets:
        double dist1 = Math.sqrt(Math.pow(yCordP + yStart - grid[i][j].y1, 2) + Math.pow(xCordP + xStart - grid[i][j].x1, 2));
        double dist2 = Math.sqrt(Math.pow(yCordP + yStart - grid[i][j].y1, 2) + Math.pow(xCordP + xStart - grid[i][j].x2, 2));
        double dist3 = Math.sqrt(Math.pow(yCordP + yStart - grid[i][j].y2, 2) + Math.pow(xCordP + xStart - grid[i][j].x1, 2));
        double dist4 = Math.sqrt(Math.pow(yCordP + yStart - grid[i][j].y2, 2) + Math.pow(xCordP + xStart - grid[i][j].x2, 2));
        if (dist1 < radius && dist2 < radius && dist3 < radius && dist4 < radius) {
            return 1;
        }
        if (dist1 < radius || dist2 < radius || dist3 < radius || dist4 < radius || (i == xBucket && j == yBucket)) {
            return 2;
        }
        if (i == xBucket) {
            return 2;
        }
        if (j == yBucket) {
            return 2;
        }
        return 0;
    }
}
