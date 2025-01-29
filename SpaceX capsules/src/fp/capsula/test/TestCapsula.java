package fp.capsula.test;

import java.time.LocalDate;
import fp.capsula.Capsula;
import fp.capsula.Status;

public class TestCapsula {
	public static void main(String[] args) {
		LocalDate fa=LocalDate.now();
		Capsula c1=new Capsula("capsulatest", "C300", Status.retired, true, 3, 1, fa);
		Capsula c2=new Capsula("5e9e2c5bf35918ed873b2664;C101;retired;false;1;3;26/11/2014");
		System.out.println(c1);
		System.out.println(c1.aterrizajesTotales());
		System.out.println(c2);
	}
	
}
