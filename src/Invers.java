public class Invers extends Matrix{
    public static double[][] InversCofactor(double[][] m) {
        // Invers Matriks metode Cofactor
        m = noAugmented(m);
        double det = Determinan.determinan(m);
        double[][] mCof = Determinan.MatrixCof(m);
        double[][] mCofTrans = transpose(mCof);
        double[][] mInvers = kaliKonstanta(mCofTrans, (1 / det));

        return mInvers;
    }
}
