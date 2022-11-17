package com.acc.java.ui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.acc.java.bean.AdminBean;
import com.acc.java.service.AdminService;



public class AdminLogin {
	static AdminService adminService = new AdminService();
	public static void main(String[] args) throws Exception {
		AdminBean admin = new AdminBean();
		Scanner sc =  new Scanner(System.in);
		String username, password;
		int id,cnt =0;
		Map<String,String> fileValue = new HashMap<String,String>();
		fileValue = adminService.readFile();
		do
		{
		System.out.println("Enter the username:");
		username= sc.next();
		System.out.println("Enter the password:");
		password = sc.next();
		if(adminService.isPresent(username,password,fileValue))
		{
			System.out.println("Login Successful");
			cnt=3;
			System.out.println("Please enter the choice:\n1.Create\n2.View\n3.Update\n4.Delete");
			int ch =sc.nextInt();
			switch(ch)
			{
			case 1:
				System.out.println("Please enter the details:");
				System.out.println("Unique Id:");
				id=sc.nextInt();
				System.out.println("First Name:");
				admin.setFirstName(sc.next());
				System.out.println("Middle Name:");
				admin.setMiddleName(sc.next());
				System.out.println("Last Name:");
				admin.setLastName(sc.next());
				System.out.println("Date of Birth:");
				admin.setDob(sc.next());
				System.out.println("Email:");
				admin.setEmail(sc.next());
				System.out.println("Phone Number:");
				admin.setPhoneNumber(sc.next());
				sc.nextLine();
				System.out.println("Address:");
				admin.setAddress(sc.nextLine());
				System.out.println("Occupation:");
				admin.setOccupation(sc.nextLine());
				System.out.println(admin);
	
				adminService.createProfile(admin,id);
				break;
			case 2:
				System.out.println("Choose\n1.ViewAll\n2.ViewById");
				int c=sc.nextInt();
				if(c==1)
					adminService.fetchAllProfile();
				if(c==2)
					System.out.println("Enter the id:");
					id=sc.nextInt();
					adminService.fetchProfile(id);
				break;
			case 3:
				System.out.println("Enter the id:");
				id=sc.nextInt();
				System.out.println("Choose the number for particular column need to update:");
				System.out.println("1.FirstName\n2.MiddleName\n3.LastName\n4.DOB\n5.Email\n6.PhoneNumber\n7.Address\n8.Occupation");
				int colNum=sc.nextInt();
				System.out.println("Enter the value:");
				sc.nextLine();
				String val=sc.nextLine();
				adminService.updateProfile(id,colNum,val);
				break;
			case 4:
				System.out.println("Enter the id:");
				id=sc.nextInt();
				adminService.deleteProfile(id);
				break;
			default:
				System.out.println("Invalid Choice!");
				break;
			}
		}
		else
		{
			System.out.println("Invalid Login");
			cnt++;
		}
		}while (cnt<3 && cnt>=0);
	}
	
	
	
}
