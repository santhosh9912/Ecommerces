package wrkout;

import java.io.Serializable;

public class Cart extends Product implements Serializable {
	private int quantity;
	
	public Cart() {
		
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
