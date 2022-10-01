public class RegMatrix extends Matrix {
    public static void HasilRegresi(double[][] m, double[][] m2){
        //Menambahkan angka 1 di kolom pertama
        //Kemudian dijadikan matriks augmented bersamaan dengan Y 
        double[][] m1,m3,m4;
        int i,j,k,l; 
        m1 = createMatrix(nBaris(m), nKolom(m)+2);
        m3 = createMatrix(nKolom(m)+1, nKolom(m)+2);
        
        for(i = 0; i < nBaris(m1);i++)
        {
            m1[i][0] = 1;
        }

        for (i = 0; i < nBaris(m); i++) 
        {
            for (j = 1; j <= nKolom(m); j++) 
            {
                m1[i][j] = m[i][j - 1];
            }

        }

        for(i = 0; i < nBaris(m); i++)
        {
            m1[i][nKolom(m)+1] = m2[i][0];
        }

   

        //Setelah itu sesuai dengan rumus yang ada pada modul,kita buat matriks
        //Yang nantinya bisa diselesaikan dengan gauss Jordan 
        for(i = 0; i < nKolom(m)+1;i++)
        {
            for(j = 0; j < nKolom(m)+1;j++)
            {
                m3[i][j] = 0;
            }
        }
        for(i = 0; i <= nKolom(m); i++)
        {
            for(j = 0; j <= nKolom(m)+1;j++)
            {
                for(k = 0; k <nBaris(m);k++)
                {
                    m3[i][j] = m3[i][j]+(m1[k][i] * m1[k][j]);
                }
            }
        }
      
        tulisMatrix(m3);

        m4 = Gauss_Jordan.eselonBarisTereduksi(m3);
        tulisMatrix(m3);
        double[] koefreg = new double[nKolom(m) + 1];

        for(i = 0; i <= nKolom(m);i++)
        {
            koefreg[i] = m4[i][nKolom(m) + 1];
            System.out.println(koefreg[i]);
            System.out.println("\n");
    
        }
        

    }

    public static void main(String[] args) {
        double[][] m,m2;
        m = bacaMatrix();
        m2 = createMatrix(nBaris(m), 1);
        m2 = bacaMatrix();
        HasilRegresi(m, m2);
    }
}