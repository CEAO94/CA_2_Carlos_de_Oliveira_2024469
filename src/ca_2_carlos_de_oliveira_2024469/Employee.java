/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2_carlos_de_oliveira_2024469;

/**
 *
 * @author Carlos Eduardo Alves de Oliveira | 2024469
 */

//This Java Class will define each employee attibute, such name, position and department
public class Employee {

    private String name;
    private Function position;
    private Department department;

    public Employee(String name, Function position, Department department) {
        this.name = name;
        this.position = position;
        this.department = department;
    }

//Will return a name from the list, as a String;
    public String getName() {
        return name;
    }

//Will return one of the valid Departments fixed as an ENUM in the parent Class Department;
    public Department getDepartment() {
        return department;
    }

//Will return one of the valid job roles fixed as an ENUM in the parent Class Function;
    public Function getPosition() {
        return position;
    }

//Will display the ramdom result, with "Name" follow by department and job role. 
    public String toString() {
        return name + "|" + department + "|" + position + ";";
    }
}
