package top.jokeme.pojo;

public class Dog {
    private String World;

    public String getWorld() {
        return World;
    }

    public void setWorld(String world) {
        World = world;
    }
    public void action(){
        System.out.println("Dog say : "+ World);
    }
}
