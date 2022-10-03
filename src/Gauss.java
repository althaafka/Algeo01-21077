import java.util.Arrays;

public class Gauss extends Matrix{


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
