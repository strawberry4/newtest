package Manager;

public class FooImpl2 implements Foo {
    @Override
    public void doAction() {
        System.out.println("in Fooimpl2 doAction");
    }

    @Override
    public String seesomething(String name) {
        System.out.println("in Fooimpl2 see "+name);
        return name+" Fooimpl2";
    }
}
