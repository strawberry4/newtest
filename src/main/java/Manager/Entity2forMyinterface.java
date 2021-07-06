package Manager;

public class Entity2forMyinterface implements Myinterface {
    @Override
    public void sellMobilePhone() {
        System.out.println("this is other interface for");
    }

    @Override
    public Intu sellbae() {
        System.out.println("this is other interface sellbae");
        return new Intuio();
    }
}
