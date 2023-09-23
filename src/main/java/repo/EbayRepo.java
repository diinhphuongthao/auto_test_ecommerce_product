package repo;

public class EbayRepo {
    public static final String INPUT_SEARCH = "//input[@id='gh-ac']";
    public static final String SUBMIT_INPUT_SEARCH="//input[@id='gh-btn']";
    public static final String LST_PRODUCT_ITEM="(//li[@id]//a[@class='s-item__link'])";
    public static final String LBL_PRODUCT_PRICE= "(//ul[contains(@class, 'srp-results')]/li[contains(@class, 's-item')][${param}]//div[contains(@class,'s-item')]/span[contains(@class,'s-item__price')])";

}
