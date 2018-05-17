public class List {
    private Data head;
    private int lengthOfList;

    public void  add(Data d){
        if(head == null){
            head = new Data(d);
        }
        else {
            Data temp = head;
            head = new Data(d);
            head.setNext(temp);
            }
            lengthOfList++;
        }

    public int getLengthOfList() {
        return lengthOfList;
    }
    public void print(){
        Data n = head;
        while (n != null){
            System.out.println(n.toString());
            n = n.getNext();
        }
    }
}
