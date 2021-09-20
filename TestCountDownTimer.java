package project1;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Test;

public class TestCountDownTimer {

	@Test
	public void testDefaultConstructor() {
		CountDownTimer s = new CountDownTimer();
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
	}

	@Test
	public void testConstructor3Parameters() {
		CountDownTimer s = new CountDownTimer(0, 0, 0);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);

		s = new CountDownTimer(2, 3, 4);
		assertTrue(s.getHours() == 2);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(-2, 3, 4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegHour() {
		new CountDownTimer(-2, 3, 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(2, -3, 4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegMinute() {
		new CountDownTimer(2, -3, 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(2, 3, -4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegSecond() {
		new CountDownTimer(2, 3, -4);
	}

	// Testing for exceptions; testing all 3 at the same time
	@Test
	public void testConstructor3ParametersNegInput() {
		try {
			new CountDownTimer(-2, 3, 4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}

		try {
			new CountDownTimer(2, -3, 4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}

		try {
			new CountDownTimer(2, 3, -4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

	// Testing for an exception; no lines of code after
	// "new CountDownTimer(12,67,14);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3LargeMinute() {
		new CountDownTimer(12, 60, 14);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3LargeSecond() {
		new CountDownTimer(12, 59, 60);
	}

	// Testing for an exception; no lines of code after
	// "new CountDownTimer("a");" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString1ParameterAlpha() {
		new CountDownTimer("a");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringLarge() {
		new CountDownTimer("1:23:45:678");
	}

	@Test
	public void testAdd1() {
		CountDownTimer s = new CountDownTimer();

		s.add(1);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 1);
	}

	@Test
	public void testAdd10() {
		CountDownTimer s = new CountDownTimer();

		s.add(10);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 10);
	}

	@Test
	public void testAdd59() {
		CountDownTimer s = new CountDownTimer();

		s.add(59);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testAdd60() {
		CountDownTimer s = new CountDownTimer();

		// inc to 1 min
		s.add(60);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 1);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testAddNeg() {
		CountDownTimer s = new CountDownTimer();

		try{
			s.add(-10);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
			assertEquals(s.getHours(), 0);
			assertEquals(s.getMinutes(), 0);
			assertEquals(s.getSeconds(), 0);
		}

	}


	@Test
	public void testDec1Second() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// dec 1
		s.dec();
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 58);
	}

	@Test
	public void testEquals() {
		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
		CountDownTimer s2 = new CountDownTimer(6, 01, 20);
		CountDownTimer s3 = new CountDownTimer(5, 59, 30);
		CountDownTimer s4 = new CountDownTimer(5, 59, 20);
		CountDownTimer s5 = new CountDownTimer(5, 40, 30);
		CountDownTimer s6 = new CountDownTimer(4, 59, 30);
		CountDownTimer s7 = new CountDownTimer(5, 40, 20);
		CountDownTimer s8 = new CountDownTimer(4, 59, 20);
		CountDownTimer s9 = new CountDownTimer(4, 40, 30);

		assertFalse(s1.equals(s2));
		assertTrue(s1.equals(s3));
		assertEquals(s3, s1);
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		assertFalse(s1.equals(s9));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test (expected = IllegalArgumentException.class)
	public void testEqualsInt() {
		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
		int s2 = 10;
		s1.equals(s2);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test (expected = IllegalArgumentException.class)
	public void testEqualsDouble() {
		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
		double s2 = 1.20;
		s1.equals(s2);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test (expected = IllegalArgumentException.class)
	public void testEqualsString() {
		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
		String s2 = "1:2:3";
		s1.equals(s2);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test (expected = IllegalArgumentException.class)
	public void testEqualsBoolean() {
		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
		boolean s2 = false;
		s1.equals(s2);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testEqualsNull() {
		CountDownTimer s = new CountDownTimer();
		s.equals(null);
	}

	@Test 
	public void testEqualsNull2() {
		CountDownTimer s = new CountDownTimer();
		try {
			s.equals(null);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
			assertEquals(s.getHours(), 0);
			assertEquals(s.getMinutes(), 0);
			assertEquals(s.getSeconds(), 0);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetHours() {
		CountDownTimer s = new CountDownTimer();
		s.setHours(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMinutes() {
		CountDownTimer s = new CountDownTimer();
		s.setMinutes(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSeconds() {
		CountDownTimer s = new CountDownTimer();
		s.setSeconds(-1);
	}

	@Test
	public void testConstructor2Parameters() {
		CountDownTimer s = new CountDownTimer(0, 0);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);

		s = new CountDownTimer(3, 4);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 4);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersNegMinute() {
		new CountDownTimer(-3, 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(3, -4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersNegSecond() {
		new CountDownTimer(3, -4);
	}

	@Test
	public void testConstructor1Parameter() {
		CountDownTimer s = new CountDownTimer(0);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);

		s = new CountDownTimer(4);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(3, -4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor1ParameterNegSecond() {
		new CountDownTimer(-4);
	}

	@Test
	public void testConstructorOther() {
		CountDownTimer s = new CountDownTimer(0, 0, 0);
		CountDownTimer sCopy = new CountDownTimer(s);
		assertTrue(sCopy.getHours() == 0);
		assertTrue(sCopy.getMinutes() == 0);
		assertTrue(sCopy.getSeconds() == 0);

		s = new CountDownTimer(2, 3, 4);
		sCopy = new CountDownTimer(s);
		assertTrue(sCopy.getHours() == 2);
		assertTrue(sCopy.getMinutes() == 3);
		assertTrue(sCopy.getSeconds() == 4);
	}

	@Test
	public void testConstructorString() {
		CountDownTimer s = new CountDownTimer("1:2:3");
		assertTrue(s.getHours() == 1);
		assertTrue(s.getMinutes() == 2);
		assertTrue(s.getSeconds() == 3);

		s = new CountDownTimer("13:14");
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 13);
		assertTrue(s.getSeconds() == 14);

		s = new CountDownTimer("5");
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 5);
	}

	@Test
	public void testTwoTimersEquals() {
		CountDownTimer s1 = new CountDownTimer(1, 2, 3);
		CountDownTimer s2 = new CountDownTimer(1, 2, 3);
		assertTrue(CountDownTimer.equals(s1, s2));

		s1 = new CountDownTimer(1, 2, 3);
		s2 = new CountDownTimer(1, 2, 4);
		assertFalse(CountDownTimer.equals(s1, s2));

		s1 = new CountDownTimer(1, 2, 3);
		s2 = new CountDownTimer(1, 3, 4);
		assertFalse(CountDownTimer.equals(s1, s2));

		s1 = new CountDownTimer(1, 2, 3);
		s2 = new CountDownTimer(2, 3, 4);
		assertFalse(CountDownTimer.equals(s1, s2));
	}

	@Test
	public void testCompareToOther() {
		CountDownTimer s1 = new CountDownTimer(1, 2, 3);
		CountDownTimer s2 = new CountDownTimer(1, 2, 3);
		assertEquals(0, s1.compareTo(s2));

		s1 = new CountDownTimer(1, 2, 4);
		s2 = new CountDownTimer(1, 2, 3);
		assertEquals(1, s1.compareTo(s2));

		s1 = new CountDownTimer(1, 2, 3);
		s2 = new CountDownTimer(1, 2, 4);
		assertEquals(-1, s1.compareTo(s2));
	}

	@Test
	public void testCompareTwoTimers() {
		CountDownTimer s1 = new CountDownTimer(1, 2, 3);
		CountDownTimer s2 = new CountDownTimer(1, 2, 3);
		assertEquals(0, CountDownTimer.compareTo(s1, s2));

		s1 = new CountDownTimer(1, 2, 4);
		s2 = new CountDownTimer(1, 2, 3);
		assertEquals(1, CountDownTimer.compareTo(s1, s2));

		s1 = new CountDownTimer(1, 2, 3);
		s2 = new CountDownTimer(1, 2, 4);
		assertEquals(-1, CountDownTimer.compareTo(s1, s2));
	}

	@Test
	public void testSubSeconds() {
		CountDownTimer s = new CountDownTimer(1, 2, 3);
		s.sub(2);
		assertTrue(s.getHours() == 1);
		assertTrue(s.getMinutes() == 2);
		assertTrue(s.getSeconds() == 1);

		s = new CountDownTimer(1, 2, 3);
		s.sub(50);
		assertTrue(s.getHours() == 1);
		assertTrue(s.getMinutes() == 1);
		assertTrue(s.getSeconds() == 13);
	}

	@Test
	public void testSubOtherTimer() {
		CountDownTimer s1 = new CountDownTimer(6, 5, 4);
		CountDownTimer s2 = new CountDownTimer(1, 2, 3);
		s1.sub(s2);
		assertTrue(s1.getHours() == 5);
		assertTrue(s1.getMinutes() == 3);
		assertTrue(s1.getSeconds() == 1);

	}

	@Test (expected = IllegalArgumentException.class)
	public void testSubOtherTimerLarger() {
		CountDownTimer s1 = new CountDownTimer(1, 2, 3);
		CountDownTimer s2 = new CountDownTimer(6, 5, 4);
		s1.sub(s2);		
	}

	@Test
	public void testInc() {
		CountDownTimer s = new CountDownTimer(1, 2, 3);
		s.inc();
		assertTrue(s.getHours() == 1);
		assertTrue(s.getMinutes() == 2);
		assertTrue(s.getSeconds() == 4);
	}

	@Test
	public void testAddOtherTimer() {
		CountDownTimer s1 = new CountDownTimer(1, 2, 3);
		CountDownTimer s2 = new CountDownTimer(6, 5, 4);
		s1.add(s2);	
		assertTrue(s1.getHours() == 7);
		assertTrue(s1.getMinutes() == 7);
		assertTrue(s1.getSeconds() == 7);
	}

	@Test
	public void testToString() {
		CountDownTimer s = new CountDownTimer(1, 2, 3);
		assertEquals("1:02:03", s.toString());

		s = new CountDownTimer(1, 2, 13);
		assertEquals("1:02:13", s.toString());

		s = new CountDownTimer(1, 12, 13);
		assertEquals("1:12:13", s.toString());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetHoursOver24() {
		CountDownTimer s = new CountDownTimer();
		s.setHours(30);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetMinutesOve60() {
		CountDownTimer s = new CountDownTimer();
		s.setMinutes(80);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetSecondsOve60() {
		CountDownTimer s = new CountDownTimer();
		s.setSeconds(80);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersHoursOver24() {
		new CountDownTimer(30, 3, 4);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersMinutesOver60() {
		new CountDownTimer(1, 80, 4);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersSecondsOver60() {
		new CountDownTimer(1, 2, 80);
	}


	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersMinutesOver60() {
		new CountDownTimer(80, 4);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersSecondsOver60() {
		new CountDownTimer(2, 80);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor1ParameterSecondsOver60() {
		new CountDownTimer(80);
	}




	@Test
	public void testSaveLoad() {
		CountDownTimer s1 = new CountDownTimer(1,2,3);
		CountDownTimer s2 = new CountDownTimer(4,5,6);
		s2.save("testFile");
		s1.load("testFile");
		assertTrue(s1.getHours() == 4);
		assertTrue(s1.getMinutes() == 5);
		assertTrue(s1.getSeconds() == 6);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSaveBadFileName() {
		CountDownTimer s1 = new CountDownTimer(1,2,3);
		s1.save("test/File");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testLoadBadFileName() {
		CountDownTimer s1 = new CountDownTimer(1,2,3);
		s1.load("test/File");
	}

	@Test
	public void testSetSuspend() {
		CountDownTimer s = new CountDownTimer();
		CountDownTimer.setSuspend(true);
		s.setHours(1);
		s.setMinutes(2);
		s.setSeconds(3);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
		CountDownTimer.setSuspend(false);
	}

	@Test
	public void testDecSuspend() {
		CountDownTimer s = new CountDownTimer();
		CountDownTimer.setSuspend(true);
		s.dec();
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
		CountDownTimer.setSuspend(false);
	}

	@Test
	public void testSubSecondsSuspend() {
		CountDownTimer s1 = new CountDownTimer(0,1,0);
		CountDownTimer.setSuspend(true);
		s1.sub(30);
		assertTrue(s1.getHours() == 0);
		assertTrue(s1.getMinutes() == 1);
		assertTrue(s1.getSeconds() == 0);
		CountDownTimer.setSuspend(false);
	}

	@Test
	public void testSubOtherSuspend() {
		CountDownTimer s1 = new CountDownTimer(0,1,0);
		CountDownTimer s2 = new CountDownTimer(0,0,30);
		CountDownTimer.setSuspend(true);
		s1.sub(s2);
		assertTrue(s1.getHours() == 0);
		assertTrue(s1.getMinutes() == 1);
		assertTrue(s1.getSeconds() == 0);
		CountDownTimer.setSuspend(false);
	}

	@Test
	public void testIncSuspend() {
		CountDownTimer s = new CountDownTimer();
		CountDownTimer.setSuspend(true);
		s.inc();
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
		CountDownTimer.setSuspend(false);
	}

	@Test
	public void testAddSecondsSuspend() {
		CountDownTimer s1 = new CountDownTimer();
		CountDownTimer.setSuspend(true);
		s1.add(30);
		assertTrue(s1.getHours() == 0);
		assertTrue(s1.getMinutes() == 0);
		assertTrue(s1.getSeconds() == 0);
		CountDownTimer.setSuspend(false);
	}

	@Test
	public void testAddOtherSuspend() {
		CountDownTimer s1 = new CountDownTimer();
		CountDownTimer s2 = new CountDownTimer(1,2,3);
		CountDownTimer.setSuspend(true);
		s1.add(s2);
		assertTrue(s1.getHours() == 0);
		assertTrue(s1.getMinutes() == 0);
		assertTrue(s1.getSeconds() == 0);
		CountDownTimer.setSuspend(false);
	}

	@Test
	public void testIsSuspended() {
		CountDownTimer.setSuspend(true);
		assertTrue(CountDownTimer.isSuspended());
		CountDownTimer.setSuspend(false);
		assertFalse(CountDownTimer.isSuspended());
	}

}