package a2;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Enumeration;
import java.util.Vector;

public class Inspector {
	public void inspect(Object obj, boolean recursive) throws IllegalArgumentException, IllegalAccessException {
		//name of declaring class
		Class classObj = obj.getClass();
		Vector objects = new Vector();
		String declaringClass = obj.getClass().getSimpleName();
		System.out.print("The name of the declaring class is: ");
		System.out.println(declaringClass);
		//name of immediate superclass
		String immediateSuperClass = obj.getClass().getSuperclass().getSimpleName();
		System.out.print("The name of the immediate superclass is: ");
		System.out.println(immediateSuperClass);
		//name of interfaces implemented

		inspectInterfaces(obj, classObj);
		inspectMethods(obj, classObj);
		inspectConstructors(obj, classObj);
		inspectFields(obj, classObj, objects);
		if ((classObj.getSuperclass() != null) && (classObj.getSuperclass() != Object.class)) {
			inspectSuperclass(obj, classObj, objects);
		}

		if (recursive==true) {
			inspectFieldClasses(obj, classObj, objects, recursive);
		}
			


		
	}
	private void inspectSuperclass(Object obj, Class classObj, Vector objects) throws IllegalArgumentException, IllegalAccessException {
		System.out.println();
		System.out.println("'" + classObj.getSimpleName() + "' Superclass:");
		Class superclass = classObj.getSuperclass();
		inspectMethods(obj, superclass);
		inspectConstructors(obj, superclass);
		inspectFields(obj, superclass, new Vector());
		
	}
	private void inspectFieldClasses(Object obj, Class classObj, Vector objects, boolean recursive) {
		if (objects.size() > 0) {
			System.out.println("'" + classObj.getSimpleName() + "' Field Classses are:");
		}
		Enumeration e = objects.elements();
		while (e.hasMoreElements()) {
			Field f = (Field) e.nextElement();
			System.out.println("Inspecting Field: " + f.getName());

			try {
				System.out.println("---");
				inspect(f.get(obj), recursive);
				System.out.println("---");
			} catch (NullPointerException nullExp) {
				System.out.println("Field not instantiated at runtime");
				System.out.println("---");
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
		System.out.println("End of " + classObj.getSimpleName()+ "field classes");
	}
		
	
	public void inspectInterfaces(Object obj, Class classObj) {
		Class[] interfaces = classObj.getInterfaces();
		if (interfaces.length > 0) {
			
			System.out.println("The interfaces implemented by this class are: ");
			for (Class j : interfaces) {
				System.out.println(j.getName());
				//now we need to inspect the interfaces
				System.out.println("Inspecting " + j.getSimpleName() + "...");
				inspectMethods(obj, j);
				inspectConstructors(obj, j);
			}
			System.out.println("---End of " + classObj.getSimpleName()+ " interfaces---");
		}
		else {
			System.out.println("No interfaces found");
		}
		
	}

	public void inspectMethods(Object obj, Class classObj) {
		Method[] methods = classObj.getDeclaredMethods();
		System.out.println("The methods in " + classObj.getName() + " are: ");
		if (methods.length >0) {
			for (Method i : methods) {
				//System.out.println();
				//get exception types
				Class [] exception = i.getExceptionTypes();	
				//get parameter types
				Class[] parameterTypes = i.getParameterTypes();
				//get return type
				Class returnType = i.getReturnType();
				//get modifiers
				int modifiers = i.getModifiers();
				String sModifiers = Modifier.toString(modifiers);
				System.out.print("Method: ");
				System.out.print(i.getName());
				//loop through exceptions
				for (Class j : exception) {
					System.out.print(", Exception Name: ");
					System.out.print(j.getName());
				}
				System.out.print(", Paramter Types: ");
				for (Class j : parameterTypes) {
					System.out.print(j.getName() + ",");
				}
				System.out.print(" Return Type: " +  returnType.getName());
				System.out.println(" Modifiers: " +  sModifiers);
				

			}

		}
		else {
			System.out.println("No methods found");
		}
		
	}
	public void inspectConstructors(Object obj, Class classObj) {
		System.out.println("The constructors in " + classObj.getName() + " are: ");
		Constructor[] constructors = classObj.getDeclaredConstructors();
		if (constructors.length >0 ) {
			for (Constructor x : constructors) {
				System.out.println();
				System.out.println(x.getName());
				//get parameter types
				Class [] parameterTypes = x.getParameterTypes();
				//get modifiers
				int modifiers = x.getModifiers();
				String sModifiers = Modifier.toString(modifiers);
				System.out.print("Parameter types: ");
				for (Class c : parameterTypes) {
					System.out.print(c.getName());
				}
				System.out.print(" Modifiers: " +  sModifiers);
				
			}
		}
		else {
			System.out.println("No constructors found");
		}

		
	}
	public void inspectFields(Object obj, Class classObj, Vector objects) throws IllegalArgumentException, IllegalAccessException {
		System.out.println("\n\nThe fields in this class are:");
		//System.out.println();
		Field[] fields = classObj.getDeclaredFields();
		System.out.println("Number of fields: " +fields.length);
		for (Field x : fields) {
			System.out.println();
			x.setAccessible(true);
			if (!x.getType().isPrimitive()) {
				objects.addElement(x);
			}
				//now print the info
			if (x.getType().isArray()) {
				System.out.println("Field: '" + x.getName() + "'\n\t-Type: " + x.getType().getComponentType() + "\n\t-Modifier: " + Modifier.toString(x.getModifiers()));
			} else {
				System.out.println("Field: '" + x.getName() + "' = " + x.get(obj) + "\n\t-Type: " + x.getType()+ "\n\t-Modifier: "+ Modifier.toString(x.getModifiers()));
			}		
		}
		
		
		
	}
	public void fieldNonPrimitive(Field fields, Object obj) throws IllegalArgumentException, IllegalAccessException {
		if (fields.getType().isArray()) {
			fields.setAccessible(true);
			System.out.println(" ...This field is an array");
			System.out.println("Array length is:" + Array.getLength(fields.get(obj)));
			//to do
			// iterate through the array and print
		}
		
		
	}
	private void inspectFieldsRecursive(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Class classObj = obj.getClass();
		System.out.println("\n\nThe fields in this class are:");
		System.out.println();
		Field[] fields = classObj.getDeclaredFields();
		for (Field x : fields) {
			System.out.println();
			System.out.print(x.getName());
			int modifiers = x.getModifiers();
			Type type = x.getGenericType();
			String sModifiers = Modifier.toString(modifiers);
			System.out.print(", " + type.getTypeName());
			System.out.print(", " + sModifiers);
			if (x.getType().isPrimitive()){
				System.out.println(", " + x.getType());
			}
			else {
				inspectFieldsRecursive(x);
			}
			
		
		}
		
	}

}
