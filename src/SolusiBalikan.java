public class SolusiBalikan extends Matrix{
    public static double[][] SolveBalikan(double[][] A, double[][] b){
        A = AugmentedtoSquare(A);
        double[][] Ainverse = Invers.InversCofactor(A);
        double[][] x = kaliMatriks(Ainverse, b);

        return x;
    }
}