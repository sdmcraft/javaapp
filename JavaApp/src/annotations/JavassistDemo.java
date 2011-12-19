package annotations;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistDemo {

	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass demoClass = pool.get("annotations.Demo");
		CtMethod mainMethod = CtNewMethod.make(
				"	public static void main(String[] args) { "
						+ "System.out.println(\"Hello javassist!!!\");" + "}",
				demoClass);
		demoClass.addMethod(mainMethod);
		demoClass.writeFile();
	}

}
