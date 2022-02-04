package dev.thomas;

import java.util.List;
import java.util.Scanner;

import dev.thomas.models.User;
import dev.thomas.repositories.UserDAO;
import dev.thomas.services.AuthService;
import dev.thomas.services.UserService;

public class Driver {
/*
    private static Scanner = new Scanner(System.in);
    private static AuthService authService = new AuthService();
    private static UserService userService = new UserService();

    public static void main(String[] args) {

        List<User> users = UserDAO.getAllUsers();
        System.out.println(users);

        System.out.println("Employee Reimbursement System (ERS)");
        System.out.println("Register or Login?");
        System.out.println("1 For Register");
        System.out.println("2 For Login");
        System.out.println("3 To Exit");

        String option = scanner.nextLine();

        while( option != "3" || option != "exit") {
            switch (option) {
                case "1":
                    System.out.println("Register");
                    register();
                    login();
                    break;
                case "2":
                    System.out.println("Login");
                    login();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Oops!, try again");
                    option = scanner.nextLine();
            }
        }
    }

    private static void register() {
        String role = "";

        System.out.println("Please enter your First Name");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your Last Name");
        String lastName = scanner.nextLine();
        System.out.println("Please enter your Username");
        String username = scanner.nextLine();
        System.out.println("Please enter your Password");
        String passwrd = scanner.nextLine();
        System.out.println("Please choose your Role");
        System.out.println("1. Employee");
        System.out.println("2. Finance Manager");
        String roleChoice = scanner.nextLine();
        String lowerRole = roleChoice.toLowerCase();
        role = getRole(lowerRole);
        User user = UserDAO.getByUsername(username);
        System.out.println(user);
        if (user == null) {
            try {
                User newUser = new User(0, firstName, lastName, username, passwrd, role);
                UserDAO.create(newUser);
                System.out.println(firstName+" "+lastName+"'s account created successfully");
            } catch (Exception e) {
                System.out.println("User creation failed");
            }
            System.out.println("'" + username + "' Registered");
            //System.out.println(UserDAO.getByUsername(username));
        } else {
            System.out.println(username +" already exists");
            System.out.println(user);
        }
    }

    private static String getRole(String lowerRole) {
        switch(lowerRole) {
            case "1":
                return "EMPLOYEE";
            case "2":
                return "FINANCE MANAGER";
            default:
                System.out.println("Invalid choice, please try again");
                lowerRole = scanner.nextLine();
                return lowerRole = lowerRole.toLowerCase();
        }
    }

    // Case 1: Login
    private static void login() {
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        try {
            if(authService.login(username, password)) {
                System.out.println("Login successful");
                System.out.println("Welcome " + username);
            } else {
                System.out.println("Login unsuccessful");
            }
        } catch (Exception e) {
            System.out.println("Invalid username or password");
        }
    }*/
}
