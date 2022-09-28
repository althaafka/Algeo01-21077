public class Invers extends Matrix{
    public static void main(String[] args){
        double[][] m1;
        m1 = bacaMatrix();
        tulisMatrix(m1);
        m1 = Invers.InversCofactor(m1);
        tulisMatrix(m1);
    }
    public static double[][] InversCofactor(double[][] m) {
        // Invers Matriks metode Cofactor
        // Prekondisi determinan matriks != 0
        if (nBaris(m) != nKolom(m)) m = AugmentedtoSquare(m);
        double det = Determinan.determinan(m);
        double[][] mCof = Determinan.MatrixCof(m);
        double[][] mCofTrans = transpose(mCof);
        double[][] mInvers = kaliKonstanta(mCofTrans, 1 / det);
        return mInvers;
    }
}
