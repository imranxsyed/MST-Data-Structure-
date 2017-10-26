package apps;

import structures.*;
import java.util.ArrayList;



public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
	
		int length = graph.vertices.length;
		PartialTreeList List = new PartialTreeList();
		
		for (int i=0 ;i< length; i++){
			
			Vertex each_Element = graph.vertices[i]; // each vertices of the tree
			Vertex.Neighbor nbr = each_Element.neighbors; //adjancy Linked list of the each vertices
			Vertex root = each_Element; //root vertex with the null neighbor
			//root.neighbors=null;//making sure the neighbor is not inserted in the heap
			PartialTree each_Tree= new PartialTree(each_Element); // each tree that would be inserted in the List after the completion of the loop
			MinHeap<PartialTree.Arc> heap= each_Tree.getArcs();//heap of the each tree that would be implemented in the loop
			
			
			while(nbr!=null){
				
				Vertex toInsert = nbr.vertex;//variable to insert with the root in the partial tree
				heap.insert(new PartialTree.Arc(root,toInsert,nbr.weight)); //inserting in the heap
				nbr = nbr.next;
			}
			
			
				List.append(each_Tree);
				
		}// all trees are in the list (Completed)
		
		return List;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		
		/* COMPLETE THIS METHOD */
		PartialTreeList List= ptlist;
		ArrayList<PartialTree.Arc> output = new ArrayList<PartialTree.Arc>();
		
		while(List.size()>1){ //runs until the list has only one tree
			
			boolean found = false;
			PartialTree tree = List.remove(); //removing the tree from the list
			
				while(!(found)&& !(tree.getArcs().isEmpty())){//either found a vertix that does not belong to the same tree or queue is empty
			
			
					PartialTree.Arc arc = tree.getArcs().getMin();
					tree.getArcs().deleteMin();
					Vertex v1 = arc.v1;
					Vertex v2 = arc.v2;
					int weigth = arc.weight;
					
					
					
				
			
					if (v1.getRoot()!= v2.getRoot()){//!(equalsParent(tree.getRoot(),v2 helper method to check all the parent of the vertex
				
						found = true;
						PartialTree toInsert = List.removeTreeContaining(v2);
						
						
						
						//setRoot(tree.getRoot(),toInsert.getRoot()); //places the root of toInsert in the end of root of the tree
						tree.merge(toInsert);
						//tree.getRoot().parent= toInsert.getRoot();
						
						
					//	System.out.println("\n\tmerged Tree-->"+tree+"\n");//delete it
						List.append(tree);
					//	System.out.println("list size--> "+List.size()+"\n\tNew List\n"); //delete it
					//	List.printList(); //delete
						
						output.add(arc);
					}
			
				}
			
			}
		

		return output;
	}
/*	Do not need these methods, since apprently comapring to roots does all the job itself
private static boolean equalsParent(Vertex vt1 , Vertex vertex){
		
		
		if (vt1.name.equalsIgnoreCase(vertex.name)){
			
			return true;
		}
		Vertex root = vt1.parent;
		
		
		while(root != root.parent){  //!root.name.equals(vt1.name
			
			if (vt1.name.equalsIgnoreCase(vertex.name)){
				
				return true;
			}
			root = root.parent;
		}
		return false;
	}
		

	private static void setRoot(Vertex root, Vertex toAdd){
		
		Vertex ptr = root;
		
		while(ptr!= ptr.parent){
			
			ptr = ptr.parent;
		}
		ptr.parent= toAdd;
		
	}
	*/
}
