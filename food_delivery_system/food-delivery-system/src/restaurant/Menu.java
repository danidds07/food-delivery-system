package restaurant;

public class Menu {
    private int id;
    private String desc;
    private int price;

    public Menu(int id, String desc, int price){
        this.id = id;
        this.desc = desc;
        this.price = price;
    }

    public Menu(String desc, int price){
        this.id = 0;
        this.desc = desc;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public int getPrice() {
        return price;
    }
}
