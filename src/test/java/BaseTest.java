import config.TestConfigFactory;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected static TestConfigFactory configFactory;

    @BeforeSuite
    public static void prepareDate() {
        configFactory = TestConfigFactory.getInstance();
    }
}
