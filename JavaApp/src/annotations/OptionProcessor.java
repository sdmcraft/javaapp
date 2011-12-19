package annotations;

import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes({ "annotations.Option" })
public class OptionProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		// processingEnv is a predefined member in AbstractProcessor class
		// Messager allows the processor to output messages to the environment
		Messager messager = processingEnv.getMessager();

		// Create a hash table to hold the option switch to option bean mapping
		HashMap<String, String> values = new HashMap<String, String>();

		// Loop through the annotations that we are going to process
		// In this case there should only be one: Option
		for (TypeElement te : annotations) {

			// Get the members that are annotated with Option
			for (Element e : roundEnv.getElementsAnnotatedWith(te))
				// Process the members. processAnnotation is our own method
				processAnnotation(e, values, messager);
		}

		// If there are any annotations, we will proceed to generate the
		// annotation
		// processor in generateOptionProcessor method
		if (values.size() > 0)
			try {
				// Generate the option process class
				// generateOptionProcessor(processingEnv.getFiler(), values);
			} catch (Exception e) {
				messager.printMessage(Diagnostic.Kind.ERROR, e.getMessage());
			}

		return (true);
	}

	private void processAnnotation(Element element,
			HashMap<String, String> values, Messager msg) {

		// Get the Option annotation on the member
		Option opt = element.getAnnotation(Option.class);

		// Get the class name of the option bean
		String className = element.getEnclosingElement().toString();

		// Check if the type in the member is a String. If not we igonre it
		// We are currently only supporting String type
		if (!element.asType().toString().equals(String.class.getName())) {
			msg.printMessage(Diagnostic.Kind.WARNING, element.asType()
					+ " not supported. " + opt.name() + " not processed");
			return;
		}

		// Save the option switch and the member's name in a hash set
		// Eg. -filename (option switch) mapped to fileName (member)
		values.put(opt.name(), element.getSimpleName().toString());
	}

	// private void generateOptionProcessor(Filer filer,
	// HashMap<String, String> values) throws Exception {
	//
	// String generatedClassName = className + "Processor";
	//
	// Writer writer = filer.createSourceFile(generatedClassName);
	//
	// writer.write("/* Generated on " + new Date() + " */\n");
	//
	// writer.write("public class " + generatedClassName + " {\n");
	//
	// writer.write("\tpublic static " + className
	// + " process(String[] args) {\n");
	//
	// writer.write("\t\t" + className + " options = new " + className
	// + "();\n");
	// writer.write("\t\tint idx = 0;\n");
	//
	// writer.write("\t\twhile (idx < args.length) {\n");
	//
	// for (String key : values.keySet()) {
	// writer.write("\t\t\tif (args[idx].equals(\"" + key + "\")) {\n");
	// writer.write("\t\t\t\toptions." + values.get(key)
	// + " = args[++idx];\n");
	// writer.write("\t\t\t\tidx++;\n");
	// writer.write("\t\t\t\tcontinue;\n");
	// writer.write("\t\t\t}\n");
	// }
	//
	// writer.write("\t\t\tSystem.err.println(\"Unknown option: \" + args[idx++]);\n");
	//
	// writer.write("\t\t}\n");
	//
	// writer.write("\t\treturn (options);\n");
	// writer.write("\t}\n");
	//
	// writer.write("}");
	//
	// writer.flush();
	// writer.close();
	// }

}
