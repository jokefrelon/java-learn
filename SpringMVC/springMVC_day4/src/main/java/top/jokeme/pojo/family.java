package top.jokeme.pojo;

public class family {
    private String fmad;

    public String getFmad() {
        return fmad;
    }

    @Override
    public String toString() {
        return "family{" +
                "fmad='" + fmad + '\'' +
                '}';
    }

    public void setFmad(String fmad) {
        this.fmad = fmad;
    }
}
