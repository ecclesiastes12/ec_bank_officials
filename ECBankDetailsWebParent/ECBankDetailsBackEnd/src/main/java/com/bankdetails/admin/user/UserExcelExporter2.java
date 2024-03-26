package com.bankdetails.admin.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.bankdetails.entity.User;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;



//business class that export data to excel
public class UserExcelExporter2  extends AbstractExporter{


//method for writing csv file
	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		
		  // the code below is for generating the file name for the excel file 
		//using time stamp
		  
			/*//code moved to AbstractExport.java
			 * //creates date format object 
			 * DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			 * 
			 * //creates time stamp that format the current date and time 
			 * String timestamp = dateFormatter.format(new Date());
			 * 
			 * 
			 * // users_ can be any name of your choice 
			 * //creates file name. 
			 * String fileName = "users_" + timestamp + ".xlsx";
			 * 
			 * 
			 * //header for the file to be downloaded. 
			 * String headerKey = "Content-Disposition"; 
			 * String headerValue = "attachment; filename=" +
			 * fileName; response.setHeader(headerKey, headerValue);
			 */
		 
		super.setResponseHeader(response,"application/octet-stream",".xlsx");
		
		
		//creates excel workbook object
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		//create excel sheet with name Users
		XSSFSheet sheet = workbook.createSheet("Users");
		
		//create sheet row
		XSSFRow row = sheet.createRow(0);
		
		//create row cells
		XSSFCell cell = row.createCell(0);
		
		//set string  value for the cell
		cell.setCellValue("User ID");
		
		//get output stream from the response
		ServletOutputStream outputStream = response.getOutputStream();
		
		//write the workbook to the output stream
		workbook.write(outputStream);
		
		//close the workbook
		workbook.close();
		
		//close the output stream
		outputStream.close();
		
		
		
	}
}
