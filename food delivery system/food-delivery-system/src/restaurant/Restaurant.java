package restaurant;

import users.User;
import java.util.ArrayList;
import java.util.List;

public class Restaurant extends User {
    private List<Menu> menures;

    public Restaurant(String name, int id, String email){
        super(name, id, email);
        this.menures = new ArrayList<>();
    }

    public List<Menu> getMenu() {
        return menures;
    }

    public void add(Menu item){
        this.menures.add(item);
    }

    public void addMenuItem(String desc, int price){
        this.menures.add(new Menu(desc, price));
    }

    @Override
    public void viewProfile(){
        System.out.println("--- Restaurant: " + getName() + " ---");

        if (menures.isEmpty()){
            System.out.println("Nothing in the MENU >:");
            return;
        }

        System.out.println("Menu:");
        for (Menu menus : menures){
            System.out.println("- " + menus.getDesc() + " | R$ " + menus.getNumber());
        }
    }
}
