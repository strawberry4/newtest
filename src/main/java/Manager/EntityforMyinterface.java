package Manager;

public class EntityforMyinterface implements Myinterface {
    @Override
    public void sellMobilePhone() {
        System.out.println("this is my interface for ");
    }

    @Override
    public Intu sellbae() {
        System.out.println("this is my interface sellbae ");
        return new Intuio();
    }

}
