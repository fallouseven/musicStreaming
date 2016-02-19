package SERVICES_STREAM;

public class KeepValue{
	private int  rowSelected ;
	private int rowSelected1;
	private int rowSelected2;
	KeepValue(){
		this.setRowSelected(-1);
		this.setRowSelected1(-1);
		this.setRowSelected2(-1);
	}
	/**
	 * @return the rowSelected
	 */
	public int getRowSelected() {
		return rowSelected;
	}
	/**
	 * @param rowSelected the rowSelected to set
	 */
	public void setRowSelected(int rowSelected) {
		this.rowSelected = rowSelected;
	}
	/**
	 * @return the rowSelected1
	 */
	public int getRowSelected1() {
		return rowSelected1;
	}
	/**
	 * @param rowSelected1 the rowSelected1 to set
	 */
	public void setRowSelected1(int rowSelected1) {
		this.rowSelected1 = rowSelected1;
	}
	/**
	 * @return the rowSelected2
	 */
	public int getRowSelected2() {
		return rowSelected2;
	}
	/**
	 * @param rowSelected2 the rowSelected2 to set
	 */
	public void setRowSelected2(int rowSelected2) {
		this.rowSelected2 = rowSelected2;
	}
}