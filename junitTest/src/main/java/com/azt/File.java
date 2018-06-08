package com.azt;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import java.io.*;

public class File {
	//��ȡExcel�е�����,i��j�ֱ��ʾ��ʼ���к���
	public void readExcel(String filePath,int row,int col) {
	    Workbook rwb = null;
	    Cell cell = null;
		try {
			//����������
			InputStream stream = new FileInputStream(filePath);
			//��ȡExcel�ļ�����
		    rwb = Workbook.getWorkbook(stream);
		    //��ȡ�ļ���ָ�������� Ĭ�ϵĵ�һ��
		    Sheet sheet = rwb.getSheet(0); 
		    String[][] str = new String[sheet.getRows()][sheet.getColumns()];
		    //i��ʾ�У�j��ʾ��
		    int x = 0;
		    for(int i = row;i<sheet.getRows();i++) {
		    	int y = 0;
		    	for(int j = col;j<sheet.getColumns();j++) {
		    		//ע���ȡ��Ԫ������ʱ������������ʾ��������
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
