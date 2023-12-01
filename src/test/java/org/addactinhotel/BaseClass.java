package org.addactinhotel;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static Actions a;
	public static Robot r;
	public static Alert al;
	public static JavascriptExecutor js;  
	public static Select s;
	public static FileInputStream fi;
	public static Workbook book;
	public static FileInputStream fin;
	public static Workbook w;
	public static FileOutputStream fos;
	
	public static void loadBrowser() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}
	
	public static void launchUrl(String url) {
		driver.get(url);
	}
	
	public static void maximizePage() {
		driver.manage().window().maximize();

	}
	
	public static String retriveText(WebElement element2) {
		return element2.getText();
	}
	public static String retriveAttribute(WebElement element,String value) {
		return element.getAttribute(value);
	}
	public static void enterText(WebElement element, String txt) {
		element.sendKeys(txt);
	}
	public static void btnClick(WebElement element2) {
		element2.click();
	}
	 
	public static void closePage() {
		driver.close();
	}
	
	public static void quitPage() {
	     driver.quit();
	}
	//SELECT CLASS
	   public static void selByVal(WebElement element,String val) {
		s = new Select(element);
		s.selectByValue(val);
	   }
	   public static void selByTxt(WebElement element,String txt) {
			s = new Select(element);
			s.selectByVisibleText(txt);
		   }
	   public static void selByIndex(WebElement element,int index) {
			s = new Select(element);
			s.selectByIndex(index);
		   }
	   
	   //Read Data From Excel
	   public static String readDataFromExcel(String fileName,String name,int row,int cell) {
			File f =new File("C:\\Users\\Admin\\eclipse-workspace\\TestNG\\ExcelFiles"+fileName+".xlsx");
			
			try { 
				fin = new FileInputStream(f);
			} catch (FileNotFoundException e) {
			}
			
			try {
				w = new XSSFWorkbook(fin);
			} catch (IOException e) {
			}
			Sheet sheet = w.getSheet(name);
			Row row2 = sheet.getRow(row);
			Cell cell2 = row2.getCell(cell);
			int cellType = cell2.getCellType();
			String value = null;
			if (cellType == 1) {
				value = cell2.getStringCellValue();
			}
			else if (DateUtil.isCellDateFormatted(cell2)) {
				Date d = cell2.getDateCellValue();
				SimpleDateFormat s = new SimpleDateFormat("dd-MMMM-YYYY");
				value = s.format(d);		
			}
			else {
				double d = cell2.getNumericCellValue();
				long l = (long) d;
				value = String.valueOf(l);
				}
		 return value;
		}
		   //Create New Sheet
		      public static void writeExcel(String fileName,String sheetName,int rowNo,int cellNo,String value) {
		    	  File f = new File ("C:\\Users\\Admin\\eclipse-workspace\\TestNG\\ExcelFiles"+fileName+".xlsx");
		    	  Workbook w = new XSSFWorkbook();
		    	  Sheet s = w.createSheet(sheetName);
		    	  Row r = s.createRow(rowNo);
		    	  Cell c = r.createCell(cellNo);
		    	  c.setCellValue(value);
		    	  
				try {
					fos = new FileOutputStream(f);
				} catch (FileNotFoundException e) {
				}
		    	  try {
					w.write(fos);
				} catch (IOException e) {
				}
		    	  System.out.println("done");
			}
		      //To Create Row only	
		      
		      public static void createRowOnly(String fileName,String sheetName,int rowNo,int cellNo,String value) {
		    	  File f = new File ("C:\\Users\\Admin\\eclipse-workspace\\TestNG\\ExcelFiles"+fileName+".xlsx");
				try {
					fin = new FileInputStream(f);
				} catch (FileNotFoundException e1) {
				}
				try {
					w = new XSSFWorkbook(fin);
				} catch (IOException e1) {
				}
		    	  Sheet s = w.getSheet(sheetName);
		    	  Row r = s.createRow(rowNo);
		    	  Cell c = r.createCell(cellNo);
		    	  c.setCellValue(value);
		    	  
				try {
					fos = new FileOutputStream(f);
				} catch (FileNotFoundException e) {
				}
		    	  try {
					w.write(fos);
				} catch (IOException e) {
				}
		    	  System.out.println("done");
			}
		      
		      //To Create Cell only	
		      
		      public static void createCellOnly(String fileName,String sheetName,int rowNo,int cellNo,String value) {
		    	  File f = new File ("C:\\Users\\Admin\\eclipse-workspace\\TestNG\\ExcelFiles"+fileName+".xlsx");
				try {
					fin = new FileInputStream(f);
				} catch (FileNotFoundException e1) {
				}
				try {
					w = new XSSFWorkbook(fin);
				} catch (IOException e1) {
				}
		    	  Sheet s = w.getSheet(sheetName);
		    	  Row r = s.getRow(rowNo);
		    	  Cell c = r.createCell(cellNo);
		    	  c.setCellValue(value);
		    	  
				try {
					fos = new FileOutputStream(f);
				} catch (FileNotFoundException e) {
				}
		    	  try {
					w.write(fos);
				} catch (IOException e) {
				}
		    	  System.out.println("done");
			}
		      
		      //To update a value in cell
		      
		      public static void updateCellValue(String fileName,String sheetName,int rowNo,int cellNo,String oldValue,String newValue) {
		    	  File f = new File ("C:\\Users\\Admin\\eclipse-workspace\\TestNG\\ExcelFiles"+fileName+".xlsx");
				try {
					fin = new FileInputStream(f);
				} catch (FileNotFoundException e1) {
				}
				try {
					w = new XSSFWorkbook(fin);
				} catch (IOException e1) {
				}
		    	  Sheet s = w.getSheet(sheetName);
		    	  Row r = s.getRow(rowNo);
		    	  Cell c = r.getCell(cellNo);
		    	  String text = c.getStringCellValue();
		    	  if (text.equals(oldValue)) {
					c.setCellValue(newValue);
				}
				try {
					fos = new FileOutputStream(f);
				} catch (FileNotFoundException e) {
				}
		    	  try {
					w.write(fos);
				} catch (IOException e) {
				}
		    	  System.out.println("done");
			}
		      public static void dateAndTime() {
		    	  Date d = new Date();
		    	  
		    	  SimpleDateFormat s = new SimpleDateFormat();
		    	  String format = s.format(d);
		    	  System.out.println(format);
		      }
}
