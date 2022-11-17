package com.acc.java.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.acc.java.bean.AdminBean;
import com.acc.java.dao.AdminDao;



public class AdminService {
	
	AdminDao adminDao = new AdminDao();
	public void createProfile(AdminBean admin, int id)
	{
		adminDao.createProfile(admin,id);
	}
	public void fetchAllProfile()
	{
		adminDao.fetchAllProfile();
	}
	public void fetchProfile(int id)
	{
		adminDao.fetchProfile(id);
	}
	public void updateProfile(int id, int colNum, String val)
	{
		adminDao.updateProfile(id,colNum,val);
	}
	public void deleteProfile(int id)
	{
		adminDao.deleteProfile(id);
	}
	public Map<String,String> readFile() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("UserDetails.txt"));
		Map<String,String> fileData = new HashMap<String,String>();
		String fileLine =null;
		while((fileLine=br.readLine())!=null)
		{
			String fileArray[] = fileLine.split(" ");
			String name = fileArray[0];
			String pwd = fileArray[1];
			if(!name.equals("") && !pwd.equals(""))
			{
				fileData.put(name, pwd);
			}
		}
		System.out.println(fileData);
		return fileData;
	}

	public boolean isPresent(String username, String password, Map<String,String> fileData) throws IOException {
		boolean res=true;
		if(fileData.containsKey(username) && fileData.containsValue(password))
			res=true;
		else
			res=false;
		return res;
	}	
}
