package top.jokeme.pojo;

import java.util.*;

public class Student {
    private String name;
    private family fam;
    private String[] room;
    private List<Integer> famWeight;
    private Map<String,String> chairModule;
    private Set<String> aspn;
    private Properties hash;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", fam=" + fam.toString()+
                ", room=" + Arrays.toString(room) +
                ", famWeight=" + famWeight +"\n"+
                ", chairModule=" + chairModule +
                ", aspn=" + aspn +
                ", hash=" + hash +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public family getFam() {
        return fam;
    }

    public void setFam(family fam) {
        this.fam = fam;
    }

    public String[] getRoom() {
        return room;
    }

    public void setRoom(String[] room) {
        this.room = room;
    }

    public List<Integer> getFamWeight() {
        return famWeight;
    }

    public void setFamWeight(List<Integer> famWeight) {
        this.famWeight = famWeight;
    }

    public Map<String, String> getChairModule() {
        return chairModule;
    }

    public void setChairModule(Map<String, String> chairModule) {
        this.chairModule = chairModule;
    }

    public Set<String> getAspn() {
        return aspn;
    }

    public void setAspn(Set<String> aspn) {
        this.aspn = aspn;
    }

    public Properties getHash() {
        return hash;
    }

    public void setHash(Properties hash) {
        this.hash = hash;
    }
}
