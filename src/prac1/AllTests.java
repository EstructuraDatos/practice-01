package prac1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ArraySetTest.class, CribaArrayTest.class,
		CribaLinkedTest.class, LinkedSetTest.class })
public class AllTests {

}
