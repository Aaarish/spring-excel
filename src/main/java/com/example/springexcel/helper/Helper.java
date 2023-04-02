package com.example.springexcel.helper;

import com.example.springexcel.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

    public static boolean ifExcelFile(MultipartFile file) throws IOException{
        return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Product> convertExcelToList(MultipartFile file){
        List<Product> products = new ArrayList<>();

        try{
            InputStream is = file.getInputStream();

            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
            int row_num = 0;

            while(rows.hasNext()){
                Row row = rows.next();

                if(row_num == 0){
                    //skipping the header of excel file
                    row_num++;
                    continue;
                }

                Product product = new Product();
                Iterator<Cell> cells = row.iterator();
                int cell_num = 0;

                while(cells.hasNext()){
                    Cell cell = cells.next();

                    switch (cell_num){
                        case 0:
                            product.setProductId((int)cell.getNumericCellValue());
                            break;
                        case 1:
                            product.setProductName(cell.getStringCellValue());
                            break;
                        case 2:
                            product.setProductDesc(cell.getStringCellValue());
                            break;
                        case 3:
                            product.setProductPrice((int)cell.getNumericCellValue());
                            break;
                    }

                    cell_num++;
                }

                products.add(product);
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

        return products;
    }
}
