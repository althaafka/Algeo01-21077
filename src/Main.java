class Main extends Menu{
    public static void main(String[] args){

        int opt;
        double[][] m;
        double det;
        boolean run=true;
        while (run){
        displayMenu();
        opt = optionInput(1, 7);
        switch(opt){
            case 1: //SPL
                System.out.println("Sistem Persamaan Liniear");
                displayMenuInput();
                opt = optionInput(1,2);
                switch(opt){
                    case 1: //input keyboard
                        m = Matrix.bacaMatrix();
                        break;
                    case 2: //input file
                        String fileName;
                        fileName = File.inputFileName();
                        m = File.fileMatrix(fileName);
                        break;
                    default:
                        m = Matrix.bacaMatrix();
                }
                displayMenuSPL();
                opt = optionInput(1, 4);
                switch(opt){
                    case 1: //gauss
                        System.out.println("SPL Metode Gauss");
                        m = Gauss.eselonBaris(m);
                        Matrix.tulisMatrix(m);
                        SPLSolver.splSolution(m);
                        System.out.println();
                        break;
                    case 2: //gauss-jordan
                        System.out.println("SPL Metode Gaus Jordan");
                        m = Gauss_Jordan.eselonBarisTereduksi(m);
                        Matrix.tulisMatrix(m);
                        SPLSolver.splSolution(m);
                        System.out.println();
                        break;
                    case 3: //matriks invers
                        System.out.println("SPL Metode Matrix Inverse");
                        if (DeterminanGJ.determiananGaussJordan(Matrix.AugmentedtoSquare(m))==0){
                            System.out.println("Matrix memiliki determinan = 0");
                            System.out.println("Matrix tidak dapat diselesaikan dengan metode ini");
                            System.out.println();
                        } else{
                            SPLSolver.displayUniqueSol(SolusiBalikan.SolveBalikan(m));
                            System.out.println();
                        }
                        break;
                    case 4: //crammer
                        System.out.println("SPL Metode Crammer");
                        if (DeterminanGJ.determiananGaussJordan(Matrix.AugmentedtoSquare(m))==0){
                            System.out.println("Matrix memiliki determinan = 0");
                            System.out.println("Matrix tidak dapat diselesaikan dengan metode ini");
                            System.out.println();
                        } else{
                            double[][] m1 = Matrix.AugmentedtoSquare(m);
                            double[][] m2 = Matrix.augmentedtoKoef(m);
                            SPLSolver.displayUniqueSol(Crammer.crammerhasil(m1,m2));
                            System.out.println();
                        }
                        break;
                }
                displayMenuSave();
                opt = optionInput(1, 2);
                switch(opt){
                    case 1:
                        System.out.println("Simpan Hasil");
                        break;
                    case 2:
                        break;
                }
                break;
            case 2: //determinan
                System.out.println("Determinan");
                boolean valid = false;
                m = new double[][] {};
                while (!valid){
                displayMenuInput();
                opt = optionInput(1,2);
                switch(opt){
                    case 1: //input keyboard
                        m = Matrix.bacaMatrixSquare();
                        valid = true;
                        break;
                    case 2: //input file
                        String fileName;
                        fileName = File.inputFileName();
                        m = File.fileMatrix(fileName);
                        if (Matrix.nBaris(m)!=Matrix.nKolom(m)) {
                            System.out.println("Maaf Matrix harus matrix persegi");
                            System.out.println();
                        } else {valid = true;}
                        break;
                    default: m = Matrix.bacaMatrixSquare();
                }
                }
                displayMenuDet();
                opt = optionInput(1, 2);
                switch(opt){
                    case 1: //Reduksi Baris
                        System.out.println("Determinan Metode Reduksi Baris");
                        det = DeterminanGJ.determiananGaussJordan(m);
                        System.out.println(det);
                        System.out.println();
                        break;
                    case 2:
                        System.out.println("Determinan Ekspansi Kofaktor");
                        det = Determinan.determinan(m);
                        System.out.println(det);
                        System.out.println();
                        break;
                }
                displayMenuSave();
                opt = optionInput(1, 2);
                switch(opt){
                    case 1:
                        System.out.println("Simpan Hasil");
                        break;
                    case 2:
                        break;
                }
                break;
            case 3: //Invers
                System.out.println("Matrix Invers");
                valid = false;
                m = new double[][] {};
                while (!valid){
                displayMenuInput();
                opt = optionInput(1,2);
                switch(opt){
                    case 1: //input keyboard
                        m = Matrix.bacaMatrixSquare();
                        valid = true;
                        break;
                    case 2: //input file
                        String fileName;
                        fileName = File.inputFileName();
                        m = File.fileMatrix(fileName);
                        if (Matrix.nBaris(m)!=Matrix.nKolom(m)) {
                            System.out.println("Maaf Matrix harus matrix persegi");
                            System.out.println();
                        } else {valid = true;}
                        break;
                    default: m = Matrix.bacaMatrixSquare();
                }
                }
                displayMenuInverse();
                opt = optionInput(1, 2);
                switch(opt){
                    case 1: //el gaus jordan
                        System.out.println("Matrix Invers Metode Gauss Jordan");
                        m = inversGauss.InversGauss(m);
                        Matrix.tulisMatrix(m);
                        System.out.println();
                        break;
                    case 2:
                        System.out.println("Matrix Invers Metode Kofaktor");
                        m = Invers.InversCofactor(m);
                        Matrix.tulisMatrix(m);
                        System.out.println();
                        break;
                }
                displayMenuSave();
                opt = optionInput(1, 2);
                switch(opt){
                    case 1:
                        System.out.println("Simpan Hasil");
                        break;
                    case 2:
                        break;
                }
                break;
            case 4:
                System.out.println("Interpolasi Polinom");
                displayMenuInput();
                opt = optionInput(1,2);
                switch(opt){
                    case 1: //input keyboard
                        InterpolasiPolinom.inputInterpolasiFull();
                        break;
                    case 2: //input file
                        String fileName;
                        fileName = File.inputFileName();
                        System.out.println();
                        m = File.fileMatrix(fileName);
                        InterpolasiPolinom.interpolasiFile(m);
                        break;
                    default: m = Matrix.bacaMatrixSquare();
                }
                displayMenuSave();
                opt = optionInput(1, 2);
                switch(opt){
                    case 1:
                        System.out.println("Simpan Hasil");
                        break;
                    case 2:
                        break;
                }
                break;
            case 5:
                System.out.println("Bicubic Interpolation");
                displayMenuInput();
                opt = optionInput(1,2);
                switch(opt){
                    case 1: //input keyboard
                        System.out.println("Bicubic Interpolation");
                        System.out.println("Input Matrix 4x4");
                        m = Bicubic.bacaMatrixBicubic();
                        double[] ab = Bicubic.bacaABBicubic();
                        double a = ab[0];
                        double b = ab[1];
                        double fx = Bicubic.bicubic(m, a, b);
                        System.out.println();
                        System.out.println("f("+a+","+b+") = "+fx);
                        System.out.println();
                        break;
                    case 2: //input file
                        String fileName = File.inputFileName();
                        System.out.println();
                        m = File.fileMatrix(fileName);
                        m = Bicubic.fileMatrixBicubic(m);
                        ab = Bicubic.fileABBicubic(m);
                        a = ab[0];
                        b = ab[1];
                        fx = Bicubic.bicubic(m, a, b);
                        System.out.println("f("+a+","+b+") = "+fx);
                        System.out.println();
                        break;
                        // InterpolasiPolinom.interpolasiFile(m);
                        // break;
                    default: m = Matrix.bacaMatrixSquare();
                }
                break;
            case 6:
                System.out.println("Regresi Liniear");
                displayMenuInput();
                opt = optionInput(1,2);
                switch(opt){
                    case 1: //input keyboard
                        InterpolasiPolinom.inputInterpolasiFull();
                        break;
                    case 2: //input file
                        // String fileName;
                        // fileName = File.inputFileName();
                        // System.out.println();
                        // m = File.fileMatrix(fileName);
                        // InterpolasiPolinom.interpolasiFile(m);
                        // break;
                    default: m = Matrix.bacaMatrixSquare();
                }
            case 7:
        }
    }
    }
}
