package annotations;

import java.io.File;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistDemo {

	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass demoClass = pool.get("annotations.Demo");
		CtMethod annotatedMainMethod = null;
		for (CtMethod method : demoClass.getMethods()) {
			System.out.println(method.getName());
			if (method.getAnnotation(Main.class) != null) {
				annotatedMainMethod = method;
				System.out.println("The method annotated as main is:"
						+ method.getName());
				break;
			}
		}

		if (annotatedMainMethod != null) {
			CtMethod mainMethod = CtNewMethod
					.make("	public static void main(String[] args) { \n"							
							+ annotatedMainMethod.getName() + "(args);\n" + "}",
							demoClass);
			demoClass.addMethod(mainMethod);
			System.out.println(System.getProperty("user.dir"));
			demoClass.writeFile(System.getProperty("user.dir") + File.separator + "bin");
		}
	}
}
