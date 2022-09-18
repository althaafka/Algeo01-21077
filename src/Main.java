class Main extends Menu{
    public static void main(String[] args){
        int opt;
        displayMenu();
        opt = optionInput(1, 7);
        switch(opt){
            case 1:
                displayMenuSPL();
                opt = optionInput(1, 4);
                break;
            case 2:
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