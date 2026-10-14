import java.util.Objects;
import java.util.Scanner;

public class Main{
    public static String[] saved_login=new String[10];
    public static String [][] Admins = {{"mina"},{"mina@gmail.com"},{"123"}};
    public static String [][] data_user = new String[40][10];
    public static String [][][] Transaction = new String[40][40][40];
    public static String [][] Favorite=new String[40][40];
    public static Scanner scanner = new Scanner(System.in);
    public static void start(){

        System.out.println("========== EasyPAY ==========");
        System.out.println();
        System.out.println("1. Login as Admin\n" +
                "2. Login as User\n" +
                "3. Exit");
        System.out.println();
        System.out.print("Choose an option:");
        String input = scanner.next().trim();
        if (Objects.equals(input, "1")){
            Login(1);
        }else if (Objects.equals(input, "2")){
        Login(2);
        }else {
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

            if (!phone.matches("01[012][0-9]{8}")) {
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

    public static void Login(int type){
        boolean found = false;
        scanner.nextLine();
        System.out.print("Enter Your Email: ");
        String email=scanner.nextLine();
        System.out.print("Enter your Password: ");
        String pass=scanner.nextLine();
        for (int i =0;i<data_user.length;i++){
            if (type==1){
                if (Objects.equals(email, Admins[i][1]) && Objects.equals(pass, Admins[i][2])){
                    System.out.println("Login Successfully");
                    for(int j=0 ; j < 10; j++){
                    saved_login[j]= Admins[i][j];
                    saved_login[8]=Integer.toString(i);
                    found=true;
                    Admin();
                    }
                }
            }else if (type==2){
                if(Objects.equals(email,data_user[i][1]) && Objects.equals(pass,data_user[i][3])){
                    System.out.println("Login Successfully");
                    for(int j=0 ; j < 10; j++){
                        saved_login[j]= data_user[i][j];
                        saved_login[9]=Integer.toString(i);
                        found=true;
                    }
                    for (String element: saved_login){
                        System.out.println(element);
                    }
                    User();
                }
            }
        }
        if (!found){
            System.out.println("Wrong");
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
            case 9:
                saved_login=new String[10];
                start();
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
                if (choose>8){
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
                break;
            case 9:

            default:
                System.out.println("Invalid number");
                Admin();
        }
    }
    public static void showBalance(){
        System.out.print("Your Balance is : ");
        System.out.println(saved_login[4]);
    }

    public static void transferMoney(){
        boolean found = false;
        scanner.nextLine();
        System.out.print("Enter the Email or Phone number for transfer: ");
        String Email_phone= scanner.nextLine();
        for (int i=0;i<data_user.length;i++){
            if(!Objects.equals((data_user[i][1]),saved_login[1]) || !Objects.equals((data_user[i][2]),saved_login[2])){
            if(Objects.equals(data_user[i][1], Email_phone) || Objects.equals(data_user[i][2], Email_phone)) {
                if (Objects.equals((data_user[i][6]), "Blocked")) {
                    System.out.println("The user is blocked by Admin , You can't transfer money for him");
                } else {
                    System.out.println("User is found");
                    System.out.println("Enter amount: ");
                    int amount = scanner.nextInt();
                    int Recipient = Integer.parseInt(data_user[i][6]);
                    int availableـamount = Integer.parseInt(saved_login[4]);
                    if (availableـamount < amount) {
                        System.out.println("Your balance is less than the amount that you want to send , Your available amount : " + availableـamount);
                    } else if (availableـamount == 0) {
                        System.out.println("Your amount is ZERO , You can't transfer money, Recharge your account ");
                    } else {
                        availableـamount = availableـamount - amount;
                        Recipient = Recipient + amount;
                        data_user[i][6] = Integer.toString(Recipient);
                        data_user[Integer.parseInt(saved_login[8])][4] = Integer.toString(availableـamount);
                        System.out.println("Sent successfully");
                        System.out.println("");
                        found=true;
                    }
                }
            }
            }else {
                System.out.println("You Can't sent to yourself");
                break;
            }
            if(!found){
                System.out.println("User is not found");
            }
        }
    }
    public static void showTransactionHistoryForUser() {

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

        // Update name
        if (choose == 1) {
            data_user[userIndex][0] = value;
            saved_login[0] = value;

            System.out.println("Name updated successfully!");
        }

        // Update email
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

        // Update phone number
        else if (choose == 3) {
            if (!value.matches("01[012][0-9]{8}")) {
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
