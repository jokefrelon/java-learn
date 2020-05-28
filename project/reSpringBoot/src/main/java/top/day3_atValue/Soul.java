package top.day3_atValue;

public class Soul {
    private Integer Weight;
    private Integer Data;
    private Integer Power;
    private Boolean Lovely;

    @Override
    public String toString() {
        return "Soul{" +
                "Weight=" + Weight +
                ", Data=" + Data +
                ", Power=" + Power +
                ", Lovely=" + Lovely +
                '}';
    }

    public Integer getWeight() {
        return Weight;
    }

    public void setWeight(Integer weight) {
        Weight = weight;
    }

    public Integer getData() {
        return Data;
    }

    public void setData(Integer data) {
        Data = data;
    }

    public Integer getPower() {
        return Power;
    }

    public void setPower(Integer power) {
        Power = power;
    }

    public Boolean getLovely() {
        return Lovely;
    }

    public void setLovely(Boolean lovely) {
        Lovely = lovely;
    }
}
