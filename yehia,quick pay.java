import java.util.Scanner;
public class yahia {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
    }
    public static void userpanal(){
        Scanner input=new Scanner(System.in);
        while(data_user!=-1) {
            System.out.println("========== USER PANEL =========");
            System.out.println("1. show balance");
            System.out.println("2. transfer money ");
            System.out.println("3. transaction history ");
            System.out.println("4. show favorites ");
            System.out.println("5. add favorites ");
            System.out.println("6. remove favorites ");
            System.out.println("7. search recipient ");
            System.out.println("8. view profile ");
            System.out.println("9. update profile ");
            System.out.println("10. logout ");
            System.out.print("\nchoose an option:");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("showing balance..");
                    break;
                case 2:
                    //transfer money
                    break;
                case 3:
                    System.out.println("showing transaction history..");
                    showUserTransactionHistory(String thisUSER);
                    break;
                case 4:
                    System.out.println("showing favorites..");
                    break;
                case 5:
                    //add favorites
                    break;
                case 6:
                    //remove favorites
                    break;
                case 7:
                    System.out.println("searching..");
                    searchRecipient(String thisUSER);
                    break;
                case 8:
                    viewprofile();
                    break;
                case 9:
                    updateprofile();
                    break;
                case 10:
                    System.out.println("logging out..\nthank you for using QUICK PAY");
                    logout();
                    break;
                default:
                    System.out.println("invalid option please re-enter");
            }
        }
    }
    public static void updateprofile(){
        Scanner input=new Scanner(System.in);
        while(true) {
            System.out.println("choose an option");
            System.out.println("1. update name" + "\n2. update e-mail" + "\n3.update phone number");
            int choice = input.nextInt();
            input.nextLine();
            if (choice == 1) {
                System.out.println("enter your new name");
                //users[][]=input.nextLine();
                System.out.println("updated!");
                return;

            } else if (choice == 2) {
                System.out.println("enter your updated email");
                //users[][]=input.nextLine();
                System.out.println("updated!");
                return;
            } else if (choice == 3) {
                System.out.println("enter your new phone number");
                //users[][]=input.nextLine();
                System.out.println("updated!");
                return;

            } else {
                System.out.println("invalid choice ");

            }
            return;
        }
    }

    public static void viewprofile(){
        System.out.println("====your profile====");
        System.out.println("ID: ");
        System.out.println("NAME: ");
        System.out.println("E-Mail: ");
        System.out.println("phone: ");

    }
    public static void logout(){
        data_user=-1;
        System.out.println("logging out!");
        start();
    }
    public static int searchRecipient(int thisUSER){
        Scanner input=new Scanner(System.in);
        System.out.println("enter the recipient email or phone number");
        String INPUT=input.next();
        for (int i=0;i<data_user.lenght;i++){
            if(i==thisUSER)continue;
            if (data_user[i][0]==null)continue;
            if (data_user[i][1].equals(INPUT) || data_user[i][3].equalsIgnoreCase(INPUT) ){
                if (data_user[i][4].equalsIgnoreCase("blocked")) {
                    System.out.println("can not show profile this user is blocked");
                    return -1;
                }
                System.out.println("==Recipient is found==");
                System.out.println("name: "+data_user[i][0]);
                System.out.println("phone:"+data_user[i][1]);
                System.out.println("E-mail: "data_user[i][3]);
                return i;
            }
        }
        System.out.println("recipient is not found");
        return -1;
    }
    public  static void showUserTransactionHistory(String thisUSER) {

        int found=0;

        for (int i = 0; i < Transaction.length; i++) {
            for (int j = 0; j < Transaction[i].length; j++) {

                if (Transaction[i][j][0] == null ||
                        Transaction[i][j][0].equals("Transaction ID")) {
                    continue;
                }

                String sender = Transaction[i][j][1];
                String receiver = Transaction[i][j][2];

                if (currentUser.equals(sender) ||
                        currentUser.equals(receiver)) {
                    found=1;
                    System.out.println("transaction ID: " + Transaction[i][j][0]);
                    if (currentUser.equals(sender)) {
                        System.out.println("type: Sent");
                        System.out.println("to: " + receiver);
                    } else {
                        System.out.println("type: Received");
                        System.out.println("from: " + sender);
                    }
                    System.out.println("amount: " + Transaction[i][j][3]);
                    System.out.println("status: " + Transaction[i][j][4]);

                }
            }
        }

        if (found==0) {
            System.out.println("transactions=0");
        }

    }




}