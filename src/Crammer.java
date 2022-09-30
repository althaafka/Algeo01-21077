public class Crammer extends Matrix {
    public static double[] crammerhasil(double[][] m2,double[][] m1)  {
        double[][] m3,temp;
        double[][] pembanding;
        Double detUmum, hasil;
        
        
        int i,j,k,l;
        m3 = createMatrix(nBaris(m2),nKolom(m2)*nKolom(m2));
        temp = createMatrix(10, 10);
        //System.out.println("Untuk matriks utama");
        //System.out.println(nBaris(m2));
        //System.out.println(nKolom(m2));
        //m2 = bacaMatrix();

        //System.out.println("Untuk matriks hasil");
        //System.out.println(nBaris(m1));
        //System.out.println(nKolom(m1));
        //m1 = bacaMatrix();

        
        

        //Deklarasi array untuk menyimpan nilai sebelumnya 
        Double[] nilaisebelumnya = new Double[nBaris(m2)];
        //Deklarasi array untuk menyimpan nilai-nilai determinan 
        Double[] nilaidet = new Double[nKolom(m2)+1];
        double[] output = new double[nKolom(m2)];
        
        detUmum = Determinan.determinan(m2);
        k = 1;
        while(k <= nKolom(m2))
        {
                for(i = 0;i < nBaris(m2);i++)
                {
                    //Baris m1 dan m2 akan selalu sama
                    nilaisebelumnya[i] = m2[i][(nKolom(m2)-k)];
                    m2[i][nKolom(m2)-k] = m1[i][0];
                }
                j = 0; 
                temp = m2;
                nilaidet[k] = Determinan.determinan(temp);
                for(i = 0;i < nBaris(m2);i++)
                {
                    //Baris m1 dan m2 akan selalu sama
                    m2[i][nKolom(m2)-k] = nilaisebelumnya[i];
                   
                }
                
                k++;
        }
        
        // if(detUmum == 0)
        // {
        //     System.out.println("Tidak Memenuhi syarat");
        // }

        // else
        // {
            int idxoutput = 0;
            for(i = nBaris(m2); i >= 1;i--)
            {
                hasil = nilaidet[i]/detUmum;
                // System.out.print(hasil);
                // System.out.print("\n");
                output[idxoutput]= hasil;
                idxoutput++;
            }
        // }
            return output;
        
    }

    public static void main(String[] args) {
        double[][] m1,m2,m3;

        m1 = bacaMatrix();
        m2 = AugmentedtoSquare(m1);
        m3 = augmentedtoKoef(m1);

        crammerhasil(m2,m3);
        
    }
}


