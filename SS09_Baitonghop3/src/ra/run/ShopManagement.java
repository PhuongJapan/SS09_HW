package ra.run;

import jdk.nashorn.internal.runtime.SharedPropertyMap;
import ra.entity.Categories;
import ra.entity.Product;

import java.text.ParseException;
import java.util.Scanner;

public class ShopManagement {
    public static Scanner scanner = new Scanner(System.in);
    public static Categories[] arrCategories = new Categories[100];
    public static Product[] arrProduct = new Product[100];
    public static int indexOfCategories = 0;
    public static int indexOfProduct = 0;

    public static void main(String[] args) throws ParseException {
        do {
            System.out.println("******************SHOP MENU*******************\n");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("Lựa chọn của bạn là: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ShopManagement.menuCatalog();
                    break;
                case 2:
                    ShopManagement.menuProduct();
                    break;
                case 3:
                    System.exit(0);
            }


        }
        while (true);
    }

    //Xây dựng 1 phương thức quản lý danh mục
    public static void menuCatalog() {
        do {
            System.out.println("********************CATEGORIES MENU***********");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.println("Lựa chọn của bạn là: ");
            int choice = Integer.parseInt(scanner.nextLine());
            boolean checkExit = false;
            switch (choice) {
                case 1:
                    ShopManagement.inputDataOfCatalog();
                    break;
                case 2:
                    ShopManagement.displayDataOfCatalog();
                    break;
                case 3:
                    ShopManagement.updateDataOfCatalog();
                    break;
                case 4:
                    ShopManagement.deleteCatalog();
                    break;
                case 5:
                    ShopManagement.updateStatusOfCatalog();
                    break;
                case 6:
                    checkExit = true;
                    break;
            }
            if (checkExit) {
                break;
            }

        } while (true);
    }

    public static void inputDataOfCatalog() {
        System.out.println("Số lượng danh mục cần nhập: ");
        int numberOfCatalog = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfCatalog; i++) {
            arrCategories[indexOfCategories] = new Categories();
            //Khai báo khởi tạo đối tượng cho phần từ arrCategories[i]
            arrCategories[indexOfCategories].inputData(scanner, arrCategories, indexOfCategories);
            //Từ phẩn tử đối tượng vừa khởi tạo gọi đến phương thức inputData và truyền vào các đối số tương ứng
            indexOfCategories++;
        }
    }

    public static void displayDataOfCatalog() {
        for (int i = 0; i < indexOfCategories; i++) {
            if (arrCategories[i] == null) {
                continue;
            }
            //Ko cần khởi tạo vì các phần tử đã nhập đã là đối tượng
            arrCategories[i].displayData();

        }
        System.out.println(arrCategories.length);
    }

    public static void updateDataOfCatalog() {
        System.out.println("Nhập mã danh mục cần cập nhật thông tin");
        String updateCatalogId = scanner.nextLine();
        boolean catalogFound = false;
        for (int i = 0; i < indexOfCategories; i++) {
            if (arrCategories[i] != null && Integer.toString(arrCategories[i].getCatalogId()).equals(updateCatalogId)) {
                arrCategories[i].inputData(scanner, arrCategories, i);
                catalogFound = true;
                break;
            }
        }
        if (!catalogFound) {
            System.out.println("Mã danh mục không tồn tại");
        }
    }

    public static void deleteCatalog() {
        System.out.println("Nhập mã danh mục cần xóa");
        int deleteCatalogId = Integer.parseInt(scanner.nextLine());
        boolean catalogDelete = false;
        int indexToDelete = -1;
        for (int i = 0; i < indexOfCategories; i++) {
            if (arrCategories[i] != null && arrCategories[i].getCatalogId() == deleteCatalogId) {
//                if (arrCategories[i].isEmptyCatalog()){
                //Chỉ xóa Categories khi ko có sản phẩm (product) nào trong đó
//                arrCategories[i]=null;
                //Cách làm này sai, cần dùng cách xóa khác
                catalogDelete = true;
                indexToDelete = i;
//                    indexOfCategories--;
                System.out.println("Danh mục đã được xóa");
                break;

//                }
//                else {
//                    System.out.println("Không thể xóa");
//                }
            }
        }

        if (indexToDelete != -1) {
            Categories[] newArrCategories = new Categories[arrCategories.length];
            //Tạo mảng mới có độ dài nhỏ hơn mảng cũ 1 đơn vị
//            for(int i=0,j=0;i<arrCategories.length-1;i++){
//                if (arrCategories[i]==null){
//                    continue;
//
//                }else {
//                    newArrCategories[j]=arrCategories[i];
//                    j++;
//                }
//            }
//            for (int i=0;i<arrCategories.length-1;i++){
//                arrCategories[i]=newArrCategories[i];
//            }
            //To Do later

            System.arraycopy(arrCategories, 0, newArrCategories, 0, indexToDelete);
            System.arraycopy(arrCategories, indexToDelete + 1, newArrCategories, indexToDelete, arrCategories.length - indexToDelete - 1);
            arrCategories = newArrCategories;
        }

        for (Categories category : arrCategories) {
            if (category != null) {
                System.out.println(category.getCatalogId() + ":" + category.getCatalogName());
            }
        }
        if (!catalogDelete) {
            System.out.println("Mã danh mục không tồn tại");
        }
    }
    public static void updateStatusOfCatalog() {
        System.out.println("Nhập mã danh mục cần cập trạng thái");
        int catalogIdToUpdateStatus = Integer.parseInt(scanner.nextLine());
        boolean statusCatalogFound = false;
        for (int i = 0; i < indexOfCategories; i++) {
            if (arrCategories[i] != null && arrCategories[i].getCatalogId()==catalogIdToUpdateStatus) {
                boolean newStatus= !arrCategories[i].isStatus();
                arrCategories[i].setCatologStatus(newStatus);
                statusCatalogFound = true;
                break;
            }
        }
        if (!statusCatalogFound) {
            System.out.println("Không tìm thấy sản phẩm có mã:"+catalogIdToUpdateStatus);
        }
        System.out.println("Danh mục có mã: "+ catalogIdToUpdateStatus+ "đã được cập nhật trạng thái");
    }
    public static void menuProduct() {
        do {
            System.out.println("********************PRODUCT MANAGEMENT***********");
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp các sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("6. Tìm kiếm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)");
            System.out.println("8. Thoát");
            System.out.println("Lựa chọn của bạn là: ");
            int choice = Integer.parseInt(scanner.nextLine());
            boolean checkExit = false;
            switch (choice) {

                case 1:
                    ShopManagement.inputDataOfProducts();
                    break;
                case 2:
                    ShopManagement.displayDataOfProduct();
                    break;
                case 3:
                    ShopManagement.sortDataOfProduct();
                    break;
                case 4:
                    ShopManagement.updateDataOfProduct();
                    break;
                case 5:
                    ShopManagement.deleteProduct();
                    break;
                case 6:
                    ShopManagement.searchProduct();
                    break;
                case 7:
                    ShopManagement.searchPrice();
                    break;
                case 8:
                    checkExit = true;
                    break;
            }
            if (checkExit) {
                break;
            }

        } while (true);
    }

    public static void inputDataOfProducts() {
        System.out.println("Số lượng sản phẩm cần nhập: ");
        int numberOfProducts = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfProducts; i++) {
            arrProduct[indexOfProduct] = new Product();
//            Khai báo khởi tạo đối tượng cho phần từ arrCategories[i]
            arrProduct[indexOfProduct].inputData(scanner, arrProduct, indexOfProduct, arrCategories, indexOfCategories);
//            Từ phẩn tử đối tượng vừa khởi tạo gọi đến phương thức inputData và truyền vào các đối số tương ứng
            indexOfProduct++;
        }
    }

    public static void displayDataOfProduct() {
        for (int i = 0; i < indexOfProduct; i++) {
            //Ko cần khởi tạo vì các phần tử đã nhập đã là đối tượng
            arrProduct[i].displayData();
        }
    }
    public static void sortDataOfProduct(){
        //Sắp xếp sản phẩm theo giá
        for (int i = 0; i < indexOfProduct-1; i++) {
            for (int j = i+1; j < indexOfProduct; j++) {
                if (arrProduct[i].getPrice()<arrProduct[j].getPrice()){
                    Product temp = arrProduct[i];
                    arrProduct[i]= arrProduct[j];
                    arrProduct[j]=temp;
                }
            }

        }
        System.out.println("Đã sắp xếp sản phẩm theo giá tăng dần");

    }

    public static void updateDataOfProduct() {
        System.out.println("Nhập mã sản phẩm cần cập nhật thông tin");
        String updateProductId =scanner.nextLine();
        boolean productIdFound= false;
        for (int i = 0; i < indexOfProduct; i++) {
            if (arrProduct[i]!=null && arrProduct[i].getProductId().equals(updateProductId)){
                arrProduct[i].inputData(scanner, arrProduct, indexOfProduct, arrCategories,indexOfCategories);
                productIdFound =true;
                break;
            }
        }
        if (!productIdFound){
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }
    public static void deleteProduct() {
        System.out.println("Nhập mã sản phẩm cần xoá: ");
        String deleteId = scanner.nextLine();
        for (int i = 0; i < indexOfProduct; i++) {
            if (arrProduct[i].getProductId().equals(deleteId)) {
                //để dịch chuyển các sản phẩm phía sau của sản phẩm bị xoá lên trước
                for (int j = i; j < indexOfProduct; j++) {
                    arrProduct[j] = arrProduct[j + 1];
                }
                indexOfProduct--;
            }
        }
        System.out.println("Đã xoá xong");
    }
    public static void searchProduct() {
        System.out.println("Nhập tên sản phẩm cần tìm kiếm: ");
        String searchProductName = scanner.nextLine();
        System.out.println("Thông tin sản phẩm bạn cần tìm kiếm như sau:");
        for (int i = 0; i < indexOfProduct; i++) {
            if (arrProduct[i].getProductName().contains(searchProductName)) {
                //để dịch chuyển các sản phẩm phía sau của sản phẩm bị xoá lên trước
                arrProduct[i].displayData();
            }
        }
    }
    public static void searchPrice(){
        System.out.println("Bạn cần tìm sản phẩm có giá từ: ");
        float a = Float.parseFloat(scanner.nextLine());
        System.out.println("Bạn cần tìm sản phẩm có giá đến: ");

        int count = 0;
        float b = Float.parseFloat(scanner.nextLine());
        for (int i = 0; i < indexOfProduct; i++) {
            if (arrProduct[i].getPrice()>=a && arrProduct[i].getPrice()<=b){
                arrProduct[i].displayData();
                count++;
            }
        }
        System.out.println("Có"+ count + " sản phẩm có giá trong khoảng bạn cần tìm kiếm: ");
    }
}
