package top.day3_atValue;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@PropertySource(value={"classpath:per.properties"})
@Component
@ConfigurationProperties(prefix = "dog2")
@RestController
public class human {
    /**
     * <bean class="human">
     *  <property name="Typeof" value="Person"></property>
     * </>
     *
     * @Value 注解则可以直接写值与数据绑定
     * 上面这两种都是数据绑定,并且支持一些特殊语法和符号
     */
//    @Value("Person")
    String Typeof;

//    @Value("Earth")
    String LiveEnv;

//    @Value("Apple")
    String EatingFoods;

//    @Value("24")
    Integer SunDay;

    Map<String,String> Example;

    List<Object> Friends;

    Soul soul;

    @Override
    @RequestMapping("/")
    public String toString() {
        return "human{" +
                "Typeof='" + Typeof + '\'' +
                ", LiveEnv='" + LiveEnv + '\'' +
                ", EatingFoods='" + EatingFoods + '\'' +
                ", SunDay=" + SunDay +
                ", Example=" + Example +
                ", Friends=" + Friends +
                ", soul=" + soul +
                '}';
    }

    public String getTypeof() {
        return Typeof;
    }

    public void setTypeof(String typeof) {
        Typeof = typeof;
    }

    public String getLiveEnv() {
        return LiveEnv;
    }

    public void setLiveEnv(String liveEnv) {
        LiveEnv = liveEnv;
    }

    public String getEatingFoods() {
        return EatingFoods;
    }

    public void setEatingFoods(String eatingFoods) {
        EatingFoods = eatingFoods;
    }

    public Integer getSunDay() {
        return SunDay;
    }

    public void setSunDay(Integer sunDay) {
        SunDay = sunDay;
    }

    public Map<String, String> getExample() {
        return Example;
    }

    public void setExample(Map<String, String> example) {
        Example = example;
    }

    public List<Object> getFriends() {
        return Friends;
    }

    public void setFriends(List<Object> friends) {
        Friends = friends;
    }

    public Soul getSoul() {
        return soul;
    }

    public void setSoul(Soul soul) {
        this.soul = soul;
    }
}
