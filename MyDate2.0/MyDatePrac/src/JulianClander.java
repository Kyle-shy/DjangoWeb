
/**
 * �������������
 * @author Administrator
 *
 */
public class JulianClander implements LeapYear {

	/* �����������ж�
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
