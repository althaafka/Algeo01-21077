
public class inversGauss extends Matrix {

    public static void main(String[] args){
        double[][] m1,m2;
        String fileName;
        System.out.println("input file belum jadii");
        fileName = File.inputFileName();
        m1 = File.fileMatrix(fileName);
        // m1 = bacaMatrix();
        m2 = InversGauss(m1);
        tulisMatrix(m2);


    }

    public static double[][] InversGauss(double[][] m){
        double[][] midentitas,m1,m2;

        //Matriks Identitas
        midentitas = createMatrix(nBaris(m),nKolom(m));
        for(int i = 0; i<nBaris(m);i++){
            for(int j = 0; j < nKolom(m); j++){
                if (i == j) midentitas[i][j] = 1;
                else midentitas[i][j] = 0;
            }
        }

        m1 = createMatrix(nBaris(m), 2*nKolom(m));
        for(int i = 0; i<nBaris(m1);i++){
            for(int j = 0; j <nKolom(m1); j++){
                if (j<nKolom(m)){
                    m1[i][j] = m[i][j];
                } else m1[i][j] = midentitas[i][j-nKolom(m)];
            }
        }
       

        m1 = Gauss_Jordan.eselonBarisTereduksi(m1);

        m2 = createMatrix(nBaris(m), nKolom(m));
        for (int i=0; i<nBaris(m1); i++){
            for (int j=nKolom(m); j<nKolom(m1); j++){
                m2[i][j-nKolom(m)] = m1[i][j];
            }
        }
        return (m2);

    }
}

        
    
       

    


