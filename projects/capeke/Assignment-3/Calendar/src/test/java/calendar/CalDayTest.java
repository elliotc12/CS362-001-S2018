/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.GregorianCalendar;
import java.util.LinkedList;


public class CalDayTest{

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      CalDay bad = new CalDay();
      assertFalse(bad.isValid());
      assertTrue(bad.toString().length() == 0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      GregorianCalendar cal = new GregorianCalendar(2018, 1, 1);
      CalDay calday = new CalDay(cal);
      assertEquals(calday.getDay(), 1);
      assertEquals(calday.getMonth(), 2);
      assertEquals(calday.getYear(), 2018);
  }

    @Test(timeout = 4000)
    public void test02()  throws Throwable  {
	GregorianCalendar cal = new GregorianCalendar(2018, 1, 1);
	CalDay calday = new CalDay(cal);
	CalDay calday2 = new CalDay(cal);
	Appt appt = new Appt(15, 30, 9, 14, 2018, "", "", "");
	Appt appt2 = new Appt(0, 30, 9, 14, 2018, "", "", "");
	Appt appt3 = new Appt(16, 5, 9, 14, 2018, "", "", "");
	Appt appt4 = new Appt(-1, 5, 9, 14, -1, "", "", "");
	appt4.setValid();
	calday.addAppt(appt);
	calday.addAppt(appt2);
	calday.addAppt(appt3);
	calday.addAppt(appt4);
	assertEquals(calday.getAppts().size(), 3);
	assertTrue(calday.toString().length() > calday2.toString().length());
	assertEquals(calday.getSizeAppts(), 3);
	assertEquals(calday2.getSizeAppts(), 0);
	assertTrue(calday.getFullInfomrationApp(calday).length() > calday2.getFullInfomrationApp(calday2).length());
  }

    @Test(timeout = 4000)
    public void test03()  throws Throwable  {
	GregorianCalendar cal = new GregorianCalendar(-1, 1, 1);
	CalDay calday = new CalDay(cal);
	assertTrue(calday.getFullInfomrationApp(calday).length() > 0);
	assertTrue(calday.toString().length() > 0);
  }

}
