package lists;

public class LinkedList<T>{

    class Node{

        public Object data;
        public Node next;

        public boolean hasNext(){
            if (this.next != null) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    private Node head;
    private int length;


    public LinkedList(){
        this.length = 0;
        head = null;
    }

    public int length(){
        return this.length;
    }

    public void add(T object){
        Node node = new Node();
        node.data = object;
        node.next = this.head;
        this.head = node;
        this.length ++;
    }

    public T get(int index) throws Exception{

        Node head = this.head;

        for (int i=0; i<this.length(); i++) {
            if (i == index) {
                return (T) head.data;
            }
            else{
                head = head.next;
            }
        }
        throw new Exception("Item not found.");
    }

}


