class DeterminanGJ extends Matrix {
    public static void main(String[] args) {
        double[][] m;
        m = createMatrix(10, 10);
        System.out.println(nBaris(m));
        System.out.println(nKolom(m));
        m = bacaMatrix();
        determiananGaussJordan (m);

    }

    public static void determiananGaussJordan (double[][] m)
    {
        //INI KAMUS
        double det,temp,copyan;
        int i,j,k,l,x,swapcount;
        //INI INISIALISASI NILAI
        temp = 0;
        det = 1;
        swapcount = 0;
        
        
    
        //INI ALGORITMA

        //Cek nbaris sama nkolom
        //Keadaan 1 ketika nbaris = 1 dan nkolom = 1 maka determinan : m[0][0]
        if(nBaris(m) == 1)
        {
            det = m[0][0];
        }

        else if(nBaris(m) == 2)
        {
            det = m[0][0] * m[1][1] - m[0][1]*m[1][0];
        }

        else 
        {
            //jika nbaris dan nkolom lebih dari 2
            //Akan digunakan reduksi disini 
            //Programnya itu kita harus berusaha membuat segitiga ntah itu segitiga atas atau bawah biar mudah jadiin segitiga atas aja jadi 000 nya di bawah 
            //Proses reduksi nya yang kepikiran gini sih 
            //Misal cek dulu 0 yang paling banyak dimana nah taro di bawah 
            //Terus yang paling kecil elemennya ditaruh di paling atas baris
            //nah misalin aja jumlah 0 dari baris2 itu tuh di array jumlahnol gtuu 

            //Deklarasi array jumlah nol perbaris
            Double[] nilaiindex0 = new Double[nBaris(m)];
            int[] jumlahNol = new int[nBaris(m)];

            for(i = 0; i < nBaris(m);i++)
            {
                jumlahNol[i] = 0;
            }

            //Searching di setiap baris aada berapa jumlahnya

            //Pertama nentuin jumlah 0 kalo 0 nya sama kaya banyak nKolom-1 berarti dia semua nilainya 0
            for(i = 0; i < nBaris(m);i++)
            {
                for(j=0;j<nKolom(m);j++)
                {
                    if(m[i][j] == 0)
                    {
                        jumlahNol[i]++;
                    }

                }
            }

            //ini kalo 0 semua index nya pasti langsung ke bawah
            for(i=0;i<nBaris(m);i++)
            {
                if((jumlahNol[i] == nBaris(m)-1) && i != nBaris(m)-1)
                {
                    for(j=0;j<nKolom(m);j++)
                    {
                        m[i][j] = m[nBaris(m)-1][j];
                    }
                    swapcount++;  
                }
                

                
                
            }
            

            //terus ini cek elemen pertamanya kalo 0 dia diturunin ke bawah 

            for(i = 0; i<nBaris(m)-1;i++)
            {
                while(((m[i][0] > m[i+1][0]) && m[i+1][0] != 0) || (m[i][0]==0 && m[i+1][0]!=0))
                {
                    for(k = i; k < nBaris(m)-1;k++)
                    {
                        for(l = 0; l < nKolom(m);l++)
                        {
                            temp = m[k][l];
                            m[k][l] = m[k+1][l];
                            m[k+1][l] = temp;
                        }
                    
                    }
                    swapcount++;
                    
                }
            }

            //Waktunya reduksi baris 
            //Nah jadi kan udah diurutin yang paling kecil itu di atas itu dijadiin pivot aku bikin array untuk mencakup pivot itu
            Double[] nilaipivot = new Double[nBaris(m)];
            for(j = 0; j <nKolom(m);j++)
            {
                nilaipivot[j] = m[0][j];
            }

            Double[] pembagi = new Double[nBaris(m)];
            for(j=0;j<nKolom(m);j++)
            {
                pembagi[j] = m[1][0]/m[0][j];
            }
            
            Double[] nilaiseharusnya = new Double[nBaris(m)];
            for (k = 0; k < nKolom(m) - 1; k++)
            {
                for (i = k + 1; i < nBaris(m); i++) {
                    if ((m[i][k]) == 0) {
                        continue;
                    }
                    double factor = m[i][k] / m[k][k];
                    if(m[k][k] == 0)
                    {
                        factor = 0;
                    }
    
                    for (j = k; j < nKolom(m); j++) {
    
                        m[i][j] = (m[i][j] - (factor * m[k][j]));
    
                    }
                }
            }

            for(i = 0; i < nBaris(m); i++)
            {
                det *= m[i][i];
                if(swapcount % 2== 0)
                {
                    det *= 1;
                }
                else 
                {
                    det *= -1;
                }       
                
            } 
            
        }
       
        System.out.print(det);

        }        

}
