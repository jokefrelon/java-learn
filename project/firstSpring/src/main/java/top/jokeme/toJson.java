package top.jokeme;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class toJson {
    ResultSet lol = null;
    public toJson(ResultSet args) {
        this.lol = args;
    }

    public toJson() {
    super();
    }
    public void toJsonMeh() throws SQLException {
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        while (lol.next()){
            Map<String,Object> map = new HashMap<String, Object>();
//            map.put()
        }
    }
}