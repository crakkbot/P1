public class Node{
    private String name;
    private double xCord;
    private double yCord;
    private String typ;
    private Node left;
    private Node right;
    private Node parent;

    public Node(String name, double xCord, double yCord, String typ) {
        this.name = name;
        this.xCord = xCord;
        this.yCord = yCord;
        this.typ = typ;
    }
    public Node(Node n) {
        this.name = n.name;
        this.xCord = n.xCord;
        this.yCord = n.yCord;
        this.typ = n.typ;
    }

    public int getLevel() {
        int level = 1; //root level 1
        Node n = this;
        while (n != null && n.parent != null) {
            level++;
            n = n.parent;
        }
        return level;
    }


    public void addNode(Node n){
        //Gerade level Vergleich auf X Koordinate
        if (this.getLevel() % 2 == 0){
            if(xCord <= n.xCord){
                if(left != null){
                    left.addNode(n);
                }
                else{
                    left = new Node(n);
                    left.parent = this;
                }
            }else {
                if(right != null){
                    right.addNode(n);
                }
                else{
                    right = new Node(n);
                    right.parent = this;
                }
            }
        }
        //ungerader Level, Vergleich auf Y Koordinate
        else {
            if(yCord <= n.yCord){
                if(left != null){
                    left.addNode(n);
                }
                else{
                    left = new Node(n);
                    left.parent = this;
                }
            }else {
                if(right != null){
                    right.addNode(n);
                }
                else{
                    right = new Node(n);
                    right.parent = this;
                }
            }
        }

    }
    public String getName() {
        return name;
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

}
