package sjsu.testing;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({LoggedInFlow.class, LoggedOutFlow.class})
public class GrouponTestSuite {

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println("Starting Test Suite and initializing Capabilities....");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        System.out.println("Test Suite Finished....");
    }

}
