package restaurant;

public class Menu {
    private String desc;
    private int number;

    public Menu(String desc, int number){
        this.desc = desc;
        this.number = number;
    }

    public String getDesc() {
        return desc;
    }

    public int getNumber() {
        return number;
    }
}