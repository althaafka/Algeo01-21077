import java.util.Arrays;

public class SPLSolver extends Matrix {

    public static void main (String args[]){
        // double[] a = new double[] {1,0,0};
        // double[] b = new double[] {3,2,0};
        // double[] c = new double[] {0,0,0};
        // double x = -2;
        // // c = addArr(a, b);
        // for (int i = 0; i<c.length; i++){
        //     c[i] = a[i]+b[i];
        // }
        // System.out.println(Arrays.toString(a));
        // System.out.println(Arrays.toString(c));
        



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
    public static String[] splSolution(double[][] m){
        int solution;
        solution = whatSolution(m);
        String[] output = new String[] {"SPL tidak memiliki solusi"};
        switch (solution){
            case 1: 
                double[] result = splUniqueSol(m);
                System.out.println("SPL Memiliki Solusi Unik");
                displayUniqueSol(result);
                output = uniqueSol2Arr(result);
                break;
            case 2:
                System.out.println("SPL Memiliki Solusi Tak Hingga");
                double[][] infresult =  splInfiniteSol(m);
                output = infiniteSol2Arr(infresult);
                displayInfiniteSol(output);
                break;
            case 3:
                System.out.println("SPL Tidak Memiliki Solusi");
                break;
        }
        return output;
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

    public static String[] uniqueSol2Arr (double[] arr){
        String[] output = new String[arr.length] ;
        for (int i =0; i<arr.length; i++){
            output[i] = "X"+(i+1)+" = "+ Double.toString(arr[i]);
        }
        return output;
    }

    public static double[][] splInfiniteSol(double[][] m){
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
                    } else if (j==nKolom(m)-1){
                        result[idxVar][nKolom(result)-1] = m[i][j];
                    }

                    }
                }
            }
        return result;
        }
        
        public static void displayInfiniteSol(String[] result){
            for (int i = 0; i<result.length;i++){
                System.out.println(result[i]);
            }
            // char var = 's';
            // boolean first;
            // for (int i=0; i<nBaris(result); i++){
            //     System.out.print("X"+(i+1)+ " = ");
            //     first = true;
            //     for (int j=0; j<nKolom(result); j++){
            //         if (!isZero(result[i][j])){
            //             if (j==0){
            //                 System.out.print("("+ result[i][j] +")"+ Character.toString((char)((int)(var+j))));
            //                 first = false;
            //             } else if (j==nKolom(result)-1){
            //                 if (!first) System.out.print(" + ");
            //                 System.out.print(result[i][j]);
            //                 first = false;
            //             } else{
            //                 if (!first) System.out.print(" + ");
            //                 System.out.print("("+ result[i][j] +")"+ Character.toString((char)((int)(var+j))));
            //                 first = false;
            //             }
            //         }
            //     }
            //     System.out.println();
            // }
        }

        public static String[] infiniteSol2Arr(double[][] result){
            String[] output = new String [nBaris(result)];
            String str = "";
            char var = 's';
            boolean first;
            for (int i=0; i<nBaris(result); i++){
                str = "X"+ Integer.toString(i+1)+ " = ";
                first = true;
                for (int j=0; j<nKolom(result); j++){
                    if (!isZero(result[i][j])){
                        if(j==nKolom(result)-1){
                            if (!first) str+=" + ";
                            str += Double.toString(result[i][j]);
                        } else{
                            if (!first) str+=" + ";
                            str += "("+ Double.toString(result[i][j]) +")"+Character.toString((char)((int)(var+j)));
                            first = false;
                        }
                    }
                }
                output[i] = str;
            }
            return output;
        }
        
    }


    


