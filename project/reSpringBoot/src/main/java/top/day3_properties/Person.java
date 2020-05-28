package top.day3_properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.day2_yaml.dog;

/**
 * 将配置文件里的属性の值映射到这个组件中
 * @ConfigurationProperties の作用是将本类中的属性与配置文件里的值进行绑定
 * prefix = "person" 表示需要绑定yaml里面の哪个属性
 * 只有这个组件是容器的组件才能使用容器的功能
 *
 */
@RestController
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    String sname;
    Integer sage;
    String Sod;
    dog Lovelydog;

    @Override
    @RequestMapping("/")
    public String toString() {
        return "Person{" +
                "sname='" + sname + '\'' +
                ", sage=" + sage +
                ", Sod='" + Sod + '\'' +
                ", Lovelydog=" + Lovelydog +
                '}';
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public String getSod() {
        return Sod;
    }

    public void setSod(String sod) {
        Sod = sod;
    }

    public dog getLovelydog() {
        return Lovelydog;
    }

    public void setLovelydog(dog lovelydog) {
        Lovelydog = lovelydog;
    }
}
