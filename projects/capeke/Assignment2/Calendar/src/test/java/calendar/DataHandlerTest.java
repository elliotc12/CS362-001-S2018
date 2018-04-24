
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;


public class DataHandlerTest{

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      String fname = System.getProperty("user.dir") + System.getProperty("file.separator") + "calendar.xml";
      String badfname = "/notreal.xml";
      DataHandler dh = new DataHandler();
      DataHandler dhfname = new DataHandler(fname);
      DataHandler dhfnamesave = new DataHandler(fname, true);
      DataHandler dhfnamesavebad = new DataHandler(badfname, true);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      GregorianCalendar cal1 = new GregorianCalendar(2016, 1, 1);
      GregorianCalendar cal2 = new GregorianCalendar(2019, 1, 1);
      Appt appt = new Appt(1, 1, 1, 1, 2017, "", "", "");
      Appt appt2 = new Appt(1, 1, 1, 1, 2018, "", "", "");
      appt2.setRecurrence(new int[2], 2, 2, 2);
      Appt badappt = new Appt(1, 1, 1, 1, -2018, "", "", "");
      badappt.setValid();
      DataHandler dh = new DataHandler();
      dh.saveAppt(appt);
      assertEquals(dh.getApptRange(cal1, cal2).size(), 365*3+1);
      dh.diagnose = false;
      dh.saveAppt(appt2);
      assertEquals(dh.getApptRange(cal1, cal2).size(), 365*3+1);
      dh.deleteAppt(appt2);
      assertEquals(dh.getApptRange(cal1, cal2).size(), 365*3+1);
      dh.saveAppt(badappt);
      dh.deleteAppt(badappt);
      appt2.setRecurrence(new int[2], Appt.RECUR_BY_WEEKLY, 2, 2);
      dh.saveAppt(appt2);
      dh.getApptRange(cal1, cal2);
      appt2.setRecurrence(new int[2], Appt.RECUR_BY_YEARLY, 2, 2);
      dh.getApptRange(cal1, cal2);
      appt2.setRecurrence(new int[2], Appt.RECUR_BY_WEEKLY, 2, 2);
      dh.getApptRange(cal1, cal2);
  }

}
