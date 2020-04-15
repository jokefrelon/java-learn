package top.jokeme;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fre{
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String uri = "jdbc:mysql://127.0.0.1:3306/solo";
    public static final String user = "root";
    public static final String passcode = "123456";
    final String sqlScript = "select * from b3_solo_article";
    Connection connection= null;
    PreparedStatement pst = null;

    public ResultSet StartCollection() throws SQLException {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(uri,user,passcode);
            if(!connection.isClosed())
                System.out.println("connection successfully");
            Statement statement = connection.createStatement();
            ResultSet replyString = statement.executeQuery(sqlScript);
            return replyString;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toTheJson(List<Map> list){
        return list.toString().replace("{", "{\"").replace("}", "\"}").replace("=","\":\"").replace(", ", "\",\"").replace("}\",\"{", "},{").replace("{", "{\"").replace("}", "\"}").replace("=","\":\"").replace(", ", "\",\"").replace("}\",\"{", "},{").replace("\"\"","\"");
    }

    public void close(){
        try{

            this.connection.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   // @Test
   // public void ssr() throws SQLException, IOException {
   //     Map<String,Object> outer = new HashMap<String, Object>();
   //     List<Map> list = new ArrayList<Map>();
   //     Fre solo = new Fre();
   //     ResultSet so  = solo.StartCollection();
   //     while(so.next()){
   //         Map<String,Object>  map = new HashMap<String, Object>();
   //         map.put("User_ID",so.getString(1));
   //         map.put("User_Name",so.getString(2));
   //         map.put("User_Mod",so.getString(3));
   //         map.put("User_IDE",so.getString(4));
   //         list.add(map);
   //     }
   //     String Solo = toTheJson(list);
   //     System.out.println(Solo);
   //     FileOutputStream fos = new FileOutputStream("solo.json",true);
   //     OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
   //     osw.write(Solo);
   //     System.out.println("Write Over");
   //     osw.close();
   // }

    public static void main(String[] args) throws SQLException, IOException {

        Map<String,Object> outer = new HashMap<String, Object>();
        List<Map> list = new ArrayList<Map>();
        Fre solo = new Fre();
        ResultSet so  = solo.StartCollection();
        while(so.next()){
            Map<String,Object>  map = new HashMap<String, Object>();
            map.put("oId",so.getString(1));
            map.put("articleTitle",so.getString(2));
            map.put("articleAbstractText",so.getString(4));
            map.put("articlePermalink",so.getString(10));
            map.put("articleImg1URL",so.getString(18));
//            map.put("articleAbstract",so.getString(3));
//            map.put("articleTags",so.getString(5));
//            map.put("articleAuthorId",so.getString(6));
//            map.put("articleCommentCount",so.getString(7));
//            map.put("articleViewCount",so.getString(8));
//            map.put("articleContent",so.getString(9));
//            map.put("articlePutTop",so.getString(11));
//            map.put("articleCreated",so.getString(12));
//            map.put("articleUpdated",so.getString(13));
//            map.put("articleRandomDouble",so.getString(14));
//            map.put("articleSignId",so.getString(15));
//            map.put("articleCommentable",so.getString(16));
//            map.put("articleViewPwd",so.getString(17));
//            map.put("articleStatus",so.getString(19));
            list.add(map);
        }
        String Solo = toTheJson(list);
        System.out.println(Solo);
        FileOutputStream fos = new FileOutputStream("solo.json",true);
        OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
        osw.write(Solo);
        System.out.println("Write Over");
        osw.close();
    }
}
