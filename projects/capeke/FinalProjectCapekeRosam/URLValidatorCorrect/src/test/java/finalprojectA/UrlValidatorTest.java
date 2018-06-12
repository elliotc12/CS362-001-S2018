package finalprojectA;

import junit.framework.TestCase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

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

    public void testIsValidUnitTest_1() {
	String[] v_schemes = {"file"};
	UrlValidator urlValidator = new UrlValidator(v_schemes, UrlValidator.ALLOW_ALL_SCHEMES);
	assertEquals(urlValidator.isValid(null), false);
    }

    public void testIsValidUnitTest_2() {
	System.out.println("\n\n----Starting regex test----");
	String[] v_schemes = {"file"};
	UrlValidator urlValidator = new UrlValidator(v_schemes, UrlValidator.ALLOW_ALL_SCHEMES);

	String my_regex =
	    "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	// Valid URL regex found on https://regexr.com/3e6m0
	Pattern my_pattern = Pattern.compile(my_regex);

	Random rand = new Random();

	String valid_chars = "au./:?";
	int vclen = valid_chars.length();
	// System.out.println("vc is: " + valid_chars);

	for (int t = 0; t < 1000000; t++) {
	    StringBuilder test_match = new StringBuilder();
	    for (int i = 0; i < 8; i++) {
		int rnum = rand.nextInt(valid_chars.length());
		// System.out.println(i + " " + rnum);
		test_match.append(valid_chars.charAt(rnum));
	    }
	    String test_url = "http://" + test_match.toString();
	    boolean my_regex_matches = my_pattern.matcher(test_url).matches();
	    boolean code_isValid = urlValidator.isValid(test_url);

	    if (code_isValid) {
		System.out.print(test_url + " passes through isValid regex");
		if (my_regex_matches)
		    System.out.print(" and my regex\n");
		else {
		    System.out.print(" NOT my regex.\n");
		    assertTrue(my_regex_matches);
		}
	    }
	}
    }
}
