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
        // System.out.println(dir.replace('\\', '/'));
        FileReader file = null;

        System.out.println("Masukkan nama file");
        System.out.print(">>> ");
        fileName = scan.next();
        fileName = dir.replace('\\', '/') + "/test/"+ fileName;
        try {
            file = new FileReader(fileName);
        } catch (FileNotFoundException fe) {
        System.out.println("File tidak ditemukan.");
        System.out.println();
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
                try {
                    double el = scan.nextDouble();
                    m[i][j] = el;
                } catch (Exception e){
                    m[i][j] = 0;
                }
            }
        }
        scan.close();
        return m;
    }

    public static boolean writeFile(String dir, double[][] m){
        // Menuliskan hasil operasi matriks ke file.
        try {
            FileWriter file = new FileWriter(dir);
            for (int i=0; i<Matrix.nBaris(m); i++){
                for(int j=0; i<Matrix.nKolom(m); j++) {
                    file.write(Double.toString(m[i][j]) +" ");
                }
                file.write("\n");
            }
            // End Of File
            file.write("\n");
            file.close();
            System.out.println("Matriks telah tersimpan di "+dir);
            return true;
        } catch (IOException e){
            System.out.println("[Error] Gagal menyimpan file.");
            return false;
        }
    }

    public static boolean writeSPLSol (String dir, String[] SPLsolved){
        // Menuliskan hasil penyelesaian SPL ke file
        try {
            FileWriter file = new FileWriter(dir);
            for (String s : SPLsolved) {
                file.write(s + "\n");
            }
            // End Of File
            file.write("\n");
            file.close();
            System.out.println("Hasil penyelesaian SPL telah tersimpan di "+dir);
            return true;
        } catch (IOException e){
            System.out.println("[Error] Gagal menyimpan file.");
            return false;
        }
    }

    public static boolean writeDeterminan(String dir, double[][] m, double det){
        // Menuliskan hasil perhitungan determinan matriks ke file
        try {
            FileWriter file = new FileWriter(dir);
            file.write("Hasil determinan matriks \n");
            for (int i=0; i<Matrix.nBaris(m); i++){
                for (int j=0; j<Matrix.nKolom(m); j++){
                    file.write(Double.toString(m[i][j]) + " ");
                }
                file.write("\n");
            }
            // End Of File
            file.write("adalah" + Double.toString(det) + "\n");
            file.close();
            System.out.println("Hasil determinan telah tersimpan di " + dir);
            return true;
        } catch (IOException e){
            System.out.println("[Error] Gagal menyimpan file.");
            return false;
        }
    }

    public static boolean writeInvers (String dir, double[][] m, double[][] mInverse){
        // Menuliskan hasil inverse matriks ke file
        try {
            FileWriter file = new FileWriter(dir);
            file.write("Inverse matriks ");
            for (int i=0; i<Matrix.nBaris(m); i++){
                for (int j=0; j<Matrix.nKolom(m); j++){
                    file.write(Double.toString(m[i][j]) + " ");
                }
                file.write("\n");
            }
            // End Of File
            file.write("adalah \n");
            for (int i=0; i<Matrix.nBaris(mInverse); i++){
                for (int j=0; j<Matrix.nKolom(mInverse); j++){
                    file.write(Double.toString(mInverse[i][j]) + " ");
                }
                file.write("\n");
            }
            file.close();
            System.out.println("Inverse matriks telah tersimpan di " + dir);
            return true;
        } catch (IOException e) {
            System.out.println("[Error] Gagal menyimpan file.");
            return false;
        }
    }

    public static boolean writeFailInverse (String dir, double[][] m){
        // Jika determinan matriks = 0, maka matriks tidak memiliki invers
        // Menuliskan pesan error matriks tidak memiliki inverse
        try {
            FileWriter file = new FileWriter(dir);
            file.write("Determinan matriks ");
            for (int i=0; i<Matrix.nBaris(m); i++){
                for (int j=0; j<Matrix.nKolom(m); j++){
                    file.write(Double.toString(m[i][j]) + " ");
                }
                file.write("\n");
            }
            file.write("adalah 0, sehingga matriks tidak memiliki Inverse \n");
            file.close();
            return true;
        } catch (IOException e){
            System.out.println("[Error] Gagal menyimpan file.");
            return false;
        }
    }

    public static boolean writeInterpolasi(String dir, double[][] a, double findValOf, double estimateVal){
        // Menuliskan hasil perhitungan Interpolasi ke file
        try {
            FileWriter file = new FileWriter(dir);
            file.write("Interpolasi menghasilkan persamaan \nf(x) = ");
            boolean a0 = true;
            for (int i = 0; i<Matrix.nBaris(a); i++){
                if (!(Matrix.isZero(a[i][0]))) {
                    if (a0){
                        // a[i] koefisien pertama sehingga tidak perlu diawali +
                        a0 = false;
                    } else {
                        file.write(" + ");
                    }
                    file.write(Double.toString(a[i][0]));
                    if (i != 0){
                        file.write("x^");
                        file.write(Integer.toString(i));
                    }
                }
            }
            // End Penulisan polinom
            file.write("\n");
            // Penulisan hasil taksiran
            file.write("Dengan taksiran f(  " + Double.toString(findValOf) + " ) = " + Double.toString(estimateVal));
            // End Of File
            file.write("\n");
            file.close();
            System.out.println("Berhasil menyimpan hasil interpolasi di "+ dir);
            return true;
        } catch (IOException e){
            System.out.println("[Error] Gagal menyimpan file.");
            return false;
        }
    }
}

