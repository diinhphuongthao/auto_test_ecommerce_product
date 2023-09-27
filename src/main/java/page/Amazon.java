package page;

import basePage.BasePage;
import io.cucumber.messages.types.Product;
import io.qameta.allure.Step;
import keywords.WebUI;
import org.openqa.selenium.WebElement;
import repo.AmazonRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Amazon extends BasePage {
    private static String EBAY_URL = "https://www.ebay.com/";
    private ArrayList<Product> products = new ArrayList<>();

    public Amazon(WebUI webUI) {
        super(webUI);
    }

    public void inputSearch() {
        webUI.sendKeys(AmazonRepo.LBL_INPUT_SEARCH, "iphone 11");
        webUI.delayInSecond(1);
        webUI.clickElement(AmazonRepo.BTN_SUBMIT_SEARCH);
    }

    public void inputSearch(String inputSearch) {
        webUI.sendKeys(AmazonRepo.LBL_INPUT_SEARCH, inputSearch);
        webUI.delayInSecond(1);
        webUI.clickElement(AmazonRepo.BTN_SUBMIT_SEARCH);
    }

    //    @Step("Search all products on amazon.com")
//    public Boolean isSearch() {
//        List<WebElement> lstProducts = webUI.findElements(AmazonRepo.LST_PRODUCT_NAMES);
//        for (int i = 0; i < lstProducts.size(); i++) {
//            webUI.scrollToElementCenter(lstProducts.get(i));
//            webUI.delayInSecond(4);
//            String productName = webUI.getElementText(lstProducts.get(i));
//            WebElement lblPrice = webUI.findElementWithParam(AmazonRepo.LST_PRODUCT_PRICES, String.valueOf(i), 10);
//            if (lblPrice == null) {
//                products.add(new Product(productName, "null"));
//            } else {
//                String price = webUI.getElementText(lblPrice).trim();
//                price = price.lines().collect(Collectors.joining());
//                products.add(new Product(productName, price));
//            }
//        }
//        return true;
//    }
//
//    @Step("print information list product from page amazon.com")
//    public void printProductInfoFromAmazon() {
//        System.out.println("Product after search from page amazon.com: ");
//        for (int i = 0; i < products.size(); i++) {
//            System.out.println("Name: " + products.get(i).getName() + " | Price: " + products.get(i).getVersion().orElse("null"));
//
//        }
//    }
    @Step("find all products after search on amazon.com")
    public Boolean findListProductSearch() {
        List<WebElement> lstProducts = webUI.findElements(AmazonRepo.LST_PRODUCT_NAMES);
        for (int i = 0; i < lstProducts.size(); i++) {
            webUI.scrollToElementCenter(lstProducts.get(i));
            webUI.delayInSecond(4);
            String productName = webUI.getElementText(lstProducts.get(i));
            WebElement lblPrice = webUI.findElementWithParam(AmazonRepo.LST_PRODUCT_PRICES, String.valueOf(i), 10);
            if (lblPrice == null) {
                products.add(new Product(productName, "$0"));
            } else {
                String price = webUI.getElementText(lblPrice).trim();
                price = price.lines().collect(Collectors.joining());
                products.add(new Product(productName, price));
            }
        }
        return true;
    }

    @Step("Print information list product from page amazon.com")
    public void printProductInfoFromAmazon() {
        System.out.println("Products after search from page amazon.com: ");
        for (Product product : products) {
            System.out.println("Name: " + product.getName() + " | Price: " + product.getVersion().orElse("$0"));
        }
    }

    @Step("Sort products by price ascending")
    public void sortByPriceAscending() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                double price1 = parsePrice(product1.getVersion().orElse("0"));
                double price2 = parsePrice(product2.getVersion().orElse("0"));
                return Double.compare(price1, price2);
            }
        });
    }

    @Step("Print products sorted by price")
    public void printProductsSortedByPrice() {
        sortByPriceAscending();

        System.out.println("Products sorted by price (ascending): ");
        for (Product product : products) {
            System.out.println("Name: " + product.getName() + " | Price: " + product.getVersion().orElse("null"));
        }
    }

    private double parsePrice(String priceString) {
        try {
            priceString = priceString.replaceAll("[^0-9.]", "");
            if (priceString.isEmpty() || priceString.equals(".")) {
                return 0.0;
            }
            return Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private String formatPrice(double price) {
        return "$" + String.format("%.2f", price);
    }


    @Step("Go to Ebay page")
    public Ebay goToEbayPage() {
        webUI.openNewTab();
        webUI.switchToTab(1);
        webUI.navigateToUrl(EBAY_URL);
        return new Ebay(this.webUI);
    }


}
