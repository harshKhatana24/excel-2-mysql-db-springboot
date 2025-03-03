package com.algorithmavengers.excel2database.Services;

import com.algorithmavengers.excel2database.Entities.Customer;
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
import java.util.Objects;

public class ExcelUploadService {
    //file excel file ha ya nhi yea check kiya first
    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
    //customer ka data fetch kar rhe hai excel file se
    public static List<Customer> getCustomerDataFromExcel(InputStream inputStream){
        List<Customer> customers=new ArrayList<>();
        try {
            //data fetch karna hai excel file se
            XSSFWorkbook workbook=new XSSFWorkbook(inputStream);//yea workbook hai
            XSSFSheet sheet=workbook.getSheet("customers");//jisse humne customer nam ke sheet fetch ki
            int rowIndex=0;//intially 0th row se start karenge
            for (Row row:sheet){//sheet ke har row mea jayenge and data set karenge 1-1 customer ka and then list mea customer ko save karenge
                if (rowIndex==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator=row.iterator();
                int cellIndex=0;
                Customer customer=new Customer();
                while (cellIterator.hasNext()){//jab tak ek row mea cell hai tab tak values customer mea add karenge har cell ki
                    Cell cell=cellIterator.next();
                    switch (cellIndex){
                        case 0 -> customer.setId((int) cell.getNumericCellValue());
                        case 1 -> customer.setFirstName(cell.getStringCellValue());
                        case 2 -> customer.setLastName(cell.getStringCellValue());
                        case 3 -> customer.setCountry(cell.getStringCellValue());
                        case 4 -> customer.setTelephone((int) cell.getNumericCellValue());
                        default -> {

                        }
                    }
                    cellIndex++;
                }
                customers.add(customer);//list mea add kardenge customer
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return customers;
    }
}
