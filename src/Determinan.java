public class Determinan extends Matrix {
    public static double[][] ElmtCofactor (double[][] m, int row, int col){
        // Menghasilkan elemen matriks kofaktor [row][col]
        // Prekondisi m persegi
        double[][] mCof;
        mCof = createMatrix(nBaris(m), nKolom(m));

        for (int i=0; i<nBaris(m); i++){
            for (int j=0; j<nKolom(m); j++){
                if (i<row){
                    if (j<col) {
                        mCof[i][j] = m[i][j];
                    } else if (j>col) {
                        mCof[i][j-1] = m[i][j];
                    }
                } else if (i>row){
                    if (j<col) {
                        mCof[i-1][j] = m[i][j];
                    } else if (j>col) {
                        mCof[i-1][j-1] = m[i][j];
                    }
                }
            }
        }
        return mCof;
    }
    public static double[][] MatrixCof (double[][] m){
        // Menghasilkan matriks kofaktor
        // Prekondisi m persegi
        double [][] mCof = createMatrix(nBaris(m), nKolom(m));
        if (nBaris(m) == 1){
            mCof[0][0] = m[0][0];
        } else {
            for (int i=0; i<nBaris(mCof); i++){
                for(int j=0; j<nKolom(mCof); j++){
                    int sign;
                    if ((i+j)%2 == 0){
                        sign = 1;
                    } else {
                        sign = -1;
                    }
                    mCof[i][j] = sign*determinan(ElmtCofactor(m, i, j));
                }
            }
        }
        return mCof;
    }
    public static double determinan(double[][] m) {
        // Menerima masukkan matriks dan mengihutung determianannya menggunakan cofaktor
        m = noAugmented(m);
        double det = 0;
        if (m.length == 1) {
            det = m[0][0];
        } else {
            double [][] mElmtCof;
            int sign = 1;
            for (int i=0; i<m.length; i++){
                mElmtCof = ElmtCofactor(m, 0, i);
                det += sign*m[0][i]*determinan(mElmtCof);
                sign *= (-1);
            }
        }
        return det;
    }
}