import java.util.Scanner;

public class Matrix {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        double[][] m,m2;
        m = createMatrix(3, 7);
        System.out.println(nBaris(m));
        System.out.println(nKolom(m));
        m2 = bacaMatrix();
        tulisMatrix(m2);
    }

    public static double[][] createMatrix(int nrow, int ncol){
        double[][] m = new double[nrow][ncol];
        return m;
    }

    public static int nBaris(double[][] m){
        return m.length;
    }

    public static int nKolom(double[][] m){
        return m[0].length;
    }

    public static double[][] bacaMatrix(){
        int nrow, ncol;
        double [][] m;
        System.out.println("Masukkan ukuran matriks");
        System.out.print("Jumlah baris: ");
        nrow = scan.nextInt();
        System.out.print("Jumlah kolom: ");
        ncol = scan.nextInt();
        while (nrow<=0 || ncol<=0){
            System.out.println("Masukkan invalid. Ukuran baris dan kolom harus >0");
            nrow = scan.nextInt();
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
        for (int i=0;i<nBaris(m);i++){
            for (int j=0;j<nKolom(m);j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }
}
