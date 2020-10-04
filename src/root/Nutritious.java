package root;

public interface Nutritious {

    int calculateCalories();

    enum Item {

        BUTTER(880), CHEESE(400), TOMATO(20), FISH(200), SAUSAGE(300), FILLET(280), BREAD(265);

        public final int calories;

        Item(int calories) {
            this.calories = calories;
        }
    }
}

