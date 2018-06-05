package calendar;

import java.util.Calendar;
import java.util.Random;
import org.junit.Test;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class CalDayRandomTest {

        private static final long TestTimeout = 10000; /* Timeout at 30 seconds */
    private static final int NUM_TESTS=100;

    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"setTitle","setRecurrence"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
    }

    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
    }	

    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
    }

    @Test
    public void radnomtest()  throws Throwable  {

	long startTime = Calendar.getInstance().getTimeInMillis();
	long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


	System.out.println("Start testing...");

	long randomseed = System.currentTimeMillis(); //10
	Random random = new Random(randomseed);
	GregorianCalendar cal =
	    new GregorianCalendar(ValuesGenerator.getRandomIntBetween(random, -5, 2018),
				  ValuesGenerator.getRandomIntBetween(random, 1, 12),
				  ValuesGenerator.getRandomIntBetween(random, 1, 28));

	CalDay calday = new CalDay(cal);

	try { 
	    for (int iteration = 0; elapsed < TestTimeout; iteration++) {
		int startHour=ValuesGenerator.getRandomIntBetween(random, -2, 26);
		int startMinute=ValuesGenerator.getRandomIntBetween(random, -2, 62);
		int startDay=ValuesGenerator.getRandomIntBetween(random, -2, 33);
		int startMonth=ValuesGenerator.getRandomIntBetween(random, -2, 14);
		int startYear=ValuesGenerator.getRandomIntBetween(random, -5, 2018);

		String title="Birthday Party";
		String description="This is my birthday party.";
		String emailAddress="xyz@gmail.com";

		//Construct a new Appointment object with the initial data	 
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(startHour, startMinute , startDay , startMonth , startYear , title, description, emailAddress);

		appt.setValid();
		calday.addAppt(appt);

		elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
		if((iteration%10000)==0 && iteration!=0 )
		    System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
	    }
	}
	catch(NullPointerException e) {

	}

	System.out.println("Done testing...");
    }
}
