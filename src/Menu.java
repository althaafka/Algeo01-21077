import java.util.Scanner;

public class Menu {
    static Scanner scan = new Scanner(System.in);  
    
    public static void displayMenu(){      
        // Menampilkan main menu
        System.out.println("Main Menu");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Inverse");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi Linear Berganda");
        System.out.println("7. Keluar");
    }

    public static int optionInput(int min, int max){
        //Mengembalikan opsi dari user
        int opt;
        System.out.println("Input menu "+ min + " sampai "+max);
        System.out.print(">>> ");
        opt = scan.nextInt();
        while (opt<min || opt>max){
            System.out.println("Masukkan salah, input menu "+ min + " sampai "+max);
            System.out.print(">>> ");
            opt = scan.nextInt();
        }
        System.out.println();
        return opt;
    }

    public static void displayMenuSPL(){
        // Menampilkan submenu SPL
        System.out.println("Sistem Persamaan Liniear");
        System.out.println("1. Eliminasi Gauss");
        System.out.println("2. Eliminasi Gauss-Jordan");
        System.out.println("3. Matriks Inverse");
        System.out.println("4. Metode Cramer");
    }

    public static void displayMenuDet(){
        // Menampilkan submenu SPL
        System.out.println("Determinan");
        System.out.println("1. Reduksi Baris");
        System.out.println("2. Ekspansi Kofaktor");
    }

    public static void displayMenuInverse(){
        // Menampilkan submenu SPL
        System.out.println("Inverse Matrix");
        System.out.println("1. Eliminasi Gauss-Jordan" );
        System.out.println("2. Metode Kofaktor");
    }
}

