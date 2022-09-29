// import java.io.*;
// import java.util.*;

// public class File {
//     static Scanner sc = new Scanner(System.in);

//     public static double[][] readFile(String fileName, int row, int col){
//         double[][] mat = new double[row][col];
//         FileReader f = null;
//         try {
//             f = new FileReader(fileName);
//         } catch (FileNotFoundException fe){
//             System.out.println("File tidak ditemukan");
//         }
//         Scanner rowScanner = new Scanner(f);

//         for (int i=0; i<row; i++){
//             for (int j=0; j<col; j++){
//                 double input = rowScanner.nextDouble();
//                 mat[i][j] = input;
//             }
//         }
//         rowScanner.close();
//         return mat;
//     }

//     public static String inputFileName(){
//         String fileName;
//         FileReader f = null;
//         System.out.println("Masukkan nama file");
//         System.out.println(">>> ");
//         fileName = sc.next();
//         try{
//             f = new FileReader("../test/" + fileName);
//         } catch (FileNotFoundException fe) {
//         System.out.println("File tidak ditemukan.");
//         fileName = inputFileName();
//     }
//         }
//     }

//     public static double[][] inputMatrixFile(){
//         String fileName;
//         int row;
//         int col;
//         double[][] m;
//         while(true){
//             fileName = "../test/" + inputFileName();
//             row
//         }
//     }
// }

