package androiddev.nhom5.calculatorapp;

import java.io.Serializable;

public class savekq implements Serializable {
    String bieuthu;
    double ketqua;

    public savekq(String bieuthu, double ketqua) {
        this.bieuthu = bieuthu;
        this.ketqua = ketqua;
    }

    public String getBieuthu() {
        return bieuthu;
    }

    public double getKetqua() {
        return ketqua;
    }

    public void setBieuthu(String bieuthu) {
        this.bieuthu = bieuthu;
    }

    public void setKetqua(double ketqua) {
        this.ketqua = ketqua;
    }


    @Override
    public String toString() {
        return this.bieuthu+"\n"+this.ketqua;
    }
}
