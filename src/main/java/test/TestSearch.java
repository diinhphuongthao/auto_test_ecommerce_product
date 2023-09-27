package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Amazon;
import page.Ebay;

import java.lang.reflect.Method;


public class TestSearch extends BaseTest {
    private Amazon objAmazon;
    private Ebay objEbay;

    public TestSearch() {
        super();
        objAmazon = new Amazon(this.webUI);
    }

    @Test(description = "P001: should show output after search", groups = {
            "functiontest"})
    public void P001_should_show_output_after_search(Method method) {
        logger.info("Test case: " + method.getName());
        objAmazon.inputSearch();
        webUI.delayInSecond(3);
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(objAmazon.findListProductSearch());
        webUI.delayInSecond(2);
        objEbay = objAmazon.goToEbayPage();
        objEbay.inputSearch();
        softAssertion.assertTrue(objEbay.isSearch());
        webUI.delayInSecond(3);
        objAmazon.printProductInfoFromAmazon();
    }

}
