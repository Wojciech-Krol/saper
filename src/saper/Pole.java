package saper;

public class Pole {
    private boolean bomba;
    private boolean czyOdkryte;

    public Pole(boolean b)
    {
        this.bomba = b;
        this.czyOdkryte = false;
    }
    public Pole()
    {
        this.bomba = true;
        this.czyOdkryte = false;
    }

    public boolean getBomba()
    {
        return bomba;
    }

    public boolean getCzyOdkryte()
    {
        return czyOdkryte;
    }

    public void setCzyOdkryte(boolean x)
    {
        czyOdkryte = x;
    }

}
