package bean;

/**
 * @author ly
 * @create 2018-12-02 11:42
 **/
public class User {


    public User(){

    }

    public User(String name,int age){
        this.name=name;
        this.age=age;
    }
    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void init(){
        System.out.println("init...");
    }

    public void destory(){
        System.out.println("destory...");
    }

    @Override
    public String toString() {
        return name + ":" + age;
    }

    public void sayHello(){
        System.out.println("hello...");
    }

    public static User staticCreateMethod(){
        User user = new User("staticName",1);
        return user;
    }


}
