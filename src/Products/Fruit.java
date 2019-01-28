package Products;

public enum Fruit {
    BANANA, PEAR, APPLE;

    //calories per gram
    public static double getBananaCalories() {
        return 0.88;
    }

    public static double getPearCalories() {
        return 0.57;
    }

    public static double getAppleCalories() {
        return 0.52;
    }
}
