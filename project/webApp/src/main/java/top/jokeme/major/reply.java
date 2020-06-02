package top.jokeme.major;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class reply {
    @RequestMapping("/")
    public String golong (){
        return "hello";
    }
}
