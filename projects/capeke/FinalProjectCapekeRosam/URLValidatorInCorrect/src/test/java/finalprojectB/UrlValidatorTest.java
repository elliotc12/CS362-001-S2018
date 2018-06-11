package finalprojectB;

import junit.framework.TestCase;

public class UrlValidatorTest extends TestCase {

    public UrlValidatorTest(String testName) {
	super(testName);
    }

    public void testManualTest() {
        String[] schemes = {"http","https"};
	UrlValidator urlValidator = new UrlValidator(schemes);
	boolean output1 = urlValidator.isValid("www.google.com");
	boolean output2 = urlValidator.isValid("");
	boolean output3 = urlValidator.isValid("");
	boolean output4 = urlValidator.isValid("");
	boolean output5 = urlValidator.isValid("http://tech.yahoo.com/rc/desktops/102;_ylt=Ao8yevQHlZ4On0O3ZJGXLEQFLZA5");
	System.out.println("Output1 is valid: " + output1);
	System.out.println("Output2 is valid: " + output2);
	System.out.println("Output3 is valid: " + output3);
	System.out.println("Output4 is valid: " + output4);
	System.out.println("Output5 is valid: " + output5);
    }

    public void testYourFirstPartition() {
	System.out.println("Hello,, World");
    }
   
   public void testYourSecondPartition() {

   }
   
   public void testIsValidUnitTest() {

   }
}
