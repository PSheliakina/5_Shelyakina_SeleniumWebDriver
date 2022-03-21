package demoQaTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class DemoTest {

    private WebDriver driver;
    private File file;

    @BeforeEach
    public void setUp() {

        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    @Test
    public void checkFormIsFilled() throws InterruptedException {

        file = new File("src/kotik.jpg");

        SoftAssertions softAssert = new SoftAssertions();

        String firstName = "Ivan";
        String lastName = "Ivanov";
        String email = "iivanov@nothing.com";
        String mobileNum = "1234567890";
        String subject = "Maths";
        String address = "Lenina 1, apartment 1";

        //First Name
        driver.findElement(By.cssSelector("#firstName")).click();
        driver.findElement(By.cssSelector("#firstName")).sendKeys(firstName);

        //Last Name
        driver.findElement(By.cssSelector("#lastName")).click();
        driver.findElement(By.cssSelector("#lastName")).sendKeys(lastName);

        //Email
        driver.findElement(By.cssSelector("#userEmail")).click();
        driver.findElement(By.cssSelector("#userEmail")).sendKeys(email);

        //Gender
        driver.findElement(By.cssSelector("#genterWrapper .custom-control:nth-child(1)")).click();

        //Mobile number
        driver.findElement(By.cssSelector("#userNumber")).click();
        driver.findElement(By.cssSelector("#userNumber")).sendKeys(mobileNum);

        //Address
        driver.findElement(By.cssSelector("textarea[placeholder='Current Address']")).click();
        driver.findElement(By.cssSelector("textarea[placeholder='Current Address']")).sendKeys(address);

        //Hobbies
        driver.findElement(By.cssSelector("#hobbiesWrapper .custom-control:nth-child(1)")).click();

        driver.findElement(By.cssSelector("#hobbiesWrapper .custom-control:nth-child(2)")).click();

        //Upload picture
        driver.findElement(By.cssSelector("#uploadPicture")).sendKeys(file.getAbsolutePath());

        //Date of Birth
        driver.findElement(By.id("dateOfBirthInput")).click();

        driver.findElement(By.cssSelector(".react-datepicker__month-select")).click();
        driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']//option[5]")).click();

        driver.findElement(By.cssSelector(".react-datepicker__year-select")).click();
        driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']//option[@value='2003']")).click();

        driver.findElement(By.xpath("//div[@class='react-datepicker__week'][3]//div[@class='react-datepicker__day react-datepicker__day--013']")).click();

        //State
        driver.findElement(By.id("state")).click();
        driver.findElement(By.xpath("//div[text()='NCR']")).click();

        //City
        driver.findElement(By.id("city")).click();
        driver.findElement(By.xpath("//div[text()='Gurgaon']")).click();

        //Subject - не работает

        WebElement subjectInput = driver.findElement(By.id("subjectsInput"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", subjectInput);
        subjectInput.sendKeys(subject);
        subjectInput.sendKeys(Keys.ENTER);

        //Submit
        driver.findElement(By.cssSelector("button#submit")).click();

        //Verify
        WebElement studentNameTable = driver.findElement(By.xpath("//tr[1]//td[2]"));
        String studentName = studentNameTable.getText();
//        Assertions.assertEquals("Ivan Ivanov", studentName);
        softAssert.assertThat(studentName).isEqualTo("Ivan Ivanov");

        WebElement studentEmailtable = driver.findElement(By.xpath("//tr[2]//td[2]"));
        String studentEmail = studentEmailtable.getText();
//        Assertions.assertEquals(email, studentEmail);
        softAssert.assertThat(studentEmail).isEqualTo(email);

        WebElement genderTable = driver.findElement(By.xpath("//tr[3]//td[2]"));
        String genderSelected = genderTable.getText();
//        Assertions.assertEquals("Male", genderSelected);
        softAssert.assertThat(genderSelected).isEqualTo("Male");

        WebElement mobileTable = driver.findElement(By.xpath("//tr[4]//td[2]"));
        String StudentMobile = mobileTable.getText();
//        Assertions.assertEquals(mobileNum, StudentMobile);
        softAssert.assertThat(StudentMobile).isEqualTo(mobileNum);

        WebElement birthDateTable = driver.findElement(By.xpath("//tr[5]//td[2]"));
        String StudentBirthDate = birthDateTable.getText();
//        Assertions.assertEquals("13 May,2003", StudentBirthDate);
        softAssert.assertThat(StudentBirthDate).isEqualTo("13 May,2003");

        WebElement subjectTable = driver.findElement(By.xpath("//tr[6]//td[2]"));
        String studentSubject = subjectTable.getText();
//        Assertions.assertEquals(subject, studentSubject);
        softAssert.assertThat(studentSubject).isEqualTo(subject);

        WebElement hobbiesTable = driver.findElement(By.xpath("//tr[7]//td[2]"));
        String studentHobbies = hobbiesTable.getText();
//        Assertions.assertEquals("Sports, Reading", studentHobbies);
        softAssert.assertThat(studentHobbies).isEqualTo("Sports, Reading");

        WebElement pictureTable = driver.findElement(By.xpath("//tr[8]//td[2]"));
        String studentPicture = pictureTable.getText();
//        Assertions.assertEquals("kotik.jpg", studentPicture);
        softAssert.assertThat(studentPicture).isEqualTo("kotik.jpg");

        WebElement adressTable = driver.findElement(By.xpath("//tr[9]//td[2]"));
        String studentAddress = adressTable.getText();
//        Assertions.assertEquals(address, studentAddress);
        softAssert.assertThat(studentAddress).isEqualTo(address);

        WebElement StateCityTable = driver.findElement(By.xpath("//tr[10]//td[2]"));
        String studentStateCity = StateCityTable.getText();
//        Assertions.assertEquals("NCR Gurgaon", studentStateCity);
        softAssert.assertThat(studentStateCity).isEqualTo("NCR Gurgaon");

        softAssert.assertAll();

        Thread.sleep(3000);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

}
