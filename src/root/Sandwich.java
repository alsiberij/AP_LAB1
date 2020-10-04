package root;

import static java.lang.String.format;

public class Sandwich extends Food {

    private final String filling1;
    private final String filling2;

    public Sandwich(String filling1, String filling2) {
        super("Sandwich");
        this.filling1 = filling1;
        this.filling2 = filling2;
    }

    public Sandwich(String filling1) {
        super("Sandwich");
        this.filling1 = filling1;
        this.filling2 = "?";
    }

    public Sandwich() {
        super("Sandwich");
        this.filling1 = "?";
        this.filling2 = "?";
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (obj instanceof Sandwich) {
                return filling1.equals(((Sandwich) obj).filling1) && filling2.equals(((Sandwich) obj).filling2);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer(super.toString());
        result.append(filling1.equals("?") ? "" :
                (filling2.equals("?") ?
                        format(" with %s", filling1) :
                        format(" with %s and %s", filling1, filling2)));
        return result.toString();
    }

    @Override
    public void consume() {
        System.out.println(this + " has been eaten.");
    }

    @Override
    public int calculateCalories() {
        int calories = Nutritious.Item.BREAD.calories;

        try {
            calories += Item.valueOf(filling1.toUpperCase()).calories;
        } catch (IllegalArgumentException ignored) { }

        try {
            calories += Item.valueOf(filling2.toUpperCase()).calories;
        } catch (IllegalArgumentException ignored) { }

        return calories;
    }
}
