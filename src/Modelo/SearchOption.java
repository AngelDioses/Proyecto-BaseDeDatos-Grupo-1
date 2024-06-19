package Modelo;

import javax.swing.Icon;

public class SearchOption {

    private String name;
    private Icon icon;
    
    public SearchOption(String name, Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    public SearchOption() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
