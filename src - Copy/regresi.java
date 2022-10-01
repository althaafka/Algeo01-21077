public class regresi extends Matrix{
    public static double[][] HasilRegresi(double[][] m, double[][] m1) {
        //Menerima Masukkan sebuah matriks dengan elemen terakhir Y yang dipengaruhi
        double[][] MatrixTransposeInvers,MatrixTransposeY,m2,temp;
        int i,j;
        //m = bacaMatrix();
        m2 = createMatrix(nBaris(m),nKolom(m));
        m1 = createMatrix(nBaris(m),1);
        //tulisMatrix(m);
        //Untuk indeks kolom terakhir pisahkan menjadi matriks Y saja
        for(i = 0; i < nBaris(m); i++)
        {
            m1[i][0] = m[i][nKolom(m)-1];
        }
    
        
        //kemudian matriks ini disajikan tanpa akhiran Y dan bagian depan di insert 1
        
        for(j=1; j < nKolom(m);j++)
        {
            temp = m;
            for(i = 0; i < nBaris(m);i++)
            {
                m2[i][j] = m[i][j-1];
            }
            m = temp;
        }
        

        for(i = 0; i <nBaris(m);i++)
        {
            m2[i][0] = 1;
        }
        
        MatrixTransposeInvers = inversGauss.InversGauss(kaliMatriks(transpose(m2), m2));
        MatrixTransposeY = kaliMatriks(transpose(m2), m1);
        return kaliMatriks(MatrixTransposeInvers, MatrixTransposeY);

        //Nilai nilai yang dihasilkan 
        /*-16.102612596765084   b0      
        0.48772871217454394    b1   
        0.10058937368053478         b2*/
        
    }
    
}
