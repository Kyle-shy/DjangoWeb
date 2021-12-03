/**
 * 
 * @author zzl
 *
 */
public final class MyDate {
	private final int YEAR;
	private final int MONTH;
	private final int DAY;
	private final int HOUR;
	private final int MINUTE;
	private final int SECOND;
	private final int DAYS;
	private final int LEAP_YEAR_MONTH_DAY[] = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private final int NORMAL_YEAR_MONTH_DAY[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	/**
	 * 构造函数 参数：秒数（可为负） 整数转换为日期
	 */
	public MyDate(long elpasedSecond) {
		this.DAYS = (int) (elpasedSecond / (24 * 60 * 60));
		int MYEAR = 1970;
		int MMONTH = 1;
		int MDAY = 1;
		int MHOUR = 0;
		int MMINUTE = 0;
		int MSECOND = 0;
		LeapYear julianclander = new JulianClander();
		LeapYear CEclander = new CEClander();
		/**
		 * 输入为正的情况
		 */
			if(elpasedSecond>0) {
			long elpasedDay = elpasedSecond / (60 * 60 * 24);
			long leftSecond = elpasedSecond % (60 * 60 * 24);
			// 比一年多的
				int leapYearCount = utils.leapYearCount(MYEAR, (int) (MYEAR + (elpasedDay / 365))); // 闰年个数，也即需要减去的天数
				MYEAR = (int) (MYEAR + (elpasedDay / 365));
				elpasedDay = elpasedDay % 365;
				if (utils.isLeapYear(MYEAR)) {
					// 当前年 闰年
					for (int i = 1; i <= 12 && elpasedDay > LEAP_YEAR_MONTH_DAY[i] - 1; i++) {
						elpasedDay = elpasedDay - LEAP_YEAR_MONTH_DAY[i];
						MMONTH++;
					}
					MDAY = (int) (MDAY + elpasedDay);
					// 往回退少算闰年的天数 leapYearCount*1
					for (; leapYearCount >= MDAY;) {
						leapYearCount = leapYearCount - MDAY;
						if (MMONTH == 1) {
							MMONTH = 12;
							MYEAR--;
						} else
							MMONTH--;
						if (utils.isLeapYear(MYEAR))
							MDAY = LEAP_YEAR_MONTH_DAY[MMONTH];
						else
							MDAY = NORMAL_YEAR_MONTH_DAY[MMONTH];
					}
					MDAY = MDAY - leapYearCount;
					// 计算时分秒
					MHOUR = (int) (MHOUR + leftSecond / (60 * 60));
					leftSecond = leftSecond % (60 * 60);
					MMINUTE = (int) (MMINUTE + leftSecond / 60);
					leftSecond = leftSecond % 60;
					MSECOND = (int) (MSECOND + leftSecond);
				} else {
					// 当前年平年
					for (int i = 1; i <= 12 && elpasedDay > NORMAL_YEAR_MONTH_DAY[i] - 1; i++) {
						elpasedDay = elpasedDay - NORMAL_YEAR_MONTH_DAY[i];
						MMONTH++;
					}
					MDAY = (int) (MDAY + elpasedDay);
					// 往回退少算闰年的天数 leapYearCount*1
					for (; leapYearCount >= MDAY;) {
						leapYearCount = leapYearCount - MDAY;
						if (MMONTH == 1) {
							MMONTH = 12;
							MYEAR--;
						} else
							MMONTH--;
						if (utils.isLeapYear(MYEAR))
							MDAY = LEAP_YEAR_MONTH_DAY[MMONTH];
						else
							MDAY = NORMAL_YEAR_MONTH_DAY[MMONTH];
					}
					MDAY = MDAY - leapYearCount;
					// 计算时分秒
					MHOUR = (int) (MHOUR + leftSecond / (60 * 60));
					leftSecond = leftSecond % (60 * 60);
					MMINUTE = (int) (MMINUTE + leftSecond / 60);
					leftSecond = leftSecond % 60;
					MSECOND = (int) (MSECOND + leftSecond);
				}

			
			}
		
		/**
		 * 输入为负的情况
		 * v2.0 ：考虑1752儒略历情况
		 */
		if (elpasedSecond < 0) {
			elpasedSecond = Math.abs(elpasedSecond);// 取绝对值
			long elpasedDay = elpasedSecond / (60 * 60 * 24);
			long leftSecond = elpasedSecond % (60 * 60 * 24);
				int leapYearCount = utils.leapYearCount((int) (MYEAR - (elpasedDay / 365)), MYEAR-1); // 闰年个数，也即需要减去的天数
				MYEAR = (int) (MYEAR - (elpasedDay / 365));
				elpasedDay = elpasedDay % 365;
				for (; elpasedDay >= MDAY;) {
					elpasedDay = elpasedDay - MDAY;
					if (MMONTH == 1) {
						MMONTH = 12;
						MYEAR--;
					} 
					else
						MMONTH--;
					if (utils.isLeapYear(MYEAR))
						MDAY = LEAP_YEAR_MONTH_DAY[MMONTH];
					else
						MDAY = NORMAL_YEAR_MONTH_DAY[MMONTH];
				}
				if (utils.isLeapYear(MYEAR)) {
					// 当前年 闰年
					MDAY = (int) (MDAY - elpasedDay);
					// 往后加少算闰年的天数 leapYearCount*1
					int monthday = LEAP_YEAR_MONTH_DAY[MMONTH];
					for (; leapYearCount + MDAY > monthday ;) {
						leapYearCount -= (monthday - MDAY+1);
						if (MMONTH == 12) {
							MMONTH = 1;
							MYEAR++;
						}
						else
							MMONTH++;
						if (utils.isLeapYear(MYEAR))
							monthday = LEAP_YEAR_MONTH_DAY[MMONTH];
						else {
							monthday = NORMAL_YEAR_MONTH_DAY[MMONTH];
						}
						MDAY = 1;
					}
					MDAY = MDAY + leapYearCount;
					if (leftSecond > 0)
						MDAY--;
					if (leftSecond > 0)
						MDAY--;
					if(MDAY == 0 ) {
						if(MMONTH == 1) {
							MMONTH = 12;
							MYEAR-- ; 
						}
						else
							MMONTH --;
						if(utils.isLeapYear(MYEAR))
							MDAY =  LEAP_YEAR_MONTH_DAY[MMONTH];
						else
							MDAY =  NORMAL_YEAR_MONTH_DAY[MMONTH];
					}
					// 时分秒
					int dec_hour = (int) (leftSecond / (60 * 60));
					leftSecond = leftSecond % (60*60);
					int dec_min = (int) (leftSecond / 60);
					leftSecond = leftSecond % 60;
					int dec_sec = (int) leftSecond;
					if (dec_sec > 0) {
						MSECOND = 60 - dec_sec;
						MMINUTE = 60 - 1;
						MHOUR = 24 - 1;
					}
					if (dec_min > 0 && dec_sec == 0)
						MMINUTE = 60 - dec_min;
					MMINUTE = MMINUTE - dec_min;
					if (dec_hour > 0 && dec_sec == 0)
						MHOUR = 24 - dec_hour;
					MHOUR = MHOUR - dec_hour;
				}

				else {
					// 当前年平年

					MDAY = (int) (MDAY - elpasedDay);
					// 往后加少算闰年的天数 leapYearCount*1
					int monthday = NORMAL_YEAR_MONTH_DAY[MMONTH];
					for (; leapYearCount + MDAY > monthday ;) {
						leapYearCount -= (monthday - MDAY+1);
						if (MMONTH == 12) {
							MMONTH = 1;
							MYEAR++;
						}
						else
							MMONTH++;
						if (utils.isLeapYear(MYEAR))
							monthday = LEAP_YEAR_MONTH_DAY[MMONTH];
						else {
							monthday = NORMAL_YEAR_MONTH_DAY[MMONTH];
						}
						MDAY = 1;
					}
					MDAY = MDAY + leapYearCount;
					if (leftSecond > 0)
						MDAY--;
					if(MDAY == 0 ) {
						if(MMONTH == 1) {
							MMONTH = 12;
							MYEAR-- ; 
						}
						else
							MMONTH --;
						if(utils.isLeapYear(MYEAR))
							MDAY =  LEAP_YEAR_MONTH_DAY[MMONTH];
						else
							MDAY =  NORMAL_YEAR_MONTH_DAY[MMONTH];
					}
					// 时分秒
					int dec_hour = (int) (leftSecond / (60 * 60));
					leftSecond = leftSecond % (60*60);
					int dec_min = (int) (leftSecond / 60);
					leftSecond = leftSecond % 60;
					int dec_sec = (int) leftSecond;
					if (dec_sec > 0) {
						MSECOND = 60 - dec_sec;
						MMINUTE = 60 - 1;
						MHOUR = 24 - 1;
					}
					if (dec_min > 0 && dec_sec == 0)
						MMINUTE = 60 - dec_min;
					else
						MMINUTE = MMINUTE - dec_min;
					if (dec_hour > 0 && dec_sec == 0)
						MHOUR = 24 - dec_hour;
					else 
						MHOUR = MHOUR - dec_hour;

				}
				if(utils.isJulianCalendar(MYEAR, MMONTH, MDAY)) {
					int gapClanderDay = 13 - 2;
					int gapLeapYear = (1752/100 - 1752/400) - (MYEAR/100 - MYEAR/400);
					int gapDay = gapLeapYear +gapClanderDay;
					if(MMONTH==9 && MDAY == 2) {
								gapDay--;
								MDAY = 14;
							}
					if(julianclander.isLeapYear(MYEAR)) {
						if(MDAY + gapDay >LEAP_YEAR_MONTH_DAY[MMONTH]) {
							gapDay = gapDay - (LEAP_YEAR_MONTH_DAY[MMONTH] - MDAY - 1);
							MMONTH++;
							MDAY = 1;
							if(MMONTH==13)
							{
								MMONTH = 1;
								MYEAR++;
							}
						}
						MDAY = MDAY + gapDay;
				}
					else {
						if(MDAY + gapDay >NORMAL_YEAR_MONTH_DAY[MMONTH]) {
							gapDay = gapDay - (NORMAL_YEAR_MONTH_DAY[MMONTH] - MDAY - 1);
							MMONTH++;
							MDAY = 1;
							if(MMONTH==13)
							{
								MMONTH = 1;
								MYEAR++;
							}
						}
						MDAY = MDAY + gapDay;
					}
			
		}
		/**
		 * ISO标准 公园前一年为0 前两年为-1
		 */
		if(MYEAR <= 0)
			MYEAR -= 1;
		/**
		 * 支持公元前10000年到公元9999年，超出范围以1970-01-01存储
		 * 
		 */
		if(MYEAR<-9999 || MYEAR>9999) {
			MYEAR = 1970;
			MMONTH = 1;
			MDAY = 1;
			MHOUR = 0;
			MMINUTE = 0;
			MSECOND = 0;
		}
		}
		//initliza	
		this.YEAR = MYEAR;
		this.MONTH = MMONTH;
		this.DAY = MDAY;
		this.HOUR = MHOUR;
		this.MINUTE = MMINUTE;
		this.SECOND = MSECOND;
		
	
	}

/**
 * 构造函数日月天
 * @param yEAR
 * @param mONTH
 * @param dAY
 * V2.0 -9999 至 9999
 */
	public MyDate(int yEAR, int mONTH, int dAY) {
		if(utils.isLeapYear(yEAR)) {
			if(yEAR>9999 || yEAR<-9999 || mONTH<1 || mONTH>12||
					dAY<1 || dAY > LEAP_YEAR_MONTH_DAY[mONTH]) {
				yEAR = 1970;
				mONTH = 01;
				dAY = 01;
			}
				
		}
		else
			if(yEAR>9999 || yEAR<-9999 || mONTH<1 || mONTH>12||
					dAY<1 || dAY > NORMAL_YEAR_MONTH_DAY[mONTH]) {
				yEAR = 1970;
				mONTH = 01;
				dAY = 01;
			}
		if(yEAR == 1752 && mONTH==9 && (dAY>2&&dAY<14)) {
			//历法切换 无此日期 以默认存储
			yEAR = 1970;
			mONTH = 01;
			dAY = 01;
		}
		YEAR = yEAR;
		MONTH = mONTH;
		DAY = dAY;
		HOUR = 0;
		MINUTE = 0;
		SECOND = 0;
		DAYS = 0;
	}

	@Override
	public String toString() {
		return this.YEAR + "-" + this.MONTH + "-" + this.DAY + " "+this.HOUR + ":"+this.MINUTE + ":"
				+ this.SECOND;
	}
	/**
	 * 比较两对象，年月日相同返回true
	 * @param m1
	 * @return
	 */
	public boolean equals(MyDate m1) {
		if(this.YEAR == m1.YEAR && this.MONTH == m1.MONTH && this.DAY == m1.DAY)
			return true;
		else
			return false;
	}
	/**
	 * 当此对象早于形参日期时 返回true
	 * @param m1
	 * @return
	 */
	public boolean lessThan(MyDate m1) {
		if(m1.YEAR > this.YEAR) {
			return true;
		}
		else if(m1.YEAR < this.YEAR) {
			return false;
		}
		else if(m1.MONTH > this.MONTH) {
			return true;
		}
		else if(m1.MONTH < this.MONTH) {
			return false;
		}
		else if(m1.DAY > this.DAY) {
			return true;
		}
		else if(m1.DAY < this.DAY) {
			return false;
		}
		else
			return false;
	}
	/**
	 * 当此对象早于或等于形参日期时 返回true
	 * @param m1
	 * @return
	 */
	public boolean lessThanOrEquals(MyDate m1) {
		if(m1.YEAR > this.YEAR) {
			return true;
		}
		else if(m1.YEAR < this.YEAR) {
			return false;
		}
		else if(m1.MONTH > this.MONTH) {
			return true;
		}
		else if(m1.MONTH < this.MONTH) {
			return false;
		}
		else if(m1.DAY > this.DAY) {
			return true;
		}
		else if(m1.DAY < this.DAY) {
			return false;
		}
		else
			return true;
	}
	/**
	 * 当对象晚于形参日期时  返回true
	 * @param m1
	 * @return
	 */
	public boolean greaterThan(MyDate m1) {
		if(m1.YEAR < this.YEAR) {
			return true;
		}
		else if(m1.YEAR > this.YEAR) {
			return false;
		}
		else if(m1.MONTH < this.MONTH) {
			return true;
		}
		else if(m1.MONTH > this.MONTH) {
			return false;
		}
		else if(m1.DAY < this.DAY) {
			return true;
		}
		else if(m1.DAY > this.DAY) {
			return false;
		}
		else
			return false;
	}
	/**
	 * 当对象晚于或等于形参日期时  返回true
	 * @param m1
	 * @return
	 */
	public boolean greaterThanOrEquals(MyDate m1) {
		if(m1.YEAR < this.YEAR) {
			return true;
		}
		else if(m1.YEAR > this.YEAR) {
			return false;
		}
		else if(m1.MONTH < this.MONTH) {
			return true;
		}
		else if(m1.MONTH > this.MONTH) {
			return false;
		}
		else if(m1.DAY < this.DAY) {
			return true;
		}
		else if(m1.DAY > this.DAY) {
			return false;
		}
		else
			return true;}
		/**
		 * 返回对象增加相应日期的天数
		 */
	public MyDate add(int elpasedDay) {
			if(elpasedDay<0) {
				System.out.print("输入天数应为正数");
				return this;	
			}	
			int MYEAR = this.YEAR;
			int MMONTH = this.MONTH;
			int MDAY = this.DAY;
			int MHOUR = this.HOUR;
			int MMINUTE = this.MINUTE;
			int MSECOND = this.SECOND;
			boolean isjulian = utils.isJulianCalendar(MYEAR, MMONTH, MDAY);
			int leapYearCount = utils.leapYearCount(MYEAR, (int) (MYEAR + (elpasedDay / 365))); // 闰年个数，也即需要减去的天数
			MYEAR = (int) (MYEAR + (elpasedDay / 365));
			elpasedDay = elpasedDay % 365;
			if (utils.isLeapYear(MYEAR)) {
				// 当前年 闰年
				for (int i = 1; i <= 12 && elpasedDay > LEAP_YEAR_MONTH_DAY[i] - 1; i++) {
					elpasedDay = elpasedDay - LEAP_YEAR_MONTH_DAY[i];
					MMONTH++;
				}
				MDAY = (int) (MDAY + elpasedDay);
				// 往回退少算闰年的天数 leapYearCount*1
				for (; leapYearCount >= MDAY;) {
					leapYearCount = leapYearCount - MDAY;
					if (MMONTH == 1) {
						MMONTH = 12;
						MYEAR--;
					} else
						MMONTH--;
					if (utils.isLeapYear(MYEAR))
						MDAY = LEAP_YEAR_MONTH_DAY[MMONTH];
					else
						MDAY = NORMAL_YEAR_MONTH_DAY[MMONTH];
				}
				MDAY = MDAY - leapYearCount;
			} else {
				// 当前年平年
				for (int i = 1; i <= 12 && elpasedDay > NORMAL_YEAR_MONTH_DAY[i] - 1; i++) {
					elpasedDay = elpasedDay - NORMAL_YEAR_MONTH_DAY[i];
					MMONTH++;
				}
				MDAY = (int) (MDAY + elpasedDay);
				// 往回退少算闰年的天数 leapYearCount*1
				for (; leapYearCount >= MDAY;) {
					leapYearCount = leapYearCount - MDAY;
					if (MMONTH == 1) {
						MMONTH = 12;
						MYEAR--;
					} else
						MMONTH--;
					if (utils.isLeapYear(MYEAR))
						MDAY = LEAP_YEAR_MONTH_DAY[MMONTH];
					else
						MDAY = NORMAL_YEAR_MONTH_DAY[MMONTH];
				}
				MDAY = MDAY - leapYearCount;
			}
			if(utils.isJulianCalendar(MYEAR, MYEAR, MDAY)) {
				MyDate m1 = new MyDate(MYEAR,MMONTH,MDAY);
			return m1;
			}
			else {
				//减去历法切换的天数损失
				if (utils.isLeapYear(MYEAR)) {
					if(MDAY + 11 > LEAP_YEAR_MONTH_DAY[MMONTH]) {
						MDAY = MDAY + 11 - LEAP_YEAR_MONTH_DAY[MMONTH];
						MMONTH++;
						if(MMONTH == 13) {
							MMONTH = 1;
							MYEAR++;
						}
					}
					MDAY = MDAY + 11 ;
					
				}
				else {
					if(MDAY + 11 > NORMAL_YEAR_MONTH_DAY[MMONTH]) {
						MDAY = MDAY + 11 - NORMAL_YEAR_MONTH_DAY[MMONTH];
						MMONTH++;
						if(MMONTH == 13) {
							MMONTH = 1;
							MYEAR++;
						}
					}
						
				}
			}
			
			MyDate m1 = new MyDate(MYEAR,MMONTH,MDAY);
			return m1;	
		}
	/**
	 * 返回对象减少相应天数的日期
	 * @param elapsedDay
	 * @return
	 */
	public MyDate subtract(int elpasedDay) {
		if(elpasedDay>0) {
			System.out.print("输入天数应为负数");
			return this;	
		}
		elpasedDay = Math.abs(elpasedDay);
		int MYEAR = this.YEAR;
		int MMONTH = this.MONTH;
		int MDAY = this.DAY;
		int MHOUR = this.HOUR;
		int MMINUTE = this.MINUTE;
		int MSECOND = this.SECOND;
		boolean isjulian = utils.isJulianCalendar(MYEAR, MMONTH, MDAY);
		int leapYearCount = utils.leapYearCount((int) (MYEAR - (elpasedDay / 365)), MYEAR-1); // 闰年个数，也即需要减去的天数
		MYEAR = (int) (MYEAR - (elpasedDay / 365));
		elpasedDay = elpasedDay % 365;
		for (; elpasedDay >= MDAY;) {
			elpasedDay = elpasedDay - MDAY;
			if (MMONTH == 1) {
				MMONTH = 12;
				MYEAR--;
			} 
			else
				MMONTH--;
			if (utils.isLeapYear(MYEAR))
				MDAY = LEAP_YEAR_MONTH_DAY[MMONTH];
			else
				MDAY = NORMAL_YEAR_MONTH_DAY[MMONTH];
		}
		if (utils.isLeapYear(MYEAR)) {
			// 当前年 闰年
			MDAY = (int) (MDAY - elpasedDay);
			// 往后加少算闰年的天数 leapYearCount*1
			int monthday = LEAP_YEAR_MONTH_DAY[MMONTH];
			for (; leapYearCount + MDAY > monthday ;) {
				leapYearCount -= (monthday - MDAY+1);
				if (MMONTH == 12) {
					MMONTH = 1;
					MYEAR++;
				}
				else
					MMONTH++;
				if (utils.isLeapYear(MYEAR))
					monthday = LEAP_YEAR_MONTH_DAY[MMONTH];
				else {
					monthday = NORMAL_YEAR_MONTH_DAY[MMONTH];
				}
				MDAY = 1;
			}
			MDAY = MDAY + leapYearCount;
			
		}

		else {
			// 当前年平年

			MDAY = (int) (MDAY - elpasedDay);
			// 往后加少算闰年的天数 leapYearCount*1
			int monthday = NORMAL_YEAR_MONTH_DAY[MMONTH];
			for (; leapYearCount + MDAY > monthday ;) {
				leapYearCount -= (monthday - MDAY+1);
				if (MMONTH == 12) {
					MMONTH = 1;
					MYEAR++;
				}
				else
					MMONTH++;
				if (utils.isLeapYear(MYEAR))
					monthday = LEAP_YEAR_MONTH_DAY[MMONTH];
				else {
					monthday = NORMAL_YEAR_MONTH_DAY[MMONTH];
				}
				MDAY = 1;
			}
			MDAY = MDAY + leapYearCount;
			

		}
		/**
		 * ISO标准 公园前一年为0 前两年为-1
		 */
		if(MYEAR <= 0)
			MYEAR -= 1;
		//历法切换
		if(!isjulian && utils.isJulianCalendar(MYEAR, MMONTH, MDAY)) {
			
			if(MDAY -11 > 0 )MDAY = MDAY -11;
			else {
				MMONTH--;
				if(MMONTH == 0) {
					MMONTH=1;
					MYEAR--;
				}
				MDAY = 31 - (11-MDAY);
			}
		}
		MyDate m1 = new MyDate(MYEAR,MMONTH,MDAY);
		return m1;
	}
	/**
	 * 返回对象与形参间的距离 对象比形参晚时返回正数
	 * @param m1
	 * @return
	 */
	public int subtract(MyDate m1) {	
		boolean thisjulian = utils.isJulianCalendar(this.YEAR, this.MONTH,this.DAY);
		boolean m1julian = utils.isJulianCalendar(m1.YEAR, m1.MONTH,m1.DAY);
		int yearCount = m1.YEAR - this.YEAR;
		int dayCount = 0;
		if(m1.MONTH > this.MONTH) {
			 dayCount = m1.DAY;
			for(int i=m1.MONTH - 1;i>this.MONTH;i--) {
				if(utils.isLeapYear(m1.YEAR))
					dayCount = dayCount + LEAP_YEAR_MONTH_DAY[i];
				else
					dayCount = dayCount + NORMAL_YEAR_MONTH_DAY[i];
			}
			if(utils.isLeapYear(m1.YEAR))
				dayCount = dayCount + LEAP_YEAR_MONTH_DAY[this.MONTH] - this.DAYS;
			else
				dayCount = dayCount + NORMAL_YEAR_MONTH_DAY[this.MONTH] - this.DAYS;
			
		}
		if(m1.MONTH < this.MONTH) {
			dayCount = this.DAY;
			for(int i=this.MONTH - 1;i>m1.MONTH;i--) {
				if(utils.isLeapYear(m1.YEAR))
					dayCount = dayCount + LEAP_YEAR_MONTH_DAY[i];
				else
					dayCount = dayCount + NORMAL_YEAR_MONTH_DAY[i];
			}
			if(utils.isLeapYear(m1.YEAR))
				dayCount = dayCount + LEAP_YEAR_MONTH_DAY[m1.MONTH] - m1.DAYS;
			else
				dayCount = dayCount + NORMAL_YEAR_MONTH_DAY[m1.MONTH] - m1.DAYS;
			
		}
		if(m1.MONTH == this.MONTH)
			dayCount = m1.DAY - this.DAY;
		if(yearCount > 0)
			dayCount += yearCount*365 - (utils.leapYearCount(this.YEAR, m1.YEAR));
		else
			dayCount += -(yearCount*365) + (utils.leapYearCount(this.YEAR, m1.YEAR));
		
		if(thisjulian && !m1julian)
			dayCount -= 11;
		if(!thisjulian && m1julian)
			dayCount += 11;
		return dayCount;
	}
	/**
	 * 返回当前对象的星期数
	 * @return
	 */
	public int getWeekday() {
		/**
		 * 基姆拉尔森计算公式

			W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
			
			在公式中d表示日期中的日数，m表示月份数，y表示年数。
			
			注意：在公式中有个与其他公式不同的地方：
			
			把一月和二月看成是上一年的十三月和十四月，例：如果是2004-1-10则换算成：2003-13-10来代入公式计算。
		 */
		int d = this.DAY;
		int y = 0;
		int m = 0;
		if(this.MONTH == 1) {
			m = 13;
			y = this.YEAR - 1;
			if(this.YEAR - 1 <= 0)
				y--;
		}
		if(this.MONTH == 2) {
			m = 14;
			y = this.YEAR - 1;
			if(this.YEAR - 1 <= 0)
				y--;
		}
		
		return (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400)%7 ;
	}
	}



