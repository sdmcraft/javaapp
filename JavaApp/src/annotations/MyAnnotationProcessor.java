package annotations;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class MyAnnotationProcessor implements ClassFileTransformer {

	public static void premain(String agentArgument,
			Instrumentation instrumentation) {
		instrumentation.addTransformer(new MyAnnotationProcessor());
	}

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		System.out.println("transform->" + className);
		return null;
	}

}
