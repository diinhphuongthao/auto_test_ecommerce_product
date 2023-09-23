package page;

import basePage.BasePage;
import io.cucumber.messages.types.Product;
import io.qameta.allure.Step;
import keywords.WebUI;
import org.openqa.selenium.WebElement;
import repo.EbayRepo;

import java.util.ArrayList;
import java.util.List;


public class Ebay extends BasePage {

    public Ebay(WebUI webUI) {
        super(webUI);
    }

    @Step("input data search")
    public void inputSearch() {
        webUI.sendKeys(EbayRepo.INPUT_SEARCH, "iphone 11");
        webUI.clickElement(EbayRepo.SUBMIT_INPUT_SEARCH);
        webUI.delayInSecond(2);
    }

    public Boolean isSearch() {
        ArrayList<Product> products = new ArrayList<>();
        List<WebElement> lstProducts = webUI.findElements(EbayRepo.LST_PRODUCT_ITEM);
        int maxProducts = Math.min(lstProducts.size(), 6);
        for (int i = 0; i < maxProducts; i++) {
            webUI.scrollToElementCenter(lstProducts.get(i));
            webUI.delayInSecond(4);
            String productName = webUI.getElementText(lstProducts.get(i));
            productName = productName.replaceAll("Opens in a new window or tab", "").trim();
            List<WebElement> lblPrices = webUI.findElementsWithParam(EbayRepo.LBL_PRODUCT_PRICE, String.valueOf(i), 10);
            List<String> prices = new ArrayList<>();

            for (WebElement lblPrice : lblPrices) {
                String price = lblPrice.getText().trim();
                if (!price.isEmpty()) {
                    prices.add(price);
                }
            }
            for (String price : prices) {
                System.out.println("Price: " + price);
            }
            String price;
            if (!prices.isEmpty()) {
                price = String.join(", ", prices);
            } else {
                price = "null";
            }
            products.add(new Product(productName, price));
        }

        System.out.println("Product after search from page Ebay.com: ");
        for (int i = 0; i < products.size(); i++) {
            System.out.println("Name: " + products.get(i).getName() + " | Price: " + products.get(i).getVersion().orElse("null"));
        }

        return true;
    }



}
