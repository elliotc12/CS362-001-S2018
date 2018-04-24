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
}
