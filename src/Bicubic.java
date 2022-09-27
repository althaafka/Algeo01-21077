import java.util.Arrays;

public class Bicubic extends Matrix{

    public static void main(String[] args){
        double[][] m;
        m = bacaMatrix();
        // tulisMatrix(m);
        // System.out.println();
        bicubic(m,1,3);
        
    }

    public static void bicubic(double[][] m,double a,double b){
        double[][] X = new double[16][16];
        int idxRow=0;
        int idxCol=0;
        idxRow = 0;
        for (int y = -1; y<3; y++){
            for (int x =-1; x<3; x++){
                idxCol =0;
                for (int j=0; j<4; j++){
                    for (int i=0; i<4; i++){
                        X[idxRow][idxCol]= Math.pow(x,i)*Math.pow(y,j);
                        idxCol++;
                    }
                }
                idxRow++;
            }
        }
        tulisMatrix(X);
        double [] F = new double[16];
        int idxF=0;
        for (int i =0; i<4; i++){
            for (int j=0; j<4; j++){
                F[idxF] = m[i][j];
                idxF++;
            }
        }
        System.out.println(Arrays.toString(F));

    }
}