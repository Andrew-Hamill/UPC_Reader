import java.util.Scanner;
//Tester for BarcodeHelper.java. To use, run in BarcodeScanner.java and provide a sample bar code in the desired
//format. If the test code is invalid (ie. empty, null, or an incorrect format), an appropriate error will be given
//Otherwise, the bar code will be processed by BarcodeHelper.java and converted into the correct UPC format.
//The resulting UPC bar code will then be returned and printed for verification.
public class BarcodeScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Create Scanner object
        System.out.print("Enter barcode: ");
        String barcode = scanner.nextLine(); //Read user input

        try {
            BarcodeData parsedData = BarcodeHelper.parse(barcode);
            System.out.println("Processed Barcode Data:");
            System.out.println("Source: " + parsedData.getSource());
            System.out.println("UPC: " + parsedData.getUPC());
            System.out.println("Type: " + parsedData.getType());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());//Error handler
        }

        scanner.close(); // Close the scanner
    }
}