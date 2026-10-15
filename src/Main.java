import java.util.Objects;
import java.util.Scanner;

public class Main{
        public static String[] saved_login=new String[10];
        public static String [][] Admins = {{"mina","mina@gmail.com","123"}};
        public static String [][] data_user = new String[40][10];
        public static String [][][] Transaction = new String[40][40][40];
        public static String [][] Favorite=new String[40][40];
    public static Scanner scanner = new Scanner(System.in);
    public static void start(){

        System.out.println("========== EasyPAY ==========");
        System.out.println();
        System.out.println("1. Login as Admin\n" +
                "2. Login as User\n" +
                "3. Register\n"+"4. Exit");
        System.out.println();
        System.out.print("Choose an option:");
        String input = scanner.next().trim();
        if (Objects.equals(input, "1")){
            Login(1);
        }else if (Objects.equals(input, "2")){
        Login(2);
        }else if(Objects.equals(input,"3")) {
            register();
        }
    }
    public static String generateID() {
        String id;
        do {
            id = "EP" + java.util.UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase();

        } while (isIDExists(id));

        return id;
    }
    public static boolean isIDExists(String id) {
        for (int i = 0; i < data_user.length; i++) {
            if (data_user[i][8] != null &&
                    data_user[i][8].equalsIgnoreCase(id)) {
                return true;
            }
        }

        return false;
    }

    public static void register() {
        scanner.nextLine();

        System.out.print("Enter Your Full Name: ");
        String name = scanner.nextLine();

        String email;

        while (true) {
            System.out.print("Enter Your Email: ");
            email = scanner.nextLine().trim();

            if (!email.contains("@") ||
                    email.indexOf("@") == 0 ||
                    email.indexOf("@") == email.length() - 1) {

                System.out.println("Invalid email! Try again.");
                continue;
            }

            boolean exists = false;

            for (int i = 0; i < data_user.length; i++) {
                if (data_user[i][0] != null &&
                        data_user[i][1] != null &&
                        data_user[i][1].equalsIgnoreCase(email)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Email already exists! Try another one.");
                continue;
            }

            break;
        }

        String phone;

        while (true) {
            System.out.print("Enter Your Phone: ");
            phone = scanner.nextLine().trim();

            if (!phone.matches("01[0125][0-9]{8}")) {
                System.out.println("Invalid phone number! Try again.");
                continue;
            }

            boolean exists = false;

            for (int i = 0; i < data_user.length; i++) {
                if (data_user[i][0] != null &&
                        data_user[i][2] != null &&
                        data_user[i][2].equals(phone)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Phone already exists! Try another one.");
                continue;
            }

            break;
        }

        System.out.print("Enter Your password: ");
        String password = scanner.nextLine();

        String id = generateID();

        for (int i = 0; i < data_user.length; i++) {
            if (data_user[i][0] == null) {
                data_user[i][0] = name;
                data_user[i][1] = email;
                data_user[i][2] = phone;
                data_user[i][3] = password;
                data_user[i][4] = "6000";
                data_user[i][5] = "0";
                data_user[i][6] = "0";
                data_user[i][7] = "Active";
                data_user[i][8] = id;

                System.out.println("Registration successful!");
                System.out.println("Your ID: " + id);

                start();
                return;
            }
        }

    }

    public static void Logout(){
        saved_login = new String[10];
        start();
    }
    public static void Login(int type) {
        scanner.nextLine();

        while (true) {
            boolean found = false;

            System.out.print("Enter Your Email (0 to return): ");
            String email = scanner.nextLine().trim();

            if (email.equals("0")) {
                start();
                return;
            }

            System.out.print("Enter your Password: ");
            String pass = scanner.nextLine();

            if (type == 1) {
                for (int i = 0; i < Admins.length; i++) {
                    if (Objects.equals(email, Admins[i][1])
                            && Objects.equals(pass, Admins[i][2])) {

                        System.out.println("Login Successfully");

                        if (Admins[i][0] != null) {
                            for (int j = 0; j < 3; j++) {
                                saved_login[j] = Admins[i][j];
                            }
                        }

                        saved_login[9] = Integer.toString(i);
                        found = true;
                        break;
                    }
                }

                if (found) {
                    Admin();
                    return;
                }

            } else if (type == 2) {
                for (int i = 0; i < data_user.length; i++) {
                    if (data_user[i][0] != null
                            && Objects.equals(email, data_user[i][1])
                            && Objects.equals(pass, data_user[i][3])) {

                        System.out.println("Login Successfully");

                        for (int j = 0; j < 9; j++) {
                            saved_login[j] = data_user[i][j];
                        }

                        saved_login[9] = Integer.toString(i);
                        found = true;
                        break;
                    }
                }

                if (found) {
                    User();
                    return;
                }

            } else {
                System.out.println("Invalid login type!");
                return;
            }

            System.out.println("Wrong email or password! Try again.\n");
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
                break;
            }
            case 2: {
                System.out.print("Enter a Full name or ID or Email or Phone: ");
                scanner.nextLine();
                String search = scanner.nextLine();
                find_one_all(search);
                Admin();
                break;
            }
            case 3:
            {
                find_one_all("");
                Admin();
                break;
            }
            case 4:
            {
                updateUser();
                Admin();
                break;
            }
            case 5:
            {
                showTransactionHistory();
                Admin();
                break;
            }
            case 6:
                block_unblock("1");
                Admin();
                break;
            case 7:
            block_unblock("2");
            Admin();
            break;
            case 8:
                viewUserDetails();
                Admin();
                break;
            case 9:
                Logout();
                break;
            default:
                System.out.println("Invalid number");
                Admin();
                break;
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
               if (data_user[i][6] != null){
                   failed_trans=failed_trans+Integer.parseInt(data_user[i][6]);
               }
           }

           if (data_user[i][7] != null &&
                   data_user[i][7].equalsIgnoreCase("Active")) {

               num_activ++;

           } else if (data_user[i][7] != null &&
                   data_user[i][7].equalsIgnoreCase("Blocked")) {

               num_blocks++;
           }
           for (int j = 0; j < Transaction[i].length; j++) {

               if (Transaction[i][j][0] != null
                       && Transaction[i][j][6] != null
                       && Transaction[i][j][6].equals("Success")) {

                   amount += Integer.parseInt(Transaction[i][j][2]);
                   amount=amount/2;
               }
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
                            (data_user[i][3] != null && data_user[i][8].equalsIgnoreCase(input)))) {
                System.out.println("====================================================================================");
                System.out.println("Full name: " + data_user[i][0]);
                System.out.println("Phone number: " + data_user[i][2]);
                System.out.println("User ID: " + data_user[i][8]);
                System.out.println("Email address: " + data_user[i][1]);
                System.out.println("The Balance is : "+data_user[i][4]);
                System.out.println("Successful Operations: "+data_user[i][6]);
                System.out.println("Failed operations: "+data_user[i][5]);
                System.out.println("Status: "+data_user[i][7]);
                System.out.println("====================================================================================");
                found = true;
                return data_user[i];
            } else if (input.equalsIgnoreCase("" )&& data_user[i][0]!=null) {
                System.out.println("====================================================================================");
                System.out.println("Full name: " + data_user[i][0]);
                System.out.println("Phone number: " + data_user[i][2]);
                System.out.println("User ID: " + data_user[i][8]);
                System.out.println("Email address: " + data_user[i][1]);
                System.out.println("The Balance is : "+data_user[i][4]);
                System.out.println("Successful Operations: "+data_user[i][6]);
                System.out.println("Failed operations: "+data_user[i][5]);
                System.out.println("Status: "+data_user[i][7]);
                System.out.println("====================================================================================");
                found=true;
            }
        }

        if (!found) {
            System.out.println("User not found :(");
            return null;
        }
        return null;
    }
    public static void updateUser() {
        System.out.print("Enter a ID or Email or Phone: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        String[] information = find_one_all(name);

        if (information == null) {
            return;
        }

        System.out.println(
                "1. Change username\n" +
                        "2. Change Email\n" +
                        "3. Change Phone Number\n" +
                        "Choose an option: "
        );

        String choose = scanner.next();

        if (Objects.equals(choose, "1")) {
            scanner.nextLine();
            System.out.print("Enter the new name: ");
            String update_name = scanner.nextLine();

            information[0] = update_name;

        } else if (Objects.equals(choose, "2")) {
            scanner.nextLine();
            System.out.print("Enter the new email: ");
            String update_email = scanner.nextLine();

            information[1] = update_email;

        } else if (Objects.equals(choose, "3")) {
            scanner.nextLine();
            System.out.print("Enter the new Phone: ");
            String update_phone = scanner.nextLine();

            information[2] = update_phone;

        } else {
            System.out.println("Invalid choice!");
            return;
        }

        System.out.println("User updated successfully!");

        for (String element : information) {
            System.out.println(element);
        }
    }
    public static void showTransactionHistory(){
        for (int i = 0; i <Transaction.length;i++){
            if (data_user[i][0]!=null && Transaction[i][0][0]!=null){
                System.out.println("User: "+data_user[i][0]);
            }else if(data_user[i][0]!=null && Transaction[i][0][0]==null) {
                System.out.println("User: "+data_user[i][0]);
                System.out.println("NO TRANSACTIONS");
            }

            for (int j = 0; j < Transaction.length;j++){
                if (Transaction[i][j][0]!=null) {
                    System.out.println("====================================================================================");
                    System.out.println("Sender ID:" + Transaction[i][j][0]);
                    System.out.println("Receiver ID"+Transaction[i][j][1]);
                    System.out.println("Amount: "+Transaction[i][j][2]);
                    System.out.println("After sending: "+Transaction[i][j][4]);
                    System.out.println("Status: "+ Transaction[i][j][5]);
                    System.out.println("Type: "+Transaction[i][j][6]);
                    System.out.println("====================================================================================");
                }
            }
        }
    }
    public static void block_unblock(String status) {
        if (Objects.equals(status, "1")) {
            scanner.nextLine();
            System.out.print("Enter a Full name or ID or Email or Phone: ");
            String name = scanner.nextLine();

            String[] information = find_one_all(name);

            if (information == null) {
                return;
            }

            information[7] = "Blocked";

            System.out.println("User blocked successfully!");

            for (int i = 0; i < 7; i++) {
                System.out.println(information[i]);
            }

        } else if (Objects.equals(status, "2")) {
            scanner.nextLine();
            System.out.print("Enter a Full name or ID or Email or Phone: ");
            String name = scanner.nextLine();

            String[] information = find_one_all(name);

            if (information == null) {
                return;
            }

            information[7] = "Active";

            System.out.println("User unblocked successfully!");

        } else {
            System.out.println("Invalid choice!");
        }
    }
    public static void viewUserDetails() {
        scanner.nextLine();

        System.out.print("Enter a Full name or ID or Email or Phone: ");
        String name = scanner.nextLine();

        String[] information = find_one_all(name);

        if (information == null) {
            return;
        }

        System.out.println("Account status: " + information[7]);
        System.out.println("Balance: " + information[4]);
    }

    public static void User(){
        System.out.print("========== USER PANEL ==========\n" +
                "1. Show Balance\n" +
                "2. Transfer Money\n" +
                "3. Transaction History\n" +
                "4. Show Favorites\n" +
                "5. Search Recipient\n" +
                "6. View Profile\n" +
                "7. Update Profile\n" +
                "8. Logout\n" +
                "Choose an option: ");
        int choose ;
        while (true) {
            if (scanner.hasNextInt()) {
                choose = scanner.nextInt();
                if (choose>8 || choose <= 0 ){
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
            showBalance();
            User();
            break;
            }
            case 2: {
                transferMoney();
                User();
                break;
            }
            case 3:
            {
                showTransactionHistoryForUser();
                User();
                break;
            }
            case 4:
            {
                showFavorites();
                User();
                break;
            }
            case 5:
            {
                searchRecipient();
                User();
                break;
            }
            case 6:
                viewProfile();
                User();
                break;
            case 7:
                updateProfile();
                User();
                break;
            case 8:
                Logout();
                break;
            default:
                System.out.println("Invalid number");
                User();
        }
    }
    public static void showBalance(){
        System.out.print("Your Balance is : ");
        System.out.println(saved_login[4]);
    }


    public static void transferMoney() {
        scanner.nextLine();
        int senderIndex = Integer.parseInt(saved_login[9]);
        System.out.print("Enter the Email or Phone number for transfer: ");
        String emailPhone = scanner.nextLine().trim();
        int receiverIndex = -1;
        for (int i = 0; i < data_user.length; i++) {
            if (data_user[i][0] == null) {
                continue;
            }

            boolean emailMatch = data_user[i][1] != null
                    && data_user[i][1].equalsIgnoreCase(emailPhone);

            boolean phoneMatch = data_user[i][2] != null
                    && data_user[i][2].equals(emailPhone);

            if (emailMatch || phoneMatch) {
                receiverIndex = i;
                break;
            }
        }

        if (receiverIndex == -1) {
            System.out.println("User is not found!");
            return;
        }

        if (senderIndex == receiverIndex) {
            System.out.println("You can't send money to yourself!");
            return;
        }

        if ("Blocked".equalsIgnoreCase(data_user[receiverIndex][7])) {
            System.out.println("The user is blocked by Admin!");
            return;
        }
        if ("Blocked".equalsIgnoreCase(saved_login[7])) {
            System.out.println("You are blocked by Admin!");
            return;
        }
        System.out.print("Enter amount: ");

        if (!scanner.hasNextInt()) { // scanner.hasNextint == false
            System.out.println("Invalid amount!");
            scanner.nextLine();
            return;
        }
        int amount = scanner.nextInt();

        int senderBefore = Integer.parseInt(data_user[senderIndex][4]);
        int receiverBefore = Integer.parseInt(data_user[receiverIndex][4]);

        int transactionIndex = -1;

        for (int i = 0; i < Transaction[senderIndex].length; i++) {
            if (Transaction[senderIndex][i][0] == null) {
                transactionIndex = i;
                break;
            }
        }

        int receiverTransactionIndex = -1;

        for (int i = 0; i < Transaction[receiverIndex].length; i++) {
            if (Transaction[receiverIndex][i][0] == null) {
                receiverTransactionIndex = i;
                break;
            }
        }

        if (amount <= 0 || senderBefore < amount) {

            String errorType;

            if (amount <= 0) {
                errorType = "Invalid Amount";
                System.out.println("Amount must be greater than zero!");
            } else {
                errorType = "Insufficient Balance";
                System.out.println(
                        "Insufficient balance! Your balance: " + senderBefore
                );
            }

            int failedTransactions =
                    Integer.parseInt(data_user[senderIndex][6]);

            data_user[senderIndex][6] =
                    Integer.toString(failedTransactions + 1);
            if (transactionIndex != -1) {
                Transaction[senderIndex][transactionIndex][0] =
                        data_user[senderIndex][8];
                Transaction[senderIndex][transactionIndex][1] =
                        data_user[receiverIndex][8];
                Transaction[senderIndex][transactionIndex][2] =
                        Integer.toString(amount);
                Transaction[senderIndex][transactionIndex][3] =
                        Integer.toString(senderBefore);
                Transaction[senderIndex][transactionIndex][4] =
                        Integer.toString(senderBefore);
                Transaction[senderIndex][transactionIndex][5] = "Sent";
                Transaction[senderIndex][transactionIndex][6] = "Failed";
            }

            if (receiverTransactionIndex != -1) {
                Transaction[receiverIndex][receiverTransactionIndex][0] =
                        data_user[senderIndex][8];
                Transaction[receiverIndex][receiverTransactionIndex][1] =
                        data_user[receiverIndex][8];
                Transaction[receiverIndex][receiverTransactionIndex][2] =
                        Integer.toString(amount);
                Transaction[receiverIndex][receiverTransactionIndex][3] =
                        Integer.toString(receiverBefore);
                Transaction[receiverIndex][receiverTransactionIndex][4] =
                        Integer.toString(receiverBefore);
                Transaction[receiverIndex][receiverTransactionIndex][5] = "Received";
                Transaction[receiverIndex][receiverTransactionIndex][6] = "Failed";
            }

            System.out.println("Failed transaction recorded!");
            return;
        }

        if (transactionIndex == -1 || receiverTransactionIndex == -1) {
            System.out.println("Transaction history is full!");
            return;
        }

        int senderAfter = senderBefore - amount;
        int receiverAfter = receiverBefore + amount;

        Transaction[senderIndex][transactionIndex][0] =
                data_user[senderIndex][8];
        Transaction[senderIndex][transactionIndex][1] =
                data_user[receiverIndex][8];
        Transaction[senderIndex][transactionIndex][2] =
                Integer.toString(amount);
        Transaction[senderIndex][transactionIndex][3] =
                Integer.toString(senderBefore);
        Transaction[senderIndex][transactionIndex][4] =
                Integer.toString(senderAfter);
        Transaction[senderIndex][transactionIndex][5] = "Sent";
        Transaction[senderIndex][transactionIndex][6] = "Success";

        Transaction[receiverIndex][receiverTransactionIndex][0] =
                data_user[senderIndex][8];
        Transaction[receiverIndex][receiverTransactionIndex][1] =
                data_user[receiverIndex][8];
        Transaction[receiverIndex][receiverTransactionIndex][2] =
                Integer.toString(amount);
        Transaction[receiverIndex][receiverTransactionIndex][3] =
                Integer.toString(receiverBefore);
        Transaction[receiverIndex][receiverTransactionIndex][4] =
                Integer.toString(receiverAfter);
        Transaction[receiverIndex][receiverTransactionIndex][5] = "Received";
        Transaction[receiverIndex][receiverTransactionIndex][6] = "Success";
        data_user[senderIndex][4] = Integer.toString(senderAfter);
        data_user[receiverIndex][4] = Integer.toString(receiverAfter);

        int senderSuccessful =
                Integer.parseInt(data_user[senderIndex][5]);

        int receiverSuccessful =
                Integer.parseInt(data_user[receiverIndex][5]);

        data_user[senderIndex][5] =
                Integer.toString(senderSuccessful + 1);

        data_user[receiverIndex][5] =
                Integer.toString(receiverSuccessful + 1);

        System.out.println("Sent successfully!");
        System.out.println("Your balance : " + senderAfter);
    }
    public static void showTransactionHistoryForUser() {

        int userIndex = Integer.parseInt(saved_login[9]);
        boolean found = false;

        System.out.println("\n========== Transaction History ==========");

        for (int i = 0; i < Transaction[userIndex].length; i++) {

            if (Transaction[userIndex][i][0] == null) {
                continue;
            }

            found = true;

            String sender = Transaction[userIndex][i][0];
            String receiver = Transaction[userIndex][i][1];
            String amount = Transaction[userIndex][i][2];
            String status = Transaction[userIndex][i][6];
            String Before = Transaction[userIndex][i][3];
            String After = Transaction[userIndex][i][4];
            System.out.println("====================================================================================");
            System.out.println("\nTransaction #" +(i+1));
            System.out.println("Sender ID: " + sender);
            System.out.println("Receiver ID: " + receiver);
            System.out.println("Amount: " + amount);
            System.out.println("Balance Before: " +Before);
            System.out.println("Balance After: " + After);
            System.out.println("Status: "+status);
            if (sender.equalsIgnoreCase(data_user[userIndex][8])) {
                System.out.println("Type: Sent");
            } else {
                System.out.println("Type: Received");
            }
            System.out.println("====================================================================================");
            System.out.println("-----------------------------------------");
        }

        if (!found) {
            System.out.println("You have no transactions yet.");
        }
    }
    public static void searchRecipient(){
        scanner.nextLine();
        System.out.print("Enter the Email or Phone number for searching: ");
        boolean found = false;
        String search = scanner.nextLine();
        for (int i = 0; i < data_user.length ; i++){
            if(!Objects.equals((data_user[i][1]),saved_login[1]) || !Objects.equals((data_user[i][2]),saved_login[2])){
                if(Objects.equals(data_user[i][1], search) || Objects.equals(data_user[i][2], search)) {
                    System.out.println("User is found");
                    System.out.print("1. Add Favorite\n" +
                            "2. Remove Favorite\n"+"Choose an option: ");
                    int choose ;
                    while (true) {
                        if (scanner.hasNextInt()) {
                            choose = scanner.nextInt();
                            if (choose>3){
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
                            remove_add_favorite(1,data_user[i][1]);
                            User();
                        }
                        case 2:
                      remove_add_favorite(2,data_user[i][1]);
                      User();
                    }
                        found= true;
                }
            }
        }
        if(!found){
            System.out.println("User is not found");
        }
    }

    public static void remove_add_favorite(int type, String user) {
        int loginIndex = Integer.parseInt(saved_login[9]);
        if (type == 1) {
            for (int i = 0; i < Favorite[loginIndex].length; i++) {
                if (Objects.equals(Favorite[loginIndex][i], user)) {
                    System.out.println(user + " is already in favorites!");
                    return;
                }
            }
            for (int i = 0; i < Favorite[loginIndex].length; i++) {
                if (Favorite[loginIndex][i] == null) {
                    Favorite[loginIndex][i] = user;
                    System.out.println(user + " is added successfully");
                    return;
                }
            }

        } else if (type == 2) {
            for (int i = 0; i < Favorite[loginIndex].length; i++) {
                if (Objects.equals(Favorite[loginIndex][i], user)) {
                    Favorite[loginIndex][i] = null;
                    System.out.println(user + " is removed successfully");
                    return;
                }
            }
            System.out.println(user + " was not found in favorites!");

        } else {
            System.out.println("Invalid type!");
        }
    }
    public static void showFavorites() {
        boolean found=false;
        for (int i = 0; i < data_user.length; i++) {
            if (Favorite[Integer.parseInt(saved_login[9])][i] !=null){
                System.out.println(Favorite[Integer.parseInt(saved_login[9])][i]);
                found=true;
            }
        }
        if(!found){
            System.out.println("You don't have favorite users");
        }
    }
    public static void viewProfile(){
        System.out.println("Your UserName is: "+saved_login[0]);
        System.out.println("Your Email is: "+saved_login[1]);
        System.out.println("Your Phone is: " +saved_login[2]);
        System.out.println("Your ID is : "+saved_login[8]);
        System.out.println("Your balance is : "+saved_login[4]);
        System.out.println("Your status is : "+saved_login[7]);
        System.out.println("Your success transactions is : "+saved_login[5]);
        System.out.println("Your Failed transactions is : "+saved_login[6]);
    }

    public static void updateProfile() {
        System.out.println("""
            What do you want to edit?
            1. Name
            2. Email
            3. Phone Number
            Choose an option:
            """);

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid choice!");
            scanner.nextLine();
            return;
        }

        int choose = scanner.nextInt();
        scanner.nextLine();

        if (choose < 1 || choose > 3) {
            System.out.println("Invalid choice!");
            return;
        }
        int userIndex = -1;
        for (int i = 0; i < data_user.length; i++) {
            if (data_user[i][8] != null &&
                    data_user[i][8].equals(saved_login[8])) {
                userIndex = i;
                break;
            }
        }

        System.out.print("Enter the new value: ");
        String value = scanner.nextLine().trim();

        if (value.isEmpty()) {
            System.out.println("Value cannot be empty!");
            return;
        }

        if (choose == 1) {
            data_user[userIndex][0] = value;

            saved_login[0] = value;

            System.out.println("Name updated successfully!");
        }

        else if (choose == 2) {
            if (!value.contains("@") ||
                    value.indexOf("@") == 0 ||
                    value.indexOf("@") == value.length() - 1) {
                System.out.println("Invalid email!");
                return;
            }

            for (int i = 0; i < data_user.length; i++) {
                if (i != userIndex &&
                        data_user[i][0] != null &&
                        data_user[i][1] != null &&
                        data_user[i][1].equalsIgnoreCase(value)) {
                    System.out.println("Email already exists!");
                    return;
                }
            }

            data_user[userIndex][1] = value;
            saved_login[1] = value;

            System.out.println("Email updated successfully!");
        }

        else if (choose == 3) {
            if (!value.matches("01[0125][0-9]{8}")) {
                System.out.println("Invalid phone number!");
                return;
            }

            for (int i = 0; i < data_user.length; i++) {
                if (i != userIndex &&
                        data_user[i][0] != null &&
                        data_user[i][2] != null &&
                        data_user[i][2].equals(value)) {
                    System.out.println("Phone number already exists!");
                    return;
                }
            }

            data_user[userIndex][2] = value;
            saved_login[2] = value;

            System.out.println("Phone number updated successfully!");
        }
    }

    public static void main (String[] args){
        start();
}
}
