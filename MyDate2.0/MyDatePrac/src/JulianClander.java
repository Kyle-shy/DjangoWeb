
/**
 * 儒略历闰年计算
 * @author Administrator
 *
 */
public class JulianClander implements LeapYear {

	/* 儒略历闰年判断
	 * @see LeapYear#isLeapYear(int)
	 */
	@Override
	public boolean isLeapYear(int year) {
			if(year % 4 == 0 )
				return true;
			else
				return false;
	}

}
