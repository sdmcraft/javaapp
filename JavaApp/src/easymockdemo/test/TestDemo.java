package easymockdemo.test;

//import org.easymock.EasyMock;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.api.easymock.PowerMock;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;

import easymockdemo.testable.ClassA;
import easymockdemo.testable.ClassB;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ ClassA.class, ClassB.class })
public class TestDemo {

//	@Test
	public void test1() throws Exception {
//		ClassA classAMockNoArg = PowerMock.createMock(ClassA.class);
//		PowerMock.expectNew(ClassA.class).andReturn(classAMockNoArg);
//
//		ClassA classAMock1Arg = PowerMock.createMock(ClassA.class);
//		PowerMock.expectNew(ClassA.class, EasyMock.anyInt()).andReturn(
//				classAMock1Arg);
//		EasyMock.expect(classAMock1Arg.methodA1()).andReturn(null);
//		PowerMock.replay(ClassA.class, classAMock1Arg, classAMockNoArg);
//		ClassB classB = new ClassB();
//		classB.method();
//		PowerMock.verify(ClassA.class, classAMock1Arg, classAMockNoArg);
	}
}
