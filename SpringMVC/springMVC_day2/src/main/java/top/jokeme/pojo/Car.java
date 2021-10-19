package top.jokeme.pojo;

public class Car {
    private String module;
    private String price;

    @Override
    public String toString() {
        return "car{" +
                "module='" + module + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
