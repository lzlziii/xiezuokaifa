package xx.yy.qian;

import org.litepal.crud.DataSupport;

public class Thing extends DataSupport {
    private String detil;
    private String cal;
    private String cal2;

    public Thing(String detil, String cal, String cal2) {
        this.detil = detil;
        this.cal = cal;
        this.cal2 = cal2;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "detil='" + detil + '\'' +
                ", cal='" + cal + '\'' +
                ", cal2='" + cal2 + '\'' +
                '}';
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public String getCal2() {
        return cal2;
    }

    public void setCal2(String cal2) {
        this.cal2 = cal2;
    }

    public Thing(String detil) {
        this.detil=detil;
    }

    public String getDetil() {
        return detil;
    }

    public void setDetil(String detil) {
        this.detil = detil;

    }
}