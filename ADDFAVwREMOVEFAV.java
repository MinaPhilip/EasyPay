/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package addfavwremovefav;
 import java.util.Scanner;
public class ADDFAVwREMOVEFAV {

     static Scanner input = new Scanner (System.in);

   static int MAX_USERS = 100;
    static int MAX_FAVORITES = 10;
    
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
        
        
}
    
    
    }
    
