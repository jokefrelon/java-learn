package top.jokeme.pojo;

public class Cat {
    private String world;

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void action (){
        System.out.println("Cat say : " + world);
    }
}
