package repo;

public class AmazonRepo {
    public static final String LBL_INPUT_CHARACTOR = "//input[@id='captchacharacters']";
    public static final String TXT_INPUT_CHARACTOR = "//input[@id='captchacharacters']";
    public static final String LBL_INPUT_SEARCH = "//input[@id='twotabsearchtextbox']";
    public static final String BTN_SUBMIT_SEARCH = "//input[@id='nav-search-submit-button']";
    public static final String LBL_PRODUCT_NAME = "//span[@class='a-size-medium a-color-base a-text-normal']";
    public static final String LBL_PRODUCT_PRICE = "//span[@class='a-price-whole']\n";
    public static final String LBL_PRODUCT_ITEM = "//div[@data-component-type=\"s-search-result\" and contains(@class, \"sg-col-20-of-24\")]";
    public static final String LST_PRODUCT_NAMES = "//div[starts-with(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//h2//a//span";
        public static final String LST_PRODUCT_PRICES = "//div[@data-component-type='s-search-result'][${param}]//div[starts-with(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//a/span[@class='a-price']";

}
