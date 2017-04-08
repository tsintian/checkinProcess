package test;

import java.util.Calendar;
import java.util.Date;

public class CommonTest {
	
	private static void testCalendar(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		System.out.println(cal);
		System.out.println("year: "+cal.get(Calendar.YEAR));
		System.out.println("month: "+cal.get(Calendar.MONTH));
		System.out.println("day_of_month: "+cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("day_of_week: "+cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("hour_of_day: "+cal.get(Calendar.HOUR_OF_DAY));

	}
	
	
	private static void testSwitch(){
		String level = "city";
		String trueValue;
		switch(level){
		case "poi":
			trueValue = "poi";
			break;
		case "city":
			trueValue = "city";
			break;
		case "contry":
			trueValue = "contry";
			break;
		default:
			System.out.println("default: " + level);
			trueValue="level";
			break;
		
		}
		System.out.print("true value: " + trueValue);
	}
	
	public static void testSubstr(){
		String code="012012";
		System.out.println("large category: " + code.substring(0, 2));
		System.out.println("medium category: " + code.substring(2, 4));
		System.out.println("small category: " + code);
	}

	
	public static void double2String(){
		double d = Double.parseDouble("2708788183");
		System.out.println("string: " + String.format("%.0f", d));
		
	}
	
	public static void testIntegerMax(){
		System.out.println("integer maxValue: "+Integer.MAX_VALUE);
	}
	public static void main(String[] args) {
		//testCalendar();
		//testSwitch();
		//testSubstr();
		//double2String();
		testIntegerMax();
	}

}
