package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String description;
    //
    private Date created;
    private int catalogId;

    private int productStatus;

    public Product(String productId, String productName, float price, String description, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public Product() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner, Product[] arrProduct, int indexOfProduct, Categories[] arrCategories, int indexOfCategories) {
        System.out.println("Nhập thông tin của sản phẩm");
//        /gồm 4 ký tự bắt đầu là một trong 3 ký tự (C: các đồ uống là café,
//                S: các đồ uống là sinh tố, A: các đồ ăn nhanh), không được trùng lặp/
        System.out.println("Nhập vào mã đồ uống: ");
        do {
            this.productId = scanner.nextLine();
            boolean checkProductId = false;
            if (productId.length() == 4) {
                if (productId.startsWith("C")|| productId.startsWith("S")||productId.startsWith("A")) {
                    //không được trùng lặp
                    for (int i = 0; i < indexOfProduct; i++) {
                        if (this.productId.equals(arrProduct[i].getProductId())) {
                            checkProductId = true;
                            break;
                        }
                    }
                    if (checkProductId) {
                        System.err.println("Đã tồn tại mã sản phẩm, vui lòng nhập lại!");

                    }else {
                        //nếu thoả mãn thì vào đây
                        //check = true;
                        break;
                    }
                } else {
                    System.err.println("C: các đồ uống là café,S: các đồ uống là sinh tố, A: các đồ ăn nhanh, vui lòng nhập lại!");
                }
            } else
                System.err.println("Độ dài phải đủ 4 ký tự, vui lòng nhập lại!");
        } while (true);
//        boolean checkProductId = true;
//        System.out.println("Nhập thông tin sản phẩm");
//        do {
//            this.productId = scanner.nextLine();
//            boolean isProductIdExist = false;
//            if (indexOfProduct==1){
//                isProductIdExist=false;
//                checkProductId = false;
//
//            }else {
//                for (int i = 0; i < indexOfProduct; i++) {
//                    if (arrProduct[i].productId.equals(this.productId)) {
//                        System.out.println("Đã tồn tại mã sản phẩm, vui lòng nhập lại");
//                        isProductIdExist = true;
//                        break;
//                    }
//                }
//            }
//            if (!isProductIdExist) {
////
//                if (this.productId.length() == 4 && Pattern.matches("[CSA]",this.productId.substring(0,1))){
////                        && (this.productId.startsWith("C") || this.productId.startsWith("S") || this.productId.startsWith("A"))) {
//                    //Kiểm tra bắt đầu ký tự
////                    if (this.productId.startsWith("C") || this.productId.startsWith("S") || this.productId.startsWith("A")) {
////                        //Check ko trùng lặp
//                    checkProductId=false;
//                        //Mã sản phẩm hợp lệ thoát khỏi vòng lặp//
//                    } else {
//                        System.err.println("Vui lòng nhập mã có 4 ký tự và bắt đầu là C hoặc S hoặc A");//
//                    }
//                }
//            }while (checkProductId);


        System.out.println("Nhập tên sản phẩm");
        this.productName = scanner.nextLine();

        System.out.println("Nhập giá sản phẩm");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập mô tả sản phẩm");
        this.description = scanner.nextLine();
        System.out.println("Nhập ngày nhập sản phẩm với theo dd-MM-yyyy");
//        this.created= new SimpleDateFormat("dd-MM-yyyy").parse(scanner.nextLine());
        do {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String birthDateStr = scanner.nextLine();
            try {
                //or Data created
                this.created = sdf.parse(birthDateStr);
                break;
            } catch (Exception ex) {
                System.err.println("Ngày nhập sản phẩm không đúng định dạng, vui lòng  nhập lại!");
            }
        }while (true);
        System.out.println("Nhập mã danh mục");
        System.out.println("Chọn danh mục của sản phẩm ");
        //hiển thị ra các danh mục
        for (int i = 0; i < indexOfCategories; i++) {
            System.out.println(i + 1 + "." + arrCategories[i].getCatalogName());
        }
        System.out.println("Chọn tên danh mục trong danh sách");

        //Duyệt danh sách tên các danh mục trong mảng Catalog
//        for (int i = 0; i < indexOfCategories; i++) {
//            System.out.println((i + 1) + "." + arrCategories[i].getCatalogId());
//
//        }
        System.out.println("Lựa chọn của bạn");

        do{
            int number = Integer.parseInt(scanner.nextLine());
            if(number>=1 && number<=indexOfCategories){
                //Những number này thỏa mãn điều kiện thì cho phép nhập
                this.catalogId = arrCategories[number - 1].getCatalogId();
                break;
            }else {
                System.err.println("Mã danh mục không tồn tại, vui lòng nhập lại");
            }

        }while(true);

        System.out.println("Nhập trạng thái của sản phẩm");
        boolean checkStatus = false;
        do {
            int productStatusNumber = Integer.parseInt(scanner.nextLine());
            switch (productStatusNumber){
                case 0:
                    checkStatus = true;
                    this.productStatus = productStatusNumber;
                    break;
                case 1:
                    checkStatus = true;
                    this.productStatus = productStatusNumber;
                    break;
                case 2:
                    checkStatus = true;
                    this.productStatus = productStatusNumber;
                    break;
                default:
                    System.out.println("Vui lòng chỉ nhập từ 0-2");
            }
            if (checkStatus){
                break;
            }
        }while (true);
    }
    public void displayData(){
        String productStatusString = "";
        switch (this.productStatus){
            case 0:
                productStatusString = "Đang bán";
                break;
            case 1:
                productStatusString = "Hết hàng";
                break;
            case 2:
                productStatusString = "Không bán";
                break;
        }
        System.out.printf("Mã Sp: %s- Tên SP: %s - Giá SP: %.2f - Mô tả SP: %s- Ngày nhập SP: %s - " +
                        "Danh mục của SP: %s - Trạng thái của SP:%s\n",
                this.productId,this.productName,this.price,this.description,this.created,
                this.catalogId, productStatusString);

    }

}





