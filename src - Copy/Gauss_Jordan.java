import java.util.Arrays;

public class Gauss_Jordan extends Matrix{

    public static void main(String[] args){
        double[][] m;
        int[] a = new int[]{-1,-1};
        int[] b;
        
    }
    public static int[] nextPivot (double[][] m, int[] pivot){
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
            if (!isZero(m[i][pivot[1]])) {
                m = kaliBaris(m, i, (1/m[i][pivot[1]]));
            }
            for (int k = pivot[0]+1; k<nBaris(m);k++){
                if (!isZero(m[k][pivot[1]])){
                    m = barisKurangNBaris(m, k, pivot[0], m[k][pivot[1]]);
                    
                }
            }
        }
        return m;
    }
    public static double[][] eselonBarisTereduksi(double[][] m){
        int [] pivot = new int[] {-1,-1};
        m = Gauss.eselonBaris(m);
        for(int i=0; i< nBaris(m); i++){
            pivot = nextPivot(m,pivot);
            if (pivot[0]==-1 && pivot[1]==-1){
                return m;
            }
            for (int k = pivot[0]-1; k>=0;k--){
                if (!isZero(m[k][pivot[1]])){
                    m = barisKurangNBaris(m,k,pivot[0],m[k][pivot[1]]);
                }
            }
        }
        return m;
    }

    
}
