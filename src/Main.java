import java.util.Objects;
import java.util.Scanner;

public class Main{
    public static String [][] data_user = new String[40][7];
    public static String [][][] Transaction = new String[40][40][40];
    public static Scanner scanner = new Scanner(System.in);
    public static void start(){
        Transaction[0][0][0]="Transaction ID.";
        Transaction[0][0][1]="Sender.";
        Transaction[0][0][2]="Receiver.";
        Transaction[0][0][3]="Amount";
        Transaction[0][0][4]="Transaction status.";
        data_user[0][0]="mina";
        data_user[0][1]="01210236546";
        data_user[0][2]="2339239319";
        data_user[0][3]="mina@gmail.com";
        data_user[0][4]="Activ";
        data_user[0][5]="4";
        data_user[0][6]="800";
        data_user[1][5]="5";
        data_user[1][6]="6000";
        data_user[1][0]="philip";
        data_user[1][1]="0121023226546";
        data_user[1][2]="233923931229";
        data_user[1][3]="mi123na@gmail.com";
        data_user[1][4]="blocked";
        data_user[1][5]="4";
        System.out.println("========== EasyPAY ==========");
        System.out.println();
        System.out.println("1. Login as Admin\n" +
                "2. Login as User\n" +
                "3. Exit");
        System.out.println();
        System.out.print("Choose an option:");
        String input = scanner.next().trim();
        if (Objects.equals(input, "1")){
            Admin();
        }else if (Objects.equals(input, "2")){

        }else {
            return;
        }
    }

    public static void Admin()
    {
        System.out.print("========== ADMIN PANEL ==========\n" +
                "1. Show Statistics\n" +
                "2. Search User\n" +
                "3. View All Users\n" +
                "4. Update User\n" +
                "5. View All Transactions\n" +
                "6. Block Account\n" +
                "7. Unblock Account\n" +
                "8. View User Details\n" +
                "9. Logout\n" +
                "Choose an option: ");
        int choose ;
        while (true) {
            if (scanner.hasNextInt()) {
                choose = scanner.nextInt();
                if (choose>9){
                    System.out.println("Please enter a number!");
                    continue;
                }
                break;
            } else {
                System.out.println("Please enter a number!");
                scanner.next();
            }
        }
        switch (choose){
            case 1:
            {
                Showstatistics();
                Admin();
            }
            case 2: {
                System.out.print("Enter a Full name or ID or Email or Phone: ");
                scanner.nextLine();
                String search = scanner.nextLine();
                find_one_all(search);
                break;
            }
            case 3:
            {
                find_one_all("");
                break;
            }
            case 4:
            {
                updateUser();
                break;
            }
            case 5:
            {
                showTransactionHistory();
            }
            case 6:
                block_unblock("1"); break;
            case 7: block_unblock("2"); break;
            case 8: viewUserDetails(); break;
            case 9: start();
            default:
                System.out.println("Invalid number");
                Admin();
        }
    }

   public static void Showstatistics(){
        int num_users=0;
        int num_activ=0;
        int num_blocks=0;
        int suc_trans=0;
        int failed_trans=0;
        int amount=0;
       for (int i = 0; i < data_user.length; i++) {
           if (data_user[i] == null) {
               continue;
           }

           if (data_user[i][0] != null) {
               num_users++;
               if (data_user[i][5] != null) {
                   suc_trans = suc_trans + Integer.parseInt(data_user[i][5]);
               }
           }

           if (data_user[i][4] != null &&
                   data_user[i][4].equalsIgnoreCase("activ")) {

               num_activ++;

           } else if (data_user[i][4] != null &&
                   data_user[i][4].equalsIgnoreCase("block")) {

               num_blocks++;
           }
           if (data_user[i][6] != null) {
               amount += Integer.parseInt(data_user[i][6]);
           }
       }
       System.out.println("Total number of registered users: " + num_users);
       System.out.println("Number of active users: " + num_activ);
       System.out.println("Number of blocked accounts: " + num_blocks);
       System.out.println("Total number of successful transactions: " + suc_trans);
       System.out.println("Total number of failed transactions: " + failed_trans);
       System.out.println("Total amount of successful transfers: " + amount);
   }

    public static String[] find_one_all(String input) {
        boolean found = false;

        for (int i = 0; i < data_user.length; i++) {

            if (data_user[i][0] != null &&
                    (data_user[i][0].equalsIgnoreCase(input) ||
                            (data_user[i][1] != null && data_user[i][1].equalsIgnoreCase(input)) ||
                            (data_user[i][2] != null && data_user[i][2].equalsIgnoreCase(input)) ||
                            (data_user[i][3] != null && data_user[i][3].equalsIgnoreCase(input)))) {

                System.out.println("Full name: " + data_user[i][0]);
                System.out.println("Phone number: " + data_user[i][1]);
                System.out.println("User ID: " + data_user[i][2]);
                System.out.println("Email address: " + data_user[i][3]);
                found = true;
                return data_user[i];
            } else if (input.equalsIgnoreCase("" )&& data_user[i][0]!=null) {
                System.out.println("Full name: " + data_user[i][0]);
                System.out.println("Phone number: " + data_user[i][1]);
                System.out.println("User ID: " + data_user[i][2]);
                System.out.println("Email address: " + data_user[i][3]);
                found=true;
            }
        }

        if (!found) {
            System.out.println("User not found :(");
            return null;
        }
        return null;
    }
    public static void updateUser(){
        System.out.print("Enter a Full name or ID or Email or Phone (For updating a special user): ");
        scanner.nextLine();
        String name=scanner.nextLine();
        String[] information= find_one_all(name);
        System.out.println( "1. Change username\n" +
                "2. Change Email\n" +
                "3. Change Phone Number\n" + "Choose an option: ");
        String choose = scanner.next();
        if (Objects.equals(choose, "1")){
            scanner.nextLine();
            System.out.println("Enter the new name: ");
            String update_name=scanner.nextLine();
            assert information != null;
            information[0]=update_name;
            for (String element : information){
                System.out.println(element);
            }
        }else if (Objects.equals(choose,"2")){
            scanner.nextLine();
            System.out.println("Enter the new email: ");
            String update_email=scanner.nextLine();
            assert information != null;
            information[3]=update_email;
            for (String element : information){
                System.out.println(element);
        }
    }else if (Objects.equals(choose,"3")){
            scanner.nextLine();
            System.out.println("Enter the new Phone: ");
            String update_phone=scanner.nextLine();
            assert information != null;
            information[1]=update_phone;
            for (String element : information){
                System.out.println(element);
            }
        }else{
            System.out.println("Invaild choose");
        }
    }
    public static void showTransactionHistory(){
        for (int i = 0; i <Transaction.length;i++){
            if (data_user[i][0]!=null && Transaction[i][0][0]!=null){
                System.out.println("user"+data_user[i][0]);
            }else if(data_user[i][0]!=null && Transaction[i][0][0]==null) {
                System.out.println("user"+data_user[i][0]);
                System.out.println("o trans");
            }

            for (int j = 0; j < Transaction.length;j++){
                if (Transaction[i][j][0]!=null) {
                    System.out.println("id" + Transaction[i][j][0]);
                    System.out.println(Transaction[i][j][1]);
                    System.out.println(Transaction[i][j][2]);
                    System.out.println(Transaction[i][j][3]);
                }
            }
        }
    }
    public static void block_unblock(String status){
        if (Objects.equals("1", status)){
            scanner.nextLine();
            System.out.print("Enter a Full name or ID or Email or Phone (For blocking user): ");
            String name=scanner.nextLine();
            String[] information= find_one_all(name);
            assert information != null;
            information[4]="Blocked";
            for (int i =0 ; i<7;i++){
                System.out.println(information[i]);
            }
            System.out.println("u blocked acc suc");
        }
        if(Objects.equals(status,"2")){
            scanner.nextLine();
            System.out.print("Enter a Full name or ID or Email or Phone (For blocking user): ");
            String name=scanner.nextLine();
            String[] information= find_one_all(name);
            assert information != null;
            information[4]="Activ";
            System.out.println("U ublocked account succfully");
        }
    }
    public static void viewUserDetails(){
        scanner.nextLine();
        String name=scanner.nextLine();
        String[] information= find_one_all(name);
        assert information != null;
        System.out.println("account status: "+information[4]);
        System.out.println("balance: "+information[6]);
    }
public static void main (String[] args){
        start();
}
}
