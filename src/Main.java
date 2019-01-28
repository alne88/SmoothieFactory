import Products.Product;
import Products.ProductGroup;

public class Main {
    public static void main(String[] args) {

        Factory factory = new Factory();

        new Thread(() -> {
            int i = 0;
            while(i < 5){
                factory.makeProducts(ProductGroup.FRUIT);
                i++;
            }
        }).start();

        new Thread(() -> {
            int i = 0;
            while(i < 5){
                factory.makeProducts(ProductGroup.VEGETABLE);
                i++;
            }
        }).start();

        //print finished products
        try{
            Thread.sleep(1000);
            for (Product product : factory.getFinishedProducts()) {
                System.out.println(product.toString());
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted exception");
        }
    }
}
