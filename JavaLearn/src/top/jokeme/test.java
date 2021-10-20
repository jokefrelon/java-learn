package top.jokeme;

import java.util.ArrayList;

class test{
	public static void main (String[] args){
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		ArrayList<String> c = new ArrayList<String>();

		a.add("red");a.add("pink");a.add("gray");a.add("blue");a.add("green");a.add("black");

		System.out.println(a + " : " + b + " : " + c);
		hanoi(a.size(),a,b,c);
		System.out.println(a + " : " + b + " : " + c);
	}
	public static void hanoi(int n, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c){
		if(n == 1){
			c.add(a.get(a.size()-1));a.remove(a.size()-1);
		}else{
			hanoi(n-1,a,c,b);
			c.add(a.get(a.size()-1));a.remove(a.size()-1);
			hanoi(n-1,b,a,c);
		}
	}
}