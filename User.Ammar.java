/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package addfavwremovefav;
 import java.util.Scanner;
public class ADDFAVwREMOVEFAV {

     static Scanner input = new Scanner (System.in);
     
     public static void showBalance(int currentUserIndex) {
    System.out.println("========== Your Balance ==========");
    
    
    System.out.println("Current Balance: " + balances[currentUserIndex]);
    
    System.out.println("==================================");
}
     
     static int MAX_USERS = 1000;
     static int MAX_FAVORITES = 10;
     static int MAX_TRANSACTIONS = 10000;
     static int transactionCount = 0; 
    

static int[] balances = new int[MAX_USERS]; 
static String[] emails = new String[MAX_USERS]; 
static String[] phoneNumbers = new String[MAX_USERS]; 
static String[] accountStatuses = new String[MAX_USERS]; 




static String[] transactionIDs = new String[MAX_TRANSACTIONS];
static String[] transactionSenders = new String[MAX_TRANSACTIONS];
static String[] transactionReceivers = new String[MAX_TRANSACTIONS];
static int[] transactionAmounts = new int[MAX_TRANSACTIONS];
static String[] transactionStatuses = new String[MAX_TRANSACTIONS];



public static void transferMoney(int senderIndex, String recipientIdentifier, int amount) {
    String senderID = userIDs[senderIndex];
    String recipientID = "UNKNOWN"; 
    
    
    if (amount <= 0) {
        System.out.println("Error: The transfer amount must be greater than zero.");
        recordTransaction(senderID, recipientIdentifier, amount, "FAILED");
        return;
    }

    
    int recipientIndex = -1;
    for (int i = 0; i < MAX_USERS; i++) {
        if ((emails[i] != null && emails[i].equals(recipientIdentifier)) || 
            (phoneNumbers[i] != null && phoneNumbers[i].equals(recipientIdentifier))) {
            recipientIndex = i;
            recipientID = userIDs[i];
            break;
        }
    }

  
    if (recipientIndex == -1) {
        System.out.println("Error: Recipient does not exist in the system.");
        recordTransaction(senderID, recipientIdentifier, amount, "FAILED");
        return;
    }

    
    if (senderIndex == recipientIndex) {
        System.out.println("Error: You cannot transfer money to yourself.");
        recordTransaction(senderID, recipientID, amount, "FAILED");
        return;
    }

    
    if ("ACTIVE".equals(accountStatuses[senderIndex])) {
        System.out.println("Error: Your account is blocked. Transfer failed.");
        recordTransaction(senderID, recipientID, amount, "FAILED");
        return;
    }
    if ("ACTIVE".equals(accountStatuses[recipientIndex])) {
        System.out.println("Error: Recipient's account is blocked. Transfer failed.");
        recordTransaction(senderID, recipientID, amount, "FAILED");
        return;
    }

    
    if (balances[senderIndex] < amount) {
        System.out.println("Error: Insufficient balance.");
        recordTransaction(senderID, recipientID, amount, "FAILED");
        return;
    }

   
    balances[senderIndex] -= amount;      // خصم المبلغ من المرسل
    balances[recipientIndex] += amount;   // إضافة المبلغ للمستلم
    
    System.out.println("Success: Amount transferred successfully!");
    System.out.println("Your new balance is: " + balances[senderIndex]);
    
   
    recordTransaction(senderID, recipientID, amount, "SUCCESS");
}


public static void recordTransaction(String sender, String receiver, int amount, String status) {
    if (transactionCount < MAX_TRANSACTIONS) {
        transactionIDs[transactionCount] = "TXN" + (transactionCount + 1);
        transactionSenders[transactionCount] = sender;
        transactionReceivers[transactionCount] = receiver;
        transactionAmounts[transactionCount] = amount;
        transactionStatuses[transactionCount] = status;
        transactionCount++;
    }
}

    
    static String[] userIDs = new String[MAX_USERS]; 
    static String[][] favorites = new String[MAX_USERS][MAX_FAVORITES]; 

    
    public static void addFavorite(int currentUserIndex, String favoriteUserID) {
        
        boolean userExists = false;
        for (int i = 0; i < userIDs.length; i++) {
            if (userIDs[i] != null && userIDs[i].equals(favoriteUserID)) {
                userExists = true;
                break;
            }
        }
        
        
        if (userExists) {
            System.out.println("Sorry, this user does not exist in the system.");
            return;
        }

        for (int i = 0; i < MAX_FAVORITES; i++) {
            if (favorites[currentUserIndex][i] != null && favorites[currentUserIndex][i].equals(favoriteUserID)) {
                System.out.println("This user is already on your favorites list.");
                return;
            }
        }
      
        for (int i = 0; i < MAX_FAVORITES; i++) {
            if (favorites[currentUserIndex][i] == null) {
                favorites[currentUserIndex][i] = favoriteUserID;
                System.out.println("The user has been successfully added to favorites.");
                return;
            }
        }
        
        if (MAX_FAVORITES > 10) { 
        System.out.println("Your favorites list is full, you cannot add more.");                    
    }
    }
       
    public static void removeFavorite(int currentUserIndex, String favoriteUserID) {
    boolean isRemoved = false; 

    
    for (int i = 0; i < MAX_FAVORITES; i++) {
        
        if (favorites[currentUserIndex][i] != null && favorites[currentUserIndex][i].equals(favoriteUserID)) {
            
            favorites[currentUserIndex][i] = null; 
            isRemoved = true;
            System.out.println("The user has been successfully removed from favorites.");
            break; 
        }
    }

    
    if (isRemoved) {
        System.out.println("This user is not in your favorites list.");
    }
       
}
    public static void showFavorites(int currentUserIndex) {
    boolean hasFavorites = false; 

    System.out.println("========== Your Favorites List ==========");
    
    
    for (int i = 0; i < MAX_FAVORITES; i++) {
        
        if (favorites[currentUserIndex][i] != null) {
            System.out.println(" User ID: " + favorites[currentUserIndex][i]);
            hasFavorites = true; 
        }
    }
    
    if (hasFavorites) {
        System.out.println("Your favorites list is currently empty.");
    }
    
    System.out.println("=========================================");
}
    
   





    
    public static void main(String[] args) {
        
    
        userIDs[0] = "User1"; 
        userIDs[1] = "User2"; 
        userIDs[2] = "User3";
        
        System.out.print("Enter the ID of the user you want to add to favorites: ");
        String enteredID = input.nextLine();  
        
        addFavorite(0, enteredID);
        System.out.print("Enter the ID of the user you want to remove from favorites: ");
        
         String removeID = input.nextLine();
   
        removeFavorite(0,removeID);
        
    int myIndex =0;
    showFavorites(myIndex);
 favorites[myIndex][0] = "1010"; 
    favorites[myIndex][5] = "2020"; 
    showFavorites(myIndex);
}
        
        
}
    
    
    
    
