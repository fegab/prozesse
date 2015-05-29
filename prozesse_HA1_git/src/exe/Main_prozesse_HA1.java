package exe;

import java.awt.EventQueue;

import model.Element;
import model.Menge;
import model.Sense;

public class Main_prozesse_HA1 {

	public static void main(String[] args) {

		Menge testMenge = new Menge(null, null);

		Element in1 = new Element(null, "in", null);
		Element out1 = new Element(null, "out", null);
		in1.setname("in1");
		out1.setname("out1");

		for (int i = 0; i < 10; i++) {

			Sense testSense = new Sense("iSense=" + i);
			Element test = new Element(null, "i=" + i, testSense);
			test.setname("i=" + i);

			System.out.print("name: " + test.getname() + " Bedeutung: "
					+ test.getSense().getSense());
			System.out.println(" ");
			// System.out.println(test);
			// System.out.println(test.getname());
			// System.out.println(test.getSense().getSense());

		}

	}

}
