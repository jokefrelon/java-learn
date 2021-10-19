package top.jokeme.service.serviceImpl;

import top.jokeme.service.UserService;


public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("exec add action");
    }

    @Override
    public void del() {
        System.out.println("exec del action");
    }

    @Override
    public void update() {
        System.out.println("exec update action");
    }

    @Override
    public void query() {
        System.out.println("exec query action");
    }
}
