package keywords.drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager{
    @Override
    protected void createDriver() {
    //WebDriverManager.chromedriver().setup(); // only use from selenium 3.141.59 to selenium 4.5
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
}
