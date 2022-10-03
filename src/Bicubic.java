import java.util.Arrays;
public class Bicubic extends Matrix{

    public static double bicubic(double[][] m,double a,double b){
        // Mengembalikan nilai f(a,b) melalui metode bicubic interpolation
        double[][] X = new double[16][16];
        int idxRow=0;
        int idxCol=0;
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
        // tulisMatrix(X);
        double [][] F = new double[16][1];
        int idxF=0;
        for (int j =0; j<4; j++){
            for (int i=0; i<4; i++){
                F[idxF][0] = m[i][j];
                idxF++;
            }
        }
        // tulisMatrix(F);
        X = inversGauss.InversGauss(X);
        // tulisMatrix(X);
        double[][] A;
        A = kaliMatriks(X, F);
        // tulisMatrix(A);


        double fx = 0;
        int idA = 0;
        for (int j = 0; j<4; j++){
            for (int i =0; i<4; i++){
                fx += A[idA][0]*(Math.pow(a,i))*(Math.pow(b,j));
                idA++;
            }
        }
        // System.out.println(fx);
        return fx;

    }
    static public double[][] bacaMatrixBicubic(){
        // input Matrix 4x4 melalui keyboard
        double [][] m;
        m = createMatrix(4, 4);
        for (int i=0; i<4;i++){
            for (int j=0; j<4; j++){
                System.out.print("f("+(i-1)+","+(j-1)+"): ");
                m[i][j] = scan.nextDouble();
            }
        }
        return m;
    }
    static public double[] bacaABBicubic(){
        // input nilai a,b melalui keyboard
        double[] ab = new double[2];
        System.out.print("a: ");
        double a = scan.nextDouble();
        System.out.print("b: ");
        double b = scan.nextDouble();
        while (a<0 || b<0 || a>1 || b>1){
            System.out.print("a: ");
            a = scan.nextDouble();
            System.out.print("b: ");
            b = scan.nextDouble();
        }
        ab[0]=a;
        ab[1]=b;
        return ab;
    }
        


    static public double[][] fileMatrixBicubic(double[][] m){
        // mengembalikan Matrix 4x4 dari matrix inputan file
        double[][] output = new double[4][4];
        for (int i=0; i<4;i++){
            for (int j=0; j<4; j++){
                output[i][j] = m[i][j];
            }
        }
        return output;
    }

    static public double[] fileABBicubic(double[][] m){
        // mengembalikan nilai a,b dari matrix inputan file
        double[] ab = new double[2];
        ab[0] = m[4][0];
        ab[1] = m[4][1];
        return ab;
    }
}