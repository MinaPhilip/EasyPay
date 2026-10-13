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
        data_user[1][5]="5";
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
       showTransactionHistory();
    }
   public static void Showstatistics(){
        int num_users=0;
        int num_activ=0;
        int num_blocks=0;
        int suc_trans=0;
        int failed_trans=0;
       for (int i = 0; i < data_user.length; i++) {
           if (data_user[i] == null) {
               continue;
           }

           if (data_user[i][0] != null) {
               num_users++;

               if (data_user[i][5] != null) {
                   suc_trans =suc_trans+ Integer.parseInt(data_user[i][5]);
               }
           }

           if (data_user[i][4] != null &&
                   data_user[i][4].equalsIgnoreCase("activ")) {

               num_activ++;

           } else if (data_user[i][4] != null &&
                   data_user[i][4].equalsIgnoreCase("block")) {

               num_blocks++;
           }
       }
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
        scanner.nextLine();
        String name=scanner.nextLine();
        String[] information= find_one_all(name);
        String choose = scanner.next();
        if (Objects.equals(choose, "1")){
            scanner.nextLine();
            String update_name=scanner.nextLine();
            assert information != null;
            information[0]=update_name;
            for (String element : information){
                System.out.println(element);
            }
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
public static void main (String[] args){
        start();
}
}
