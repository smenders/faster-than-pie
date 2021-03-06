package components;

import java.io.File;

import javax.swing.table.AbstractTableModel;

import ftp.FTPController;

public class LocalTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 2833873735851543443L;

	File[] data;
	private static final String[] columns = {"Name","Size"};

	public File[] getData(){
		return data;
	}
	
	public void setData(File[] data){
		if(data != null){
			this.data = new File[data.length+1];
			this.data[0] = null;
			for(int i = 0; i < data.length; i++) this.data[i+1] = data[i];
		}else{
			this.data = new File[1];
			this.data[0] = null;
		}
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int columnNumber){
		return columns[columnNumber];
	}
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		if(data == null) return 0;
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String value = "";
		switch(columnIndex){
			case 0:{

				if(data[rowIndex] == null) return "..";
				value = data[rowIndex].getName(); 
				break;
			}
			case 1:{

				if(data[rowIndex] == null) return "Previous Directory";
				value = FTPController.analyizeSize(data[rowIndex].length(),data[rowIndex].isDirectory(),0) + ""; break;
			}
		}
		return value;
	}
	@Override
    public boolean isCellEditable(int row, int columnIndex)
    {
        return false;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columns[columnIndex].getClass();
    }
    


}
