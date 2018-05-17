public class Data {
    private String Name;
    private double xCord;
    private double yCord;
    private String typ;
    private Data next;

    public Data(String name, double xCord, double yCord, String typ) {
        Name = name;
        this.xCord = xCord;
        this.yCord = yCord;
        this.typ = typ;
    }
    public Data(Data d){
        this.Name = d.Name;
        this.xCord = d.xCord;
        this.yCord = d.yCord;
        this.typ = d.typ;
    }

    @Override
    public String toString() {
        return "Data{" +
                "Name='" + Name + '\'' +
                ", xCord=" + xCord +
                ", yCord=" + yCord +
                ", typ='" + typ + '\'' +
                '}';
    }

    public String getName() {
        return Name;
    }


    public double getxCord() {
        return xCord;
    }

    public double getyCord() {
        return yCord;
    }


    public String getTyp() {
        return typ;
    }


    public Data getNext() {
        return next;
    }

    public void setNext(Data next) {
        this.next = next;
    }
}
