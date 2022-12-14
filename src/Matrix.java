import java.util.Scanner;

public class Matrix {

    static Scanner scan = new Scanner(System.in);

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
        while (nrow<=0 || ncol<=0 || nrow!=ncol){
            System.out.println("Masukkan invalid. Baris & kolom harus >0 dan Matrix harus persegi");
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
    public static double[][] AugmentedtoSquare(double[][] m){
        // Mengubah matriks augmented menjadi matriks persegi
        double[][] SquareMat = createMatrix(nBaris(m), nKolom(m)-1);
        for (int i = 0; i < nBaris(SquareMat); i++) {
            for (int j = 0; j < nKolom(SquareMat); j++) {
                SquareMat[i][j] = m[i][j];
            }
        }
        return SquareMat;
    }

    public static double[][] augmentedtoKoef(double[][] m){
        double[][] output = createMatrix(nBaris(m), 1);
        for (int i=0; i<nBaris(m); i++ ){
            output[i][0] = m[i][nKolom(m)-1];
        }
        return output;
    }
    public static double[][] transpose(double[][] m){
        // Transpose Matriks;
        double[][] mTranspose = createMatrix(nKolom(m), nBaris(m));
        for (int i=0; i<nBaris(mTranspose); i++){
            for (int j=0; j<nKolom(mTranspose); j++){
                mTranspose[i][j] = m[j][i];
            }
        }
        return mTranspose;
    }

    public static double[][] kaliKonstanta(double[][] m, double x){
        for (int i=0; i<nBaris(m); i++){
            for (int j=0; j<nKolom(m); j++){
                m[i][j] = m[i][j]*x;
            }
        }
        return m;
    }
    public static double[][] kaliMatriks (double[][] m1, double[][] m2){
        // Prekondisi nKolom(m1) = nBaris(mat2)
        double[][] mKali = createMatrix(nBaris(m1), nKolom(m2));

        for (int i = 0; i < nBaris(mKali); i++) {
            for (int j = 0; j < nKolom(mKali); j++) {
                mKali[i][j] = 0;
                for (int k = 0; k < nKolom(m1); k++) {
                    mKali[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return mKali;
    }

    public static double[][] matEfektif (double[][] m){
        // mengembalikan matrix m tanpa baris 0
        int roweff = nBaris(m);
        for (int i=0; i<nBaris(m); i++){
            if(isRowZero(m, i)) roweff--;
        }
        double[][] output = createMatrix(roweff, nKolom(m));
        for (int i=0;i<nBaris(output);i++){
            for (int j=0; j<nKolom(output);j++){
                output[i][j] = m[i][j];
            }
        }
        return output;
    }

    
}

