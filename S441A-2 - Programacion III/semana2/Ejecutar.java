public class Ejecutar {
   public static void main(String[] args) {
     
      //Forma 1 - Creación del arreglo
      int[] a = {2, 8, 10, 6, 15, 20, 21, 1, 3, 12};
 
      //recorremos el arreglo
      for (int i = 0; i < a.length; i++) {
         System.out.println("a[" + i + "]=" + a[i]);  
      }
   }
}