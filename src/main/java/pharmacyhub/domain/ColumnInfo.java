package pharmacyhub.domain;

public class ColumnInfo {
	
	private String columnName;
	private int columnValue;
	
	public ColumnInfo() {
		
	}
	
	public ColumnInfo(String columnName, int columnValue) {
		super();
		this.columnName = columnName;
		this.columnValue = columnValue;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(int columnValue) {
		this.columnValue = columnValue;
	}

}
