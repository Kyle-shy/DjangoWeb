/**
 * ����������ϵ���㷨
 * @author Administrator
 *
 */
public class CEClander implements LeapYear {

	@Override
	public boolean isLeapYear(int year) {
		// TODO �Զ����ɵķ������
		int year_4 = year % 4;
		int year_100 = year % 100;
		int year_400 = year % 400;
		if((year_4 == 0 && year_100 != 0)||year_400 == 0) {
			return true;
		}
		else
			return false;
	}

}
