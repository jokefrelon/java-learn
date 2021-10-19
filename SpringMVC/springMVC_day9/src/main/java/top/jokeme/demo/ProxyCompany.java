package top.jokeme.demo;

public class ProxyCompany implements SaleCar {

    Saler saler = new Saler();

    @Override
    public void Sale() {
        saler.Sale();
        System.out.println("ProxyCompany sala the car");
    }
}
