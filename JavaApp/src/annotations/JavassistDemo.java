package annotations;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistDemo {

	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass demoClass = pool.get("annotations.Demo");
		for(CtMethod method : demoClass.getMethods())
		{
			System.out.println(method.getName());
			if(method.getAnnotation(Main.class) != null)
			{
				System.out.println("The method annotated as main is:" + method.getName());
				break;
			}
		}
		CtMethod mainMethod = CtNewMethod.make(
				"	public static void main(String[] args) { "
						+ "System.out.println(\"Hello javassist!!!\");" + "}",
				demoClass);
		demoClass.addMethod(mainMethod);
		//demoClass.writeFile();
	}

}
