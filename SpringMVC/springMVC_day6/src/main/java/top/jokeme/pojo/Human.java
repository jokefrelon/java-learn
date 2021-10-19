package top.jokeme.pojo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class Human {

//    @Autowired
    @Resource(name="dog232")
    private Dog dog;

//    @Autowired
    @Resource
    private Cat cat;

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}
