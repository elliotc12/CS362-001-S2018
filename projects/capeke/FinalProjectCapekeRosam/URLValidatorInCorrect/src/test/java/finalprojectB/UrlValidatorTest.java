package finalprojectB;

import junit.framework.TestCase;

public class UrlValidatorTest extends TestCase {

    public UrlValidatorTest(String testName) {
	super(testName);
    }

    public void testManualTest() {
        String[] schemes = {"http","https"};
	UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.ALLOW_ALL_SCHEMES);
	boolean output_notilde = urlValidator.isValid("http://www.google.com");
	boolean output_tilde = urlValidator.isValid("http://www.google.com/abc");
	boolean output_long = urlValidator.isValid("http://tech.yahoo.com/rc/desktops/102;_ylt=Ao8yevQHlZ4On0O3ZJGXLEQFLZA5");
	boolean output_empty = urlValidator.isValid("http:");

	System.out.println("http://www.google.com is valid: " + output_notilde);
	System.out.println("http://www.google.com/abc is valid: " + output_tilde);
	System.out.println("http://tech.yahoo.com/rc/desktops/102;_ylt=Ao8yevQHlZ4On0O3ZJGXLEQFLZA5 is valid: " + output_long);
	System.out.println("http: is valid: " + output_empty);
    }

    public void testYourFirstPartition() {
	System.out.println("\n\n---------Begin partition testing----------");
	String[] v_schemes = {"http","https"};
	UrlValidator urlValidator = new UrlValidator(v_schemes, UrlValidator.ALLOW_ALL_SCHEMES);

        // Our partition scheme will sample all combinations of valid scheme, authority, path,
	// schemes: http, https, ftp and blank
	String[] schemes = {"http://", "https://", "h3t://"};

	// authorities: test the basic google and an IP address
	String[] authorities = {"www.google.com", "0.0.0.0"};

	// ports: with and without a port
	String[] ports = {":80", ""};

	// paths: none, 1 subtree and 2 subtrees
	String[] paths = {"/test1/file", "/test1/", ""};

	// queries: witha and without query
	String[] queries = {"?mode=down", ""};

	int num_permutations =
	    schemes.length * authorities.length * ports.length * paths.length * queries.length;

	for (int i = 0; i < num_permutations; i++) {
	    int permutation_idx = i;
	    StringBuilder permutation_builder = new StringBuilder();

	    permutation_builder.append(schemes[permutation_idx % schemes.length]);
	    permutation_idx /= schemes.length;

	    permutation_builder.append(authorities[permutation_idx % authorities.length]);
	    permutation_idx /= authorities.length;

	    permutation_builder.append(ports[permutation_idx % ports.length]);
	    permutation_idx /= ports.length;

	    permutation_builder.append(paths[permutation_idx % paths.length]);
	    permutation_idx /= paths.length;

	    permutation_builder.append(queries[permutation_idx % queries.length]);
	    permutation_idx /= queries.length;

	    String permutation = permutation_builder.toString();
	    System.out.println(permutation + " checking if valid...");

	    try {
		System.out.println(urlValidator.isValid(permutation));
	    } catch (Throwable except) {
		System.out.println("Checking would lead to an exception.");
	    }
	}
    }

    public void testYourSecondPartition() {
	System.out.println("\n\n---------Begin partition testing II----------");
	String[] v_schemes = {"file"};
	UrlValidator urlValidator = new UrlValidator(v_schemes, UrlValidator.ALLOW_ALL_SCHEMES);

        // Our partition scheme will sample all combinations of valid scheme, authority, path,
	// schemes: http, https, ftp and blank
	String[] schemes = {"file://"};

	// authorities: test the basic google and an IP address
	String[] authorities = {"/root/file", "/root", "~", "/c:"};

	// paths: none, 1 subtree and 2 subtrees
	String[] paths = {"/test1/file.txt", "/test1/", "/file%20name", ""};

	int num_permutations = schemes.length * authorities.length * paths.length;

	for (int i = 0; i < num_permutations; i++) {
	    int permutation_idx = i;
	    StringBuilder permutation_builder = new StringBuilder();

	    permutation_builder.append(schemes[permutation_idx % schemes.length]);
	    permutation_idx /= schemes.length;

	    permutation_builder.append(authorities[permutation_idx % authorities.length]);
	    permutation_idx /= authorities.length;

	    permutation_builder.append(paths[permutation_idx % paths.length]);
	    permutation_idx /= paths.length;

	    String permutation = permutation_builder.toString();
	    System.out.println(permutation + " checking if valid...");

	    try {
		System.out.println(urlValidator.isValid(permutation));
	    } catch (Throwable except) {
		System.out.println("Checking would lead to an exception.");
	    }
	}
    }
   
   public void testIsValidUnitTest() {

   }
}
