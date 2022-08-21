package Table;

/**
 * Represents a table in the restaurant
 */
public class Table {
	/**
	 * The unique table identifier.
	 */
	private int tableNumber;
	/**
	 * seating capacity of the table.
	 */
	private int capacity;
	/**
	 * The current status of the table.
	 */
	private TableStatus status;

	public Table() {}
  /**
   * Create a table object.
    * @param tableNumber This Table's number.
    * @param capacity This table's capacity.
    */
	public Table(int tableNumber, int capacity) {
		this.tableNumber = tableNumber;
		this.capacity = capacity;
		this.status = TableStatus.EMPTY;
	}
  /**
    * Gets the table number . 
    * @return this is the table number.
    */
	public int getTableNumber() {
		return tableNumber;
	}
  /**
   * Sets the table number . 
   * @param tableNumber This is the table number.
   */

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
  /**
   * Gets the capacity of a table . 
   * @return this is the table's capacity.
   */
	public int getCapacity() {
		return capacity;
	}
  /**
   * Sets the capacity of the table. 
   * @param capacity this is the table's  capacity.
   */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
  /**
   * Gets the status of the table . 
   * @return this is the  table's status.
   */
	public TableStatus getStatus() {
		return status;
	}
  /**
   * sets the table's name . 
   * @param status this is the table's status.
   */
	public void setStatus(TableStatus status) {
		this.status = status;
	}
}