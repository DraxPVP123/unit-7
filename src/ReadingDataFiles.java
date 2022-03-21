import java.io.File;
import java.util.Scanner;

public class ReadingDataFiles {
    public static void main(String[] args) {

        readingFilesOne();
        readingFilesTwo();
        readingFilesThree();
        readingFilesFour();
    }
    public static void  readingFilesOne() {
        try {
            Scanner inFile = new Scanner(new File("Inventory.txt"));
            String item;
            int originalNumber;
            double price;
            System.out.println("Inventory.txt:");

            while (inFile.hasNext()) {
                item = inFile.nextLine();
                System.out.print(item + "\t");
                originalNumber = Integer.parseInt(inFile.nextLine());
                System.out.print(originalNumber + "\t");
                price = Double.parseDouble(inFile.nextLine());
                System.out.println(price);
            }
            inFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readingFilesTwo() {
        try {
            Scanner inFile = new Scanner(new File("Numbers.txt"));
            int intNum;
            double doubleNum;
            System.out.println("Numbers.txt:");

            while (inFile.hasNext()) {
                intNum = inFile.nextInt();
                System.out.print(intNum + "\t");
                doubleNum = inFile.nextDouble();
                System.out.print(doubleNum + "\t");
                System.out.println();
            }
            inFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readingFilesThree() {
        try {
            Scanner inFile = new Scanner(new File("Inventory2.txt"));
            String nextLine;
            String[] newData;
            System.out.println("Inventory2.txt:");

            while (inFile.hasNext()) {
                nextLine = inFile.nextLine();
                newData = nextLine.split(" ");
                System.out.println(newData[0] + "\t"
                        + Integer.parseInt(newData[1]) + "\t"
                        + Double.parseDouble(newData[2]));
            }
            inFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void readingFilesFour() {
        int counter = 0;
        String[] item = new String[3];
        int[] originalNumber = new int[3];
        double[] price = new double[3];
        try {
            Scanner inFile = new Scanner(new File("Inventory.txt"));

            System.out.println("Inventory.txt:");

            while (inFile.hasNext()) {
                item[counter] = inFile.nextLine();
                originalNumber[counter] = Integer.parseInt(inFile.nextLine());
                price[counter] = Double.parseDouble(inFile.nextLine());
                counter += 1;
            }
            inFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(item[i] + "\t" + originalNumber[i] + "\t" + price[i]);
        }
    }
}