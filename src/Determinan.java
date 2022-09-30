public class Determinan extends Matrix {
    public static void main(String[] args){
        double[][] m1;
        m1 = bacaMatrix();
        tulisMatrix(m1);
        System.out.println("hasil " + Determinan.determinan(m1));
    }
    public static double[][] ElmtCofactor (double[][] m, int row, int col){
        // Menghasilkan elemen matriks kofaktor [row][col]
        // Prekondisi m persegi dan nBaris > 1 nKolom > 1
        double[][] mCof;
        mCof = createMatrix(nBaris(m)-1, nKolom(m)-1);

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
        // Det = A01C01 + A02C02 + ... + A0nC0n
        if (nBaris(m) != nKolom(m)) {
            // mengubah matriks augmented menjadi matriks persegi
            m = AugmentedtoSquare(m);
        }
        double det = 0;
        if (m.length == 1) {
            det = m[0][0];
        } else if (m.length>1){
            double [][] mElmtCof;
            int sign;
            for (int i=0; i<nKolom(m); i++){
                if (i%2==0) sign = 1;
                else sign = -1;

                mElmtCof = ElmtCofactor(m, 0, i);
                det += sign*m[0][i]*determinan(mElmtCof);
            }
        }
        return det;
    }
}