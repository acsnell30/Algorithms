import java.util.*;

public class AdjacencyList{

   // Array of linked list (adjacency list)
   LinkedList<Node> list[];
   
   // Constructor creates new AdjacencyList object and initilizes each element of array to a LinkedList 
   public AdjacencyList() {
      list = new LinkedList[8];
      
      for (int i=0; i < 8; i++) {
         list[i] = new LinkedList();
      }
   }
   
   // Adds an edge to adjacency list
   public void addEdge(int u, Node v) {   
      list[u-1].add(v);
   }
   
   // Prints adjacency list in correct format      
   public void printAdjList(){
      for (int i = 0; i < list.length; i++) {
            System.out.print(i+1 + ":");
         for (int j=0; j < list[i].size(); j++) {
            System.out.print(" " + list[i].get(j));
         }
         System.out.print("\n");
      }

   }
}
