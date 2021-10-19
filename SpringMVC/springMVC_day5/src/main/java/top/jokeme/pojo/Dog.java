package top.jokeme.pojo;

public class Dog {

    private String world;

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void action (){
        System.out.println("Dog say : " + world);
    }
}
