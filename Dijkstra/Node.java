import java.util.*;

public class Node{

   private final int vertex;
   private int weight;
   public ArrayList<Integer> predecessors;
      
   // Construct node that holds integer and list of child nodes
   public Node(int vertex, int weight) {
      this.vertex = vertex;
      this.weight = weight;
      predecessors = new ArrayList();
      
   }
   
   // Returns vertex
   public int getVertex() {
      return vertex;
   }
   
   // Returns weight of path
   public int getWeight() {
      return weight;
   }
   
   // Returns list of preceding paths to current node
   public ArrayList getPredecessors(){
      return predecessors;
   }
   
   // Adds node to list of predecessors
   public void addPredecessor(int vertex){
      predecessors.add(vertex);
   }
   
   // Sets weight of path
   public void setWeight(int w){
      this.weight = weight;
   }
}
   