package test;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;
import keywords.WebUI;
import keywords.helper.FileHelper;
import keywords.helper.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.*;
import page.Amazon;
import page.Ebay;

import java.io.File;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;

public class BaseTest {
    protected static Logger logger = LogHelper.getLogger();
    private static String AMAZON_URL = "https://www.amazon.com/";
    private static String EBAY_URL = "https://www.ebay.com/";
    protected final WebUI webUI;

    private HashMap<String, String> dataFile;

    protected Amazon objAmazon;
    protected Ebay objEbay;


    public BaseTest() {
        webUI = new WebUI();
    }

    private static Class<?> getCallerClass(final int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        try {
            return getCallerClassFromStackTrace(index + 1);
        } catch (final ClassNotFoundException e) {
            logger.error("Could not find class in ReflectionUtil.getCallerClass({}), index<" + index + ">"
                    + ", exception< " + e + ">");
        }
        return null;
    }

    private static Class<?> getCallerClassFromStackTrace(final int index)
            throws ClassNotFoundException {

        final StackTraceElement[] elements = new Throwable().getStackTrace();
        int i = 0;
        for (final StackTraceElement element : elements) {
            if (isValidMethod(element)) {
                if (i == index) {
                    return Class.forName(element.getClassName());
                }
                ++i;
            }
        }
        throw new IndexOutOfBoundsException(Integer.toString(index));
    }

    private static boolean isValidMethod(final StackTraceElement element) {
        if (element.isNativeMethod()) {
            return false;
        }
        final String cn = element.getClassName();
        if (cn.startsWith("sun.reflect.")) {
            return false;
        }
        final String mn = element.getMethodName();
        if (cn.startsWith("java.lang.reflect.") && (mn.equals("invoke") || mn.equals("newInstance"))) {
            return false;
        }
        if (cn.equals("java.lang.Class") && mn.equals("newInstance")) {
            return false;
        }
        return true;
    }

    @BeforeClass
    public void beforeClass() {
        logger.info("=====================Before Class: " + TestSearch.class.getName());
    }

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void beforeTest(String browser) {
        objAmazon = goToAmazonPage(browser);
        //    objManager = objLogin.loginGuru99BankWith("mngr523207", "hYgevev");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        logger.info("==================Before Method: " + method.getName());
    }

    @AfterMethod
    public void afterMethod(Method method) {
        logger.info("==================After Method: " + method.getName());
    }
    @AfterTest(alwaysRun = true)
    public void afterTest() {
        webUI.closeBrowser();
    }
    @AfterClass
    public void afterClass() {
        logger.info("=====================After Class: " + TestSearch.class.getName());
    }

    public String getDataFile() {
        String strClassName = getCallerClass(3).getSimpleName();
        return this.dataFile.get(strClassName);
    }

    public String setDataFile(String dataFileName) {
        if (this.dataFile == null) {
            this.dataFile = new HashMap<>();
        }
        String strClassName = getCallerClass(2).getSimpleName();

        dataFileName = FileHelper.getDataJSONFilePath(dataFileName);
        return this.dataFile.put(strClassName, dataFileName);
    }

    public String findTestData(String name) {
        String dataFile = this.getDataFile();
//    logger.info("Data file: " + dataFile);
        try {
            File jsonRepoFile = new File(dataFile);
            return JsonPath.read(jsonRepoFile, "$." + name).toString();
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot find test data with name ''{0}''. Root cause: {1}", name,
                            e.getMessage()));
        }
        return null;
    }

    @Step("Go to Amazon page")
    public Amazon goToAmazonPage(String browser) {
        webUI.openBrowser(browser, AMAZON_URL);
        webUI.maximizeWindow();
        return new Amazon(this.webUI);
    }
    @Step("Go to Ebay page")
    public Ebay goToEbayPage(String browser) {
        webUI.openBrowser(browser, EBAY_URL);
        return new Ebay(this.webUI);
    }
}