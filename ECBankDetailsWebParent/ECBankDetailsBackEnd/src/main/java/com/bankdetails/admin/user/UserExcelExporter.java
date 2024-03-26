package com.bankdetails.admin.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.bankdetails.entity.User;
import com.mysql.cj.jdbc.IterateBlock;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;



//business class that export data to excel
public class UserExcelExporter  extends AbstractExporter{


	//declare variable for workbook
	private  XSSFWorkbook workbook;
	
	//declare variable for excel sheet
	private XSSFSheet sheet;
	
	public UserExcelExporter() {
		//create workbook instance object
		 workbook = new XSSFWorkbook();
	
	}
	
//method that write header columns for the excel sheet
	private void writeHeaderLine() {
		//create excel sheet with name users
		sheet = workbook.createSheet("Users");
		
		//create sheet row
		XSSFRow row = sheet.createRow(0);
		
		//create cell style
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		
		//create font for the workbook
		XSSFFont font = workbook.createFont();
		
		//set font as bold
		font.setBold(true);
		
		//set font hight
		font.setFontHeight(16);
		
		//set font for the cell style
		cellStyle.setFont(font);
		
		//create the first cell for the header column
		createCell(row, 0, "ID", cellStyle);
		createCell(row, 1, "First Name", cellStyle);
		createCell(row, 2, "Last Name", cellStyle);
		createCell(row, 3, "Email", cellStyle);
		createCell(row, 4, "Identity Card", cellStyle);
		createCell(row, 5, "Phone Number", cellStyle);
	}
	
	//private method that create cells for the excel sheet
	private void createCell(XSSFRow row, int columnIndex, Object value, CellStyle style ) {
		//create row cells
		XSSFCell cell = row.createCell(columnIndex);
		
		//auto size cell column
		sheet.autoSizeColumn(columnIndex);
		
		//set string  value for the cell
		//cell.setCellValue(value);
		
		//if statement that set cell value based on the object type
		if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			cell.setCellValue((String) value);
		}
		
		//set cell style
		cell.setCellStyle(style);
	}
 
//method for writing csv file
	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		//NB check UserExcelExporter2.java for the previous code before creating of cell style
		super.setResponseHeader(response,"application/octet-stream",".xlsx");
		
		writeHeaderLine();
		
		//actual user data
		writeDataLines(listUsers);
		
		//create sheet row
		//XSSFRow row = sheet.createRow(0);
		
//		//create cell style
//		XSSFCellStyle cellStyle = workbook.createCellStyle();
//		
//		//create font for the workbook
//		XSSFFont font = workbook.createFont();
//		
//		//set font as bold
//		font.setBold(true);
//		
//		//set font hight
//		font.setFontHeight(16);
//		
//		//set font for the cell style
//		cellStyle.setFont(font);
		
		
//		//create row cells
//		XSSFCell cell = row.createCell(0);
//		
//		//set string  value for the cell
//		cell.setCellValue("User ID");
//		
//		//set cell style
//		cell.setCellStyle(cellStyle);
		
		
		
		//get output stream from the response
		ServletOutputStream outputStream = response.getOutputStream();
		
		//write the workbook to the output stream
		workbook.write(outputStream);
		
		//close the workbook
		workbook.close();
		
		//close the output stream
		outputStream.close();
		
		
		
	}

	//private method that write the actual user data to be exported
	private void writeDataLines(List<User> listUsers) {
		// start the row index from 1 because in the excel sheet ,index 0
		// is the column header line
		int rowIndex = 1;
		
		//create cell style
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		
		//create font for the cell
		XSSFFont font = workbook.createFont();
		
		//set cell font height
		font.setFontHeight(14);
		
		//set cell font
		cellStyle.setFont(font);
		
		//iterate through each user object in the list
		for (User user : listUsers) {
			//write a new row into the excel document. Thus create a new 
			//row from the sheet starting from the row index 1 and increase
			//the index after each loop
			XSSFRow row = sheet.createRow(rowIndex++);
			
			//specify the column index
			int columnIndex = 0;
			
			//create a new cell with the given columnIndex
			createCell(row, columnIndex++, user.getId(), cellStyle);
			createCell(row, columnIndex++, user.getFirstName(), cellStyle);
			createCell(row, columnIndex++, user.getLastName(), cellStyle);
			createCell(row, columnIndex++, user.getEmail(), cellStyle);
			createCell(row, columnIndex++, user.getIdentityCard(), cellStyle);
			createCell(row, columnIndex++, user.getPhoneNumber(), cellStyle);
			
			
		}
		
		
		
	}
}
