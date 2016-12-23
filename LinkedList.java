/* Names: Alex Graves and Caroline Lu
 * PennKeys: agrav and cmlu
 * Recitation: 219
 *
 * Execution: implements List to create methods
 * for the LinkedList class, such as add, size,
 * and remove; these methods may be used in the
 * implementation of our project
 *
 */

public class LinkedList<T> implements List<T> {
    private class Node {
        
        private Node next;
        private T object;
        
        /* Description: constructs a node
         * 
         * Input: a given point
         * Output: constructs node
         */
        private Node(T x) {
            object = x;
        }
    }
    
    //declare field head to hold the first Node
    private Node head;
    
    /* Description: adds the object x to the end
     * of the list
     * 
     * ALEX
     * 
     * Input: a T element to be added
     * Output: true when added
     */
    public boolean add(T x) {
        Node n = new Node(x);
        
        //account for empty list
        if (head == null) {
            head = n;
            head.next = null;
            return true;
        } else {
            //find end of list and add point
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = n;
            current.next.next = null;
            return true;
        }
    }
    
    /* Description: adds the object x at the
     * specified position
     * 
     * ALEX
     * 
     * Input: the position to add the element,
     * and a T element to be added
     * Output: true if added
     */
    public boolean add(int index, T x) {
        if (index < 0) {
            throw new IllegalArgumentException("ERROR: index cannot be negative");
        } else if (index > size()) {
            throw new IllegalArgumentException("ERROR: index is greater than size");
        }
        
        Node n = new Node(x);
        
        //account for if list is empty
        if (head == null) {
            head = n;
            head.next = null;
            return true;
        }
        
        //account for if list has one entry
        if (head.next == null) {
            if (index == 0) {
                Node store = head;
                head = n;
                head.next = store;
                return true;
            }
            if (index == 1) {
                head.next = n;
                head.next.next = null;
                return true;
            }
        }
        
        //account for index is 0
        if (index == 0) {
                Node store = head;
                head = n;
                head.next = store;
                return true;
            }
        
        //find location of index
        Node current = head;
        int count = 0;
        while (current.next != null) {
            if (count + 1 == index) {
                break;
            }
            count++;
            current = current.next;
        }
        
        //if index is at end, add point there
        Node place = current;
        if (place.next == null) {
            place.next = new Node(x);
            place.next.next = null;
            return true;
        }
        
        //move other points one node forward and insert point
        Node store = place.next;
        Node add = new Node(x);
        place.next = add;
        add.next = store;
        return true;
    }
    
    /* Description: returns the number of elements
     * in this list
     * 
     * CAROLINE
     * 
     * Input: none
     * Output: number of elements in the list
     */
    public int size() {
        //int variable count to store number of elements
        int count = 0;
        
        //loop through linked list
        Node curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        
        return count;
    }
    
    /* Description: returns the element with the
     * specified position in this list
     * 
     * ALEX
     * 
     * Input: a given position
     * Output: the element at the position
     */
    public T get(int index) {
        //if index is negative, throw IllegalArgumentException
        if (index < 0) {
            throw new IllegalArgumentException("ERROR: index cannot be negative");
        }
        
        //if index is greater than or equal to size, throw IllegalArgumentException
        else if (index >= size()) {
            throw new IllegalArgumentException("ERROR: index is greater than size");
        }
        
        //find location of index
        int count = 0;
        Node current = head;
        while (current.next != null) {
            if (count == index) {
                break;
            }
            count++;
            current = current.next;
        }
        
        return current.object;
    }
    
    /* Description: replaces the object at the
     * specified position
     * 
     * CAROLINE
     * 
     * Input: a given position and an element to
     * be stored
     * Output: the previous value of the element
     * at the given index
     */
    public T set(int index, T x) {
        //if index is negative, throw IllegalArgumentException
        if (index < 0) {
            throw new IllegalArgumentException("ERROR: index cannot be negative");
        }
        
        //if index is greater than size, throw IllegalArgumentException
        else if (index >= size()) {
            throw new IllegalArgumentException("ERROR: index is greater than size");
        }
        
        else {
            //Node to be returned
            Node replaced;
            
            if (index == 0) {
                replaced = new Node(head.object);
                head.object = x;
                return replaced.object;
            }
            
            //loop through linked list to get Node at index
            Node curr = head;
            int count = 0;
            while (count != index) {
                if (count + 1 == index) {
                    break;
                }
                count++;
                curr = curr.next;
            }
            
            //replace curr with x
            replaced = new Node(curr.next.object);
            curr.next.object = x;
            
            return replaced.object;
        }
    }
    
    /* Description: removes the object at the
     * specified position
     * 
     * CAROLINE
     * 
     * Input: a given position
     * Output: the object that was removed
     */
    public T remove(int index) {
        //if index is negative, throw IllegalArgumentException
        if (index < 0) {
            throw new IllegalArgumentException("ERROR: index cannot be negative");
        }
        
        //if index is greater than or equal to size, throw IllegalArgumentException
        else if (index >= size()) {
            throw new IllegalArgumentException("ERROR: index is greater than size");
        }
        
        else {
            //Node to be returned
            Node removed;
            
            //account for removing from beginning
            if (index == 0) {
                removed = new Node(head.object);
                head = head.next;
                return removed.object;
            }
            
            //loop through linked list to get Node at index
            Node curr = head;
            int count = 0;
            while (count != index) {
                if (count + 1 == index) {
                    break;
                }
                count++;
                curr = curr.next;
            }
            
            //save curr as removed
            removed = new Node(curr.next.object);
            
            //account for removing from end
            if (index == size() - 1) {
                curr.next = null;
            }
            
            //update curr to skip node at index
            else {
                curr.next = curr.next.next;
            }
            
            return removed.object;
        }
    }
    
    /* Description: tests if the list has no elements
     * 
     * ALEX
     * 
     * Input: none
     * Output: true if the list has no elements;
     * false otherwise
     */
    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }
    
    /* Description: finds if the list contains the
     * specified element
     * 
     * ALEX
     * 
     * Input: a given element
     * Output: true if the list contains the element;
     * false otherwise
     */
    public boolean contains(T element) {
        //account for list with one node
        if (head.next == null) {
            if (head.object == element) {
                return true;
            } else {
                return false;
            }
        }
        
        Node current = head;
        
        //parse through list to try to find the element
        while (current.next != null) {
            if (current.object == element) {
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    /* Description: returns the index of the specified
     * element
     * 
     * CAROLINE
     * 
     * Input: a given element
     * Output: the index of the element in the list or
     * -1 if it is not contained within the list
     */
    public int indexOf(T element) {
        //int variable index to be returned
        int index = 0;
        boolean present = false;
        
        //loop through entire linked list
        Node curr = head;
        while (curr != null) {
            //compare current node's value and element
            if (curr.object == element) {
                present = true;
                break;
            }
            index++;
            curr = curr.next;
        }
        
        if (present == true) {
            return index;
        } else {
            return -1;
        }
    }
    
    private String t = "hello";
    
    public static void main(String[] args) {
        String one = "hello";
        String two = "sup";
        String three = "?";
        String replace = "hi";
        LinkedList<String> test = new LinkedList<String>();
        
        /*
        System.out.println(test.add(two));
        System.out.println(test.add(three));
        System.out.println(test.add(0, one));
        System.out.println(test.get(0));
        System.out.println(test.get(1));
        System.out.println(test.get(2));
        System.out.println(test.set(0, replace));
        System.out.println(test.size());
        System.out.println(test.get(0));
        System.out.println(test.get(1));
        System.out.println(test.get(2));
        */
    }
}