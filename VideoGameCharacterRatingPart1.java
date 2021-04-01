// Names: Jeffery Hu and David Geng
// Teacher name: Ms. Krasteva
// File name: VideoGameCharacterRatingPart1.java
// Date: February 25, 2021.
// Description: This class will collect the statistics (name, strength, health speed, and wisdom) for video game characters from a file. It will then be able to display this data and show the overall best character.
// Citation: The syntax for how to get input using JOptionPane was learned from: https://www.homeandlearn.co.uk/java/java_option_panes.html

//Import statements
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class VideoGameCharacterRatingPart1{
   // Variable declarations
   String[] name; // Declare a String array called name, which will store all the names of the characters
   int[] strength; // Declare an integer array called strength, which will store the strength rating of all of the characters
   int[] health; // Declare an integer array called health, which will store the health rating of all of the characters
   int[] speed; // Declare an integer array called speed, which will store the speed rating of all of the characters
   int[] wisdom; // Declare an integer array called wisdom, which will store the wisdom rating of all of the characters
   String menuInput = ""; // Declare a String called menuInput, which will store what the user inputs in the menu
   boolean isBack = false; // Declare a boolean called isBack, which will store if the user has already seen the main menu. If so, it will display a different message at the top ("You are now back at the main menu").
   
   // Class constructor
   public VideoGameCharacterRatingPart1() {
   }
   
   /*
   The readFile() method, which will read the attributes and characters from the given file and store them into the arrays.
   Loops:
   1. The first while loop is used to search through the file and find the total number of lines
   2. The first for loop is used to cycle through the length of each array so it can store the values from the file
      a) The while loops inside of the for loop is used only if the values for the attributes inside of the file is not valid. It will keep displaying an error message until the user inputs a valid input to replace it.
      b) There are 5 while loops, one per attribute. Each while loop will only conclude once a proper value has been inputted into the array. 
      The while loops and try-catch statements are in the following order: 1. Stores the name, 2. Stores the strength, 3. Stores the health, 4. Stores the speed, 5. Stores the wisdom
   */
   public void readFile() throws IOException{
      int i = 0; // Declare an integer called i, assign it the value of 0 (to track the amount of values/lines in the file)
      Scanner file = new Scanner(new File("CharacterRatings.txt")); // Declare and initialize a scanner called file, which will be used to scan the file the first time to determine the number of lines
      Scanner fileTwo = new Scanner(new File("CharacterRatings.txt")); // Declare and initialize a scanner called fileTwo, which will be used to scan the file the second time to extract/copy the values into the array
      String fileInput = " "; // Declare and initialize a String called fileInput, which will serve as a storing variable for the first scanner as it determines the number of lines
      String userInput = ""; // Declare a String called userInput, which will store the value (in String form) of what the user inputs to replace an invalid file value (through JOptionPane)
      boolean fileIsRead = false; // Declare a boolean called fileIsRead, which will store whether or not the value has already been read from the file to determine if the array should parse the input from the JOptionPane
      
      while (file.hasNext()) // Find the number of lines inside of the file
      {
         fileInput = file.nextLine();
         i++;
      }
      
      // Initalize each array to the appropriate size depending on the number of values inside of the array
      name = new String[i/5];
      strength = new int[i/5];
      health = new int[i/5];
      speed = new int[i/5];
      wisdom = new int[i/5];
      
      for (int w = 0; w < name.length; w++) // Cycle through the length of the array (each iteration of this for loop will store all of the attributes associated with a character)
      { 
         name[w] = fileTwo.nextLine(); // Gets the name
         while (name[w].equals("") || name[w].equals(" ")){
            name[w] = JOptionPane.showInputDialog ("\"" + name[w] + "\"" + " is not a valid name. Please enter a valid name for the character (line " + (w*5+1) + "):");
            if (name[w] == null){
               name[w] = "No Name";
            }
         }
         
         fileIsRead = false; // Sets fileIsRead back to false so the nextLine command is run
         
         while (true){ // Stores and errortraps for the strength
            try{
               if (fileIsRead == false){ // If this is the first time this loop is being run
                  strength[w] = Integer.parseInt(fileTwo.nextLine()); 
                  fileIsRead = true;
               }
               else{ // If this loop has already been run and the user has inputted something as a replacement for an invalid value read from the file
                  strength[w] = Integer.parseInt(userInput); // The user input is originally stored in userInput as it needs to be a string (as we use JOptionPane)
               }
               if (strength[w] < 0 || strength[w] > 100){ // Make sure that the inputted value is within the specified range
                  throw new IllegalArgumentException ();
               }
               break;
            } catch(NumberFormatException e){ // If the user/file didn't input a integer
               userInput = JOptionPane.showInputDialog ("\"" + strength[w] + "\"" + " is not a valid input for "+ name[w] + "'s strength. Please enter an integer from 0 to 100:"); // error message
            } catch(IllegalArgumentException E){ // If the input isn't in the specified range
               userInput = JOptionPane.showInputDialog ("\"" + strength[w] + "\"" + " is not a valid input for "+ name[w] + "'s strength. Please enter an integer from 0 to 100:"); // error message
            } catch (NullPointerException e){ // If the user presses "cancel" for the JOptionPane
               userInput = JOptionPane.showInputDialog ("Error: Please enter an integer from 0 to 100 for " + name[w] + "'s strength:");
            }
         }
         
         fileIsRead = false; // Sets fileIsRead back to false so the nextLine command is run
         
         while (true){ // Stores and errortraps for the health
            try{
               if (fileIsRead == false){ // If this is the first time this loop is being run
                  health[w] = Integer.parseInt(fileTwo.nextLine()); 
                  fileIsRead = true;
               }
               else{ // If this loop has already been run and the user has inputted something as a replacement for an invalid value read from the file
                  health[w] = Integer.parseInt(userInput);  // The user input is originally stored in userInput as it needs to be a string (as we use JOptionPane)
               }              
               if (health[w] < 0 || health[w] > 100){ // Make sure that the inputted value is within the specified range
                  throw new IllegalArgumentException ();
               }
               break;
            } catch(NumberFormatException e){ // If the user/file didn't input a integer
               userInput = JOptionPane.showInputDialog ("\"" + health[w] + "\"" + " is not a valid input for " + name[w] + "'s health. Please enter an integer from 0 to 100:"); // error message
            } catch(IllegalArgumentException E){ // If the input isn't in the specified range
               userInput = JOptionPane.showInputDialog ("\"" + health[w] + "\"" + " is not a valid input for "+ name[w] + "'s health. Please enter an integer from 0 to 100:"); // error message
            } catch (NullPointerException e){ // If the user presses "cancel" for the JOptionPane
               userInput = JOptionPane.showInputDialog ("Error: Please enter an integer from 0 to 100 for " + name[w] + "'s health:");
            }
         }
         
         fileIsRead = false; // Sets fileIsRead back to false so the nextLine command is run
         
         while (true){ // Stores and errortraps for the speed
            try{
               if (fileIsRead == false){ // If this is the first time this loop is being run
                  speed[w] = Integer.parseInt(fileTwo.nextLine()); 
                  fileIsRead = true;
               }
               else{ // If this loop has already been run and the user has inputted something as a replacement for an invalid value read from the file
                  speed[w] = Integer.parseInt(userInput); // The user input is originally stored in userInput as it needs to be a string (as we use JOptionPane)
               }              
               if (speed[w] < 0 || speed[w] > 100){ // Make sure that the inputted value is within the specified range
                  throw new IllegalArgumentException ();
               }
               break; 
            } catch(NumberFormatException e){ // If the user/file didn't input a integer
               userInput = JOptionPane.showInputDialog ("\"" + speed[w] + "\"" + " is not a valid input for "+ name[w] + "'s speed. Please enter an integer from 0 to 100:"); // error message
            } catch(IllegalArgumentException E){ // If the input isn't in the specified range
               userInput = JOptionPane.showInputDialog ("\"" + speed[w] + "\"" + " is not a valid input for "+ name[w] + "'s speed. Please enter an integer from 0 to 100:"); // error message
            } catch (NullPointerException e){ // If the user presses "cancel" for the JOptionPane
               userInput = JOptionPane.showInputDialog ("Error: Please enter an integer from 0 to 100 for " + name[w] + "'s speed:");
            }
         }
         
         fileIsRead = false; // Sets fileIsRead back to false so the nextLine command is run
         
         while (true){ // Stores and errortraps for the wisdom
            try{ 
               if (fileIsRead == false){ // If this is the first time this loop is being run
                  wisdom[w] = Integer.parseInt(fileTwo.nextLine()); 
                  fileIsRead = true;
               }
               else{ // If this loop has already been run and the user has inputted something as a replacement for an invalid value read from the file
                  wisdom[w] = Integer.parseInt(userInput); // The user input is originally stored in userInput as it needs to be a string (as we use JOptionPane)
               }              
               if (wisdom[w] < 0 || wisdom[w] > 100){ // Make sure that the inputted value is within the specified range
                  throw new IllegalArgumentException ();
               }
               break;
            } catch(NumberFormatException e){ // If the user/file didn't input a integer
               userInput = JOptionPane.showInputDialog ("\"" + wisdom[w] + "\"" + " is not a valid input for "+ name[w] + "'s wisdom. Please enter an integer from 0 to 100:"); // error message
            } catch(IllegalArgumentException E){ // If the input isn't in the specified range
               userInput = JOptionPane.showInputDialog ("\"" + wisdom[w] + "\"" + " is not a valid input for "+ name[w] + "'s wisdom. Please enter an integer from 0 to 100:"); // error message
            } catch (NullPointerException e){ // If the user presses "cancel" for the JOptionPane
               userInput = JOptionPane.showInputDialog ("Error: Please enter an integer from 0 to 100 for " + name[w] + "'s wisdom:");
            }
         }
      }
   }
   
   /*
   The mainMenu() method, which will display the description of the program along with the menu options.
   If-else statement:
   1. The first if-else statement is used to see if this is the user's first time at the main menu. If it is, it will display a welcome message along
      with a description of the program. If not, it will display "You are now back at the main menu".
   While loop:
   1. The while loop with the try catch statement inside is used to make sure that the user enters a valid input from the menu options (either 1, 2, or 3).
   */
   public void mainMenu(){
      Scanner input = new Scanner (System.in); // Declare and initialize a new Scanner to take user input
   
      if (isBack == true){
         System.out.println("You are now back at the main menu."); // 'Welcome back to the main menu' message
      }
      else{
         System.out.println("Character Rating Program!"); // Description of the program
         System.out.println("This program will allow you to view all of the attributes associated with each video game character (as provided by the file).\nThese attributes inlcude their name, strength, health, speed, and wisdom. Additionally, you can also view the strongest character overall.");
         System.out.println("Each attribute will be ranked on a scale of 0 to 100, with 0 being very poor and 100 being very strong in that attribute.");
         isBack = true;
      }
      System.out.println("Press 1 if you want to see the all the information for all the characters."); // Menu options
      System.out.println("Press 2 if you want to see the best overall character (this is calculated by using the following formula: Strength x 35 + Health x 30 + Speed x 25 + Wisdom x 10).");
      System.out.println("Press 3 to exit the program.");
      menuInput = input.nextLine(); // Collects user input for the menu options
      while (true){ // error traps the input for the menu options
         try {
            if (menuInput.equals("1")||menuInput.equals("2")||menuInput.equals("3")){ // If the input is valid
               break;
            }
            else{
               throw new IllegalArgumentException ();
            }
         } catch(NullPointerException e){ // Catches if they try to press 'cancel'
            menuInput = JOptionPane.showInputDialog ("Error! Please enter either 1, 2, or 3.");
         } catch(IllegalArgumentException e){ // Catches if they don't enter either 1, 2, or 3
            menuInput = JOptionPane.showInputDialog ("Error! Please enter either 1, 2, or 3.");
         }
         
      }
   }
   
   /*
   The display() method, which will display the either all the characters and their attributes or the top overall player.
   If-else statement:
   1. The if-else statement is used to see if the user wants to see all the characters and their attributes or just the top overall player
   */
   public void display(){
      int score = 0; // Declare an integer named score and assign it the value of 0 (this will store the score of each character as they for loop cycles through them)
      int highestScore = 0; // Declare an integer named highestScore and assign it the value of 0 (this will store the highest score found)
      int highestPlayerIndex = 0; // Declare an integer named highestPlayerIndex and assign it the value of 0 (this will store the index of the character with the highest score)
      
      if (menuInput.equals("1")){ // If the user wants to see all the characters and their attributes
         for (int w = 0; w < name.length; w++){ // Cycle through the length of each array to print out each character's attributes
            System.out.println("Character " + (w+1) + ":");
            System.out.println("Name: " + name[w]); // Prints out the attributes for each character (there's 5 print statements as the attributes for each character are stored at the same index for each aray)
            System.out.println("Strength: " + strength[w]);
            System.out.println("Health: " + health[w]);
            System.out.println("Speed: " + speed[w]);
            System.out.println("Wisdom: " + wisdom[w]);
            System.out.println();
         }
      }
      else if (menuInput.equals("2")){ // If the user wants to see the top overall player
         for (int j = 0; j < name.length; j++){ // Cycles through each character
            score = strength[j] * 35 + health[j] * 30 + speed[j] * 25 + wisdom[j] * 10; // calculates each character's score
            if (score > highestScore){ // If the given characters score is the largest
               highestScore = score; // Store their score
               highestPlayerIndex = j; // Store the index they are at (as all the character's attributes are stored at the same index of each parallel array)
            }
         }
         System.out.println(name[highestPlayerIndex] + " is the greatest overall character with a score of " + highestScore + "!");
         System.out.println("Here are their statistics:"); // Prints their stats
         System.out.println("Strength: " + strength[highestPlayerIndex]);
         System.out.println("Health: " + health[highestPlayerIndex]);
         System.out.println("Speed: " + speed[highestPlayerIndex]);
         System.out.println("Wisdom: " + wisdom[highestPlayerIndex]);
         System.out.println();
      }
   }
   
   /*
   The goodbye() method, which prints a farewell message.
   */
   public void goodbye (){
      System.out.println("Thank you for using this program designed by Jeffery Hu and David Geng. See you next time!"); // Prints farewell message
   } 
   
   /*
   The main method, which controls the flow of the program.
   */
   public static void main(String[] args) throws IOException {
      VideoGameCharacterRatingPart1 v = new VideoGameCharacterRatingPart1(); // Creates an object of the class
      v.readFile(); // Read from file
      while(!v.menuInput.equals("3")){ // As long as the user hasn't inputted option 3 at the main menu, keep running the mainMenu and display methods
         v.mainMenu();
         v.display();
      }
      v.goodbye(); // Display farewell message
   }
}