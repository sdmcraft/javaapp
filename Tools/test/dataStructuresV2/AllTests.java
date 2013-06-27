package dataStructuresV2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dataStructuresV2.impl.TestBasicGraph;
import dataStructuresV2.impl.TestGraphFactory;
import dataStructuresV2.impl.TestGraphUtil;
import dataStructuresV2.impl.TestSimpleGraph;

@RunWith(Suite.class)
@SuiteClasses({ TestBasicGraph.class, TestGraphFactory.class,
		TestGraphUtil.class, TestSimpleGraph.class })
public class AllTests {

}
