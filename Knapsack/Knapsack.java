import java.util.*;
import java.io.*;

public class Knapsack{
   
   private int opt;
   private boolean flag;
   
   public Knapsack(int opt, boolean flag) {
      this.opt = opt;
      this.flag = flag;
   }
   
   // Method performs bottum-up knapsack algorithm
   public static Knapsack knapsack_Opt(int n, int maxWeight, ArrayList<Integer> value, ArrayList<Integer> weight, Knapsack[][] M){
      System.out.println("Solving Knapsack with weight capacity " + maxWeight + ", with " + value.size() + " items");
      System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
      
      // Set first row in table to 0's
      for (int w=0; w < M[0].length; w++){
         M[0][w].opt = 0;
      }
      printTable(maxWeight, 0, M);
      
      
      for (int i=1; i < n + 1; i++){
         for (int w=0; w < M[0].length; w++){
            // OPT(i-1, w)
            if (weight.get(i-1) > w){
               M[i][w].opt = M[i-1][w].opt;
            } else {
               // max{ OPT(i-1, w), v + OPT(i-1, w-wi)}
               if (M[i-1][w].opt > value.get(i-1) + M[i-1][w-weight.get(i-1)].opt){
                  M[i][w].opt = M[i-1][w].opt;
               } else {
                  M[i][w].opt = value.get(i-1) + M[i-1][w-weight.get(i-1)].opt;
                  M[i][w].flag = true;
               }
            }
         }
         printTable(maxWeight, i, M);
      }
      bookkeeping(maxWeight, value, weight, M);
      return M[n][maxWeight];
   }
   
   // Method performs bookkeeping to find optimal subset of items
   public static void bookkeeping(int W, ArrayList<Integer> value, ArrayList<Integer> weight, Knapsack[][] M){
      int n = weight.size();
      ArrayList<Integer> solution = new ArrayList(0);
      
      System.out.println("Knapsack with weight capacity " + W + " has optimal value: " + M[n][W].opt);
      
      while (n > 0){ 
         if (M[n][W].flag == true){
            solution.add(n);
            n--;
            W -= weight.get(n);
         } else {
            n--;
         }
      }
      System.out.println("\n-----Knapsack Contains-----");
      for(int i=0; i < solution.size(); i++){
         System.out.println("Item " + solution.get(i) + "(Value = " + value.get(solution.get(i)-1) + 
            ", Weight = " + weight.get(solution.get(i)-1) + ")");
      }
   }
   
   // Method formats and prints out memoization table
   public static void printTable(int maxWeight, int row, Knapsack[][] M){
      System.out.println("\nMemoization table, Row " + row + " completed");
      System.out.print("\t");
      for (int j=0; j <= maxWeight; j++){
         System.out.print("\t\t" + j);
      }
      System.out.println("\n-----------------------------------------------------------");
      
      for (int i=0; i < M.length; i++){
         System.out.printf("%5s", "{" + i + "}");
         for (int w=0; w < M[i].length; w++){
            System.out.print("\t\t" + M[i][w].opt);
         }
         System.out.println("\n");
      }
   }


   public static void main(String args[]) throws IOException {
      // Prompt for input file name
      Scanner keyboard = new Scanner (System.in);
      String filename;
         
      System.out.print("Enter filename: ");
      filename = keyboard.nextLine();
      
      // Read in from file
      File newFile = new File(filename);
      Scanner inputFile = new Scanner(newFile);
         
      int item = 0;
      int value;
      int weight;
      
      // Initialize lists to hold values and weights of item
      ArrayList<Integer> valueList = new ArrayList(0);
      ArrayList<Integer> weightList = new ArrayList(0);
      
      // Weight capacity
      int maxWeight = inputFile.nextInt();
      
      while (inputFile.hasNext()){
         inputFile.nextInt();
         item++;
         
         value = inputFile.nextInt();
         valueList.add(value);
         
         weight = inputFile.nextInt();
         weightList.add(weight);
      }
      
      // initialize global array
      Knapsack[][] M = new Knapsack[item + 1][maxWeight + 1];
      
      // Set all array elements to -1 and false flag
      for (int i=0; i < M.length; i++) {
         for (int w=0; w < M[i].length; w++) {
            M[i][w] = new Knapsack(-1, false);
         }
      }      
      
      // Call to knapsack algorithm
      knapsack_Opt(item, maxWeight, valueList, weightList, M);
   }
   
      
   

}