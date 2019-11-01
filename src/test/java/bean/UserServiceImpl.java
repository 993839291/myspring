package bean;

public class UserServiceImpl implements UserService{
    @Override
    public User getById(String id) {
        User user = new User();
        user.setName("name:"+id);
        user.setAge(22);
        return user;
    }

    @Override
    public User getById2(String id) {
        User user = new User();
        user.setName("name:"+id);
        user.setAge(44);
        return user;
    }
}
