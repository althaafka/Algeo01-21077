class Main extends Menu{
    public static void main(String[] args){
        int opt;
        double[][] m;
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
                        m = Gauss.eselonBaris(m);
                        SPLSolver.splSolution(m);
                        break;
                    case 2: //gauss-jordan
                        m = Gauss_Jordan.eselonBarisTereduksi(m);
                        SPLSolver.splSolution(m);
                        break;
                }
                break;
            case 2: //determinan
                displayMenuDet();
                opt = optionInput(1, 2);
                break;
            case 3:
                displayMenuInverse();
                opt = optionInput(1, 2);
                break;
            
        }
    }
}