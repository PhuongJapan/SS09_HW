package ra.entity;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;

import java.util.Scanner;

public class Categories_231003 {
    private int catalogId;
    private String catalogName;
    private String description;
    private boolean catalogStatus;

    public Categories_231003(int catalogId, String catalogName, String description, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.catalogStatus = catalogStatus;
    }

    public Categories_231003() {
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner, Categories_231003[] arrCategories, int index) {
        //Sinh ra mã danh mục
        int max = 1;
        for (int i = 0; i < index; i++) {
            if (arrCategories[i].getCatalogId() > max) {
                max = arrCategories[i].getCatalogId();
            }
        }
        this.catalogId = max + 1;
        //Nhập têm danh mục
        boolean isExit = true;
        System.out.println("Nhập vào teen danh mục:");
        do {
            this.catalogName = scanner.nextLine();
            //Check độ dài <50
            if (this.catalogName.length() < 50) {
                //Kiểm tra ko trùng lặp
                boolean isExist = false; //Không trùng
                for (int i = 0; i < index; i++) {
                    if (arrCategories[i].getCatalogName().equals(this.catalogName)) {
                        isExist = true; //Bị trùng lặp - đã tồn tại rồi
                        break;
                    }
                }
                if (isExist) {
                    System.out.println("Tên danh mục đã tồn tại, vui lòng nhập");
                } else {
                    break;
                }
            } else {
                System.err.println("Tên danh mục có độ dài nhỏ hơn 50");
            }
            //
        }while(true);
        //Nhập mô tả danh mục
        System.out.println("Nhập mô tả danh mục");
        do {
            String status = scanner.nextLine();
            if (status.equals("true")||status.equals("false")){
                this.catalogStatus= Boolean.parseBoolean(status);

            }
        }while (true);

    }
}
