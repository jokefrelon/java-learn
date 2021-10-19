package top.jokeme.service;

import top.jokeme.dao.UserDao;
import top.jokeme.dao.UserDaoMongoImpl;
import top.jokeme.dao.UserDaoMysqlImpl;

public class UserServiceImpl implements UserService{

//    private UserDao userDao = new UserDaoImpl();
//    private UserDao userDao = new UserDaoMysqlImpl();
//    private UserDao userDao = new UserDaoMongoImpl();

//    用户只能调用Userserviceimpl里面有的方法，但是要是他们有别的需求，就只能让咱们修改Userserviceimpl，显而易见这样的程序并不是健康的。
//    所以咱们要想办法让用户可以根据需求自由更改，程序可以自己适应。

    //    @Override
//    public void getUser() {
//        userDao.getUser();
//    }
//------------------------------------------------------------------------------------------------------------

    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    //  先定义好一个UserDao，利用setter()进行动态赋值 ✅

    @Override
    public void getSql() {
        userDao.getUser();
    }

}
