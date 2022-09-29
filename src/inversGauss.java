
public class inversGauss extends Gauss_Jordan {

   public static void main(String[] args) 
   {
        double[][] m, midentitas,m1,m2;
        int i,j;
        m = createMatrix(10, 10);
        System.out.println(nBaris(m));
        System.out.println(nKolom(m));
        m = bacaMatrix();
        m1 = createMatrix(nBaris(m),(2*nKolom(m)));
        m2 = createMatrix(nBaris(m), nKolom(m));

        //Buat Matriks identitasnya 
        midentitas = createMatrix(nBaris(m),nKolom(m));

        for(i = 0; i<nBaris(m);i++)
        {
            for(j = 0; j < nKolom(m); j++)
            {
                if(i == j)
                {
                    midentitas[i][j] = 1;
                }

                else 
                {
                    midentitas[i][j] = 0;
                }
            }
        }
       
        for(i = 0; i < nBaris(m1); i++)
        {
            for(j=0; j < nKolom(m1);j++)
            {
                if((j < nKolom(m)))
                {
                    m1[i][j] = m[i][j];
                }

                else 
                {
                    m1[i][j] = midentitas[i][j-nKolom(m)];
    
                }
            
            }
        }
        eselonBarisTereduksi(eselonBaris(m1));
        //Ini tahap eselon baris tereduksi copas dari yang altha

        tulisMatrix(m1);
        
        for (i = 0; i < nBaris(m2); i++) {
            for (j = 0; j < nKolom(m2); j++) 
            {
                m2[i][j] = eselonBarisTereduksi(eselonBaris(m1))[i][j + nKolom(m)];
            }
          }
          tulisMatrix(m2);


        }
}

        
    
       

    


