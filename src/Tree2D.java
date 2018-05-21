public class Tree2D{
    private Node root;

    public void add(Node node){
        if(root == null){
            root = new Node(node);
        }
        root.addNode(node);

    }
}
