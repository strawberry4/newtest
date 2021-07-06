package Manager;

public class FooImpl implements Foo {
    @Override
    public void doAction() {
        System.out.println("in Fooimpl doAction");
    }

    @Override
    public String seesomething(String name) {
        System.out.println("in Fooimpl see "+name);
        return name+" Fooimpl";
    }
}
