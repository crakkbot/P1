public class List {
    private Data head;
    private int lengthOfList;

    public void add(Data d) {
        if (head == null) {
            head = new Data(d);
        } else {
            Data temp = head;
            head = new Data(d);
            head.setNext(temp);
        }
        lengthOfList++;
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
}
