import java.util.*;

public class Dijkstra{
   
   // Determines shortest path for each node in graph G(V,E) 
   public static void dijkstra(AdjacencyList adj, int s){
      Node v;
      // Initialize Q of V and set weights to infinity
      Node[] Q = new Node[8];
      // Initialize postion array to track nodes in Q
      int[] position = new int[8];
      // Initialize set S to hold nodes with shortest path determined
      LinkedList<Node> S = new LinkedList();
      insert(Q, new Node(s, 0));
      for (int i=1; i < 8; i++){
         insert(Q, new Node(i+1, Integer.MAX_VALUE));
      }
      trackPosition(Q, position);
      // While Q is not empty
      while (Q[0] != null){
         v = extractMin(Q);
         trackPosition(Q, position);
         S.add(v);
         // For each edge between v and w, in which w does not belong to S
         for (Node w : adj.list[v.getVertex()-1]){
            if (!S.contains(w)){
               if (v.getWeight() + w.getWeight() < Q[position[w.getVertex()-1]].getWeight()){
                  int newWeight = v.getWeight() + w.getWeight();
                  changeKey(Q, position[w.getVertex()-1], newWeight);
                  trackPosition(Q, position);
                  // Add predecessors to node w
                  for (int x : v.predecessors){
                     Q[position[w.getVertex()-1]].addPredecessor(x);
                  }
                  Q[position[w.getVertex()-1]].addPredecessor(v.getVertex());
               }
            }
         }
      }
      printPaths(S);
   }
   
   // Adds node to Queue
   public static void insert(Node[] H, Node v){
      for(int i = 0; i < H.length; i++){
         if(H[i] == null) {
            H[i] = v;
            heapifyUp(H, i);
            return;
         }
      }
   }
   
   // Deletes node with smallest key value (weight) from Queue and returns it
   public static Node extractMin(Node[] H){
      Node temp = H[0];
      int n=0;
      for (Node x : H){
         if (x != null){
            n++;
         }
      }
      H[0] = H[n-1];
      H[n-1] = null;
      heapifyDown(H, 0); // check
      return temp;
   }
   
   // Changes the key value (weight) to specified input
   public static void changeKey(Node[] H, int w, int weight){
      H[w] = new Node(H[w].getVertex(), weight);
      heapifyUp(H, w);
      heapifyDown(H, w);
   }
   
   // Moves node up Queue if key value is too small for current position
   public static void heapifyUp(Node[] H, int i){
      if (i > 0){
         int j = i / 2;
         if (H[i].getWeight() < H[j].getWeight()){
            Node temp = H[i];
            H[i] = H[j];
            H[j] = temp;
            heapifyUp(H, i);
         }
      }
   }
   
   // Moves node down Queue if key value is too big for current position
   public static void heapifyDown(Node[] H, int i){
      int j;
      int n=0;
      for (Node x : H){
         if (x != null){
            n++;
         }
      }
      if (2*(i+1) > n){
         return;
      } else if(2*(i+1) < n){
         int left = 2*i+1;
         int right = 2*i+2;
         if (H[left].getWeight() < H[right].getWeight()){
            j = left;
         } else {
            j = right;
         }
      } else {
         j = 2*i;
      }
      if (H[j].getWeight() < H[i].getWeight()){
         Node temp = H[i];
         H[i] = H[j];
         H[j] = temp;
         heapifyDown(H, j);
      }
   }
   
   // Maintains an array to track position of elements in Queue
   public static void trackPosition(Node[] H, int[] P){
      int x = 0;
      for (Node i : H){
         if (i != null){
            P[i.getVertex() - 1] = x;
            x++;
         } 
      }

   }
   
   // Prints shortest paths of each node
   public static void printPaths(LinkedList<Node> S){
      for (Node n : S){
         System.out.print("Node " + n.getVertex() + " has shortest path length " + n.getWeight() +" from ");
         for (int i : n.predecessors){
            System.out.print(i + " - "); 
         }
         System.out.print(n.getVertex());
         System.out.print("\n");
      }
   }
   
  
  
   // Main method
   public static void main(String args[]) {
   
      AdjacencyList adjList = new AdjacencyList();
      
      adjList.addEdge(1, new Node(2,9));
      adjList.addEdge(1, new Node(3,15));
      adjList.addEdge(1, new Node(4,14));
      adjList.addEdge(2, new Node(7,23));
      adjList.addEdge(3, new Node(5,20));
      adjList.addEdge(3, new Node(8,44));
      adjList.addEdge(4, new Node(3,5));
      adjList.addEdge(4, new Node(5,30));
      adjList.addEdge(4, new Node(7,18));
      adjList.addEdge(5, new Node(6,11));
      adjList.addEdge(5, new Node(8,16));
      adjList.addEdge(6, new Node(7,6));
      adjList.addEdge(6, new Node(8,6));
      adjList.addEdge(7, new Node(5,2));
      adjList.addEdge(7, new Node(8,19));

      System.out.println("Graphs is represented using an adjacency list\n");
      dijkstra(adjList, 1);
    
      
   }

}

