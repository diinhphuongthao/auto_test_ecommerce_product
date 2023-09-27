package step;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.Amazon;
import test.TestNGRunner;

import java.util.List;
import java.util.Map;

public class AmazonStep extends TestNGRunner {
    private static final String AMAZON_URL = "https://www.amazon.com/";
    private final Amazon objamazon;

    public AmazonStep() {
        logger.info("Amazon Steps");
        objamazon = new Amazon(webUI);
    }


    @Given("^[a-zA-Z].* open the Amazon website")
    public void navigateToAmazonPage() {
        webUI.navigateToUrl(AMAZON_URL);
    }


    @When("^[a-zA-Z].* input into search textbox on Amazon")
    public void InputIntoSearchTextboxOnAmazon(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        String inputValue = data.get(0).get("inputSearch");
        objamazon.inputSearch(inputValue);

    }

    @Then("^[a-zA-Z].* see the list of searched products")
    public void printListOfSearchedProducts() {

        objamazon.findListProductSearch();
        objamazon.printProductInfoFromAmazon();
        objamazon.printProductsSortedByPrice();
    }


}
