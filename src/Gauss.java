import java.util.Arrays;

public class Gauss extends Matrix{

    public static void main(String[] args){
        double[][] m;
        int[] a = new int[]{-1,-1};
        int[] b;
        m = bacaMatrix();
        // m = eselonBaris(m);
        System.out.println();
        tulisMatrix(m);
        b = nextPivot(m, a);
        System.out.println(Arrays.toString(b));
        m = eselonBaris(m);
        tulisMatrix(m);
    }
    public static double[][] eselonBaris(double[][] m){
        int [] pivot = new int[] {-1,-1};

        for (int i = 0; i < nBaris(m); i++){
            pivot = nextPivot(m,pivot);

            if (pivot[0]==-1&&pivot[1]==-1){
                return m;
            } 
            if (i != pivot[0]){
                m = swapBaris(m, i, pivot[0]);
                pivot[0] = i;
                
            }
            m = kaliBaris(m, i, (1.0/m[i][pivot[1]]));
            for (int k = pivot[1]+1; k<nBaris(m);k++){
                if (m[k][pivot[1]]!=0){
                    m = barisKurangNBaris(m, k, pivot[0], m[k][pivot[1]]);
                }
            }
        }
        return m;
    }
    public static int[] nextPivot (double[][] m, int[] pivot){
        int [] idx = new int[] {-1,-1};
        for (int j = pivot[1]+1; j<nKolom(m);j++){
            for (int i = pivot[0]+1; i<nBaris(m);i++){
                if (m[i][j]!=0) {
                    idx[0]=i;
                    idx[1]=j;
                    return idx;
                }
            }
        }
        return idx;
    }

}
