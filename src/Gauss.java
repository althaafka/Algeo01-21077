import java.util.Arrays;

public class Gauss extends Matrix{

    public static void main(String[] args){
        // double[][] m;
        // double[] spl;
        // int[] a = new int[]{0,-1};
        // int[] b;
        // m = bacaMatrix();
        // // m = eselonBaris(m);
        // System.out.println();
        // tulisMatrix(m);
        // // m = eselonBaris(m);
        // // tulisMatrix(m);
        // // spl = SPLSolver.splUniqueSol(m);
        // // System.out.println(Arrays.toString(spl));
        // // m = Gauss_Jordan.eselonBarisTereduksi(eselonBaris(m));
        // // System.out.println();
        // m = eselonBaris(m);
        // System.out.println();
        // tulisMatrix(m);
        // System.out.println();
        // // b = idxLeadingOne(m, a);
        // // System.out.println(Arrays.toString(b));
        // // if (isRowZero(m,2)) System.out.println("zero");
        // SPLSolver.splSolution(m);

        String fileName;
        fileName= File.inputFileName();
        // int x = fileRow(fileName);
        // int y = fileCol(fileName);
        // System.out.println(y);
        double[][] m;
        m = File.fileMatrix(fileName);
        Matrix.tulisMatrix(m);
        m = eselonBaris(m);
        SPLSolver.splSolution(m);
    }
    public static double[][] eselonBaris(double[][] m){
        int [] pivot = new int[] {-1,-1};

        for (int i = 0; i < nBaris(m); i++){
            pivot = idxLeadingOne(m,pivot);

            // Elemen setelah pivot semuanya 0
            if (pivot[0]==-1&&pivot[1]==-1){
                return m;
            } 

            // pivot berada pada baris lain
            if (i != pivot[0]){
                m = swapBaris(m, i, pivot[0]);
                pivot[0] = i;
                
            }

            // melakukan operasi OBE
            if (!isZero(m[i][pivot[1]])) {
                m = kaliBaris(m, i, (1/m[i][pivot[1]]));
            }
            for (int k = pivot[0]+1; k<nBaris(m);k++){
                if (m[k][pivot[1]]!=0){
                    m = barisKurangNBaris(m, k, pivot[0], m[k][pivot[1]]);
                }
            }
        }
        return m;
    }
    public static int[] idxLeadingOne (double[][] m, int[] pivot){
        // Mengembalikan index elemen yang akan menjadi satu utama berikutnya
        int [] idx = new int[] {-1,-1};
        for (int j = pivot[1]+1; j<nKolom(m);j++){
            for (int i = pivot[0]+1; i<nBaris(m);i++){
                if (!isZero(m[i][j])) {
                    idx[0]=i;
                    idx[1]=j;
                    return idx;
                }
            }
        }
        return idx;
    }



}
