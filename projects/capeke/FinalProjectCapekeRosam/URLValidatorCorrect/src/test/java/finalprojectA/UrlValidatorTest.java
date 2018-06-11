package finalprojectA;

import junit.framework.TestCase;

public class UrlValidatorTest extends TestCase {

    public UrlValidatorTest(String testName) {
	super(testName);
    }

    public void testManualTest() {
        String[] schemes = {"http","https"};
	UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.ALLOW_ALL_SCHEMES);
	boolean output_notilde = urlValidator.isValid("http://www.google.com");
	boolean output_tilde = urlValidator.isValid("http://www.google.com/~");
	boolean output_long = urlValidator.isValid("http://tech.yahoo.com/rc/desktops/102;_ylt=Ao8yevQHlZ4On0O3ZJGXLEQFLZA5");
	boolean output_empty = urlValidator.isValid("http:");

	System.out.println("http://www.google.com is valid: " + output_notilde);
	System.out.println("http://www.google.com/~ is valid: " + output_tilde);
	System.out.println("http://tech.yahoo.com/rc/desktops/102;_ylt=Ao8yevQHlZ4On0O3ZJGXLEQFLZA5 is valid: " + output_long);
	System.out.println("http: is valid: " + output_empty);
    }

    public void testYourFirstPartition() {
	System.out.println("Hello,, World");
    }
   
   public void testYourSecondPartition() {

   }
   
   public void testIsValidUnitTest() {

   }
}
