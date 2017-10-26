package apps;

import java.util.Iterator;
import java.util.NoSuchElementException;

import structures.Vertex;


public class PartialTreeList implements Iterable<PartialTree> {
    
	/**
	 * Inner class - to build the partial tree circular linked list 
	 * 
	 */
	public static class Node {
		/**
		 * Partial tree
		 */
		public PartialTree tree;
		
		/**
		 * Next node in linked list
		 */
		public Node next;
		
		/**
		 * Initializes this node by setting the tree part to the given tree,
		 * and setting next part to null
		 * 
		 * @param tree Partial tree
		 */
		public Node(PartialTree tree) {
			this.tree = tree;
			next = null;
		}
	}

	/**
	 * Pointer to last node of the circular linked list
	 */
	private Node rear;
	
	/**
	 * Number of nodes in the CLL
	 */
	private int size;
	
	/**
	 * Initializes this list to empty
	 */
    public PartialTreeList() {
    	rear = null;
    	size = 0;
    }
    
    

    /**
     * Adds a new tree to the end of the list
     * 
     * @param tree Tree to be added to the end of the list
     */
    public void append(PartialTree tree) {
    	Node ptr = new Node(tree);
    	if (rear == null) {
    		ptr.next = ptr;//the purpose of this statement?
    	} else {
    		ptr.next = rear.next;
    		rear.next = ptr; //the purpose of this statement?
    	}
    	rear = ptr;
    	size++;
    }

    /**
     * Removes the tree that is at the front of the list.
     * 
     * @return The tree that is removed from the front
     * @throws NoSuchElementException If the list is empty
     */
    public PartialTree remove() 
    throws NoSuchElementException {
    	
    	/* COMPLETE THIS METHOD */
    	
    	PartialTree toReturn= null;
    		
    	if (rear == null){
    		
    		throw new NoSuchElementException();
    	}
    	
    	if (rear == rear.next){
    		
    		 toReturn = rear.tree;
    		rear=null;
    		
    	}else{
    		
    		 toReturn = rear.next.tree;
    		rear.next = rear.next.next;
    		
    	}
    	size--;
    		return toReturn;
    }

    /**
     * Removes the tree in this list that contains a given vertex.
     * 
     * @param vertex Vertex whose tree is to be removed
     * @return The tree that is removed
     * @throws NoSuchElementException If there is no matching tree
     */
   
	public PartialTree removeTreeContaining(Vertex vertex) 
    throws NoSuchElementException {
		/*COMPLETE THIS METHOD */
		
		if (rear==null){
			
			throw new NoSuchElementException();
		}
    	boolean lastNode =rear.tree.getRoot()== vertex.getRoot() && size>1; //equalsParent(rear.tree,vertex) && size >1;
    	Node ptr = rear.next;
    	Node prev = null;
    	
    	while(ptr!=rear && !(ptr.tree.getRoot()== vertex.getRoot())){ // equalsParent(ptr.tree,vertex)) runs until ptr reaches null or ptr matches the give vertix
    		
    		prev = ptr;
    		ptr = ptr.next;
    	}
    	
    	if (prev == null){
    		
    		prev = rear;
    	}
    	
    	if (rear == ptr){// in case when there is only one node(which means two node of the name vertixes)
    		
    		if (!(lastNode)){
    			
    			
    		PartialTree toReturn = rear.tree;
    		rear=null;
    		size=0;
    		return toReturn;
    		
    		}
    		else{
    			
    			PartialTree toReturn = ptr.tree;
    			ptr =null;
    			prev.next = rear.next;
    			rear = prev;
    			size--;
    			return toReturn;
    			
    		}
    		
    	}
    	
    	if (ptr!=rear){ //found the tree matching the given vertix
    		
    		PartialTree toReturn = ptr.tree;// put the tree toReturn varaible to return it
    		
    		
    		 
        	prev.next=ptr.next; //deleting the found tree
        		
    		size --;
    		return toReturn;
    		
    	}
    	
    	throw new NoSuchElementException();
     }
	
/*	private boolean equalsParent(PartialTree tree , Vertex vertex){
		
		Vertex root = tree.getRoot();
		if (root.name.equalsIgnoreCase(vertex.name)){
			
			return true;
		}
	//	root = root.parent;
		while(root!= root.parent){
			
			if (root.name.equalsIgnoreCase(vertex.name)){
				
				return true;
			}
			root = root.parent;
		}
		return false;
	}
	*/
    
    /**
     * Gives the number of trees in this list
     * 
     * @return Number of trees
     */
    public int size() {
    	return size;
    }
    
    
   /**
    * remember this method prints the list in a way the list method is implemented.
    * for personal use 
    */
    
    public void printList(){ //to print the list 
		
    	if (rear ==null){
    		
    		System.out.println("The list is empty");
    		return;
    	}
		Node ptr = rear.next;
		System.out.println(rear.tree+"\n");
		
		while(ptr!=rear){
			
			System.out.println(ptr.tree+"\n");
			ptr = ptr.next;
		}
		System.out.println(ptr.tree);
		return;
	}
	
    
    /**
     * Returns an Iterator that can be used to step through the trees in this list.
     * The iterator does NOT support remove.
     * 
     * @return Iterator for this list
     */
    public Iterator<PartialTree> iterator() {
    	return new PartialTreeListIterator(this);
    }
    
    private class PartialTreeListIterator implements Iterator<PartialTree> {
    	
    	private PartialTreeList.Node ptr;
    	private int rest;
    	
    	public PartialTreeListIterator(PartialTreeList target) {
    		rest = target.size;
    		ptr = rest > 0 ? target.rear.next : null;
    	}
    	
    	public PartialTree next() 
    	throws NoSuchElementException {
    		if (rest <= 0) {
    			throw new NoSuchElementException();
    		}
    		PartialTree ret = ptr.tree;
    		ptr = ptr.next;
    		rest--;
    		return ret;
    	}
    	
    	public boolean hasNext() {
    		return rest != 0;
    	}
    	
    	public void remove() 
    	throws UnsupportedOperationException {
    		throw new UnsupportedOperationException();
    	}
    	
    	
    	
    }
}


