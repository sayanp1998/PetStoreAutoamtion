package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir")+"//src//test//resources//Userdata.xlsx";
        excelUtility xl = new excelUtility(path);

        int rowNum = xl.getRowCount("Sheet1");
        int columnCount = xl.getCellCount("Sheet1", rowNum);

        String apiData[][] = new String[rowNum][columnCount];

        for(int i=1; i<=rowNum; i++){
            for (int j=0; j<columnCount; j++){
                apiData[i-1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return apiData;
    }

    @DataProvider(name = "userNames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir")+"//src//test//resources//Userdata.xlsx";
        excelUtility xl = new excelUtility(path);
        int rowCount = xl.getRowCount("Sheet1");
        String[] apidata = new String[rowCount];

        for(int i=1;i<=rowCount; i++){
            apidata[i-1] = xl.getCellData("Sheet1", i,1);
        }
        return apidata;
    }
}
