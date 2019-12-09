package sourceCode;

public class person {
	private int age;
	private String name;

	public person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "person [age=" + age + ", name=" + name + "]";
	}

	public boolean equals(person obj) {
		String als = this.toString();
		String objToStr = obj.toString();
		if (als==objToStr) {
			return true;
		}else {
			return false;
		}
	
		
	}
}
