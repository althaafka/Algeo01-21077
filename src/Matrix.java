import java.util.Scanner;

public class Matrix {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        double[][] m,m2;
        // m = createMatrix(3, 7);
        // System.out.println(nBaris(m));
        // System.out.println(nKolom(m));
        m2 = bacaMatrix();
        tulisMatrix(m2);
        System.out.println();
        // m2 = swapBaris(m2, 1, 0);
        // System.out.println();
        // tulisMatrix(m2);
        // m2 = kaliBaris(m2, 2, 10);
        // System.out.println();
        // tulisMatrix(m2);
        m2 = barisKurangNBaris(m2, 1, 0, 5);
        System.out.println();
        tulisMatrix(m2);

    }

    public static double[][] createMatrix(int nrow, int ncol){
        // Mengembalikan matriks dengan ukuran baris nrow dan ukuran kolom ncol
        double[][] m = new double[nrow][ncol];
        return m;
    }

    public static int nBaris(double[][] m){
        // Mengembalikan jumlah baris matriks m
        return m.length;
    }

    public static int nKolom(double[][] m){
        // Mengembalikan jumlah kolom matriks m
        return m[0].length;
    }

    public static double[][] bacaMatrix(){
        // Membaca matriks dengan elemen input user
        int nrow, ncol;
        double [][] m;
        System.out.println("Masukkan ukuran matriks");
        System.out.print("Jumlah baris: ");
        nrow = scan.nextInt();
        System.out.print("Jumlah kolom: ");
        ncol = scan.nextInt();
        while (nrow<=0 || ncol<=0){
            System.out.println("Masukkan invalid. Ukuran baris dan kolom harus >0");
            System.out.print("Jumlah baris: ");
            nrow = scan.nextInt();
            System.out.print("Jumlah kolom: ");
            ncol = scan.nextInt();
        }
        m = createMatrix(nrow, ncol);
        System.out.println("Masukan elemen matriks");
        for (int i=0; i<nrow;i++){
            for (int j=0; j<ncol; j++){
                System.out.print("Elemen M["+i+"]["+j+"]: ");
                m[i][j] = scan.nextDouble();
            }
        }
        return m;
    }

    public static double[][] bacaMatrixSquare(){
        int nrow, ncol;
        double [][] m;
        System.out.println("Masukkan ukuran matriks");
        System.out.print("Jumlah baris: ");
        nrow = scan.nextInt();
        System.out.print("Jumlah kolom: ");
        ncol = scan.nextInt();
        while (nrow!=ncol || nrow<=0 || ncol<=0){
            if (nrow<=0 || ncol<=0){
                System.out.println("Masukkan invalid. Ukuran baris dan kolom harus >0");
                System.out.print("Jumlah baris: ");
                nrow = scan.nextInt();
                System.out.print("Jumlah kolom: ");
                ncol = scan.nextInt();
            } else {
                System.out.println("Masukkan invalid. Matrix harus matrix persegi");
                System.out.print("Jumlah baris: ");
                nrow = scan.nextInt();
                System.out.print("Jumlah kolom: ");
                ncol = scan.nextInt();
            }
        }
        m = createMatrix(nrow, ncol);
        System.out.println("Masukan elemen matriks");
        for (int i=0; i<nrow;i++){
            for (int j=0; j<ncol; j++){
                System.out.print("Elemen M["+i+"]["+j+"]: ");
                m[i][j] = scan.nextDouble();
            }
        }
        return m;
    }

    public static void tulisMatrix(double[][] m){
        // Menampilkan matriks ke layar
        for (int i=0;i<nBaris(m);i++){
            for (int j=0;j<nKolom(m);j++){
                System.out.print(m[i][j]+ " ");
            }
            System.out.println();
        }
    }

    // OPERASI OBE
    public static double[][] swapBaris(double[][] m, int idxRow1, int idxRow2){
        double [] rowTemp = m[idxRow1];
        m[idxRow1]=m[idxRow2];
        m[idxRow2]=rowTemp;
        return m;
    }
    public static double[][] kaliBaris(double[][] m, int idxRow, double x){
        for (int i = 0; i<nKolom(m); i++){
            m[idxRow][i] = m[idxRow][i]*x;
        }
        return m;
    }
    public static double[][] barisKurangNBaris(double[][] m, int idxRow1, int idxRow2, double x){
        for (int i = 0; i<nKolom(m); i++){
            m[idxRow1][i] -= m[idxRow2][i]*x;
        }
        return m;
    }

    public static boolean isZero(double x){
        double epsilon = 1.0e-12;
        return ((x<epsilon) && (x>-epsilon));
    }

    public static boolean isRowZero(double[][] m, int i){
        for (int j=0; j<nKolom(m); j++){
            if (!isZero(m[i][j])) return false;
        }
        return true;
    }
}

