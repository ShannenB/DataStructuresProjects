//Shannen Barrameda
//CS3345.HON - Dr. Ivor Page
//Homework 3

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
            else if(exists(ky)){
//				1a
//                Node x = head;
//                
//                while(x != null && x.getNext() != null) {
//                    if(x.getNext().getKey() == ky) {
//                        Node next = x.getNext();
//                        x.putNext(next.getNext());
//                    }
//                    else
//                        x = x.getNext();
//
//                
//                }

                Node x = head;
                    while(x != null) {
                        if(x.getKey() == ky) {//1b won't always work if node that needs to be deleted is at end of the list since x.getNext().getNext() == null
                            x.putKey(x.getNext().getKey());                 
                            x.putNext(x.getNext().getNext());
                            return;
                        }
                    x = x.getNext();
                }
            } // end of else
        }
    }//end of delete
    public void recursiveDelete(int ky) {
        recursiveDelete(ky, head);// calls the private version
    }
    private Node recursiveDelete(int ky, Node n){
        
        if(n == null){
            return null;
        }
        
        if(n.getKey() == ky){
           return recursiveDelete(ky,n.getNext());
        }
        else
            n.putNext(recursiveDelete(ky,n.getNext()));
        
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

