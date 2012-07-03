package misc;


import java.lang.reflect.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * JStubGen - creates empty *.java files according to the signatures found
 * in an existing *.class file.
 *
 * @author Miriam Busch
 * @author Enver Haase
 */
public class GenerateStubs {

    /** */
    public static final String CHAR = "char";
    /** */
    public static final String BOOLEAN = "boolean";
    /** */
    public static final String VOID = "void";

    /** Generate stubs enabled? */
    private static boolean stubs = false;

    /** Generate stubs, but no private members */
    private static boolean noprivate;

    /** print usage */
    static void usage() {
        System.err.println("java Inspect [-s] <classname>");
        System.err.println("             where");
        System.err.println("             -s      generate stub class");
        System.err.println("             -snp    generate stub class, don't include 'package private' and 'private' members");
    }

    /**
     *
     * @param t
     * @return
     */
    private static String getDummyValueForType(Class t) {
        String ret = "";
        if (t.isPrimitive()) {
            if (t == Boolean.TYPE) {
                ret += "(" + getTypeName(t) + ") false";
            }
            else {
                ret += "(" + getTypeName(t) + ") 0";
            }
        }
        else {
            ret += "(" + getTypeName(t) + ") null";
        }
        return ret;
    }

    /**
     *
     * @param o
     */
    static void iterateAndPrint(Object[] o) {
        for (int i = 0; i < o.length; i++)
            System.out.println(o[i].toString());
    }

    /**
     *
     * @param ptypes
     * @return
     */
    private static String createParameterString(Class[] ptypes) {
        StringBuffer signature = new StringBuffer("(");
        for (int i = 0; i < ptypes.length; i++) {
            signature.append(getTypeName(ptypes[i]));
            signature.append(" p" + i);
            if (i < ptypes.length - 1)
                signature.append(", ");
        }
        signature.append(")");
        return new String(signature);

    }

    /**
     * return the type name of type c
     * @param c the type
     * @return the name, e.g. "char[][]", or "java.lang.String".
     */
    private static String getTypeName(Class c) {
        Class componentType = c.getComponentType();
        if (componentType == null) {
            return c.getName();
        }
        else {
            // Work around array names.
            String name = c.getName();
            String arrays = "";

            while (name.startsWith("[")) {
                name = name.substring(1);
                arrays += "[]";
                c = c.getComponentType();
            }
            return c.getName() + arrays;
        }
    }

    /**
     *
     * @param m
     * @return
     */
    private static String createMethodSignature(Method m) {
        StringBuffer signature = new StringBuffer();
        signature.append(Modifier.toString(m.getModifiers()) + " ");
        signature.append(getTypeName(m.getReturnType()) + " ");
        signature.append(m.getName());
        signature.append(createParameterString(m.getParameterTypes()));

        signature.append(generateThrowsClause(m));
        return new String(signature);
    }

    /**
     *
     * @param m
     * @return
     */
    private static String generateThrowsClause(Method m) {
        String ret = "";
        Class[] excs = m.getExceptionTypes();
        int numExc = excs.length;
        if (numExc != 0) {
            ret += (" throws ");
        }
        for (int i = 0; i < numExc; i++) {
            ret += excs[i].getName();
            if (i != numExc - 1) {
                ret += ", ";
            }
        }
        return ret;
    }

    /**
     *
     */
    // TODO: remove code duplication
    private static String generateThrowsClause(Constructor m) {
        String ret = "";
        Class[] excs = m.getExceptionTypes();
        int numExc = excs.length;
        if (numExc != 0) {
            ret += (" throws ");
        }
        for (int i = 0; i < numExc; i++) {
            ret += excs[i].getName();
            if (i != numExc - 1) {
                ret += ", ";
            }
        }
        return ret;
    }

    /**
     *
     * @param c
     * @return
     */
    private static String createConstructorSignature(Constructor c) {
        StringBuffer signature = new StringBuffer();
        signature.append(Modifier.toString(c.getModifiers()) + " ");

        String name = c.getName();
        if (name.lastIndexOf('.') != -1) {
            name = name.substring(name.lastIndexOf('.') + 1);
        }
        signature.append(name);
        signature.append(createParameterString(c.getParameterTypes()));
        signature.append(generateThrowsClause(c));
        return new String(signature);
    }


    /**
     * Generates empty but valid method stubs.
     *
     * @param out
     * @param m
     */
    static void generateMethodStubs(java.io.PrintStream out, Method[] m) {
        for (int i = 0; i < m.length; i++) {
            System.err.println("Processing method '" + m[i] + "'.");
            if ((!noprivate) || (Modifier.isPublic(m[i].getModifiers())) || (Modifier.isProtected(m[i].getModifiers()))) {

                if (Modifier.isAbstract(m[i].getModifiers()) || Modifier.isNative(m[i].getModifiers())) {
                    out.println(createMethodSignature(m[i]) + ";");
                    continue;
                }
                out.println("/** Empty implementation */");
                out.println(createMethodSignature(m[i]) + " {");
                Class retType = m[i].getReturnType();

                Class[] exceptions = m[i].getExceptionTypes();
                for (int j = 0; j < exceptions.length; j++) {
                    out.println(exceptions[j].getName() + " exc" + j + "=null;");
                    out.println("if (false) throw exc" + j + ";");
                }

                // return instruction
                if (!retType.getName().equals(VOID)) {
                    String retVal;
                    if (retType.isPrimitive()) {
                        if (retType.getName().equals(CHAR)) {
                            retVal = "' '";
                        }
                        else if (retType.getName().equals(BOOLEAN)) {
                            retVal = "false";
                        }
                        else
                            retVal = "0"; // algebraic types
                    }
                    else {
                        retVal = "null";
                    }
                    out.println("return " + retVal + ";");
                }
                out.println("}");
                out.println();
            }
        }
    }


    /**
     * Generates empty but valid constructor stubs.
     *
     * @param out
     * @param c
     */
    static void generateConstructorStubs(PrintStream out, Constructor[] c) {
        for (int i = 0; i < c.length; i++) {
            if ((!noprivate) || (!Modifier.isPrivate(c[i].getModifiers()))) {
                out.println("/** Empty implementation */");
                out.println(createConstructorSignature(c[i]) + " {");
                out.println(createSuperCall(c[i]));

                Class[] exceptions = c[i].getExceptionTypes();
                for (int j = 0; j < exceptions.length; j++) {
                    out.println(exceptions[j].getName() + " exc" + j + "=null;");
                    out.println("if (false) throw exc" + j + ";");
                }

                out.println(" }");
                out.println();
            }
        }
    }

    /**
     *
     * @param c
     */
    static String createSuperCall(Constructor c) {
        Class decl = c.getDeclaringClass();
        Class superClass = decl.getSuperclass();
        if (superClass == null) {
            return "/* type has no superclass */";
        }
        Constructor[] supers = superClass.getDeclaredConstructors();
        System.err.println("Searching accessible constructor in...");
        for (int j = 0; j < supers.length; j++) {
            System.err.println(supers[j]);
        }
        for (int i = 0; i < supers.length; i++) {

            String superPackageName = superClass.getName().substring(0, superClass.getName().lastIndexOf('.') + 1);
            String thisPackageName = decl.getName().substring(0, decl.getName().lastIndexOf('.') + 1);
            boolean samePackage = superPackageName.equals(thisPackageName);
            System.err.println("Package '" + thisPackageName + "' " + (samePackage ? "is same" : "ain't same") + " package as '" + superPackageName + "'.");

            if ((!Modifier.isPrivate(supers[i].getModifiers())) &&
                    (
                    (samePackage) ||
                    (Modifier.isPublic(supers[i].getModifiers()) ||
                    (Modifier.isProtected(supers[i].getModifiers())))
                    )
            ) {


                // fine, we just found an accessible Super-Constructor
                String ret = "super (";
                Class[] params = supers[i].getParameterTypes();
                for (int j = 0; j < params.length; j++) {
                    ret += getDummyValueForType(params[j]);

                    // comma-separate values
                    if (j != params.length - 1)
                        ret += ", ";
                }

                ret += ");";
                return ret; // stop iteration
            }
        }

        return "/* Problem: can't find accessible super constructor */";
    }

    /**
     * Generates fields without initialization.
     *
     * @param out
     * @param f
     */
    static void generateFields(java.io.PrintStream out, Field[] f) {
        for (int i = 0; i < f.length; i++) {

            if ((!noprivate) || (Modifier.isPublic(f[i].getModifiers()) || Modifier.isProtected(f[i].getModifiers()))) {

                String modifiers = Modifier.toString(f[i].getModifiers() & ~Modifier.INTERFACE);

                String type = getTypeName(f[i].getType());

                String name = f[i].toString();
                name = name.substring(name.lastIndexOf('.') + 1);

                if (Modifier.isStatic(f[i].getModifiers())) {

                    if (f[i].getType().isPrimitive()) {
                        Object value = null;
                        try {
                            value = f[i].get(null);


                            out.print(modifiers + " " + type + " " + name + "=");
                            //out.print(f[i].getType() == Character.TYPE ? "'" : "");

                            boolean special = false;
                            if (value.toString().equals("NaN")) {
                                if (f[i].getType() == Float.TYPE) {
                                    value = "(float) (0.0/0.0)";
                                }
                                else { // double
                                    value = "(double) (0.0/0.0)";
                                }
                                special = true;
                            }
                            else if (value.toString().equals("Infinity")) {
                                if (f[i].getType() == Float.TYPE) {
                                    value = "(float) (1.0/0.0)";
                                }
                                else { // double
                                    value = "(double) (1.0/0.0)";
                                }
                                special = true;
                            }
                            else if (value.toString().equals("-Infinity")) {
                                if (f[i].getType() == Float.TYPE) {
                                    value = "(float) (-1.0/0.0)";
                                }
                                else { // double
                                    value = "(double) (-1.0/0.0)";
                                }
                                special = true;
                            }

                            if (f[i].getType() == Character.TYPE) {
                                // take care of possibly hard-to-print chars; better use their numeric equivalent
                                out.print((int) ((Character) value).charValue());
                            }
                            else {
                                out.print(value);
                            }

                            if (!special) {
                                //out.print(f[i].getType() == Character.TYPE ? "'" : "");
                                out.print(f[i].getType() == Double.TYPE ? "d" : "");
                                out.print(f[i].getType() == Float.TYPE ? "f" : "");
                                out.print(f[i].getType() == Long.TYPE ? "l" : "");
                            }
                            out.println(";");
                        }
                        catch (IllegalAccessException iae) {
                            System.err.println("HELP, cannot access member, won't create stub field '" + f[i] + "'. " + iae);
                        }
                    }
                    else {
                        out.println(modifiers + " " + type + " " + name + "= null;");
                    }
                }
                else {
                    if (Modifier.isFinal(f[i].getModifiers())) {
                        out.print(modifiers + " " + type + " " + name + " = ");
                        out.println(getDummyValueForType(f[i].getType()) + ";");
                    }
                    else {
                        out.println(modifiers + " " + type + " " + name + ";");
                    }
                }
            }

        }
    }

    /**
     * Generate header: package, class, opening bracket...
     *
     * @param out
     * @param clazz
     */
    static void generateHeader(java.io.PrintStream out, Class clazz) {
        String clazzname = clazz.getName();
        out.println("package " + clazzname.substring(0, clazzname.lastIndexOf('.')) + ";");
        // No imports are required since we explicitly qualify everything we use.
        out.print(Modifier.toString(clazz.getModifiers() & (~Modifier.INTERFACE)));

        if (clazz.isInterface()) {
            out.println(" interface " + clazzname.substring(clazzname.lastIndexOf('.') + 1));
            Class[] interfaces = clazz.getInterfaces();
            if (interfaces.length > 0) {
                out.print("     extends ");
                for (int i = 0; i < interfaces.length; i++) {
                    out.print(interfaces[i].getName());
                    if (i < interfaces.length - 1)
                        out.print(", ");
                }
            }
        }
        else {
            out.println(" class " + clazzname.substring(clazzname.lastIndexOf('.') + 1));
            // super class
            if (clazz.getSuperclass() != null)
                out.println("    extends " + clazz.getSuperclass().getName());
            // implemented interfaces
            Class[] interfaces = clazz.getInterfaces();
            if (interfaces.length > 0) {
                out.print("     implements ");
                for (int i = 0; i < interfaces.length; i++) {
                    out.print(interfaces[i].getName());
                    if (i < interfaces.length - 1)
                        out.print(", ");
                }
            }
        }
        out.println("{");
    }

    /**
     *
     * @param out
     * @param clazz
     */
    static void generateStub(PrintStream out, Class clazz) {
        generateHeader(out, clazz);
        out.println();
        generateFields(out, clazz.getDeclaredFields());
        out.println();
        generateConstructorStubs(out, clazz.getDeclaredConstructors());
        out.println();
        generateMethodStubs(out, clazz.getDeclaredMethods());
        out.println("}");
    }

    /**
     *  Prints out all methods and field of a class using reflection.
     *
     *  The option "-s" generates a stub file in the current directory,
     *  i.e. a java class with the same signature but empty implementation.
     *
     *  Same with option "-snp", only that "private" members are not
     *  generated.
     *  The file should be valid java source code.
     * @throws FileNotFoundException 
     *
     */
    public static void main(String[] argv) throws FileNotFoundException {    	
    	main(new String[]{"-s","misc.Main"}, new PrintStream("C:\\temp\\Stub.java"));
    }

    /**
     *
     * @param argv
     * @param out
     */
    public static void main(String[] argv, PrintStream out) {
        Class clazz;

        if (argv.length < 1) {
            usage();
            return;
        }
        try {
            stubs = (argv.length > 1 && argv[0].equals("-s"));
            noprivate = (argv.length > 1 && argv[0].equals("-snp"));
            if (noprivate) stubs = true; // -snp includes "stubs"

            System.err.println("Processing class '" + argv[argv.length - 1] + "'.");
            clazz = Class.forName(argv[argv.length - 1]);
        }
        catch (ClassNotFoundException ex) {
            System.err.println("Class '" + argv[argv.length - 1] + "' could not be found.");
            usage();
            return;
        }

        if (stubs) {
            generateStub(out, clazz);

        }
        else {
            // print Information to stdout

            System.out.println("Inspecting " + clazz.getName() + ":");
            if (clazz.getSuperclass() != null)
                System.out.println("Superclass: " + clazz.getSuperclass().getName());
            System.out.println("Implemented interfaces:");
            iterateAndPrint(clazz.getInterfaces());
            System.out.println("--- Fields --------------------------------");
            iterateAndPrint(clazz.getDeclaredFields());
            System.out.println("--- Constructors --------------------------");
            iterateAndPrint(clazz.getDeclaredConstructors());
            System.out.println("--- Methods -------------------------------");
            iterateAndPrint(clazz.getDeclaredMethods());
        }
    }
}
