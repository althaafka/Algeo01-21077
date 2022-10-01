import java.util.Arrays;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

public class RegMatrix extends Matrix {
    public static String[] HasilRegresi(double[][] mat){
        //Menambahkan angka 1 di kolom pertama
        //Kemudian dijadikan matriks augmented bersamaan dengan Y 
        String[] output = new String[2];
        double[][] m,m1,m2,m3,m4;
        m = createMatrix(nBaris(mat), nKolom(mat)-1);
        for (int i=0; i<nBaris(m);i++){
            for(int j=0; j<nKolom(m);j++){
                m[i][j] = mat[i][j+1];
            }
        }
        m2 = createMatrix(nBaris(mat),  1);
        for (int i=0; i<nBaris(m2);i++){
            m2[i][0] = mat[i][0];
        }
        tulisMatrix(m);
        tulisMatrix(m2);
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

        m4 = Gauss_Jordan.eselonBarisTereduksi(Gauss_Jordan.eselonBaris(m3));
        tulisMatrix(m4);
        double[] koefreg = new double[nKolom(m) + 1];

        for(i = 0; i <= nKolom(m);i++)
        {
            koefreg[i] = m4[i][nKolom(m) + 1];
        }
        //Mengeluarkan persamaaan regresi linier berganda
        System.out.println("Persamaan regresi linier berganda adalah : ");
        System.out.printf("y = %f", koefreg[0]);
        output[0] = "y = " + koefreg[0];
        for (i = 1; i < koefreg.length; i++) {
            if (koefreg[i] > 0) {
                System.out.printf(" + %f x%d", koefreg[i], i);
                output[0] += " + "+ koefreg[i] + " x" + i;
            } else {
                System.out.printf(" + %f x%d", koefreg[i], i);
                output[0] += " + "+ koefreg[i] + " x" + i;
            }
        }

        //Penaksiran nilai fungsi 
        double[] Hasiltaksiran = new double[koefreg.length];
        System.out.println("\nMenaksir nilai fungsi");
        
        System.out.printf("Masukkan %d peubah ", koefreg.length - 1);
        
        for (i = 0; i < koefreg.length - 1; i++) {
            Hasiltaksiran[i] = scan.nextDouble();
        }
        double hasil = koefreg[0];
        for (i = 0; i < koefreg.length - 1; i++) {
            hasil += koefreg[i + 1] * Hasiltaksiran[i];
        }
        output[1]= "Hasil taksirannya adalah "+Double.toString(hasil);
        System.out.printf("%f",hasil);
        System.out.println(Arrays.toString(output));
        return output;

    }

    private static void println(String string, double d, int i) {
    }

    public static void main(String[] args) {
        String fileName;
        fileName= File.inputFileName();
        double[][] m;
        m = File.fileMatrix(fileName);
        Matrix.tulisMatrix(m);
        // double[][] m,m2;
        // m = bacaMatrix();
        // m2 = createMatrix(nBaris(m), 1);
        // m2 = bacaMatrix();
        HasilRegresi(m);
    }
}