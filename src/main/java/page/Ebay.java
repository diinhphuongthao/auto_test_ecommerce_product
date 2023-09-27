package page;

import basePage.BasePage;
import io.cucumber.messages.types.Product;
import io.qameta.allure.Step;
import keywords.WebUI;
import org.openqa.selenium.WebElement;
import repo.EbayRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Ebay extends BasePage {
    ArrayList<Product> products = new ArrayList<>();

    public Ebay(WebUI webUI) {
        super(webUI);
    }

    @Step("input data search")
    public void inputSearch() {
        webUI.sendKeys(EbayRepo.INPUT_SEARCH, "iphone 11");
        webUI.clickElement(EbayRepo.SUBMIT_INPUT_SEARCH);
        webUI.delayInSecond(2);
    }

    @Step("input data search")
    public void inputSearch(String inputSearch) {
        webUI.sendKeys(EbayRepo.INPUT_SEARCH, inputSearch);
        webUI.clickElement(EbayRepo.SUBMIT_INPUT_SEARCH);
        webUI.delayInSecond(2);
    }

    public Boolean isSearch() {

        List<WebElement> lstProducts = webUI.findElements(EbayRepo.LST_PRODUCT_ITEM);
        int maxProducts = Math.min(lstProducts.size(), 10);
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

    public void sortAndPrintProductsByPriceAscending() {
        List<Product> validProducts = products.stream()
                .filter(product -> product.getVersion().isPresent() && containsNumber(product.getVersion().get()))
                .collect(Collectors.toList());
        Collections.sort(validProducts, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                double price1 = extractFirstPrice(product1.getVersion().get());
                double price2 = extractFirstPrice(product2.getVersion().get());
                return Double.compare(price1, price2);
            }
        });

        validProducts.sort((product1, product2) -> {
            if (!product1.getVersion().isPresent() && !product2.getVersion().isPresent()) {
                return 0;
            } else if (!product1.getVersion().isPresent()) {
                return 1;
            } else if (!product2.getVersion().isPresent()) {
                return -1;
            }
            return 0;
        });

        System.out.println("Products sorted by price (ascending): ");
        for (Product product : validProducts) {
            System.out.println("Name: " + product.getName() + " | Price: " + product.getVersion().get());
        }
    }

    private boolean containsNumber(String text) {
        return text.matches(".*\\d+.*");
    }

    private double extractFirstPrice(String priceString) {
        String[] priceParts = priceString.split("[^0-9.]");
        double firstPrice = 0.0;

        for (String part : priceParts) {
            if (!part.isEmpty() && part.matches("[-+]?\\d+(\\.\\d+)?")) {
                double price = Double.parseDouble(part);
                if (firstPrice == 0.0) {
                    firstPrice = price;
                }
            }
        }

        return firstPrice;
    }


}
