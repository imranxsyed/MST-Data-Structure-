package structures;
//my file

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import apps.*;




public class MSTDriver {

	public static PartialTreeList list;
	public static Graph object;
	public static  Scanner sc;
	public static int input;
	public static void main(String[] args) throws IOException {
		
		boolean allSet =false;
		
				while(!(allSet)){
		try{
			
			sc= new Scanner(System.in);
			System.out.println("\tImrans Driver\nEnter the name of the file");
			String file = sc.nextLine().trim();//file name
			object = new Graph(file);//adding file
			list = new PartialTreeList();
			list = MST.initialize(object);
			allSet = true;
		
		}catch(FileNotFoundException e){
			System.out.println("\n\t(ERROR)\nCould not find the specified file!!!\n");
			
		}
				}
			
		
		System.out.println("\nTo print graph: press 0"
				+ "\nTo initialize List: press 1"
				+ "\nTo executure list: press 2"
				+ "\nTo remove tree from the list: press 3"
				+ "\nTo remove tree for the specified vertix: press 4"
				+ "\nTo enter a new graph: press 5"
				+ "\nTo quit: press 6\n");
		
		try{ input = sc.nextInt();}catch(InputMismatchException e){ System.out.println("\n\t(ERROR)\nInvalid input!!\nEnter again:");
		sc = new Scanner(System.in);input = sc.nextInt();}
		allSet=false;
		
		while(!(allSet)){
			
			try{
		
		while (input!= 6){
			try{
			
			switch(input){
			
			case 0: object.printGraphShape(); break;
			
			case 1: 
				System.out.println("\t(OUTPUT)\n"); 
				list.printList();
				break;
			
			case 2: System.out.println(MST.execute(list));
			break;
			
			case 3: 
				
				System.out.println("\t(OUTPUT)\n\n"+list.remove()); break;
			
			case 4:  
				
				System.out.println("Enter the name of the vertix you wish to remove");
				sc = new Scanner(System.in);
				String find = sc.nextLine().trim().toUpperCase();
				Vertex toRemove = object.findVertex(find);
				System.out.println("\t(OUTPUT)\n\n"+list.removeTreeContaining(toRemove));
			
				break;
			
			case 5: System.out.println("\nenter the name of file");
			
				sc= new Scanner(System.in);
				object = new Graph(sc.nextLine().trim());
				list = MST.initialize(object);
	
				break;
			
			default : System.out.println("\t(INVALID INPUT"); break;
			}
			
			}catch(NoSuchElementException e){
				
				System.out.println("\n\t(OUTPUT)\n\n(Cannot remove anything from the empty list)");
			}catch(FileNotFoundException e){
				
				System.out.println("\n\t(ERROR)\nCould not find the file!!!");
			}catch(NullPointerException e){
				
				System.out.println("\n\t(ERROR)\nThe Null Pointer Exception.Please check your input!!");
			}
			
			System.out.println("\n\nTo print graph: press 0"
					+ "\nTo initialize List: press 1"
					+ "\nTo executure list: press 2"
					+ "\nTo remove tree from the list: press 3"
					+ "\nTo remove tree for the specified vertix: press 4"
					+ "\nTo enter a new graph: press 5"
					+ "\nTo quit: press 6\n");
			input = sc.nextInt();
			
		}
		System.out.println("\n\t(PROGRAM TERMIANTED)\n");
		allSet = true;
		
		}catch(FileNotFoundException e){
			
			System.out.println("\n\t(ERROR)\nCould not find the specified file!!!\n");
		}catch(InputMismatchException e){
			
			System.out.println("\n\t(ERROR)\nInvalid input!!\nEnter again:");
			sc = new Scanner(System.in);
			input =sc.nextInt();
		}
				}
				
		
		

	}

}
