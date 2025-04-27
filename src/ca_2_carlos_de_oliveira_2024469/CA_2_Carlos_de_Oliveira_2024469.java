/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//Package name, as defined by the Lecture
package ca_2_carlos_de_oliveira_2024469;

//Import Java Libraries
import java.io.*;
import java.util.*;

/**
 *
 * @author Carlos Eduardo Alves de Oliveira | n.º 2024 469
 */
public class CA_2_Carlos_de_Oliveira_2024469 {

    // Main menu options using Enums - Fixed values/options
    enum Menu {
        SORT,
        SEARCH,
        ADD,
        GENERATE,
        EXIT
    }

// Array List | static method to fixed the file name and list the applicants
    static List<Employee> ListEmployee = new ArrayList<>();

//Scanner fixed as a static method - will be used multiple times. 
    static Scanner scanner = new Scanner(System.in);

//Random method fixed as a static method - will be used multiple times
    static Random random = new Random();

    public static void main(String[] args) {
        loadFile();

        //MENU - user will be required to select from a number of options
        int menuOpt = 0;

        //Looping will display the Menu until the user select the EXIT option
        do { //Present the menu for the user.
            System.out.println("*** WELCOME TO TECH COMP APP *** ");
            System.out.println("Select an option in our MENU:");

            //Will display the entired ENUM (length), formated with a number and space before the option "1. ...". 
            for (int i = 0; i < Menu.values().length; i++) {
                System.out.println((1 + i) + ". " + Menu.values()[i]);
            }
            // User will be invited to enter an option from 1 to 5
            System.out.println("Enter your choice | n.º from 1 to 5: ");

            // TRY/CATCH for exceptions | input needs to be a number between 1 and 5 to be validated. 
            try {

                //Gives the user the keyboard to enter values with scanner. 
                menuOpt = scanner.nextInt();

                //Input needs to be a number between 1 and 5 to be validated.
                if (menuOpt >= 1 && menuOpt < 6) {

                    //Based on the entered value, will acess the array (enum) to return one of the options
                    Menu option = Menu.values()[menuOpt - 1];
                    switch (option) {
                        case SORT: {
                            sortList();
                            break;
                        }
                        case SEARCH: {
                            searchEmployee();
                            break;
                        }
                        case ADD: {
                            addEmployee();
                            break;
                        }
                        case GENERATE: {
                            randomEmployee();
                            break;
                        }
                        case EXIT: {
                            System.out.println("Exiting program!");
                            break;
                        }
                    }

                } else if (menuOpt < 1 || menuOpt > 5) { //Error message for invalid value - numbers out of designed. 
                    System.out.println("Invalid choice. Please enter a number between 1 and 5");
                }

            } catch (Exception e) { //Error message for invalid value (Exceptions like String or Symbols).  
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine(); // Allow new input. 
            }

            //Will close the looping when user select 5 (position in the array/enum) as option. 
        } while (menuOpt != 5);
    }

    public static void loadFile() {
        String fileName = "Applicants_Form.txt";
        try (Scanner fileRead = new Scanner(new File(fileName))) {
            while (fileRead.hasNextLine()) {
                String name = fileRead.nextLine().trim();
                if (!name.isEmpty()) {
                    Function jobRole = Function.values()[random.nextInt(Function.values().length)];
                    Department dept = Department.values()[random.nextInt(Department.values().length)];
                    ListEmployee.add(new Employee(name, jobRole, dept));
                }
            }
            System.out.println("File read succesfully");
        } catch (Exception e) {
            System.out.println("File not found.");
        }
    }
        
        public static void sortList() {
        
        }
        
        public static void searchEmployee() {
        
        }
        
        public static void addEmployee() {
        
        }
        
        public static void randomEmployee() {
        
        }
        

    }

 
    

