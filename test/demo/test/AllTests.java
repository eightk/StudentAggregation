package demo.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import demo.aggregation.driver.StudentAggregationDriverErrorTest;
import demo.aggregation.driver.StudentAggregationDriverSuccessTest;
import demo.common.util.ValidationTest;

/**
 * The test suite for running all the tests.
 * 
 * @author richardl
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ValidationTest.class, StudentAggregationDriverSuccessTest.class, StudentAggregationDriverErrorTest.class })
public class AllTests {

}
