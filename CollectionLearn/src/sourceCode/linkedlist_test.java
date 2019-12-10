package sourceCode;

import java.util.LinkedList;

public class linkedlist_test {
	public static void main(String[] args) {
		test loser = new test();
		loser.setKail("hello");
		loser.setKail("CNN");
		loser.setKail("Love China");
		while (loser.Kailisempty()) {
			System.out.println(loser.getKail());
		}
	}
}

class test {
	private LinkedList<Object> kail;

	public Object getKail() {
		return kail.pollLast();
	}

	public Boolean Kailisempty() {
		if (kail.isEmpty()) {
			return false;
		} else if (!kail.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "test [kail=" + kail + "]";
	}

	public void setKail(Object obj) {
		this.kail.addLast(obj);
	}

	public test() {
		kail = new LinkedList<Object>();
	}
}