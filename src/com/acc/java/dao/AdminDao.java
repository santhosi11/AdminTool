package com.acc.java.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import com.acc.java.bean.AdminBean;

public class AdminDao {
	public void createProfile(AdminBean admin,int id)
	{
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		
		try {
		File file = new File("C:\\Users\\santhosa.l.nagarajan\\OneDrive - Accenture\\Desktop\\JavaTraining\\AdminDetail\\UserDetailExcel.xlsx");
		if(file.exists())
		{
			FileInputStream fis = new FileInputStream("C:\\Users\\santhosa.l.nagarajan\\OneDrive - Accenture\\Desktop\\JavaTraining\\AdminDetail\\UserDetailExcel.xlsx");
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet("UserDetails");	
		}
		else
		{
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("UserDetails");
			String header[]= {"Id","FirstName","MiddleName","LastName","DOB","Email","PhoneNumber","Address","Occupation"};
			Row row = sheet.createRow(0);
			for(int i=0;i<header.length;i++)
			{
				Cell cell = row.createCell(i);
				cell.setCellValue(header[i]);
			}
		}
		int lastrow=sheet.getLastRowNum();
		Set<Integer> idSet = new HashSet<Integer>();
		for(int i=1;i<lastrow;i++)
		{
			idSet.add((int)sheet.getRow(i).getCell(0).getNumericCellValue());
		}
		int initialVal = idSet.size();
		idSet.add(id);
		int finalVal = idSet.size();
		if(initialVal == finalVal)
		{
			System.out.println("ID already Present!");
		}
		else
		{
			Row newRow = sheet.createRow(lastrow+1);
			newRow.createCell(0).setCellValue(id);
			newRow.createCell(1).setCellValue(admin.getFirstName());
			newRow.createCell(2).setCellValue(admin.getMiddleName());
			newRow.createCell(3).setCellValue(admin.getLastName());
			newRow.createCell(4).setCellValue(admin.getDob());
			newRow.createCell(5).setCellValue(admin.getEmail());
			newRow.createCell(6).setCellValue(admin.getPhoneNumber());
			newRow.createCell(7).setCellValue(admin.getAddress());
			newRow.createCell(8).setCellValue(admin.getOccupation());
			FileOutputStream fos = new FileOutputStream("C:\\Users\\santhosa.l.nagarajan\\OneDrive - Accenture\\Desktop\\JavaTraining\\AdminDetail\\UserDetailExcel.xlsx");
			workbook.write(fos);
			fos.close();
			workbook.close();
		}
	  } 
	  catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void fetchAllProfile()
	{
		try {
		FileInputStream fis = new FileInputStream("C:\\Users\\santhosa.l.nagarajan\\OneDrive - Accenture\\Desktop\\JavaTraining\\AdminDetail\\UserDetailExcel.xlsx"); 
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet= workbook.getSheet("UserDetails");
		Iterator<Row> itRow = sheet.iterator();
		while(itRow.hasNext())
		{
			Row row = itRow.next();
			Iterator<Cell> itCell =row.cellIterator();
			while(itCell.hasNext())
			{
				Cell cell =itCell.next();
				CellType cellType = cell.getCellType();
				if(cellType.equals(CellType.NUMERIC))
					System.out.print((int)cell.getNumericCellValue()+"\t\t");
				else
					System.out.print(cell.getStringCellValue()+"\t\t");
			}
			System.out.println("");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void fetchProfile(int id)
	{
		try 
		{
		FileInputStream fis = new FileInputStream("C:\\Users\\santhosa.l.nagarajan\\OneDrive - Accenture\\Desktop\\JavaTraining\\AdminDetail\\UserDetailExcel.xlsx"); 
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet= workbook.getSheet("UserDetails");	    
		Row row = sheet.getRow(0);
		for(Cell cell :row)
		{
			System.out.print(cell.getStringCellValue()+"\t\t");
		}
		System.out.println("");
		int lastrow = sheet.getLastRowNum();
		int rIndex =-1;
		for(int i=1;i<lastrow;i++)
		{
			if(sheet.getRow(i).getCell(0).getNumericCellValue()==id)
			{
				rIndex=i;
				break;
			}
		}
		if(rIndex==-1)
			System.out.println("ID not found!");
		else
		{
			Row rowFetch=sheet.getRow(rIndex);
			for(Cell cell:rowFetch)
			{
				switch(cell.getCellType())
				{
				case NUMERIC:
					System.out.print((int)cell.getNumericCellValue()+"\t\t");
					break;
				case STRING:
					System.out.print(cell.getStringCellValue()+"\t\t");
				}
			}
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateProfile(int id, int colId, String strValue)
	{
		try 
		{
		FileInputStream fis = new FileInputStream("C:\\Users\\santhosa.l.nagarajan\\OneDrive - Accenture\\Desktop\\JavaTraining\\AdminDetail\\UserDetailExcel.xlsx"); 
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet= workbook.getSheet("UserDetails");
		int lastrow = sheet.getLastRowNum();
		int rIndex =-1;
		for(int i=1;i<lastrow;i++)
		{
			if((sheet.getRow(i).getCell(0).getNumericCellValue())==id)
			{
				rIndex=i;
				break;
			}
		}
		if(rIndex==-1)
			System.out.println("ID not found!");
		else
		{
			Row row = sheet.getRow(rIndex);
			Cell cell = row.getCell(colId);
			cell.setCellValue(strValue);	
		
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\santhosa.l.nagarajan\\OneDrive - Accenture\\Desktop\\JavaTraining\\AdminDetail\\UserDetailExcel.xlsx");
		workbook.write(fos);
		fos.close();
		workbook.close();
		System.out.println("Details Updated...");
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deleteProfile(int id)
	{
		try 
		{
		FileInputStream fis = new FileInputStream("C:\\Users\\santhosa.l.nagarajan\\OneDrive - Accenture\\Desktop\\JavaTraining\\AdminDetail\\UserDetailExcel.xlsx"); 
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet= workbook.getSheet("UserDetails");
		int lastrow = sheet.getLastRowNum();
		int rIndex =-1;
		for(int i=1;i<lastrow;i++)
		{
			if(sheet.getRow(i).getCell(0).getNumericCellValue()==id)
			{
				rIndex=i;
				break;
			}
		}
		if(rIndex==-1)
			System.out.println("ID not found!");
		else {
		if(rIndex==lastrow)
		{
			Row removerow = sheet.getRow(rIndex);
			if(removerow!=null)
				sheet.removeRow(removerow);
		}
		if(rIndex>0 && rIndex<lastrow)
		{
			sheet.shiftRows(rIndex+1,lastrow,-1);
		}
		FileOutputStream fos = new FileOutputStream("C:\\Users\\santhosa.l.nagarajan\\OneDrive - Accenture\\Desktop\\JavaTraining\\AdminDetail\\UserDetailExcel.xlsx");
		workbook.write(fos);
		fos.close();
		workbook.close();
		System.out.println("Details Deleted...");
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
