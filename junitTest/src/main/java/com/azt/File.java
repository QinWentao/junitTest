package com.azt;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import java.io.*;

public class File {
	//获取Excel中的数据,i，j分别表示起始的行和列
	public void readExcel(String filePath,int row,int col) {
	    Workbook rwb = null;
	    Cell cell = null;
		try {
			//创建输入流
			InputStream stream = new FileInputStream(filePath);
			//获取Excel文件对象
		    rwb = Workbook.getWorkbook(stream);
		    //获取文件的指定工作表 默认的第一个
		    Sheet sheet = rwb.getSheet(0); 
		    String[][] str = new String[sheet.getRows()][sheet.getColumns()];
		    //i表示行，j表示列
		    int x = 0;
		    for(int i = row;i<sheet.getRows();i++) {
		    	int y = 0;
		    	for(int j = col;j<sheet.getColumns();j++) {
		    		//注意获取单元格内容时，函数参数显示列再是行
		    		cell = sheet.getCell(j, i);
		    		str[x][y] = cell.getContents().toString();
		    		y++;
		    	}
		    	x++;
		    }
		    for(int i = 0;i<sheet.getRows() - row;i++) {
		    	for(int j = 0;j<sheet.getColumns() - col;j++) {
		    		System.out.print(str[i][j]);
		    	}
		    	System.out.print("\n");
		    }
		}catch(Exception e) {
			System.out.println("Fail to load excel:" + filePath);
			e.printStackTrace();
		}
	}
}
