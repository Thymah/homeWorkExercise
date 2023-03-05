package fileWriterIo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static inputAndOutput.bufferedIoStream.Utils.collectIntegerInput;

public class FileWriterReaderRunner {
    public static final String FILE_NAME = "src/product.txt";

    public static void main(String[] args) {
        //select operations: view products, add product, delete product, update product, sell product
        int ops;
        do {
            ops = collectIntegerInput("choice one operation" +
                    " \n1. add \n2. view product \n3. update product \n4. sell product \n5. delete product \n6. exit");
            if (ops == 1) {
                File file = new File(FILE_NAME);
                List<Product> productList = new ArrayList<>();
                if (file.exists()) {
                    productList = Utils.getProductsFromFile(file);
                }
                Utils.getProductsFromUser(productList);
                Utils.saveToFile(file, productList);
            } else if (ops == 2) {
               Utils.viewProductFromFile();
            } else if (ops == 3) {
               Utils.upDateProduct();
            }
        } while (ops != 6);
    }
}
