package top.day3_properties;

public class dog {
    Integer age;
    String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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
        return "dog{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
