package Products;

public  class Product {
    private ProductGroup productGroup;
    private String name;
    private int totalWeightInGram;
    private double totalCalories;
    private Size chunkSize;

    public Product(ProductGroup productGroup, String name, int weightInGram, double calories) {
        this.productGroup = productGroup;
        this.name = name;
        this.totalWeightInGram = weightInGram;
        this.totalCalories = calories;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalWeightInGram() {
        return totalWeightInGram;
    }

    public void setTotalWeightInGram(int totalWeightInGram) {
        this.totalWeightInGram = totalWeightInGram;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public Size getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(Size chunkSize) {
        this.chunkSize = chunkSize;
    }

    @Override
    public String toString() {
        return "Products.Product{" +
                "productGroup=" + productGroup +
                ", name='" + name + '\'' +
                ", totalWeightInGram=" + totalWeightInGram +
                ", totalCalories=" + totalCalories +
                ", chunkSize=" + chunkSize +
                '}';
    }
}
