package fileWriterIo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static inputAndOutput.fileWriterIo.FileWriterReaderRunner.FILE_NAME;

public class Utils {
    public static final String SPLIT_PATTERN = ";\n";

    private Utils() {}

    static List<Product> getProductsFromUser(List<Product> productList) {
        do {
            System.out.println("Please enter your product details in this format name,quantity,price or -1 to stop");
            Scanner scanner = new Scanner(System.in);
            String productString = scanner.nextLine().trim();
            if (productString.equals("-1")) {
                return productList;
            }
            String[] split = productString.split(",");
            Product product = new Product(productList.size() + 1,
                    split[0], Integer.parseInt(split[1]), Double.parseDouble(split[2]));
            productList.add(product);
        } while (true);
    }

    static List<Product> getProductsFromFile(File file) {
        ArrayList<Product> productList = new ArrayList<>();

        String stringFromFile = readFromFile(file);

        if (stringFromFile.isBlank()){
            return productList;
        }
        String[] split = stringFromFile.split(SPLIT_PATTERN);

        for (String productString : split) {
            String[] productStringSplits = productString.split(",");

            Product product = new Product(Integer.parseInt(productStringSplits[0]), productStringSplits[1],
                    Integer.parseInt(productStringSplits[2]), Double.parseDouble(productStringSplits[3]),
                    Boolean.parseBoolean(productStringSplits[4]));
            productList.add(product);
        }
        return productList;
    }

     static String readFromFile(File file) {
        if (!file.exists()){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(file)) {
            int i;
            while((i = fileReader.read()) != -1){
                sb.append((char)i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    static void saveToFile(File file, List<Product> productList) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Product product : productList) {
                fileWriter.write(product.getMessageDetails() + SPLIT_PATTERN);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String viewProductFromFile(){
        String product = Utils.readFromFile(new File(FILE_NAME));
        System.out.println(product);
        return product;
    }

    public static void upDateProduct() {
        viewProductFromFile();
        int productId = collectIntegerInPut("enter product you like to update") ;
        getProductByIndex(productId);
    }

    private static Product getProductByIndex(int productId) {
                ArrayList<Product> productList = new ArrayList<>();
                String name =collectStringInput("enter product name");
                Product.setName(name);
                int quantity =collectIntegerInPut("enter product quality");
                Product.setQuantity(quantity);
                double price  =collectIntegerInPut("enter product price");
                Product.setPrice(price);
                Product product = new Product(productId,name,quantity,price);
                productList.add(product);
                productList.addAll(productId, productList);
                return product;
    }

    private static int collectIntegerInPut(String massage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(massage);
        return scanner.nextInt();
    }
    private static String collectStringInput(String massage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(massage);
        return scanner.nextLine();
    }
}
