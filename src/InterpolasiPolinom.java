import java.util.Arrays;

public class InterpolasiPolinom extends Matrix {
    public static void main(String[] args){
        double[][] mInt, mAug;
        double[] mRes;
        int n = scan.nextInt();
        mInt = inputInterpolasi(n);
        tulisMatrix(mInt);
        mAug = InputtoAugmented(mInt);
        tulisMatrix(mAug);
        mRes = resultPolinom(mAug);
        System.out.println(Arrays.toString(mRes));
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

    public static double[] resultPolinom(double[][] mInterpolasi){
        double[] mResult = SPLSolver.splUniqueSol(Gauss.eselonBaris(mInterpolasi)); // Gunakan gauss hasilkan result
        return mResult;
    }

    public static double EstimasiFungsi(double[] a, double x){
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += (Math.pow(x, i) * a[i]);
            System.out.println("Mencari estimasi = " + a[i] + "*" + Math.pow(x, i));
        }
        System.out.println("\n");
        return result;
    }
}
