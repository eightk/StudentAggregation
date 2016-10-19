package demo.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import demo.common.util.ValidationTest;

@RunWith(Suite.class)
@SuiteClasses({ ValidationTest.class })
public class AllTests {

}
