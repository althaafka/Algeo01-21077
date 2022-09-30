public class InterpolasiPolinom extends Matrix {
    public static void main(String[] args){
        double[][] mInt, mAug, mRes;
        int n = scan.nextInt();
        mInt = inputInterpolasi(n);
        tulisMatrix(mInt);
        mAug = InputtoAugmented(mInt);
        tulisMatrix(mAug);
        mRes = resultPolinom(mAug);
        tulisMatrix(mRes);
        // Input nilai x untuk estimasi
        double x = scan.nextDouble();
        double result = EstimasiFungsi(mRes, x);
        System.out.println("Hasil " + result);
    }
    public static double[][] inputInterpolasi(int n){
        double[][] mInput = createMatrix(n, 2);
        for (int i=0; i<nBaris(mInput);i++){
            for (int j=0; j<nKolom(mInput); j++){
                mInput[i][j] = scan.nextDouble();
            }
        }
        return mInput;
    }

    public static double[][] InputtoAugmented(double[][] mInput){
        // Mengubah input interpolasi polinom menjadi matriks augmented
        double[][] mAug = createMatrix(nBaris(mInput), nBaris(mInput)+1);
        for (int i=0; i<nBaris(mAug);i++) {
            for (int j = 0; j < nKolom(mAug); j++) {
                if (j == nKolom(mAug)-1) {
                    mAug[i][j] = mInput[i][1];
                } else {
                    mAug[i][j] = Math.pow(mInput[i][0], j);
                }
            }
        }
        return mAug;
    }

    public static double[][] resultPolinom(double[][] mInterpolasi){
        double[][] mResult = Gauss.eselonBaris(mInterpolasi); // Gunakan gauss hasilkan result

        // Dihasilkan matriks hasil x
        double[][] x = createMatrix(nBaris(mResult), 1);
        for (int i = 0; i < nBaris(x); i++) {
            x[i][0] = mResult[i][nKolom(mResult)-1];
        }

        return x;
    }

    public static double EstimasiFungsi(double[][] a, double x){
        double result = 0;
        for (int i = 0; i < nBaris(a); i++) {
            result += (Math.pow(x, i) * a[i][0]);
            System.out.println("Mencari estimasi = " + a[i][0] + "*" + Math.pow(x, i));
        }
        System.out.println("\n");
        return result;
    }
}
