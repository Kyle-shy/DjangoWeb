
public class utils {
	/**
	 * �����������������
	 * @param beginYear
	 * @param endYear
	 * @return
	 */
	public static int leapYearCount(int beginYear, int endYear) {
		//if(beginYear > endYear)
			//System.out.print("ע������˳�򣬵�һ������Ϊ��ʼ�꣬ӦС�ڵڶ�����������Է��ؾ���ֵ");
		int start = beginYear/4 - beginYear/100 + beginYear/400;
		int end	  = endYear/4 - endYear/100 + endYear/400;
		return Math.abs(end - start);
	}
	/**
	 * �жϸ�����Ƿ������꣬�Ƿ���true
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year){
		if((year%4 == 0 && year%100 != 0 )||year%400 == 0)
			return true;
		else
			return false;
	}
	public static boolean isJulianCalendar(int year, int month, int day) {
		if(year <= 1752) {
			if(month <= 9) {
				if(day <= 2)
					return true;
			}
				
		}
			return false;
		
		
	}
}