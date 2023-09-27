package step;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.Amazon;
import page.Ebay;
import test.TestNGRunner;

import java.util.List;
import java.util.Map;

public class EbayStep extends TestNGRunner {
    private final Amazon objAmazon;
    private Ebay objEbay;

    public EbayStep() {
        logger.info("Amazon Steps");
        objEbay = new Ebay(webUI);
        objAmazon = new Amazon(webUI);
    }

    @Given("^[a-zA-Z].* open the Ebay website")
    public void navigateToEbayPage() {
        objEbay = objAmazon.goToEbayPage();
    }


    @When("^[a-zA-Z].* input into search textbox on Ebay")
    public void inputIntoSearchTextboxOnEbay(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        String inputValue = data.get(0).get("inputSearch");
        objEbay.inputSearch(inputValue);
    }

    @Then("^[a-zA-Z].* see the list of product")
    public void printListOfProduct() {
        objEbay.isSearch();
        objEbay.sortAndPrintProductsByPriceAscending();
    }
}
