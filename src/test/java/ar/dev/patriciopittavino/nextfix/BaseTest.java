package ar.dev.patriciopittavino.nextfix;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import java.time.*;
import java.util.Properties;

public class BaseTest {

    protected static ExtentReports extent = ExtentManager.getInstance();
    protected ExtentTest test;
    private Instant startTime;

    @BeforeAll
    static void setupGlobalInfo() {
        Properties properties = System.getProperties();
        extent.setSystemInfo("OS", properties.getProperty("os.name"));
        extent.setSystemInfo("Java Version", properties.getProperty("java.version"));
        extent.setSystemInfo("User", properties.getProperty("user.name"));
    }

    @BeforeEach
    void setupTest(TestInfo testInfo) {
        test = extent.createTest(testInfo.getDisplayName());
        startTime = Instant.now();
        test.info("🔹 Test start: " + testInfo.getDisplayName());
    }

    @AfterEach
    void tearDownTest(TestInfo testInfo) {
        if (test != null) {
            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            test.info("✅ Test finish: " + testInfo.getDisplayName());
            test.info("⏳ Duration: " + duration.toMillis() + " ms");
            test.addScreenCaptureFromPath("https://raw.githubusercontent.com/maxisandoval37/maxisandoval37/refs/heads/master/images/sonic.gif");
        }
    }

    @AfterAll
    static void tearDownExtent() {
        extent.flush();
    }
}

class ExtentManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/site/index.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}