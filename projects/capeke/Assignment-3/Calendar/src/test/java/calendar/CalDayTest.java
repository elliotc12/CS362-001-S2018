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
      Appt nohour = new Appt(2, 2, 2018, "notitle" , "nodescription", "noemail");
      calday.addAppt(nohour);
      assertTrue(calday.getFullInformationApp(calday).contains("2-1-2018 \n	notitle nodescription"));
      System.out.println("full information: " + calday.getFullInformationApp(calday));
  }

    @Test(timeout = 4000)
    public void test02()  throws Throwable  {
	GregorianCalendar cal = new GregorianCalendar(2018, 1, 1);
	CalDay calday = new CalDay(cal);
	CalDay calday2 = new CalDay(cal);
	Appt appt = new Appt(12, 11, 1, 1, 2018, "", "", "");
	Appt appt2 = new Appt(0, 10, 1, 1, 2018, "", "", "");
	Appt appt3 = new Appt(13, 9, 1, 1, 2018, "", "", "");
	Appt appt4 = new Appt(-1, 5, 1, 1, -1, "", "", "");
	appt4.setValid();
	calday.addAppt(appt);
	calday.addAppt(appt2);
	calday.addAppt(appt3);
	calday.addAppt(appt4);
	assertTrue(calday.getAppts().get(2).getStartHour() > calday.getAppts().get(1).getStartHour());
	assertTrue(calday.getAppts().get(1).getStartHour() > calday.getAppts().get(0).getStartHour());
	assertEquals(calday.getAppts().size(), 3);
	assertTrue(calday.toString().length() > calday2.toString().length());
	assertEquals(calday.getSizeAppts(), 3);
	assertEquals(calday2.getSizeAppts(), 0);
	assertTrue(calday.getFullInformationApp(calday).length() > calday2.getFullInformationApp(calday2).length());
	System.out.printf("full information: " + calday.getFullInformationApp(calday));
        assertTrue(calday.getFullInformationApp(calday).contains("2-1-2018 \n	12:10AM   \n	0:11AM   \n	1:09PM"));
  }

    @Test(timeout = 4000)
    public void test03()  throws Throwable  {
	GregorianCalendar cal = new GregorianCalendar(-1, 1, 1);
	CalDay calday = new CalDay(cal);
	assertTrue(calday.getFullInformationApp(calday).length() > 0);
	assertTrue(calday.toString().length() > 0);
  }

    @Test(timeout = 4000)
    public void test04()  throws Throwable  {

    }

}
