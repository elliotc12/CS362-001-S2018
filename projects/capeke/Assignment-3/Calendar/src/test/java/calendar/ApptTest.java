/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
public class ApptTest  {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals(appt0.getEmailAddress(), "xyz@gmail.com");
      assertEquals(appt0.getXmlElement(), null);
      assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
  }

    @Test(timeout = 4000)
    public void test01()  throws Throwable  {
	Appt appt0 = new Appt(1, 1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	String string0 = appt0.toString();
	assertFalse(appt0.isRecurring());
	assertEquals(0, appt0.getRecurIncrement());
	assertFalse(appt0.hasTimeSet());
    }

    @Test(timeout = 4000)
    public void test02()  throws Throwable  {
	Appt badHour = new Appt(25, 30, 9, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt badMinute = new Appt(15, 61, 9, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt badMonth = new Appt(15, 30, 9, 13, 2018, "Testtitle", "Testdescription", "testemail");
	Appt badDay = new Appt(15, 30, 32, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt badYear = new Appt(15, 30, 9, 10, -1, "Testtitle", "Testdescription", "testemail");
	badHour.setValid(); badMinute.setValid(); badMonth.setValid(); badDay.setValid(); badYear.setValid();
	assertFalse(badHour.getValid());
	assertFalse(badMinute.getValid());
	assertFalse(badMonth.getValid());
	assertFalse(badDay.getValid());
	assertFalse(badYear.getValid());
	badYear.toString();
    }

    @Test(timeout = 4000)
    public void test03()  throws Throwable  {
	Appt nullInfo = new Appt(10, 30, 9, 10, 2018, null, null, null);
	nullInfo.setRecurrence(null, 0, 0, 0);
	assertEquals(nullInfo.getRecurNumber(), Appt.RECUR_NUMBER_NEVER);
	nullInfo.setRecurrence(new int[2], 2, 2, 2);
	assertEquals(nullInfo.isRecurring(), true);
	assertEquals(nullInfo.getRecurDays().length, 2);
	nullInfo.setValid();
    }

    @Test(timeout = 4000)
    public void test04()  throws Throwable  {
	Appt appt = new Appt(1, 1, 1, 1, 1, null, null, null);
	assertFalse(appt.isOn(1, 1, 0));
	assertFalse(appt.isOn(1, 0, 1));
	assertFalse(appt.isOn(0, 1, 1));
	assertFalse(appt.isOn(1, 0, 0));
	assertFalse(appt.isOn(0, 0, 1));
	assertFalse(appt.isOn(0, 0, 0));
	assertTrue(appt.hasTimeSet());
    }

    @Test(timeout = 4000)
    public void test05()  throws Throwable  {
	Appt badMonth = new Appt(15, 30, 2, 10, 2018, "Testtitle", "Testdescription", "testemail");
        badMonth.setValid();
    }

    @Test(timeout = 4000)
    public void test06()  throws Throwable  {
	// Appt(int startHour, int startMinute, int startDay, int startMonth, int startYear, String title, String description, String emailAddress ) {

	Appt upperHour = new Appt(23, 30, 9, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt upperMinute = new Appt(15, 59, 9, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt upperMonth = new Appt(15, 30, 9, 12, 2018, "Testtitle", "Testdescription", "testemail");
	Appt upperDay = new Appt(15, 30, 31, 10, 2018, "Testtitle", "Testdescription", "testemail");
	upperHour.setValid();
	upperMinute.setValid();
	upperMonth.setValid();
	upperDay.setValid();
	assertTrue(upperHour.getValid());
	assertTrue(upperMinute.getValid());
	assertTrue(upperMonth.getValid());
	assertTrue(upperDay.getValid());

	Appt lowerHour = new Appt(15, 30, 9, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt lowerMinute = new Appt(15, 0, 9, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt lowerMonth = new Appt(15, 30, 9, 1, 2018, "Testtitle", "Testdescription", "testemail");
	Appt lowerDay = new Appt(15, 30, 1, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt lowerYear = new Appt(15, 30, 30, 10, 1, "Testtitle", "Testdescription", "testemail");
	lowerHour.setValid(); lowerMinute.setValid(); lowerMonth.setValid(); lowerDay.setValid(); lowerYear.setValid();
	assertTrue(lowerHour.getValid()); assertTrue(lowerMinute.getValid()); assertTrue(lowerMonth.getValid()); assertTrue(lowerDay.getValid()); assertTrue(lowerYear.getValid()); 

	
	Appt highHour = new Appt(25, 30, 9, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt highMinute = new Appt(15, 61, 9, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt highMonth = new Appt(15, 30, 9, 13, 2018, "Testtitle", "Testdescription", "testemail");
	Appt highDay = new Appt(15, 30, 32, 10, 2018, "Testtitle", "Testdescription", "testemail");
	highHour.setValid(); highMinute.setValid(); highMonth.setValid(); highDay.setValid();
	assertFalse(highHour.getValid()); assertFalse(highMinute.getValid()); assertFalse(highMonth.getValid()); assertFalse(highDay.getValid());

	Appt lowHour = new Appt(-1, 30, 9, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt lowMinute = new Appt(15, -1, 9, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt lowMonth = new Appt(15, 30, 9, -1, 2018, "Testtitle", "Testdescription", "testemail");
	Appt lowDay = new Appt(15, 30, -1, 10, 2018, "Testtitle", "Testdescription", "testemail");
	Appt lowYear = new Appt(15, 30, 9, 10, -1, "Testtitle", "Testdescription", "testemail");
	lowHour.setValid(); lowMinute.setValid(); lowMonth.setValid(); lowDay.setValid(); lowYear.setValid();
	assertFalse(lowHour.getValid()); assertFalse(lowMinute.getValid()); assertFalse(lowMonth.getValid()); assertFalse(lowDay.getValid()); assertFalse(lowYear.getValid());
    }
}
