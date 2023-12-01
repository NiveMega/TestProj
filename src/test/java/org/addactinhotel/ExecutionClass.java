package org.addactinhotel;
 
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExecutionClass extends BaseClass  {
	public static ElementsFB e;
	@BeforeClass
	public void beforeclass() {
		loadBrowser();
		launchUrl("https://adactinhotelapp.com/");
		maximizePage();
	}
	@BeforeMethod
	public void beforeTest() {
	dateAndTime();
	}
	@Test(priority=-1)
	public void loginData() throws IOException{
		//for LoginPage
		writeExcel("AdactinLogin", "login", 5, 5, "login");
		createRowOnly("AdactinLogin", "login", 0, 0, "UserName");
		createCellOnly("AdactinLogin", "login", 0, 1, "Password");
		
		createRowOnly("AdactinLogin", "login", 1, 0, "vaithy2410");
		createCellOnly("AdactinLogin", "login", 1, 1, "vaithy@2410");
	}
@Test(priority=0)
public void loginPage() {
	 
	e = new ElementsFB();
    enterText(e.getUsername(), readDataFromExcel("AdactinLogin", "login", 1, 0));
    enterText(e.getPassword(), readDataFromExcel("AdactinLogin", "login", 1, 1));
    btnClick(e.getBtnLogin());	
    System.out.println("Login completed successfully");
}
@Parameters({"location","hotels","roomType","noOfRoom","checkIn","checkOut","adultPer","childPer"})
@Test(priority=1)
 void booking(String location, String hotels, String roomType, String noOfRoom, String checkIn, String checkOut, String adultPer, String childPer) throws InterruptedException  
{
 Thread.sleep(3000);
	e = new ElementsFB();
	selByTxt(e.getLocation(),location);
    selByTxt(e.getHotels(), hotels);
	selByTxt(e.getRoomType(), roomType);
	selByTxt(e.getNoOfRooms(), noOfRoom);
	enterText(e.getCheckInDate(), checkIn);
	enterText(e.getCheckInDate(), checkOut);
	selByTxt(e.getAdultsPerRoom(), adultPer);
	selByTxt(e.getChildrenPerRoom(), childPer);
	btnClick(e.getBtnSearch());

}
@Test(priority=2)
public void searchHotel() throws InterruptedException {
	//Search Hotel Page
	Thread.sleep(2000);
	e = new ElementsFB();
			btnClick(e.getRadioBtn());
			btnClick(e.getContinueBtn());
}
@Test(priority=3,dataProvider="finalData")
public void finalDataHotel(String fName,String lName,String add,String num,String cName,String mon,String year,String cvv) {
	//Search Hotel Page
	e = new ElementsFB();
	enterText(e.getFirstName(), fName);
	enterText(e.getLastName(), lName);
	enterText(e.getAddress(), add);
	enterText(e.getCcNum(), num);
	selByTxt(e.getCcType(), cName);
	selByTxt(e.getCcExpMonth(), mon);
	selByTxt(e.getCcExpYear(), year);
	enterText(e.getCcCvv(), cvv);
	btnClick(e.getBookNowBtn());
}
@DataProvider(name="finalData")
private String[][] data() {
	return new String[][]	
			{
	{"Nivedha","Meganathan","Coimbatore,Tamilnadu","1234567891234567","American Express","January","2024","123"},
	{"Nived","Mega","Coimbatore,Tamilnadu","1234567891234567","American Express","January","2024","123"}
	};
}

@Test(priority=4)
public void confirmHotel() throws Throwable {
	
	//Booking Confirmation
			Thread.sleep(5000);
			 e = new ElementsFB();
			String attribute = retriveAttribute(e.getOrderNo(), "value");
			System.out.println("Order Id id "+attribute);
			}
@AfterMethod
public void afterTest() {
dateAndTime();
}
@AfterClass
public void afterClass() {
System.out.println("Order ID Generated");
} 
}



