public class Heap {
   
   public static void insert(Node[] H, Node v){
      for(int i = 0; i < H.length; i++){
         if(H[i] == null) {
            H[i] = v;
            heapifyUp(H, i);
            return;
         }
      }
   }
   
   
   public static Node extractMin(Node[] H){
      Node temp = H[0];
      H[0] = null;
      heapifyDown(H, 0); // check
      return temp;
   }
   
   public static void changeKey(Node[] H, int i, int weight){
      H[i].setWeight(weight);
      heapifyUp(H, i);
      heapifyDown(H, i);
   }
   
   
   public static void heapifyUp(Node[] H, int i){
      if (i > 1){
         int j = i / 2;
         if (H[i].getWeight() < H[j].getWeight()){
            Node temp = H[i];
            H[i] = H[j];
            H[j] = temp;
            heapifyUp(H, i);
         }
      }
   }
   
   
   public static void heapifyDown(Node[] H, int i){
      int j;
      int n = H.length;
      if (2*i > n){
         return;
      } else if(2*i < n){
         int left = 2*i;
         int right = 2*i+1;
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
   
   public static void trackPosition(Node[] H, int[] P){
      int x = 1;
      for (Node i : H){
         P[i.getWeight() - 1] = x;
         x++;
      }

   }
}