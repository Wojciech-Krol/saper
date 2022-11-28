package saper;

public class Pole {
    private boolean bomba;
    private boolean czyOdkryte;
    private boolean czyFlaga;

    public Pole(boolean b)
    {
        this.bomba = b;
        this.czyOdkryte = false;
        this.czyFlaga = false;
    }
    public Pole()
    {
        this.bomba = false;
        this.czyOdkryte = false;
        this.czyFlaga = false;
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
    public void setCzyBomba(boolean b)
    {
        bomba = b;
    }

    public void setFlaga(){
        czyFlaga = !czyFlaga;

    }
    public boolean getCzyFlaga(){
        return czyFlaga;
    }

}
