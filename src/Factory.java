import Products.*;
import java.util.*;
import java.util.stream.Collectors;

public class Factory{

    private List<Product> finishedProducts = Collections.synchronizedList(new ArrayList());
    private List<Smoothie> smoothieBottles = Collections.synchronizedList(new ArrayList());

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

        finishedProducts.add(newProduct);

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

    public Smoothie makeSmoothie(int smoothieChoice){

            if (finishedProducts.isEmpty()){
//                System.out.println("Cannot make smoothie, finished products list is empty");
                return null;
            }
            switch (smoothieChoice){
                case 1:     //toodab smuutit kõikidest köögiviljadest, millel on hakkimise aste ’väike’
                    try {
                        Product minChunkProduct = finishedProducts
                                .stream()
                                .filter(product -> product.getProductGroup().equals(ProductGroup.VEGETABLE) && product.getChunkSize().equals(Size.MIN))
                                .findFirst()
                                .get();

                        Smoothie vegSmoothie = new Smoothie(minChunkProduct.getName() + " smoothie", minChunkProduct.getTotalCalories());
                        smoothieBottles.add(vegSmoothie);
                        finishedProducts.remove(minChunkProduct);
                        return vegSmoothie;
                    } catch (Exception e){
                        // .get gives Exception java.util.NoSuchElementException: No value present
                        // no product matching to these criterias was not found
                    }
                    break;

                case 2:     //toodab smuutit banaanist ja pirnist
                    try {
                        Product banana = finishedProducts
                                            .stream()
                                            .filter(product -> product.getName().equals("BANANA"))
                                            .findFirst()
                                            .get();
                        Product pear = finishedProducts
                                            .stream()
                                            .filter(product -> product.getName().equals("PEAR"))
                                            .findFirst()
                                            .get();

                        Smoothie bananaPearSmoothie = new Smoothie("Banana+Pear smoothie", banana.getTotalCalories() + pear.getTotalCalories());
                        smoothieBottles.add(bananaPearSmoothie);
                        finishedProducts.remove(banana);
                        finishedProducts.remove(pear);
                        return bananaPearSmoothie;
                    } catch (Exception e){
                        //exception when products found at the given time that match criterias
                    }
                    break;

                case 3:     //toodab smuutit porgandist aga ainult siis, kui porgandit on kokku vähemalt kilo (kui liinil on nt 2x500g porgandit, siis see sobib)
                    try {
                        List<Product> foundCarrots = finishedProducts.stream().filter(product -> product.getName().equals("CARROT")).collect(Collectors.toList());
                        Smoothie carrotSmoothie = new Smoothie("Carrot smoothie", 0);
                        int sumOfWeight = 0;
                        //get atleast 1kg worth of carrots

                        for (Product product : foundCarrots) {
                            sumOfWeight += product.getTotalWeightInGram();
                            carrotSmoothie.setCalories(carrotSmoothie.getCalories() + product.getTotalCalories());
                            if (sumOfWeight > 1000){
                                //check if enough carrots are found, when yes, make now smoothie
                                for (Product carrot : foundCarrots){
                                    finishedProducts.remove(carrot);
                                }
                                smoothieBottles.add(carrotSmoothie);
                                return carrotSmoothie;
                            }
                        }
                    } catch (Exception e){
                        //exception when products found at the given time that match criterias
                    }
                    break;

                case 4:     //toodab smuutit porgandist ja banaanist
                    try {
                    Product carrotMix = finishedProducts
                            .stream()
                            .filter(product -> product.getName().equals("CARROT"))
                            .findFirst()
                            .get();
                    Product bananaMix = finishedProducts
                            .stream()
                            .filter(product -> product.getName().equals("BANANA"))
                            .findFirst()
                            .get();

                    Smoothie carrotBananaSmoothie = new Smoothie("Banana+Pear smoothie", bananaMix.getTotalCalories() + carrotMix.getTotalCalories());
                    smoothieBottles.add(carrotBananaSmoothie);
                    finishedProducts.remove(bananaMix);
                    finishedProducts.remove(carrotMix);
                    return carrotBananaSmoothie;

                    } catch (Exception e){
                        //exception when products found at the given time that match criterias
                    }
                    break;

                case 5:     //toodab smuutit puuviljadest, kui kalorsus on alla 500
                    try {
                    Product lowCalFruit = finishedProducts
                            .stream()
                            .filter(product -> product.getProductGroup().equals(ProductGroup.FRUIT) && product.getTotalCalories()<500)
                            .findFirst()
                            .get();

                    Smoothie lowCaloriesSmoothie = new Smoothie("Low Calories Fruit smoothie", lowCalFruit.getTotalCalories());
                    smoothieBottles.add(lowCaloriesSmoothie);
                    finishedProducts.remove(lowCalFruit);
                    return lowCaloriesSmoothie;
                    } catch (Exception e){
                        //exception when products found at the given time that match criterias
                    }
                    break;

                default:
                    break;
            }
        return null;
    }

    private Size getRandSize(){
        int randSize = getRandBetween(1, 3);
        if (randSize == 1){
            return Size.MIN;
        } else
        if (randSize == 2){
            return Size.MED;
        } else {
            return Size.MAX;
        }
    }

    public int getRandBetween(int min, int max){
        int result = new Random().nextInt((max - min) + 1) + min;
        return result;
    }

    public List<Product> getFinishedProducts() {
        return finishedProducts;
    }

    public List<Smoothie> getSmoothieBottles() {
        return smoothieBottles;
    }

}
