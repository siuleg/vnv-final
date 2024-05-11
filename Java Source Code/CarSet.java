package utep.VandV;

import utep.VandV.SinglyLinkedListCarNode;

public class CarSet {



	/*@
	  @ invariant (\forall SinglyLinkedListCarNode n; \reach(this.header, SinglyLinkedListCarNode, next).has(n); \reach(n.next, SinglyLinkedListCarNode, next).has(n)==false);
	  @ invariant (\forall SinglyLinkedListCarNode n1; \reach(this.header, SinglyLinkedListCarNode, next).has(n1); (\forall SinglyLinkedListCarNode n2; \reach(this.header, SinglyLinkedListCarNode, next).has(n2); n1 != n2 ==> n1.value != n2.value ));
	  @ invariant size == \reach(this.header, SinglyLinkedListCarNode, next).int_size();
	  @*/   

	public /*@nullable@*/ SinglyLinkedListCarNode header;

	public int size;
	
	
	//@ requires true;
	public CarSet() {
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
	      @ ensures (\exists SinglyLinkedListCarNode n; \reach(this.header, SinglyLinkedListCarNode, next).has(n); n.value==valueParam) ==> (\result==true);
	      @ ensures (\result == true) ==> (\exists SinglyLinkedListCarNode n; \reach(this.header, SinglyLinkedListCarNode, next).has(n); n.value==valueParam);
	      @ signals (RuntimeException e) false;
	      @*/
	public boolean contains( /*@nullable@*/Car valueParam ) {
		SinglyLinkedListCarNode current;
		boolean result;
		current = this.header; 													//mutGenLimit 1
		result = false; 														//mutGenLimit 1
		while (result == false && current != null) { 							//mutGenLimit 1
			boolean equalVal;
			if (valueParam == null && current.value == null) { 					//mutGenLimit 1
				equalVal = true;												//mutGenLimit 1
			} else {
				if (valueParam != null) { 										//mutGenLimit 1
					if (valueParam == current.value) { 							//mutGenLimit 1 
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
	      @ requires index>=0 && index<\reach(this.header, SinglyLinkedListCarNode, next).int_size();
	      @
	      @ ensures \reach(this.header, SinglyLinkedListCarNode, next).has(\result)==true;
	      @ ensures \reach(\result, SinglyLinkedListCarNode, next).int_size() == \reach(this.header, SinglyLinkedListCarNode, next).int_size()-index;
	      @ signals (RuntimeException e) false;
	      @*/    
	public SinglyLinkedListCarNode getNode( int index ) {
		SinglyLinkedListCarNode current = this.header; 		
		SinglyLinkedListCarNode result = null; 				
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
	      @ ensures (\exists SinglyLinkedListCarNode n; \reach(this.header, SinglyLinkedListCarNode, next).has(n); n.value == arg && n.next == null);
	      @ ensures (\forall SinglyLinkedListCarNode n; \reach(this.header, SinglyLinkedListCarNode, next).has(n); n.next != null ==> \old(\reach(this.header, SinglyLinkedListCarNode, next)).has(n));
	      @ ensures (\forall SinglyLinkedListCarNode n; \old(\reach(this.header, SinglyLinkedListCarNode, next)).has(n); \reach(this.header, SinglyLinkedListCarNode, next).has(n) && n.next != null);
	      @ signals (Exception e) false;
	      @*/    
	public void insertBack( Car arg ) {
		SinglyLinkedListCarNode freshNode = new SinglyLinkedListCarNode();
		freshNode.value = arg; 
		freshNode.next = null; 
		if (this.header == null) { 
			this.header = freshNode; 
		} else {
			SinglyLinkedListCarNode current;
			current = this.header; 
			while (current.next != null) { 
				current = current.next;
			}
			current.next = freshNode;
		}
		size++;
	}

}



