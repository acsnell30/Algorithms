import java.util.*;

public class Ford_Fulkerson {

   // Finds smallest capacity along path in residual graph
   public static int augment(int source, int sink, int path[], int graph[][], int residual[][]) {
      
      int bottleneck = bottleneck(source, sink, path, residual);
      
      // For each edge that is in path P, adjust f(e)
      for(int v = sink; v != source; v = path[v-1]) {
         int u = path[v-1];
         if (graph[u-1][v-1] != -1) {
            graph[u-1][v-1] += bottleneck;
         } else {
            graph[u-1][v-1] = -(bottleneck);
         }
      }
      return bottleneck;
   }

   // Finds max flow value of a graph given a source and sink node
   public static int max_flow(int source, int sink, int[][] graph, int[][] residual) {
      
      int newFlow = 0;
      int maxFlow = 0;
      
      // Array to track path
      int[] path = new int[8];
      
      // BFS returns true if residual path exists
      while (breadthFirstSearch(residual, source, sink, path)) {
         newFlow = augment(source, sink, path, graph, residual);
         maxFlow += newFlow;
         
         // Update residual graph to reflect f'
         for(int v = sink; v != source; v = path[v-1]) {
            int u = path[v-1];
            residual[u-1][v-1] -= newFlow;
            residual[v-1][u-1] += newFlow;
         }
         // Print augmented path
         printPath(path, newFlow);
      }
      return maxFlow;
   }


   // Implements BFS algorithm on adjacency matrix given start and stop node
   public static boolean breadthFirstSearch(int[][] residual, int source, int sink, int parent[]) {
      
      LinkedList<Integer> queue = new LinkedList();
      queue.add(source);
      // Declare array to keep track of discovered nodes
      // Add starting node to discovered list
      boolean [] discovered = new boolean[8];
      discovered[source-1] = true;
      
      // Traverse through adjacency list
      while (!queue.isEmpty()) {
  
         int u = queue.poll();
         //for each edge (u,v) indicent to u
         for (int v=0; v < residual[u-1].length; v++) {
            // if v has not been discovered, add it to discovered list
            if (residual[u-1][v] > 0 && discovered[v] == false) {
               if (v == sink-1){
                  parent[v] = u;
                  return true;
               }
               discovered[v] = true;
               parent[v] = u;
               queue.add(v+1);
            }
         }
      }
      return false;
   }

   

   // Find bottleneck
   public static int bottleneck(int source, int sink, int path[], int residual[][]) {
      int bottleneck = Integer.MAX_VALUE;
   
      for (int v = sink; v != source; v = path[v-1]) {
         int u = path[v-1];
         bottleneck = Math.min(bottleneck, residual[u-1][v-1]);
      }
      return bottleneck;
   }

   // Add edge to adj matrix
   public static void addEdge(int array[][], int v1, int v2, int capacity) {
      array[v1-1][v2-1] = capacity;
   }

   // Print path
   public static void printPath(int[] path, int newFlow) {
      LinkedList<Integer> temp = new LinkedList<>();
      for (int v = 8; v != 1; v = path[v-1]) {
         temp.add(0, path[v-1]);
      }
      System.out.print("s-");
      for (int i = 1; i < temp.size(); i++) {
         System.out.print(temp.get(i) + "-");
      }
      System.out.println("t: " + newFlow);
      
   }

   public static void main(String args[]) {
   
      // Declare Graph and Residual-graph
      int[][] graph = new int[8][8];
      int[][] residual = new int[8][8];
      
      for (int i=0; i < residual.length; i++) {
         for (int j=0; j < residual[i].length; j++) {
            residual[i][j] = 0;
         }
      }

      // Populate edges in Residual with capacities
      addEdge(residual, 1, 2, 10);
      addEdge(residual, 1,3, 5);
      addEdge(residual, 1, 4, 15);
      addEdge(residual, 2, 3, 4);
      addEdge(residual, 2, 5, 9);
      addEdge(residual, 2, 6, 15);
      addEdge(residual, 3, 4, 4);
      addEdge(residual, 3, 6, 8);
      addEdge(residual, 4, 7, 30);
      addEdge(residual, 5, 6, 15);
      addEdge(residual, 5, 8, 10);
      addEdge(residual, 6, 7, 15);
      addEdge(residual, 6, 8, 10);
      addEdge(residual, 7, 3, 6);
      addEdge(residual, 7, 8, 10);
      
      // Set edges in Graph to flow of 0, all non existent edges to -1
      for (int i=0; i < graph.length; i++) {
         for (int j=0; j < graph[i].length; j++) {
            if (residual[i][j] >= 1) {
               graph[i][j] = 0;
            } else {
               graph[i][j] = -1;
            }
         }   
      }
      
      // Run algorithm
      System.out.println("Running Ford-Fulkerson algorithm using Adjacency Matrix...");
      System.out.print("Max flow: " + max_flow(1, 8, graph, residual));
   }



}