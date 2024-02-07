package com.edu.hospital_management;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PatientOperations 
{
		static Connection conn ;
		private static Statement stmt;
		private static PreparedStatement pst;
		private static String sql;
		private static ResultSet rs;
		private static String name ,phone_no,email;
		private static int id ;
		private static int updvar;
		
		public static void PatientOperations() throws SQLException {
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("\n Patient Operations:");
	            System.out.println("1. Add Patient");
	            System.out.println("2. View Patient");
	            System.out.println("3. Update Patient");
	            System.out.println("4. Delete Patient");
	            System.out.println("5. Go back to main menu");

	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice)
	            {
	                case 1:
	                    // Call method to add a patient
	                	addPatient();
	                    break;
	                case 2:
	                    // Call method to view patient
	                	displayPatientsDetails();	                    break;
	                case 3:
	                    // Call method to update patient
	                	updatePatient();
	                    break;
	                case 4:
	                    // Call method to delete patient
	                	deletePatient();
	                    break;
	                case 5:
	                    return; // Go back to main menu
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
 }

		
		public static void displayPatientsDetails () throws SQLException {
		conn=DatabaseConnection.getConnection();
		sql="select * from patient";
	    pst=conn.prepareStatement(sql);	
		 rs=pst.executeQuery();
		 
		 
		 System.out.println("Id\tName\t\temail\t\t\tphone_no");
			System.out.println("________________________________________________________________");
			while(rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				email = rs.getString("email");
				phone_no = rs.getString("phone_no");
				
				
				System.out.println(id+"\t"+name+"\t\t"+email+"\t"+phone_no);
			}
		

	}
		
		public static void addPatient() throws SQLException {
			conn= DatabaseConnection.getConnection();
			Scanner sc = new Scanner (System.in);
			System.out.println("Enter Name");
			name=sc.nextLine();
			System.out.println("Enter Email");
			email=sc.nextLine();
			System.out.println("Enter Phone number");
			phone_no=sc.nextLine();
			stmt=conn.createStatement();
			String s="select max(id)+1 as pid from patient";
			rs=stmt.executeQuery(s);
			System.out.println(rs);
			int stid = 0;
			if ( rs.next()) {
				stid=rs.getInt("pid");
			}
			sql="insert into patient (id,name,email,phone_no)values (?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,stid);
			pst.setString(2, name);
			pst.setString(3, email);
			pst.setString(4, phone_no);

			
			int i=pst.executeUpdate();
			if( i>0){
				System.out.println("Patient Added Successfully...!");
			}
			else {
				System.out.println("Not Added Successfully");
			}
			}
		
		public static void deletePatient() throws SQLException {
			
			conn= DatabaseConnection.getConnection();
			Scanner sc = new Scanner (System.in);
	        System.out.println("Enter the patients id to delete ");
	        id=sc.nextInt();
	        
	        // cheak id exists
	        
	        sql="select * from patient where id=?";
	        pst=conn.prepareStatement(sql);
	        pst.setInt(1, id);
	        rs=pst.executeQuery();
	        if ( rs.next()){
	        	String del= "delete from patient where id =?";
	        	  pst=conn.prepareStatement(del);
	        	  pst.setInt(1, id);
	        	  int i=pst.executeUpdate();
	        	  
	        	if (i>0 ) {
	        		System.out.println("Record is deleted ");
	        	}
	        }
	        else {
	    		System.out.println("Patient id not Exists");
	    	}
	       }
		
	public static void updatePatient() throws SQLException {
			
			conn= DatabaseConnection.getConnection();
			
			Scanner sc = new Scanner (System.in);
			
			System.out.println("Enter the id");
			id=sc.nextInt();
			sql="select * from patient where id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			if ( rs.next()) {// cheak exist
				int ch;
				System.out.println("chose one of them for  update ");
				System.out.println("1.To update Patient Name\n2.To update Patient Email");
				int choise=sc.nextInt();
				switch(choise) {
				
				case 1:// update Patient Name
					
					System.out.println("Enter the Name of Patient");
					sc.nextLine();
					name=sc.nextLine();
					sql="update patient set name=? where id=?";
					pst=conn.prepareStatement(sql);
					pst.setString(1,name);
					pst.setInt(2,id);
					updvar=pst.executeUpdate();
					if ( updvar>0) {
						System.out.println("Your Name is "+name);
					}
					else {
					System.out.println(id+" Not Exist");
				}
				break;

	        case 2:// update Patient Email
	        	
	        	System.out.println("Enter the Email of Patient");
	        	sc.nextLine();
	        	email=sc.nextLine();
	        	sql="update patient set email=? where id=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,email);
				pst.setInt(2,id);
				updvar=pst.executeUpdate();
				if ( updvar>0) {
					System.out.println("Your email is: "+email);
				}
				else {
				System.out.println(id+" Not Exist");
			}
			break;
			
	        case 3:// update Phone Number
			
				System.out.println("Enter the Phone Number of patient");
				sc.nextLine();
				phone_no=sc.nextLine();
				sql="update patient set phone_no=? where id=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,phone_no);
				pst.setInt(2,id);
				updvar=pst.executeUpdate();
				if ( updvar>0) {
					System.out.println("Your Phone number is: "+ phone_no);
				}
				else {
				System.out.println(id+" Not Exist");
			}
				break;
				

		}
	
	  }

	}
	
}
