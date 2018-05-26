public class List {
    private Data head;
    private Data last;
    private int lengthOfList;
    private double x1, x2, y1, y2;
    private int numberAirports;
    private int numberTrainstations;


    public List(){
    }
    public List(double x1, double x2, double y1, double y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void add(Data d) {
        if (head == null) {
            head = new Data(d);
            last = head;
        } else {
            Data temp = last;
            last = new Data(d);
            temp.setNext(last);
        }
        lengthOfList++;
        if (d.getTyp().equals("AIRPORT")) {
            numberAirports++;
        } else {
            numberTrainstations++;
        }
    }

    public int getLengthOfList() {
        return lengthOfList;
    }

    public void print() {
        Data n = head;
        while (n != null) {
            System.out.println(n.toString());
            n = n.getNext();
        }
    }
    public double getMinX(){
        Data n = head;
        double currentMin = Double.POSITIVE_INFINITY;
        while (n != null) {
            if(n.getxCord() < currentMin){
                currentMin = n.getxCord();
            }
            n = n.getNext();
        }
        return currentMin;
    }
    public double getMaxX(){
        Data n = head;
        double currentMax = Double.NEGATIVE_INFINITY;
        while (n != null) {
            if(n.getxCord() > currentMax){
                currentMax = n.getxCord();
            }
            n = n.getNext();
        }
        return currentMax;
    }

    public double getMinY(){
        Data n = head;
        double currentMin = Double.POSITIVE_INFINITY;
        while (n != null) {
            if(n.getyCord() < currentMin){
                currentMin = n.getyCord();
            }
            n = n.getNext();
        }
        return currentMin;
    }
    public double getMaxY(){
        Data n = head;
        double currentMax = Double.NEGATIVE_INFINITY;
        while (n != null) {
            if(n.getyCord() > currentMax){
                currentMax = n.getyCord();
            }
            n = n.getNext();
        }
        return currentMax;
    }

    public void numberOfPointsinRadius(double xCordP, double yCordP, double radius) {
        int airports = 0;
        int trainstations = 0;
        Data n = head;
        while (n != null) {
            double distance = Math.sqrt(Math.pow(yCordP - n.getyCord(), 2) + Math.pow(xCordP - n.getxCord(), 2));
            if (Math.abs(distance) < radius) {
                if (n.getTyp().equals("AIRPORT")) {
                    airports++;
                } else {
                    trainstations++;
                }
            }
            n = n.getNext();
        }
        System.out.println("Junctions less than " + radius + " units away from x=" + xCordP + " and y=" + yCordP);
        System.out.println("  > Airports: " + airports + "   Trainstations: " + trainstations);
    }

    public int numberOfTrainstationsinRadius(double xCordP, double yCordP, double radius) {
        int trainstations = 0;
        Data n = head;
        while (n != null) {
            double distance = Math.sqrt(Math.pow(yCordP - n.getyCord(), 2) + Math.pow(xCordP - n.getxCord(), 2));
            if (Math.abs(distance) < radius) {
                if (n.getTyp().equals("TRAINSTATION")) {
                    trainstations++;
                }
            }
            n = n.getNext();
        }
        return trainstations;
    }

    public void findNumberOfTrainssationsAroundAirport(int numberTrainstations, double radius) {
        Data n = head;
        int airports = 0;
        while (n != null) {
            if(n.getTyp().equals("AIRPORT")){
                if (numberOfTrainstationsinRadius(n.getxCord(), n.getyCord(), radius) >= numberTrainstations) {
                    airports++;
            }
            }
            n = n.getNext();
        }
        System.out.println("Airports with at least "+ numberTrainstations+" Trainstations less than " +radius+" units away");
        System.out.println("    >"+airports);
    }

    public Data getHead() {
        return head;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public int getNumberTrainstations() {
        return numberTrainstations;
    }

    public int getNumberAirports() {
        return numberAirports;
    }
}
