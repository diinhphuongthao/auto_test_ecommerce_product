package basePage;



import keywords.WebUI;
import keywords.helper.LogHelper;
import org.slf4j.Logger;


public class BasePage {
    protected Logger logger = LogHelper.getLogger();
    protected WebUI webUI;

    public BasePage(WebUI webUI) {
        this.webUI = webUI;
    }


}
