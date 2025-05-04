package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="productList")
	public String [][] getData() throws IOException{
		String path = "./testdata/ProductList.xlsx";
		
		ExcelUtility xl = new ExcelUtility(path);
		
		int totalRows = xl.getRowCount("Sheet1");
		int totalColumns = xl.getCellCount("Sheet1", 1);
		
		String[][] itemList = new String[totalRows][totalColumns];
		
		for(int i=1;i<=totalRows;i++) {
			for (int j=0;j<totalColumns;j++) {
				itemList[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
			
		}
		
		return itemList;
		
	}
	
}
