package SHBAP1;

class Node {
    private Node next;
    private int key;
    Node(Node nxt, int keyValue){ // constructor
        next = nxt;
        key = keyValue;
    }
    Node getNext(){
        return next;
    }
    int getKey(){
        return key;
    }
    void putNext(Node nxt){
        next = nxt;
    }
    
    void putKey(int ky){
        key = ky;
    }
}
class List { // assume the class does not use a dummy Node
    private Node head;
    
    List(){
        // constructor
    }
    boolean exists(int ky){         // returns true if ky is in the list
       Node current = head;

       while(current != null){
           if(current.getKey() == ky){
               return true;
           }
           else{
               current = current.getNext();
           }
       }
       return false;
    }
    void insertAtHead(int ky){ // inserts at the beginning of the list
        Node newNode = new Node(null, ky);
        
        newNode.putNext(head);
        
        head = newNode;
    }
    void insertAtTail(int ky){ // inserts at the end of the list
        Node newNode = new Node(null,ky);
        
        if(head == null){
            head = new Node(null,ky);
            return;
        }
        
        newNode.putNext(null);
        
        Node last = head;
        
        while(last.getNext() != null){
            
            last = last.getNext();
        }
        
        last.putNext(newNode);
        return;
    }
    

    int removeFromHead(){ // Returns -1 if the list is empty
        if(head == null){
            return -1;
        }
        else{
            
            head = head.getNext();
            
            return 0;
        }
    }
    
    public String toString(){
        Node current = head;
        String str = "";
        while(current != null){
            str += current.getKey() + "\n";
            current = current.getNext();
        }
        
        return str;
    }
    
    
    void delete(int ky){ // delete the element, do nothing if ky doesn’t exist  
        if(head!=null) {
            if(head.getKey()==ky) // the first Node contains ky
                head = head.getNext();
            else {
                Node x = head;
                Node prev;
//                while(x != null && x.getNext() != null) {
//                    if(x.getNext().getKey() == ky) {//1a
//                        Node next = x.getNext();
//                        x.putNext(next.getNext());
//                    }
//                    else
//                        x = x.getNext();

                
                }
            } // end of else
        }

    }//end of delete
    public void recursiveDelete(int ky) {
        recursiveDelete(ky, head);// calls the private version
    }
    private Node recursiveDelete(int ky, Node n){
        return n;
    }

    int maxElement(){ // calls the private version, doesn’t delete the Node
        return maxElement(head);
    }
    
    int sum(){ // calls the private version
        return sum(head);
    }
    int length(){
        return length(head);
    }// calls the private version
    private int maxElement(Node x){
        
        if(x.getNext() == null){
            return x.getKey();
        }
        else{
            int max = maxElement(x.getNext());
            if(x.getKey() <= max) {
                return max;
            }
            else return x.getKey();
        }
        
        
    }
    private int sum(Node x){
        if(x.getNext() == null){
            return x.getKey();
        }
        else{
            return x.getKey() + sum(x.getNext());
        }
        
    }
    private int length(Node x){
        if(x.getNext() == null){
            return 1;
        }
        else{
           return 1 + length(x.getNext()); 
        }
    }
}

class Homework2{
    public static void main(String[] args){
        List list = new List();
        
        list.insertAtHead(2);
        list.insertAtHead(-1);
        list.insertAtHead(-40);
        list.insertAtHead(40);
        
        System.out.println(list.toString());
        
        list.removeFromHead();
        list.insertAtHead(1000000);
        list.insertAtTail(200000000);
        System.out.println(list.toString());
//        list.delete(20);
//        list.delete(2);
        System.out.println(list.toString());
        System.out.println("max element: " + list.maxElement() + "sum: " + list.sum() + "length: " +list.length());
    }
    
}
