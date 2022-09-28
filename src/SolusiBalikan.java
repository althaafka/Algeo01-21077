public class SolusiBalikan extends Matrix{
    public static double[][] SolveBalikan(double[][] A){
        // Prekondisi A selalu augmented
        // x = A^(-1)b

        // Buat matriks b terpisah;
        double[][]
        b = createMatrix(nBaris(A), 1);
        for (int i=0; i<nBaris(A); i++){
            b[i][0] = A[i][nKolom(A)-1];
        }
        // Ubah matriks menjadi matriks persegi
        A = AugmentedtoSquare(A);
        double[][] Ainverse = Invers.InversCofactor(A);
        double[][] x = kaliMatriks(Ainverse, b);

        return x;
    }
}