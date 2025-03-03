
public class BarcodeHelper {
    public static BarcodeData parse(String source) {
        if (source == null || source.isEmpty()) {//Checks for invalid inputs (ie. empty or null input)
            throw new IllegalArgumentException("Barcode cannot be null or empty.");
        }

        BarcodeData barcodeData = new BarcodeData();//Creates new barcodeData object
        barcodeData.setSource(source);

        if (source.startsWith("002") && source.length() >= 10) { // Prefix 2 - Weighted Items
            barcodeData.setType(2);
            String plu = source.substring(3, 8);
            barcodeData.setUPC("002" + plu + "00000");//Formats PLU codes as the desired UPC format.
            
        } else if (source.length() >= 13 && Character.isDigit(source.charAt(0))) { //Normal UPC
            barcodeData.setType(1);
            barcodeData.setUPC(source.length() > 13 ? source.substring(source.length() - 13) : String.format("%013d", Long.parseLong(source)));
        } else if (source.startsWith("S") && source.length() >= 24) { //Sign bar code
            barcodeData.setType(3);
            barcodeData.setUPC(source.substring(source.length() - 13));//Uses only the last 13 digits of the sign bar code
        } else {
            throw new IllegalArgumentException("Invalid barcode format.");//If given code is neither null nor empty, but is still invalid, it's thrown out
        }

        return barcodeData;
    }
}

class BarcodeData { // Structure of the BarcodeData parse method
    private String source; // Initial string input received from the scanner
    private String upc; // The bar code format types used in the parse method
    private int type; // The bar code type: (UPC, PLU, or sign)

    public String getSource() {
        return source;//Getter method for string source
    }

    public void setSource(String source) {
        this.source = source;//Setter method for string source
    }

    public String getUPC() {
        return upc;//Getter method for string upc
    }

    public void setUPC(String upc) {
        this.upc = upc;//Setter method for string upc
    }

    public int getType() {
        return type;//Getter method for int type
    }

    public void setType(int type) {
        this.type = type;//Setter method for int type
    }
}


