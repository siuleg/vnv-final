import SinglyLinkedListBrandNode;

public class BrandSet {



	/*@
	  @ invariant (\forall SinglyLinkedListBrandNode n; \reach(this.header, SinglyLinkedListBrandNode, next).has(n); \reach(n.next, SinglyLinkedListBrandNode, next).has(n)==false);
	  @ invariant size == \reach(this.header, SinglyLinkedListBrandNode, next).int_size();
	  @*/   

	public /*@nullable@*/ SinglyLinkedListBrandNode header;
	
	public int size;

	//@ requires true;
	public BrandSet() {
		header = null;
		size = 0;
	}
	

	/*@ requires true;
	  @ ensures \result == true <==> header == null;
	  @*/
	public boolean isEmpty() {
		return header == null;
	}

	/*@ 
	      @ requires true;
	      @ ensures (\exists SinglyLinkedListBrandNode n; \reach(this.header, SinglyLinkedListBrandNode, next).has(n); n.value==valueParam) ==> (\result==true);
	      @ ensures (\result == true) ==> (\exists SinglyLinkedListBrandNode n; \reach(this.header, SinglyLinkedListBrandNode, next).has(n); n.value==valueParam);
	      @ signals (RuntimeException e) false;
	      @*/
	public boolean contains( /*@nullable@*/Brand valueParam ) {
		SinglyLinkedListBrandNode current;
		boolean result;
		current = this.header; 													
		result = false; 														
		while (result == false && current != null) { 							
			boolean equalVal;
			if (valueParam == null && current.value == null) { 					
				equalVal = true;												
			} else {
				if (valueParam != null) { 										
					if (valueParam == current.value) { 							 
						equalVal = true; 										
					} else {
						equalVal = false; 										
					}
				} else {
					equalVal = false; 											
				}
			}
			if (equalVal == true) { 											
				result = true; 													
			}
			current = current.next; 											
		}
		return result; 															
	}

	/*@
	      @ requires index>=0 && index<\reach(this.header, SinglyLinkedListBrandNode, next).int_size();
	      @
	      @ ensures \reach(this.header, SinglyLinkedListBrandNode, next).has(\result)==true;
	      @ ensures \reach(\result, SinglyLinkedListBrandNode, next).int_size() == \reach(this.header, SinglyLinkedListBrandNode, next).int_size()-index;
	      @ signals (RuntimeException e) false;
	      @*/    
	public SinglyLinkedListBrandNode getNode( int index ) {
		SinglyLinkedListBrandNode current = this.header; 		
		SinglyLinkedListBrandNode result = null; 				
		int current_index = 0; 
		while (result == null && current != null) {  							
			if (index == current_index) { 									
				result = current;  											
			}
			current_index = current_index + 1; 								
			current = current.next; 											
		}
		return result; 														
	}

	
	/*@ requires true;
	      @ ensures (\exists SinglyLinkedListBrandNode n; \reach(this.header, SinglyLinkedListBrandNode, next).has(n); n.value == arg && n.next == null);
	      @ ensures (\forall SinglyLinkedListBrandNode n; \reach(this.header, SinglyLinkedListBrandNode, next).has(n); n.next != null ==> \old(\reach(this.header, SinglyLinkedListBrandNode, next)).has(n));
	      @ ensures (\forall SinglyLinkedListBrandNode n; \old(\reach(this.header, SinglyLinkedListBrandNode, next)).has(n); \reach(this.header, SinglyLinkedListBrandNode, next).has(n) && n.next != null);
	      @ signals (Exception e) false;
	      @*/    
	public void insertBack( Brand arg ) {
		SinglyLinkedListBrandNode freshNode = new SinglyLinkedListBrandNode();
		freshNode.value = arg; 
		freshNode.next = null; 
		if (this.header == null) { 
			this.header = freshNode; 
		} else {
			SinglyLinkedListBrandNode current;
			current = this.header; 
			while (current.next != null) { 
				current = current.next;
			}
			current.next = freshNode;
		}
		size++;
	}

}



