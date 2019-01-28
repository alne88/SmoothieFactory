package Products;

public enum Vegetable {
    CARROT, TOMATO, SAUERKRAUT;

    //calories per gram
    public static double getCarrotCalories() {
        return 0.41;
    }

    public static double getTomatoCalories() {
        return 0.20;
    }

    public static double getSauerkrautCalories() {
        return 0.18;
    }
}
