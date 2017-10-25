package a2;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

public class InspectorTest {
    Object classAObj;
    Object classBObj;
    Inspector testClass;
    @Before
    public void setUp() throws Exception
    {
            classAObj = new ClassA();
            classBObj = new ClassB();
            testClass = new Inspector();
            
    }
	@Test
	public void testInspectInterfaces() {
		Class classObj = classAObj.getClass();
		Vector <String> interfaces = testClass.inspectInterfaces(classAObj, classObj);
		// test to see if first interface in Class A is Serializable 
        assertEquals( interfaces.firstElement(), "java.io.Serializable");
        //check to see if second or last interface implemented is runnable 
        assertEquals( interfaces.lastElement(), "java.lang.Runnable");
	}
	
	@Test
	public void testInspectedMethods() {
		Class classObj = classAObj.getClass();
		Vector <String> methods = testClass.inspectMethods(classAObj, classObj);
		// test to see if Method in class A is run
        assertEquals( methods.firstElement(), "run");
        //check to see if return type is void for run since it throws no exceptions
        assertEquals(methods.get(1), "void");
        // check to see if 3rd method is getVal
        assertEquals( methods.get(11), "getVal"); 

	}
	@Test
	public void testInspectedContructors() {
		Class classObj = classAObj.getClass();
		Vector <String> constructors = testClass.inspectConstructors(classAObj, classObj);
        // check to see if name of first constructor is ClassA
		assertEquals( constructors.firstElement(), "a2.ClassA");
		//check to see if second constructor takes an int as parameter
		assertEquals(constructors.get(3), "int");
		// check to see if second constructor is public
        assertEquals( constructors.lastElement(), "public");

	}
	

}
