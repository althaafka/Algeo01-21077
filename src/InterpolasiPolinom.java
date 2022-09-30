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
            System.out.println("Titik "+ (i+1));
            for (int j=0; j<nKolom(mInput); j++){
                if(j==0) System.out.print("x"+(i+1)+": ");
                if(j==1) System.out.print("y"+(i+1)+": ");
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
            // System.out.println("Mencari estimasi = " + a[i] + " * " + Math.pow(x, i));
        }
        System.out.println();
        return result;
    }

    public static void displayFx(double[] mRes){
        System.out.print("f(x) = ");
        boolean first = true;
        for(int i=0; i < mRes.length; i++){
            if (!isZero(mRes[i])){
                if (i==0){
                    System.out.print(mRes[i]);
                    first=false;
                } else{
                    if(!first) {
                        System.out.print(" + ");
                        first = false;
                    }
                    System.out.print(mRes[i] + " x^"+ (i) );
                    first = false;
                }
                // if (!first) {
                //     System.out.print(" + ");
                // } else if (i==0) {
                //     System.out.print(mRes[i]);
                //     first=false;
                // } else {
                //     System.out.print(mRes[i] + " x^"+ (i+1) );
                //     first = false;
                // }
            }
        }
        System.out.println();
    }
    public static void inputInterpolasiFull(){
        System.out.print("Banyak titik: ");
        int n = scan.nextInt();
        while (n<0){
            System.out.println("Banyak titik harus >0");
            n = scan.nextInt();
        }
        double[][] mInt, mAug;
        double[] mRes;
        mInt = inputInterpolasi(n);
        mAug = InputtoAugmented(mInt);
        mRes = resultPolinom(mAug);

        System.out.print("Nilai x yang akan dicari: ");
        double x = scan.nextDouble();
        double result = EstimasiFungsi(mRes, x);
        displayFx(mRes);
        System.out.println("f("+x+") = " + result);
        System.out.println();
    }

    public static void interpolasiFile(double[][] m){
        double[][] mInt, mAug;
        double[] mRes;
        mInt = m;
        mAug = InputtoAugmented(mInt);
        mRes = resultPolinom(mAug);
        displayFx(mRes);
        System.out.print("Nilai x yang akan dicari: ");
        double x = scan.nextDouble();
        double result = EstimasiFungsi(mRes, x);
        System.out.println("f("+x+") = " + result);
        System.out.println();
    }
}
