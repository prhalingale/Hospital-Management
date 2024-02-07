package com.edu.hospital_management;
import java.sql.SQLException;
import java.util.Scanner;
public class MainApp
{

	public static void main(String[] args) throws SQLException
	{
		Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hospital Management System!");

        while (true)
        {
            System.out.println("\nSelect an option:");
            System.out.println("1.  Doctor");
            System.out.println("2.  Patient");
            System.out.println("3.  Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) 
            {
                case 1:
                    DoctorOperations.DoctorOperations();
                    break;
                case 2:
                    PatientOperations.PatientOperations();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

	}

}
}