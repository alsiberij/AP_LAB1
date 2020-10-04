package root;

public abstract class Food implements Consumable, Nutritious {

    private final String name;

    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Food) {
            return name.equals(((Food) obj).name);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
