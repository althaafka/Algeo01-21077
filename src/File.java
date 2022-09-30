import java.io.*;
import java.util.*;

public class File {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        String fileName;
        // int x = fileRow("D:/altha/Kuliah/Semester III/TubesAlgeo/Tubes-Algeo/test/text.txt");
        fileName= inputFileName();
        int x = fileRow(fileName);
        int y = fileCol(fileName);
        System.out.println(y);
        double[][] m;
        m = fileMatrix(fileName);
        Matrix.tulisMatrix(m);
    }
    
    public static String inputFileName(){
        // Mengembalikan path file input yang telah divalidasi
        String fileName;
        String dir = System.getProperty("user.dir");
        System.out.println(dir.replace('\\', '/'));
        FileReader file = null;

        System.out.println("Masukkan nama file");
        System.out.print(">>> ");
        fileName = scan.next();
        fileName = dir.replace('\\', '/') + "/test/"+ fileName;
        try {
            file = new FileReader(fileName);
        } catch (FileNotFoundException fe) {
        System.out.println("File tidak ditemukan.");
        fileName = inputFileName();
        }

        return fileName;
    }

    public static int fileRow(String fileName){
        FileReader file = null;
        try {
            file = new FileReader(fileName);
          } catch (FileNotFoundException fe) {
            System.out.println("File tidak ditemukan");
          }
        String row = "";
        int nrow =0;
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            nrow++;
            row = sc.nextLine();
        }
        sc.close();
        return nrow;
    }

    public static int fileCol(String fileName){
        FileReader file = null;
        try {
            file = new FileReader(fileName);
          } catch (FileNotFoundException fe) {
            System.out.println("File tidak ditemukan");
          }
        int ncol =0;
        String row = "";
        Scanner scrow = new Scanner(file);
        row = scrow.nextLine();
        Scanner sccol = new Scanner(row);
        while(sccol.hasNextDouble()){
            ncol++;
            sccol.nextDouble();
        }
        sccol.close();
        return ncol;
    }

    public static double[][] fileMatrix(String fileName){
        FileReader file = null;
        try {
            file = new FileReader(fileName);
        } catch (FileNotFoundException fe) {
        System.out.println("File tidak ditemukan");
        }
        Scanner scan = new Scanner(file);

        int row = fileRow(fileName);
        int col = fileCol(fileName);
        double[][] m = new double[row][col];

        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                double el = scan.nextDouble();
                m[i][j] = el;
            }
        }
        scan.close();
        return m;
    }

}

