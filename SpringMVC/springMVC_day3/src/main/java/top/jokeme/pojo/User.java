package top.jokeme.pojo;

public class User {
    private String Name;

//    如果咱们把无参构造换成有参构造，程序就会运行失败
//    public User() {
//        System.out.println("User 调用了无参构造");
//    }

    public User(String name) {
        this.Name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
