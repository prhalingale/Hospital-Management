package com.edu.hospital_management;
import java.sql.*;
import java.util.Scanner;
import com.edu.hospital_management.DatabaseConnection;

public class DoctorOperations {
	
	static Connection conn ;
	private static Statement stmt;
	private static PreparedStatement pst;
	private static String sql;
	private static ResultSet rs;
	private static String name ,specialization;
	private static int id ;
	private static int updvar;
	
	public static void DoctorOperations() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n Doctor Operations:");
            System.out.println("1. Add Doctor");
            System.out.println("2. View Doctor");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Go back to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice)
            {
                case 1:
                    // Call method to add a doctor
                	addDoctor();
                    break;
                case 2:
                    // Call method to view doctor
                	displayDoctorDetails ();	                    
                	break;
                case 3:
                    // Call method to update doctor
                	updateDoctor();
                    break;
                case 4:
                    // Call method to delete doctor
                	deleteDoctor();
                    break;
                case 5:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
}
	
	  // Create operation
    public static void  addDoctor()  throws SQLException {
    	conn= DatabaseConnection.getConnection();
		Scanner sc = new Scanner (System.in);
		System.out.println("Enter Name");
		name=sc.nextLine();
		System.out.println("Enter Specialization");
		specialization=sc.nextLine();
		stmt=conn.createStatement();
		String s="select max(id)+1 as id from doctor";
		rs=stmt.executeQuery(s);
		System.out.println(rs);
		int stid = 0;
		if ( rs.next()) {
			stid=rs.getInt("id");
		}
		sql="insert into doctor (id,name,specialization)values (?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setInt(1,stid);
		pst.setString(2, name);
		pst.setString(3,specialization );
		
		int i=pst.executeUpdate();
		if( i>0){
			System.out.println("Doctor Added Successfully...!");
		}
		else {
			System.out.println("Not Added Successfully");
		}
	}
		
    
 
		

    // Read operation
    public static void  displayDoctorDetails () throws SQLException{
    	conn=DatabaseConnection.getConnection();
    	sql="select * from doctor";
        pst=conn.prepareStatement(sql);	
    	 rs=pst.executeQuery();
        
    	 System.out.println("Id\tName\t\tspecialization");
 		System.out.println("____________________________________________________");
 		while(rs.next()) {
 			id = rs.getInt("id");
 			name = rs.getString("name");
 			specialization = rs.getString("specialization");
 			
 			System.out.println(id+"\t"+name+"\t\t"+specialization);
 		}
     }

    
    
    // Update operation
    public static void updateDoctor() throws SQLException {
		conn= DatabaseConnection.getConnection();
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Enter the id");
		id=sc.nextInt();
		sql="select * from doctor where id=?";
		pst=conn.prepareStatement(sql);
		pst.setInt(1, id);
		
		rs = pst.executeQuery();
		
		if ( rs.next()) {// cheak exist
			int ch;
			System.out.println("chose one of them for  update ");
			System.out.println("1.To update Doctor Name\n2.To update Doctors specialization");
			int choise=sc.nextInt();
			switch(choise) {
			
			case 1:// update Doctors Name
				
				System.out.println("Enter the Name of Doctor");
				sc.nextLine();
				name=sc.nextLine();
				sql="update doctor set name=? where id=?";
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

        case 2:// update Doctors specialization
        	
        	System.out.println("Enter the specialization of Doctor");
        	sc.nextLine();
        	specialization=sc.nextLine();
        	sql="update doctor set specialization=? where id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,specialization);
			pst.setInt(2,id);
			updvar=pst.executeUpdate();
			if ( updvar>0) {
				System.out.println("Your email is: "+specialization);
			}
			else {
			System.out.println(id+" Not Exist");
		}
		break;
		}
	}
}


    // Delete operation
    public static void deleteDoctor() throws SQLException 
   {
	conn= DatabaseConnection.getConnection();
	Scanner sc = new Scanner (System.in);
    System.out.println("Enter the doctor id to delete ");
    id=sc.nextInt();
    
    // cheak id exists
    
    sql="select * from doctor where id=?";
    pst=conn.prepareStatement(sql);
    pst.setInt(1, id);
    rs=pst.executeQuery();
    if ( rs.next()){
    	String del= "delete from doctor where id =?";
    	  pst=conn.prepareStatement(del);
    	  pst.setInt(1, id);
    	  int i=pst.executeUpdate();
    	  
    	if (i>0 ) 
    	{
    		System.out.println("Record is deleted ");
    	}
    }
    else 
    {
		System.out.println("doctor id not Exists");
	}
   }

}
