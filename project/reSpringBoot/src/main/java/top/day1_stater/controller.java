package top.day1_stater;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @RequestMapping("/word")
    public heloset restr(){
        heloset hel = new heloset();
        hel.setId(19);
        hel.setSna("sogalia");
        hel.setSe(12);
//        return "<p style='color:red;text-align:center'>Awesome Java</p>";
        return hel;
    }
}
