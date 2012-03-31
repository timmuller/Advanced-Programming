import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import junit.framework.*;


public class LearningTest extends TestCase{
	private Class myClass;
	private MyClass myObject;
	
	
	@Before
	public void setUp(){
		myObject = new MyClass();
		myClass = myObject.getClass();
	}
	
	@Test
	public void testGetMethods(){
		Method[] methods = myClass.getMethods();
		
		boolean foundToStringMethod = containsMethod("toString", methods);
		boolean foundFirstMethod = containsMethod("firstMethod", methods);
		boolean foundSecondMethod = containsMethod("secondMethod", methods);
		
		assertEquals(foundToStringMethod, true);
		assertEquals(foundFirstMethod, true);
		assertEquals(foundSecondMethod, true);

	}
	
	@Test
	public void testMethodExistWithoutParams(){
		Method methodExist = null;
		try {
			methodExist = myClass.getMethod("firstMethod", new Class[] {});
		} catch (SecurityException e) {
			
		} catch (NoSuchMethodException e) {
			
		}

		assertNotNull(methodExist);
	}
	
	@Test
	public void testMethodExistWithParams(){
		Method methodExist = null;
		try {
			methodExist = myClass.getMethod("secondMethod", new Class[] {String.class});
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		assertNotNull(methodExist);
	}
	
	@Test
	public void testMethodDoenstExist(){
		Method methodExist = null;
		
		try {
			methodExist = myClass.getMethod("notAExistingMethod", new Class[] {});
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		
		assertNull(methodExist);
	}
	
	@Test
	public void testGetIsClassRepresenting(){
		boolean isLocalClass = myClass.isLocalClass();
		assertEquals(isLocalClass, false);
		
		boolean isMemberClass = myClass.isMemberClass();
		assertEquals(isMemberClass, false);
		
		boolean isInterface  = myClass.isInterface();
		assertEquals(isInterface, false);
		
		boolean isPrimitieve = myClass.isPrimitive();
		assertEquals(isPrimitieve, false);
		
		boolean isArrayClass = myClass.isArray();
		assertEquals(isArrayClass, false);
		
	}
	
	@Test
	public void testGetInterfaceInformation(){
		Class[] interfaces = myClass.getInterfaces();
		
		int totalInterfaces = interfaces.length;
		assertEquals(totalInterfaces, 1);
		
		Class firstInterface = interfaces[0];
		
		boolean isInterface = firstInterface.isInterface();
		assertEquals(isInterface, true);
		
		String firstInterfaceName = firstInterface.getName();
		assertEquals(firstInterfaceName, "MyClassInterface");
		
		Method[] methods = firstInterface.getMethods();
		boolean interfaceMethodExists = containsMethod("interfaceFunction", methods);
		assertEquals(interfaceMethodExists, true);
	}
	
	@Test
	public void testGetSuperClassInformation(){
		Class superClass = myClass.getSuperclass();
		
		String superClassName = superClass.getName();
		assertEquals(superClassName, "SuperClass");		
	}
	
	@Test
	public void testObjectInstanceOfClass(){
		boolean isInstance = myClass.isInstance(myObject);
		assertEquals(isInstance, true);
	}
	
	@Test
	public void testObjectInstanceOfSuperClass(){
		Class superClass = myClass.getSuperclass();
		boolean isInstance = superClass.isInstance(myObject);
		assertEquals(isInstance, true);
	}
	
	
	
		
	private boolean containsMethod(String methodName, Method[] methods){
		for(Method method : methods){
			if(method.getName().equals(methodName)){
				return true;
			}
		}
		return false;
	}
	
	
	
}
