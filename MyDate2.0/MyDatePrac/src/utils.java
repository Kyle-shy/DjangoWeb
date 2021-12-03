
public class utils {
	/**
	 * 计算两年间的闰年个数
	 * @param beginYear
	 * @param endYear
	 * @return
	 */
	public static int leapYearCount(int beginYear, int endYear) {
		//if(beginYear > endYear)
			//System.out.print("注意输入顺序，第一个参数为起始年，应小于第二参数，结果仍返回绝对值");
		int start = beginYear/4 - beginYear/100 + beginYear/400;
		int end	  = endYear/4 - endYear/100 + endYear/400;
		return Math.abs(end - start);
	}
	/**
	 * 判断该年份是否是闰年，是返回true
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