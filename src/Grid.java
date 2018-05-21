
public class Grid{
private  static int numberOfData;
private static int buckets;
private List[][] grid;
    // das Grid ist um diese Werte verschoben um die Rechnung zu vereinfachen, damit sind nur positve Koordinaten drinnen und beim Aulsen muss um diesen Faktor korrigiert werden
private double xStart;
private double yStart;

public Grid(List list){
    double minX = list.getMinX();
    xStart = Math.abs(minX);
    double minY = list.getMinY();
    yStart = Math.abs(minY);
    double maxX = list.getMaxX();
    double maxY = list.getMaxY();
    numberOfData = list.getLengthOfList();
    buckets = (int)Math.ceil(Math.sqrt(numberOfData));
    buckets = 250;
    grid = new List[buckets][buckets];
    double stepsXDir = (Math.abs(minX) + maxX)/buckets;
    double stepsYDir = (Math.abs(minY) + maxY)/buckets;
    createBuckets(stepsXDir,stepsYDir);
    hashPoints(list);


}
private void createBuckets(double stepsXDir, double stepsYDir){
    //create buckets
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                double x1 = i * stepsXDir;
                double x2 = x1 +stepsXDir;
                double y1 = j * stepsYDir;
                double y2 = y1 + stepsYDir;
                grid[i][j] = new List(x1,x2,y1,y2);
            }
    }
}
private void hashPoints(List list){
    Data n = list.getHead();
    while (n != null){
        //Errechne Index und
        //verschiebe X und Y Koordinate ins Positive, da Array von 0 startet
        int i = (int)(xStart + n.getxCord())/buckets;
        int j = (int)(yStart + n.getyCord())/buckets;
        grid[i][j].add(n);
        n = n.getNext();
    }
}
public void analyzeBuckets(){
    int emptyBuckets= 0;
    int maxLentghOfBucket = 0;
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid.length; j++) {
            if(grid[i][j].getLengthOfList() == 0){
                emptyBuckets++;
            }
            if(grid[i][j].getLengthOfList() > maxLentghOfBucket){
                maxLentghOfBucket = grid[i][j].getLengthOfList();
            }
        }

    }
    System.out.println("Number of buckets: " + buckets*buckets);
    System.out.println("Bumber of empty buckets: " + emptyBuckets);
    System.out.println("Max length of a bucket: " + maxLentghOfBucket);
}

}