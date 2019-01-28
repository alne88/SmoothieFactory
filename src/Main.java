import Products.ProductGroup;

public class Main {
    public static void main(String[] args) {

        Factory factory = new Factory();
        int smoothieProductionLimit = 1;
        startProductionOfFruitAndVegetabele(factory, smoothieProductionLimit);
        startSmoothieProduction(factory, smoothieProductionLimit);

    }

    public static void startProductionOfFruitAndVegetabele(Factory factory, int limit){
        new Thread(() -> {
            while(factory.getSmoothieBottles().size() <= limit){
                factory.makeProducts(ProductGroup.FRUIT);
            }
            Thread.interrupted();
            System.out.println("Fruit production has stopped");
        }).start();

        new Thread(() -> {
            while(factory.getSmoothieBottles().size() <= limit){
                factory.makeProducts(ProductGroup.VEGETABLE);
            }
            Thread.interrupted();
            System.out.println("Vegetable production has stopped");
        }).start();
    }

    public static void startSmoothieProduction(Factory factory, int limit){
        new Thread(() -> {
            while(factory.getSmoothieBottles().size() <= limit){
                if(!factory.getFinishedProducts().isEmpty()){
                    Smoothie smoothie = factory.makeSmoothie(1);
                    printResult(smoothie, 1);
                }
            }
            Thread.interrupted();
            System.out.println("Thread 1 - Smoothie production has stopped");
        }).start();

        new Thread(() -> {
            while(factory.getSmoothieBottles().size() <= limit){
                if(!factory.getFinishedProducts().isEmpty()){
                    Smoothie smoothie = factory.makeSmoothie(2);
                    printResult(smoothie, 2);
                }
            }
            Thread.interrupted();
            System.out.println("Thread 2 - Smoothie production has stopped");
        }).start();

        new Thread(() -> {
            while(factory.getSmoothieBottles().size() <= limit){
                if(!factory.getFinishedProducts().isEmpty()){
                    Smoothie smoothie = factory.makeSmoothie(3);
                    printResult(smoothie, 3);
                }
            }
            Thread.interrupted();
            System.out.println("Thread 3 - Smoothie production has stopped");
        }).start();

        new Thread(() -> {
            while(factory.getSmoothieBottles().size() <= limit){
                if(!factory.getFinishedProducts().isEmpty()){
                    Smoothie smoothie = factory.makeSmoothie(4);
                    printResult(smoothie, 4);
                }
            }
            Thread.interrupted();
            System.out.println("Thread 4 - Smoothie production has stopped");
        }).start();

        new Thread(() -> {
            while(factory.getSmoothieBottles().size() <= limit){
                if(!factory.getFinishedProducts().isEmpty()){
                    Smoothie smoothie = factory.makeSmoothie(5);
                    printResult(smoothie, 5);
                }
            }
            Thread.interrupted();
            System.out.println("Thread 5 - Smoothie production has stopped");
        }).start();
    }

    synchronized static void printResult(Smoothie smoothie, int threadNr){
        if (smoothie != null){
            System.out.println("Thread "+threadNr+" has made - "+smoothie.toString());
        }
    }
}
