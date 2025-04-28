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
            System.out.println("*                              *   ");
            System.out.println("**  WELCOME TO TECH COMP APP  ** ");
            System.out.println("*** * * * * * *  * * * * * * *** ");
            System.out.println("Select an option in our MENU:");
            System.out.println("* * * * * * * * * * * * * * * *");

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
                        case SORT: { //INSERT SORT to sort the list
                            sortList(); //Sorted and limited to 20 first employees. 

                            System.out.println("Employee list sorted (First 20):");
                            /*Looping will get the sorted list from InsertionSort Method and return,
                            line by line, the first 20 names.*/
                            for (int i = 0; i < Math.min(20, ListEmployee.size()); i++) {
                                System.out.println((1 + i) + ". " + ListEmployee.get(i));
                                System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - ");
                            }
                            break;
                        }
                        case SEARCH: { //BINARY SEARCH method - Divide and Conquer
                            sortList(); //Start accessing sorted list (Insert Method) for a better result
                            System.out.println("Enter name to search: ");
                            scanner.nextLine();
                            String nameSearch = scanner.nextLine();

                            // Binary search logic
                            int index = searchEmployee(nameSearch, 0, ListEmployee.size() - 1);

                            //Based on the kB input will check the list - Divide and Conquer
                            if (index != -1) {
                                Employee searchResult = ListEmployee.get(index);
                                System.out.println(" * * * EMPLOYEE FOUND: * * * ");
                                System.out.println("Name: " + searchResult.getName());
                                System.out.println("Department: " + searchResult.getDepartment());
                                System.out.println("Function: " + searchResult.getPosition());

                            } else {
                                System.out.println("*** Employee not found! ***");

                            }
                            break;

                        }
                        case ADD: {
                            scanner.nextLine();
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

    // METHOD TO READ FROM THE FILE AND GENERATE/STORE THE LIST
    public static void loadFile() {

        // Will read the File
        String fileName = "Applicants_Form.txt";

        // From the file, will read line by line, and random associate a Function and a Department for each name (from the class Employee)
        try (Scanner fileRead = new Scanner(new File(fileName))) {
            while (fileRead.hasNextLine()) {
                String name = fileRead.nextLine().trim();
                if (!name.isEmpty()) {
                    Function jobRole = Function.values()[random.nextInt(Function.values().length)];
                    Department dept = Department.values()[random.nextInt(Department.values().length)];

                    // Will read/add to the ArrayList
                    ListEmployee.add(new Employee(name, dept, jobRole));
                }
            }
            System.out.println("File read succesfully");

            // Exception in case File not found. 
        } catch (Exception e) {
            System.out.println("File not found.");
        }
    }

    // RECURSIVE INSERTION SORT METHOD
    public static void insertionSort(List<Employee> list, int n) {

        //If list retun 0, means that it´s already sorted or have just one element. 
        if (n <= 1) {
            return;
        }

        // Sort first n-1 element
        insertionSort(list, n - 1);

        //Insert last element at the correct position
        //in sorted array 
        Employee employeeSort = list.get(n - 1);
        int j = n - 2;

        /*Move elements one position ahead inside the array when greater than the key. */
        while (j >= 0 && list.get(j).getName().compareToIgnoreCase(employeeSort.getName()) > 0) {
            list.set(j + 1, list.get(j));
            j--;
        }

        //Driver method
        list.set(j + 1, employeeSort);
    }

    // INSERTION SORT METHOD 
    public static void sortList() {

        //Validation for EMPTY list - error message
        if (ListEmployee.isEmpty()) {
            System.out.println("Employee list is EMPTY!");
            return;
        }

        //Access and sort the ListEmployee  
        insertionSort(ListEmployee, ListEmployee.size());

    }

    //BINARY SEARCH - Divide and Conquer
    public static int searchEmployee(String key, int start, int end) {

        boolean found;
        int middle = 0;
        found = false;

        while ((start <= end) && (found == false)) {
            middle = (start + end) / 2;

            //From the arrayList
            Employee midEmployee = ListEmployee.get(middle);

            //The algorithm break down the problem into smaller non-overlapping problems. 
            int result = ((Comparable) midEmployee.getName()).compareTo(key);

            if (((Comparable) midEmployee.getName()).compareTo(key) == 0) {
                found = true;

            } else if (((Comparable) midEmployee.getName()).compareTo(key) < 0) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        if (found == true) {
            return middle;
        } else {
            return -1;
        }

    }

    //METHOD TO ADD a new employee to the list
    public static void addEmployee() {

        //Give the user the keyboard to enter a name
        System.out.println("Enter new employee name: ");
        String newEmployee = scanner.nextLine().trim();

        //Loop throught the Department ENUM to display available departments
        System.out.println("Available Departments: ");
        for (int i = 0; i < Department.values().length; i++) {
            System.out.println((i + 1) + ". " + Department.values()[i]);
        }
        //Get the user choice for the department. 
        int departChoice = getIntInput("Select department: ", 1, Department.values().length);

        //Loop throught the Function/Positions ENUM to display available job tittles.
        System.out.println("Available functions:");
        for (int i = 0; i < Function.values().length; i++) {
            System.out.println((i + 1) + ". " + Function.values()[i]);
        }
        //Get the user choice for the job role/position. 
        int roleChoice = getIntInput("Select Function: ", 1, Function.values().length);

        //Create a new Employee object with the collected information
        Employee newEmp = new Employee(newEmployee, Department.values()[departChoice - 1], Function.values()[roleChoice - 1]);

        //Add the new Employee to the Array List and display the result. 
        ListEmployee.add(newEmp);
        System.out.println("Employee added: " + newEmp);

    }

    //GENERATE RAMDOM PEOPLE Method
    //Implement mechanism to generate individuals and assign valid function and department
    public static void randomEmployee() {
        //Create a String to store names
        String[] randomNames = {"Carlos de Oliveira", "Patricia Victoria", };
        
        //Generate a random index to select a name from the array
        String name = randomNames[random.nextInt(randomNames.length)];
        
        //Randomly select a department and a job function from the DEPARTMENT and FUNCTION Enum
        Department departm = Department.values()[random.nextInt(Department.values().length)];
        Function role = Function.values()[random.nextInt(Function.values().length)];
        
        //Create a new employee based on the generated name, deparment and function associate. 
        Employee randomEmp = new Employee(name, departm, role);
        
        //Add the randomly new Employee to the ListEmployee
        ListEmployee.add(randomEmp);
        System.out.println("Rampom Employee: " + randomEmp);
    }

    // SCANNER INPUT METHOD 
    //Allow to manipulate the input (scanner) and manage the enum options for ADD
    public static int getIntInput(String msg, int min, int max) {
        int input = -1;

        //Looping for min and max, based on the enum lenght. 
        while (input < min || input > max) {
            System.out.println(msg);

            //If a valid input, user will have the Keyboard to select the next option.
            //Else, system will return a error message
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please, select a valid number.");
                scanner.nextLine();
            }
        }
        return input;
    }

}
