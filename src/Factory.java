import Products.*;
import java.util.*;

public class Factory{

    private List<Product> finishedProducts = Collections.synchronizedList(new ArrayList());

    public void makeProducts(ProductGroup fruitOrVegetable) {
        Product newProduct = null;
        switch (fruitOrVegetable){
            case FRUIT:
                newProduct = makeFruit();
                break;
            case VEGETABLE:
                newProduct = makeVegetable();
                break;
            default:
                break;
        }

        addProductToList(newProduct);

        try{
            Thread.sleep(20);
        } catch (InterruptedException e) {
            System.err.println("Interrupted exception");
        }
    }

    private Product makeFruit(){
        Product newFruit = null;
        int randFruit = getRandBetween(1, 3);
        int randWeight = getRandBetween(5, 2000);
        switch (randFruit){
            case 1:
                newFruit = new Product(ProductGroup.FRUIT, Fruit.APPLE.toString(), randWeight, Math.round(Fruit.getAppleCalories()*randWeight));
                break;
            case 2:
                newFruit = new Product(ProductGroup.FRUIT, Fruit.PEAR.toString(), randWeight, Fruit.getPearCalories()*randWeight);
                break;
            case 3:
                newFruit = new Product(ProductGroup.FRUIT, Fruit.BANANA.toString(), randWeight, Fruit.getBananaCalories()*randWeight);
                break;
            default:
                break;
        }
        return newFruit;
    }

    private Product makeVegetable(){
        Product newVeg = null;
        int randVegetable = getRandBetween(1, 3);
        int randWeight = getRandBetween(5, 2000);
        switch (randVegetable){
            case 1:
                newVeg = new Product(ProductGroup.VEGETABLE, Vegetable.CARROT.toString(), randWeight, Vegetable.getCarrotCalories()*randWeight);
                newVeg.setChunkSize(getRandSize());
                break;
            case 2:
                newVeg = new Product(ProductGroup.VEGETABLE, Vegetable.TOMATO.toString(), randWeight, Vegetable.getTomatoCalories()*randWeight);
                newVeg.setChunkSize(getRandSize());
                break;
            case 3:
                newVeg = new Product(ProductGroup.VEGETABLE, Vegetable.SAUERKRAUT.toString(), randWeight, Vegetable.getSauerkrautCalories()*randWeight);
                newVeg.setChunkSize(getRandSize());
                break;
            default:
                break;
        }
        return newVeg;
    }

    private Size getRandSize(){
        int randSize = getRandBetween(1, 3);
        Size size;
        if (randSize == 1){
            size = Size.MIN;
        } else
        if (randSize == 2){
            size = Size.MED;
        } else {
            size = Size.MAX;
        }
        return size;
    }

    public int getRandBetween(int min, int max){
        int result = new Random().nextInt((max - min) + 1) + min;
        return result;
    }

    public List<Product> getFinishedProducts() {
        return finishedProducts;
    }

    public void addProductToList(Product newProduct){
        if (finishedProducts.size() == 0) {
            finishedProducts.add(newProduct);
        } else {
            for (Product product : finishedProducts) {
                if (product.getProductGroup().equals(ProductGroup.FRUIT)){
                    if (product.getName().equals(newProduct.getName())) {
                        Product newPlusOld = product;
                        newPlusOld.setTotalWeightInGram(product.getTotalWeightInGram() + newProduct.getTotalWeightInGram());
                        newPlusOld.setTotalCalories(product.getTotalCalories() + newProduct.getTotalCalories());
                        finishedProducts.set(finishedProducts.indexOf(product), newPlusOld);
                    } else {
                        finishedProducts.add(newProduct);
                    }
                } else {
                    if (product.getName().equals(newProduct.getName()) && product.getChunkSize().equals(newProduct.getChunkSize())) {
                        Product newPlusOld = product;
                        newPlusOld.setTotalWeightInGram(product.getTotalWeightInGram() + newProduct.getTotalWeightInGram());
                        newPlusOld.setTotalCalories(product.getTotalCalories() + newProduct.getTotalCalories());
                        finishedProducts.set(finishedProducts.indexOf(product), newPlusOld);
                    } else {
                        finishedProducts.add(newProduct);
                    }
                }
            }
        }
    }
}
