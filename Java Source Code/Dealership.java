package utep.VandV;

import utep.VandV.BrandSet;
import utep.VandV.VendorSet;
import utep.VandV.CarVendorMap;

public class Dealership {

	public BrandSet brands;
	public VendorSet vendors;
	public CarSet stock;
	public CarVendorMap sold;

	/*@ invariant (\forall Car c; 
	@		(\exists SinglyLinkedListCarNode n; \reach(stock.header, SinglyLinkedListCarNode, next).has(n); n.value == c);
	@		(\exists SinglyLinkedListBrandNode n; \reach(brands.header, SinglyLinkedListBrandNode, next).has(n); n.value == c.brand));
    @   invariant stock.size >= 5;
    @   invariant (\forall SinglyLinkedListCarNode n; \reach(stock.header, SinglyLinkedListCarNode, next).has(n); 
    @         (\forall SinglyLinkedListCarVendor cv; \reach(sold.header, SinglyLinkedListCarVendor, next).has(cv); cv.car != n.value));
    @*/

	//@ requires brandSet != null;
	//@ requires vendorsSet != null;
	//@ requires stockCars.size >= 5;
	//@ ensures this.brands == brandSet;
	//@ ensures this.vendors == vendorsSet;
	//@ ensures this.stock == stockCars;
	//@ ensures this.stock.size > 0;
	//@ signals (Exception) false;
	//@ signals (AssertionError) false;
	public Dealership(BrandSet brandSet, VendorSet vendorsSet, CarSet stockCars) {
		// Here you need to add the code for the constructor.
		this.brands = brandSet;
		this.vendors = vendorsSet;
		this.stock = stockCars;
		this.sold = new CarVendorMap();
	}


	/*@ requires stock.size >= 6;
	  @ requires (\exists SinglyLinkedListCarNode n; \reach(stock.header, SinglyLinkedListCarNode, next).has(n) == true; n.value == c);
	  @ requires (\exists SinglyLinkedListVendorNode n; \reach(vendors.header, SinglyLinkedListVendorNode, next).has(n) == true; n.value == v);
	  @ ensures (\forall SinglyLinkedListCarNode n; \reach(stock.header, SinglyLinkedListCarNode, next).has(n)==true; n.value != c);
	  @ ensures (\exists SinglyLinkedListCarVendor cv; \reach(sold.header, SinglyLinkedListCarVendor, next).has(cv)==true; cv.car == c && cv.vendor == v);
	  @*/
	public void sell(Car c, Vendor v) {
		//Add here the code for method "sell".
		if (!stock.size >= 6) {
			return;
		}
		if (!stock.contains(c)) {
			return;
		}		

		SinglyLinkedListCarNode prev = null;
		SinglyLinkedListCarNode current = stock.header;
		while (current != null && current.value != c) {
			prev = current;
			current = current.next;
		}
		if (current != null) {
			if (prev == null) {
				stock.header = current.next;
			} else {
				prev.next = current.next;
			}
			stock.size--;
		}
	}


	/*@ requires (\forall SinglyLinkedListCarNode n; \reach(stock.header, SinglyLinkedListCarNode, next).has(n); n.value != c);
	  @
	  @*/
	public void buy(Car c) {
		//Add here the code for method "buy".
		//Unlike "sell", CarSet provides a method for adding
		//a car to a CarSet.
		if (stock.contains(c)) {
			return;
		}
		stock.insertBack(c);
	}

}

