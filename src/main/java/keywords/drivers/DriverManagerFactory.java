package keywords.drivers;

public class DriverManagerFactory {
    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager;

        switch (type) {
            case EDGE:
                driverManager = new EdgeDriverManager();
                break;
            case CHROME:
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;

    }
}
