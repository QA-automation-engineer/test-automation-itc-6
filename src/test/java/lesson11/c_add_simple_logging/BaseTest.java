package lesson11.c_add_simple_logging;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.AssumptionViolatedException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTest extends SimpleAPI {

    String name;
    private static final Logger LOG = LogManager.getLogger(BaseTest.class);

    protected static WebDriver driver;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            LOG.info("Test '{}' - PASSED", description.getMethodName());
            super.succeeded(description);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            LOG.error("Test '{}' - FAILED due to: {}" +
                    description.getMethodName(),
                    e.getMessage());
            super.failed(e, description);
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            LOG.info("Test '{}' - SKIPPED", description.getMethodName());
            super.skipped(e, description);
        }

        @Override
        protected void starting(Description description) {
            LOG.info("Test '{}' - is starting ...", description.getMethodName());
            super.starting(description);
        }
    };

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        LOG.debug("ChromeDriver has been started");
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
        LOG.debug("ChromeDriver has been shut down");
    }

    @Override
    WebDriver getDriver() {
        return driver;
    }

    void assertAll(Assertion... assertions) {
        List<Throwable> errors = new ArrayList<>();
        for (Assertion assertion : assertions) {
            try {
                assertion.assertSmth();
            } catch (Throwable throwable) {
                errors.add(throwable);
            }
        }
        if (!errors.isEmpty()) {
            throw new AssertionError(errors
                    .stream()
                    .map(assertionError -> "\n Failed" + assertionError.getMessage())
                    .collect(Collectors.toList()).toString());
        }
    }

    @FunctionalInterface
    public interface Assertion {
        void assertSmth();
    }
}
