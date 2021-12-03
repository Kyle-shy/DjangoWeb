
public class Test {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
/**
 * jdk version = 8
 * 按题目顺序测试方法
 */
		MyDate m1 = new MyDate(2147483647 + 1);
		MyDate m2 = new MyDate(-2147483648 - 1);
		MyDate m3 = new MyDate(2021,2,29);//不合法输入
		MyDate m4 = new MyDate(2021,11,13);
		MyDate m5 = new MyDate(2021,11,13);
		boolean b1 = m4.equals(m5);
		MyDate m6 = new MyDate(2021,11,14);
		boolean b2 = m6.lessThan(m5);
		boolean b3 = m6.lessThanOrEquals(m5);
		boolean b4 = m6.greaterThan(m5);
		boolean b5 = m6.greaterThanOrEquals(m6);
		MyDate m7 = m6.add(6);
		MyDate m8 = m6.subtract(-6);
		MyDate m9 = new MyDate(1752,9,2);
		MyDate m10 = new MyDate(1752,9,14);
		int i1 = m9.subtract(m10);
	
//		System.out.println(m1.toString());
//		System.out.println(m2.toString());
//		System.out.println(m3.toString());
//		System.out.println(m4.toString());
//		System.out.println(m5.toString());
//		System.out.println(b1);
//		System.out.println(m6.toString());
//		System.out.println(b2);
//		System.out.println(b3);
//		System.out.println(b4);
//		System.out.println(b5);
//		System.out.println(m7.toString());
//		System.out.println(m8.toString());
//		System.out.println(i1);
//		System.out.println(i2);
		System.out.println(i1);
	
	}

}
