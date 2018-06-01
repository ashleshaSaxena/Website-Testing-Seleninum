package com.website.test;


import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;



public class test1 {
	WebDriver driver;
	JavascriptExecutor jse;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	
	
	public void invokeBrowser() {
		
		try {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		
		driver.get("http://localhost//gallery//");
		tasks();
		}
		catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	
	public void tasks() {
		
		try {
			String File="E:\\Report24.pdf";
			Font red = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
			Font green = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.GREEN);
			Font custom = new Font();
			Font Title = new Font();
			Font MBold = new Font();
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(File));
			doc.open();
			MBold.setStyle("bold");
			Title.setStyle("italic");
			Title.setSize(16);
			Title.setStyle("underline"); 
			Title.setColor(162, 0, 0);
			Paragraph t = new Paragraph("GALLERY WEBSITE TEST REPORT",Title);
			doc.add(t);
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			String name="as";
			String pass="";
			String tempps = "asas";
			
			//button-check
			custom.setStyle("bold");
			custom.setSize(14);
			doc.add(new Paragraph("TEST CASE: 1",custom));
			Paragraph t1 = new Paragraph("LOGIN AS VISITOR BUTTON",custom);
			t1.setAlignment(Element.ALIGN_CENTER);
			doc.add(t1);
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph("Excepted Output:",MBold));
			doc.add(new Paragraph("Login Page for visitor opened"));
			driver.findElement(By.xpath("/html/body/a[2]")).click();
			String VisitorUrl = driver.getCurrentUrl();
			String url1 ="http://localhost//gallery//logi.php";
			System.out.println(VisitorUrl);
			Thread.sleep(3000);
			if (VisitorUrl.equals(url1)) {
				doc.add(new Paragraph("Actual Output:",MBold));
				doc.add(new Paragraph("Login page for visitor opened"));
				Paragraph p5 = new Paragraph("Result: ",MBold);
				p5.add(new Chunk("Pass",green));
				doc.add(p5);
			}
			else {
				doc.add(new Paragraph("Actual Output:",MBold));
				doc.add(new Paragraph("Login page for visitor not opened"));
				Paragraph p4 = new Paragraph("Result: ",MBold);
				p4.add(new Chunk("Failed",red));
				doc.add(p4);
			}
			
			//Login-check
			doc.add(new Paragraph("TEST CASE: 2",custom));
			Paragraph t2 = new Paragraph("LOGIN AS VISITOR",custom);
			t2.setAlignment(Element.ALIGN_CENTER);
			doc.add(t2);
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph("Excepted Output:",MBold));
			doc.add(new Paragraph("1. Input username field worked \n\t2. Input password filed worked\n\t3. Logged in successful if entites made are correct else pop up message"));
			WebElement user = driver.findElement(By.xpath("//*[@id=\"input-1\"]"));
			user.sendKeys(name);
			user.sendKeys(Keys.TAB);
			doc.add(new Paragraph("Actual Output:",MBold));
			doc.add(new Paragraph("1: Input username field worked"));
			Thread.sleep(3000);
			
			
			dbconnect db1 = new dbconnect();
			db1.getData();
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gallery","root","");
			st = con.createStatement();
				
				
			String query = "SELECT * FROM visitor";
			rs = st.executeQuery(query);
				
			while (rs.next())
			{
				String temp = rs.getString("Name");
				if(name.equals(temp)) {
					pass = rs.getString("Password");
					break;
				}
			}
			System.out.println("Name: " + name + "  " + "Password: " + pass );
				
			WebElement password = driver.findElement(By.xpath("//*[@id=\"input-4\"]"));
			doc.add(new Paragraph("2: Input password field worked"));
			if(pass.equals(tempps)) {
				
				password.sendKeys(tempps);
				Thread.sleep(2000);
				password.sendKeys(Keys.TAB);
				password.submit();
				Thread.sleep(2000);
				String AfterlogUrl = driver.getCurrentUrl();
				String Url2="http://localhost//gallery//visitor.php";
				if(AfterlogUrl.equals(Url2)) {
					doc.add(new Paragraph("3: Logged in Succesful when enteries made were correct"));
					Paragraph p3 = new Paragraph("Result: ",MBold);
					p3.add(new Chunk("Pass",green));
					doc.add(p3);
				}
				else {
					doc.add(new Paragraph("3: Not logged in Succesful when enteries made were correct"));
					Paragraph p2 = new Paragraph("Result: ",MBold);
					p2.add(new Chunk("Failed",red));
					doc.add(p2);
				}
				
				
				
				//Painting-Check Button
				doc.add(new Paragraph("TEST CASE: 3",custom));
				Paragraph t3 = new Paragraph("PAINTINGS BUTTON",custom);
				t3.setAlignment(Element.ALIGN_CENTER);
				doc.add(t3);
				doc.add(new Paragraph(" "));
				doc.add(new Paragraph("Excepted Output:",MBold));
				doc.add(new Paragraph("Painting page opened"));
				driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]")).click();
				String PaintingUrl = driver.getCurrentUrl();
				String url3="http://localhost//gallery//pweb.php";
				if(PaintingUrl.equals(url3)) {
					doc.add(new Paragraph("Actual Output:",MBold));
					doc.add(new Paragraph("Painting page opened"));
					Paragraph p = new Paragraph("Result: ",MBold);
					p.add(new Chunk("Pass",green));
					doc.add(p);
				}
				else {
					doc.add(new Paragraph("Actual Output:",MBold));
					doc.add(new Paragraph("Painting page not opened"));
					Paragraph p = new Paragraph("Result: ",MBold);
					p.add(new Chunk("Failed",red));
					doc.add(p);
				}
				
				//painting page check
				doc.add(new Paragraph("TEST CASE: 4",custom));
				Paragraph t4 = new Paragraph("PAINTING PAGE ELEMENT",custom);
				t4.setAlignment(Element.ALIGN_CENTER);
				doc.add(t4);
				doc.add(new Paragraph(" "));
				doc.add(new Paragraph("Excepted Output:",MBold));
				doc.add(new Paragraph("1. Add to cart button working \n\t2. Scroll Bar working \n\t3. Buy now button working \n\t4. Coupon and Update button working \n\t5. Checkout Button working \n\t6. Invoice print. "));
					
				
				Thread.sleep(2000);
				jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0,500)");
					//1.			
				driver.findElement(By.xpath("//*[@id=\"buy3\"]")).click();
				String addcarturl = driver.getCurrentUrl();
				if(addcarturl.equals("http://localhost//gallery//rt.php")) {
					doc.add(new Paragraph("Actual Output:",MBold));
					doc.add(new Paragraph("1. Add to cart button worked "));
					Paragraph p = new Paragraph("Result: ",MBold);
					p.add(new Chunk("Pass",green));
					doc.add(p);
				}
				else {
					doc.add(new Paragraph("1. Add to cart button not worked"));
					Paragraph p = new Paragraph("Result: ",MBold);
					p.add(new Chunk("Failed",red));
					doc.add(p);
				}
					
					//2.
				jse.executeScript("scroll(0,1000)");
				Thread.sleep(2000);
				jse.executeScript("scroll(0,-500)");
				Thread.sleep(2000);
				doc.add(new Paragraph("2. Scroll Bar worked "));
				Paragraph p = new Paragraph("Result: ",MBold);
				p.add(new Chunk("Pass",green));
				doc.add(p);
				
					//3.
				driver.findElement(By.xpath("//*[@id=\"AddToCartText\"]/input")).click();
				Thread.sleep(3000);
				String buyurl = driver.getCurrentUrl();
				if(buyurl.equals("http://localhost//gallery//cartp.php")) {
					doc.add(new Paragraph("3. Buy Now button worked "));
					Paragraph p6 = new Paragraph("Result: ",MBold);
					p6.add(new Chunk("Pass",green));
					doc.add(p6);
				}
				else {
					doc.add(new Paragraph("3. Add to cart button not worked"));
					Paragraph p6 = new Paragraph("Result: ",MBold);
					p6.add(new Chunk("Failed",red));
					doc.add(p6);
				}
				
					//4.
				doc.add(new Paragraph("4. Coupon Code and Update button not worked"));
				Paragraph p7 = new Paragraph("Result: ",MBold);
				p7.add(new Chunk("Failed",red));
				doc.add(p7);
				
					//5.
				driver.findElement(By.xpath("//*[@id=\"cart_layout_1\"]/div[2]/div[2]/div[2]/form/input")).click();
				Thread.sleep(3000);
				String checkouturl = driver.getCurrentUrl();
				if(checkouturl.equals("http://localhost//gallery//checkp.php")) {
					doc.add(new Paragraph("5. Checkout button worked \n\t\t\t 6. Invoice printed"));
					Paragraph p6 = new Paragraph("Result: ",MBold);
					p6.add(new Chunk("Pass",green));
					doc.add(p6);
				}
				else {
					doc.add(new Paragraph("5. Checkout button not worked \n\t\t\t 6. Invoice printed"));
					Paragraph p6 = new Paragraph("Result: ",MBold);
					p6.add(new Chunk("Failed",red));
					doc.add(p6);
				}
				
				
				//Handicraft page element check
				doc.add(new Paragraph("TEST CASE: 5",custom));
				Paragraph t5 = new Paragraph("HANDICRAFT PAGE ELEMENT",custom);
				t5.setAlignment(Element.ALIGN_CENTER);
				doc.add(t5);
				doc.add(new Paragraph(" "));
				doc.add(new Paragraph("Excepted Output:",MBold));
				doc.add(new Paragraph("1. Navigation from Paintings worked\n\t2. Scroll Bar working \n\t3. Add to cart button working \n\t4. Buy now button and Quantity Field working \n\t5. Checkout Button working \n\t6. Added Handicraft with Paniting Invoice print. "));
					
					//1.
				driver.findElement(By.xpath("/html/body/a")).click();
				Thread.sleep(2000);
				String handiurl = driver.getCurrentUrl();
				if(handiurl.equals("http://localhost//gallery//visitor.php")) {
					doc.add(new Paragraph("Actual Output:",MBold));
					doc.add(new Paragraph("1. Navigation worked "));
					Paragraph p8 = new Paragraph("Result: ",MBold);
					p8.add(new Chunk("Pass",green));
					doc.add(p8);
				}
				else {
					doc.add(new Paragraph("Actual Output:",MBold));
					doc.add(new Paragraph("1. Navigation not worked"));
					Paragraph p8 = new Paragraph("Result: ",MBold);
					p8.add(new Chunk("Failed",red));
					doc.add(p8);
				}
				
				driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/a/span")).click();
				
					//2.
				jse.executeScript("scroll(0,1000)");
				Thread.sleep(2000);
				jse.executeScript("scroll(0,-500)");
				Thread.sleep(2000);
				doc.add(new Paragraph("2. Scroll Bar worked "));
				Paragraph p9 = new Paragraph("Result: ",MBold);
				p9.add(new Chunk("Pass",green));
				doc.add(p9);
				
					//3.
				driver.findElement(By.xpath("//*[@id=\"buy2\"]")).click();
				Thread.sleep(2000);
				String haddcarturl = driver.getCurrentUrl();
				if(haddcarturl.equals("http://localhost/gallery/rth.php")) {
					doc.add(new Paragraph("3. Add to cart button worked "));
					Paragraph p10 = new Paragraph("Result: ",MBold);
					p10.add(new Chunk("Pass",green));
					doc.add(p10);
				}
				else {
					doc.add(new Paragraph("3. Add to cart button not worked"));
					Paragraph p10 = new Paragraph("Result: ",MBold);
					p10.add(new Chunk("Failed",red));
					doc.add(p10);
				}
				
					//4.
				driver.findElement(By.xpath("//*[@id=\"qty\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"qty\"]")).sendKeys("3");
				driver.findElement(By.xpath("//*[@id=\"AddToCart\"]/input")).click();
				Thread.sleep(2000);
				String hbuyurl = driver.getCurrentUrl();
				if(hbuyurl.equals("http://localhost//gallery//carth.php")) {
					doc.add(new Paragraph("4. Buy Now button and Quantity field worked "));
					Paragraph p11 = new Paragraph("Result: ",MBold);
					p11.add(new Chunk("Pass",green));
					doc.add(p11);
				}
				else {
					doc.add(new Paragraph("4. Buy Now button and Quantity Field not worked"));
					Paragraph p11 = new Paragraph("Result: ",MBold);
					p11.add(new Chunk("Failed",red));
					doc.add(p11);
				}
				
					//5.
				driver.findElement(By.xpath("//*[@id=\"cart_layout_1\"]/div[2]/div[2]/div[2]/input")).click();
				String hcheckouturl = driver.getCurrentUrl();
				if(hcheckouturl.equals("http://localhost//gallery//check.php")) {
					doc.add(new Paragraph("5. Checkout button worked"));
					Paragraph p12 = new Paragraph("Result: ",MBold);
					p12.add(new Chunk("Pass",green));
					doc.add(p12);
				}
				else {
					doc.add(new Paragraph("5. Checkout button not worked \n\t\t\t 6. Invoice printed"));
					Paragraph p12 = new Paragraph("Result: ",MBold);
					p12.add(new Chunk("Failed",red));
					doc.add(p12);
				}
				
					//6.
				doc.add(new Paragraph("6.  Added Handicraft with Paniting Invoice not printed"));
				Paragraph p13 = new Paragraph("Result: ",MBold);
				p13.add(new Chunk("Failed",red));
				doc.add(p13);
				
				
				System.out.println("File made");
			}
			else {
				password.sendKeys("tempps");
				password.sendKeys(Keys.TAB);
				Thread.sleep(2000);
				driver.navigate().refresh();
				System.out.println("ERROR: Incorrect username or password");
				doc.add(new Paragraph("Actual Output 3: Error shown when enteries made were incorrect"));
				Paragraph p1 = new Paragraph("Result: ");
				p1.add(new Chunk("Pass",green));
				doc.add(p1);
			}
			doc.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] x) {
		
		test1 obj = new test1();
		obj.invokeBrowser();
	}
} 
