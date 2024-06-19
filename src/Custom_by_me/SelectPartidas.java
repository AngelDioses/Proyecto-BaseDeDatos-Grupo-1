package Custom_by_me;

public class SelectPartidas {
    private String label;
    private String value;

    public SelectPartidas(String label, String value) {
        this.label = "(" + value + ") " + label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return label; // This is what will be displayed in the JComboBox
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectPartidas that = (SelectPartidas) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

}
