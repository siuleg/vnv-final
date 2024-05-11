package utep.VandV;

import utep.VandV.SinglyLinkedListCarVendor;

public class CarVendorMap {



	/*@
	  @ invariant (\forall SinglyLinkedListCarVendor n; \reach(this.header, SinglyLinkedListCarVendor, next).has(n); \reach(n.next, SinglyLinkedListCarVendor, next).has(n)==false);
	  @ invariant size == \reach(header, SinglyLinkedListCarVendor, next).int_size();
      @ invariant (\forall SinglyLinkedListCarVendor n1; \reach(this.header, SinglyLinkedListCarVendor, next).has(n1); (\forall SinglyLinkedListCarVendor n2; \reach(this.header, SinglyLinkedListCarVendor, next).has(n2); n1 != n2 ==> n1.car != n2.car));
      @ invariant (\forall SinglyLinkedListCarVendor n; \reach(this.header, SinglyLinkedListCarVendor, next).has(n); n.car != null);
      @ invariant (\forall SinglyLinkedListCarVendor n; \reach(this.header, SinglyLinkedListCarVendor, next).has(n); n.vendor != null);
	  @*/   

	public /*@nullable@*/ SinglyLinkedListCarVendor header;

	public int size;

	//@ requires true;
	public CarVendorMap() {
	}


	/*@ requires true;
	  @ ensures \result == true <==> header == null;
	  @*/
	public boolean isEmpty() {
		return header == null;
	}

	/*@ 
	      @ requires true;
	      @ ensures (\exists SinglyLinkedListCarVendor n; \reach(this.header, SinglyLinkedListCarVendor, next).has(n); n.car==valueParam) ==> (\result==true);
	      @ ensures (\result == true) ==> (\exists SinglyLinkedListCarVendor n; \reach(this.header, SinglyLinkedListCarVendor, next).has(n); n.car==valueParam);
	      @ signals (RuntimeException e) false;
	      @*/
	public boolean containsKey( /*@nullable@*/Car valueParam ) {
		SinglyLinkedListCarVendor current;
		boolean result;
		current = this.header; 													//mutGenLimit 1
		result = false; 														//mutGenLimit 1
		while (result == false && current != null) { 							//mutGenLimit 1
			boolean equalVal;
			if (valueParam == null && current.car == null) { 					//mutGenLimit 1
				equalVal = true;												//mutGenLimit 1
			} else {
				if (valueParam != null) { 										//mutGenLimit 1
					if (valueParam == current.car) { 							//mutGenLimit 1 
						equalVal = true; 										//mutGenLimit 1
					} else {
						equalVal = false; 										//mutGenLimit 1
					}
				} else {
					equalVal = false; 											//mutGenLimit 1
				}
			}
			if (equalVal == true) { 											//mutGenLimit 1
				result = true; 													//mutGenLimit 1
			}
			current = current.next; 											//mutGenLimit 1
		}
		return result; 															//mutGenLimit 1

	}


	/*@ 
    @ requires key != null;
    @ ensures (\forall Vendor v; (\exists SinglyLinkedListCarVendor n; 
    @								\reach(this.header, SinglyLinkedListCarVendor, next).has(n); 
    @									n.car == key && v == n.vendor); 
    @												\result == v);
    @ signals (RuntimeException e) false;
    @*/
	public Vendor getValue( Car key ) {
		SinglyLinkedListCarVendor current;
		Vendor result;
		current = this.header; 													
		result = null; 													
		while (result == null && current != null) { 														
			if (key == current.car) { 							 
				result = current.vendor; 										
			} 										

			current = current.next; 											
		}
		return result; 															
	}


	/*@ requires (\forall SinglyLinkedListCarVendor n; \reach(this.header, SinglyLinkedListCarVendor, next).has(n); n.car != car);
	  @ ensures (\exists SinglyLinkedListCarVendor n; \reach(this.header, SinglyLinkedListCarVendor, next).has(n); n.vendor == vendor && n.car == car && n.next == null);
	  @ ensures (\forall SinglyLinkedListCarVendor n; \reach(this.header, SinglyLinkedListCarVendor, next).has(n); n.next != null ==> \old(\reach(this.header, SinglyLinkedListCarVendor, next)).has(n));
	  @ ensures (\forall SinglyLinkedListCarVendor n; \old(\reach(this.header, SinglyLinkedListCarVendor, next)).has(n); \reach(this.header, SinglyLinkedListCarVendor, next).has(n) && n.next != null);
	  @ signals (Exception e) false;
	  @*/    
	public void insertBack(Car car, Vendor vendor) {
		SinglyLinkedListCarVendor freshNode = new SinglyLinkedListCarVendor();
		freshNode.car = car; 
		freshNode.vendor = vendor;
		freshNode.next = null; 
		if (this.header == null) { 
			this.header = freshNode; 
		} else {
			SinglyLinkedListCarVendor current;
			current = this.header; 
			while (current.next != null) { 
				current = current.next;
			}
			current.next = freshNode;
		}
		size++;
	}

}



