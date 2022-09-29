import java.util.Arrays;

public class SPLSolver extends Matrix {

    public static void main (String args[]){
        double[] a = new double[] {1,0,0};
        double[] b = new double[] {3,2,0};
        double[] c = new double[] {0,0,0};
        double x = -2;
        // c = addArr(a, b);
        for (int i = 0; i<c.length; i++){
            c[i] = a[i]+b[i];
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(c));
    }
    public static int whatSolution(double[][] m){
        // Jika jumlah persamaan lebih sedikit dari jumlah variabel
        if ((nBaris(m) < nKolom(m)-1) || isRowZero(m, nBaris(m)-1)) return 2;
        // boolean noSolution = true;
        for (int j=0;j<nKolom(m)-1;j++){
            if (!isZero(m[nBaris(m)-1][j])) {
                // noSolution = false;
                return 1;
            }
        }
        return 3;
    }
    public static void splSolution(double[][] m){
        int solution;
        solution = whatSolution(m);
        switch (solution){
            case 1: 
                double[] result = splUniqueSol(m);
                System.out.println("unik");
                displayUniqueSol(result);
                break;
            case 2:
                System.out.println("tak hingga");
                splInfiniteSol(m);
                break;
            case 3:
                System.out.println("no solution");
                break;

        }
    }

    public static double[] splUniqueSol (double[][] m){
        // Mengembalikan array yang elemen elemennya merupakan penyelesaian dari SPL
        double[] result = new double[nKolom(m)-1];
        for (int i=nBaris(m)-1; i>=0; i--){
            result[i] = m[i][nKolom(m)-1];
            for (int j=i+1;j<nKolom(m)-1;j++){
                if (!isZero(m[i][j])){
                    if (i!=nBaris(m)-1){
                        result[i]-= m[i][j]*result[j];
                    }
                }
            }
        }
        return result;
    }

    public static void displayUniqueSol (double[] result){
        for (int i =0; i<result.length;i++){
            System.out.print("X"+(i+1)+" = "+ result[i] +"\n");
        }
    }

    // public static void splInfiniteSol(double[][] m){
    //     String[] result = new String [nKolom(m)-1];
    //     char[] var = new char [nKolom(m)-1];
    //     int[] idxLeading1 = new int[] {-1,-1};
    //     char firstVar = 's';
    //     for (int j=0;j<nKolom(m)-1;j++){
    //         result[j]="";
    //     }
    //     m = Gauss_Jordan.eselonBarisTereduksi(m);
    //     for (int j=0; j<nKolom(m)-1;j++){
    //         idxLeading1 = Gauss.idxLeadingOne(m, idxLeading1);
    //         if (idxLeading1[1]!=j){
    //             var[j]= (char)((int)firstVar+j);
    //             result[j] = Character.toString(var[j]);
    //         }
    //     }
    //     idxLeading1 = new int[] {-1,-1};
    //     for (int i=nBaris(m)-1; i<=0; i++){
    //         idxLeading1 = Gauss.idxLeadingOne(m,idxLeading1);
    //         result[0]= Double.toString(m[i][nKolom(m)-1]);
    //         for(int j=0; i<nKolom(m)-1; j++){
    //             if(!isZero(m[i][j]) && result[j]!=""){
    //                 result[j]+= result
    //             }
    //         }
    //     }
    public static void splInfiniteSol(double[][] m){
        m = Gauss_Jordan.eselonBarisTereduksi(m);
        double[][] result;
        int[] idxLeading1 = new int[] {-1,-1};
        int[] idxLeading1Temp = idxLeading1;
        // Menghitung jumlah parameter, jumlah parameter = jumlah kolom yang tidak memiliki leading 1
        int count = 0;
        for (int j=0; j<nKolom(m)-1;j++){
            idxLeading1Temp = Gauss.idxLeadingOne(m, idxLeading1);
            if (idxLeading1Temp[1]!=j){
                count++;
            } else{
                idxLeading1 = idxLeading1Temp;
            }
        }
            // Membuat matrix dengan jumlah baris = jumah variabel, dan jumlah kolom = jumlah parameter + 1,
            // Matrix result merepresentasikan koefisien parametrik tiap variabel yg ada
            // e.g 
            // result = 1 2 3
            //          4 5 6
            // artinya : x1 = 1 + 2s + 3t
            //           x2 = 4 + 5s + 6t
        result = createMatrix(nKolom(m)-1, count+1);


        idxLeading1 = new int[] {-1,-1};
        idxLeading1Temp = idxLeading1;
        int idxPar =0;
        for (int j=0; j<nKolom(m)-1;j++){
            idxLeading1Temp = Gauss.idxLeadingOne(m, idxLeading1);
            if (idxLeading1Temp[1]!=j){
                for (int k=0; k<nKolom(result);k++){
                    if (k==idxPar){
                        result[j][idxPar] = 1;
                    } else{
                        result[j][k]=0;
                    }
                }
                idxPar++;
            } else{
                idxLeading1 = idxLeading1Temp;
            }
        }
        tulisMatrix(result);
        // Mengisi matrix result



        int idxVar = 0;
        double[] arrTemp = new double[result[0].length];
        idxLeading1 = new int[] {-1,-1};
        for (int i=nBaris(m)-1;i>=0;i--){
            if (!isRowZero(m,i)){
                idxLeading1 = new int[] {i-1,-1};
                idxLeading1 = Gauss.idxLeadingOne(m,idxLeading1);
                idxVar = idxLeading1[1];
                for (int j = idxLeading1[1]+1; j<nKolom(m);j++){
                    if (( j!=(nKolom(m)-1)) && (!isZero(m[i][j]))){
                        for (int k = 0; k<result[j].length; k++){
                            arrTemp[k] = -m[i][j] * result[j][k];
                        }
                        for (int l = 0; l <result[j].length; l++){
                            result[idxVar][l]+= arrTemp[l];
                        }

                        // result[idxVar] = addArr(result[idxVar],multiplyArr(result[j],m[i][j]));
                        tulisMatrix(result);
                        System.out.println();
                    } else if (j==nKolom(m)-1){
                        result[idxVar][nKolom(result)-1] = m[i][j];
                    }

                    }

                        // if (j==nKolom(m)-1) {
                        //     result[idxVar][nKolom(result)-1] = m[i][j];
                        // }
                }
            }
        

        tulisMatrix(result);
        char var = 's';
        String plus = "+";
        String min = "-";
        for (int i = 0; i<nBaris(result); i++){
            for (int j=0; j<nKolom(result); j++){
                if (!isZero(result[i][j])){
                    if (j==0){
                        
                        System.out.print("X"+(i+1)+" = "+result[i][j]+ Character.toString((char)((int)(var+j))));
                    } else if (j==nKolom(result)-1){
                        if (!isZero(result[i][j])){
                            System.out.print(" + "+ result[i][j]);
                        }
                    } else{
                        if (result[i][j]==1){
                            System.out.print(" "+ Character.toString((char)((int)(var+j))));
                        }
                        System.out.print(" "+result[i][j]+ Character.toString((char)((int)(var+j))));
                    }
                }
            }
            System.out.print(", ");
        }
            // int idxVar=0;
            // idxPar=0;
            // idxLeading1 = new int[] {-1,-1};
            // for (int i=nBaris(m)-1;i>=0;i--){
            //     if (!isRowZero(m,i)){
            //         idxLeading1[0]=i;
            //         idxLeading1 = Gauss.idxLeadingOne(m,idxLeading1);
            //         idxVar = idxLeading1[1];
            //         for (int j = idxLeading1[1]+1; j<nKolom(m);j++){
            //             result[idxVar][idxPar]
            //         }
            //     }
            //     idxLeading1 = Gauss.idxLeadingOne(m,idxLeading1);
            //     if (idxLeading1[0]== -1 && idxLeading1[1]==-1){
            //     }
            // }
        }

        
    }


    


