class Main extends Menu{
    public static void main(String[] args){

        int opt;
        double[][] m, mInv;
        double[] mRes = new double[] {}, ab = new double[]{};
        double det = 0, x = 0, yEstimate = 0, a, b, fx;
        boolean run=true;
        boolean writeFile = false;
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
                String [] output = new String[] {};
                switch(opt){
                    case 1: //gauss
                        System.out.println("SPL Metode Gauss");
                        m = Gauss.eselonBaris(m);
                        Matrix.tulisMatrix(m);
                        output = SPLSolver.splSolution(m);
                        System.out.println();
                        break;
                    case 2: //gauss-jordan
                        System.out.println("SPL Metode Gauss Jordan");
                        m = Gauss_Jordan.eselonBarisTereduksi(m);
                        Matrix.tulisMatrix(m);
                        output = SPLSolver.splSolution(m);
                        System.out.println();
                        break;
                    case 3: //matriks invers
                        System.out.println("SPL Metode Matrix Inverse");
                        if (Matrix.nBaris(m)!= (Matrix.nKolom(m)-1) ) {
                            System.out.println("Matrix harus persegi!");
                            System.out.println("Matrix tidak dapat diselesaikan dengan metode ini");
                            System.out.println();
                        } else if (DeterminanGJ.determiananGaussJordan(Matrix.AugmentedtoSquare(m))==0){
                            System.out.println("Matrix memiliki determinan = 0");
                            System.out.println("Matrix tidak dapat diselesaikan dengan metode ini");
                            System.out.println();
                        } else{
                            output = SPLSolver.uniqueSol2Arr(SolusiBalikan.SolveBalikan(m));
                            SPLSolver.displayUniqueSol(SolusiBalikan.SolveBalikan(m));
                            System.out.println();
                        }
                        break;
                    case 4: //crammer
                        System.out.println("SPL Metode Crammer");
                        if (Matrix.nBaris(m)!= (Matrix.nKolom(m)-1) ) {
                            System.out.println("Matrix harus persegi!");
                            System.out.println("Matrix tidak dapat diselesaikan dengan metode ini");
                            System.out.println();
                        } else if  (DeterminanGJ.determiananGaussJordan(Matrix.AugmentedtoSquare(m))==0 || Matrix.nBaris(m)!= Matrix.nKolom(m)){
                            System.out.println("Matrix memiliki determinan = 0");
                            System.out.println("Matrix tidak dapat diselesaikan dengan metode ini");
                            System.out.println();
                        } else{
                            double[][] m1 = Matrix.AugmentedtoSquare(m);
                            double[][] m2 = Matrix.augmentedtoKoef(m);
                            SPLSolver.displayUniqueSol(Crammer.crammerhasil(m1,m2));
                            output = SPLSolver.uniqueSol2Arr(Crammer.crammerhasil(m1,m2));
                            System.out.println();
                        }
                        break;
                }
                displayMenuSave();
                opt = optionInput(1, 2);
                switch(opt){
                    case 1:
                        System.out.println("Simpan Hasil");
                        System.out.println("Masukkan nama file output");
                        System.out.print(">>> ");
                        String dir = scan.next();
                        System.out.println();
                        File.writeSPLSol(dir, output);
                        // writeFile = File.writeSPLSol("../test/" + dir, ?? );
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
                        System.out.println("Masukkan nama file output");
                        System.out.print(">>> ");
                        String dir = scan.next();
                        System.out.println();
                        File.writeDeterminan(dir, m, det);
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
                mInv = Matrix.createMatrix(Matrix.nBaris(m), Matrix.nKolom(m));
                if (Determinan.determinan(m) == 0){
                    System.out.println("Determinan matriks 0 sehingga matriks tidak memiliki invers.\n");
                } else {
                    displayMenuInverse();
                    opt = optionInput(1, 2);
                    mInv = Matrix.createMatrix(Matrix.nBaris(m), Matrix.nKolom(m));
                    switch(opt){
                        case 1: //el gaus jordan
                            System.out.println("Matrix Invers Metode Gauss Jordan");
                            mInv = inversGauss.InversGauss(m);
                            Matrix.tulisMatrix(mInv);
                            System.out.println();
                            break;
                        case 2:
                            System.out.println("Matrix Invers Metode Kofaktor");
                            mInv = Invers.InversCofactor(m);
                            Matrix.tulisMatrix(mInv);
                            System.out.println();
                            break;
                    }
                }

                displayMenuSave();
                opt = optionInput(1, 2);
                switch(opt){
                    case 1:
                        System.out.println("Simpan Hasil");
                        System.out.println("Masukkan nama file output");
                        System.out.print(">>> ");
                        String dir = scan.next();
                        dir = "../test/outputFile/" + dir;
                        System.out.println();
                        if (det != 0) File.writeInvers(dir, m, mInv);
                        else File.writeFailInverse(dir, m); // det==0
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
                        m = InterpolasiPolinom.inputInterpolasiKey();
                        System.out.print("Nilai x yang akan dicari: ");
                        x = scan.nextDouble();
                        break;
                    case 2: //input file
                        String fileName;
                        fileName = File.inputFileName();
                        m = File.fileMatrix(fileName);
                        // System.out.println(Matrix.nBaris(m) + " " + Matrix.nKolom(m) + "\n");
                        x = InterpolasiPolinom.findVal(m);
                        m = InterpolasiPolinom.fileMatrixInterpolasi(m);
                        break;
                    default: m = Matrix.bacaMatrixSquare();
                }
                mRes = InterpolasiPolinom.resultPolinom(m);
                yEstimate = InterpolasiPolinom.EstimasiFungsi(mRes, x);
                InterpolasiPolinom.displayFx(mRes);
                System.out.println("f("+x+") = " + yEstimate);
                System.out.println();

                displayMenuSave();
                opt = optionInput(1, 2);
                switch(opt){
                    case 1:
                        System.out.println("Simpan Hasil");
                        System.out.println("Masukkan nama file output");
                        System.out.print(">>> ");
                        String dir = scan.next();
                        dir = "../test/outputFile/" + dir;
                        System.out.println();
                        File.writeInterpolasi(dir, mRes, x, yEstimate );
                        //writeFile = File.writeInterpolasi("../test/" + dir, m, mInv);
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
                        ab = Bicubic.bacaABBicubic();
                        System.out.println();
                        break;
                    case 2: //input file
                        String fileName = File.inputFileName();
                        System.out.println();
                        m = File.fileMatrix(fileName);
                        ab = Bicubic.fileABBicubic(m);
                        m = Bicubic.fileMatrixBicubic(m);
                        break;
                        // InterpolasiPolinom.interpolasiFile(m);
                        // break;
                    default: m = Matrix.bacaMatrixSquare();
                }
                a = ab[0];
                b = ab[1];
                fx = Bicubic.bicubic(m, a, b);
                System.out.println("f("+a+","+b+") = "+fx);
                System.out.println();

                displayMenuSave();
                opt = optionInput(1, 2);
                switch(opt){
                    case 1:
                        System.out.println("Simpan Hasil");
                        System.out.println("Masukkan nama file output");
                        System.out.print(">>> ");
                        String dir = scan.next();
                        dir = "../test/outputFile/" + dir;
                        System.out.println();
                        File.writeBicubic(dir, a, b, fx, m);
                        //writeFile = File.writeInterpolasi("../test/" + dir, m, mInv);
                        break;
                    case 2:
                        break;
                }
                break;
            case 6:
            System.out.println("Regresi Liniear");
            displayMenuInput();
            opt = optionInput(1,2);
            output =new String[]{};
            switch(opt){
                case 1: //input keyboard
                    System.out.println("Regresi Liniear");
                    m = Matrix.bacaMatrix();
                    output = RegMatrix.HasilRegresi(m);
                    break;
                case 2: //input file
                    String fileName;
                    fileName = File.inputFileName();
                    System.out.println();
                    m = File.fileMatrix(fileName);
                    output = RegMatrix.HasilRegresi(m);
                    break;
                default: m = Matrix.bacaMatrixSquare();
            }
            displayMenuSave();
            opt = optionInput(1, 2);
            switch(opt){
                case 1:
                    System.out.println("Simpan Hasil");
                    System.out.print("Masukkan nama file output\n>>> ");
                    String dir = scan.next();
                    File.writeRegresi(dir, output);
                    break;
                case 2:
                    break;
            }
            break;
            case 7:
                run = false;
                System.out.println("Program berhenti. Terima kasih :)\n");
        }
    }
    }
}
