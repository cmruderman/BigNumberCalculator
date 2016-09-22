import java.lang.*; //needed for the method that gets the numerical value of each character
import java.text.*;
import java.util.*;
public class BigNumberLL {
	
	public Link first; // reference to first link
	public Link last; // reference to last link
	private int size = 0; // size of number (# of links)
	private boolean isPositive = true; //sign of big number

	//simple constructor
	BigNumberLL(){
		first = null;
		last = null;
		this.isPositive = true; //assumes positive number
	}
	// a constructor that takes a string and converts it into a BigNumber
	BigNumberLL(String a){ //
		this.size = a.length(); //takes in size of String
		if(a.charAt(0)=='-') //if first character is negative sign
			isPositive = false; //number is not positive
		
		insertLast(a); //inserts each character in a into BigNumberLL
	}
		// to complete use Character.getNumericalValue(C)f
	
	
	//all methods might need to have their return type changed, basic skeleton doesn't need to know this for now
	
	
	public boolean isEmpty(){
		if(first == null) //is empty if first points to null
			return true;
		else
			return false;}
	
	public String toString (){ //makes linked list into string (includes commas)
		this.trimFront(this);
		Link current  = first;
		String ans = "";
		int count=0;
		//if(!isPositive) //if it's negative, append negative sign to beginning
			//ans = "-";
		while(current!=null){
			if(ans=="" && !isPositive){ //new other condition to check so its the front of string
				String neg = "-";
				ans =  ans + "-";
			}	
			ans = ans + Integer.toString(current.val);
			current = current.next;} //takes value at current node and puts into string
			
		//ans = trimFront(answer).stringMaker(); //trims zeros off the front
		return insertCommas(ans); //inserts commas
	}
	public String insertCommas(String comma){ //recursively inserts commas
		if(comma.length() < 4){ //base case if length is less than 4
			return comma;
		}
		return insertCommas(comma.substring(0, comma.length() - 3)) + "," + comma.substring(comma.length() - 3, comma.length()); //inserts commas in substrings in groups of 3
		}
	
	public static BigNumberLL add(BigNumberLL A, BigNumberLL B){
		BigNumberLL ans = new BigNumberLL();
		Link probeA = A.last; //start probe at end of A
		Link probeB = B.last;//start probe at end of B
		if(A.size>=B.size){  //if A is bigger
			while(probeA.prev!=null){ //while A has a prev
				if(probeB==null){ //if there are no more digits in b
					String answer = Integer.toString(probeA.val); // bring A values down when B is less than A 
					ans.insertFirst(answer); //insert A
					if(probeA.prev==null) //if there are no more digits left in A
						return ans; //return answer
					else
						probeA = probeA.prev; //go to previous node
					}
				else if((probeA.val + probeB.val) > 9){ // if A + B has a carry out 
					String answer = Integer.toString((probeA.val+probeB.val)-10); // takes carry out of previous spot
					if(probeA.prev==null){ //if prev is null
						ans.insertFirst(answer); //insert answer
						ans.insertFirst("1");} //insert carry of 1
					else{
						probeA.prev.val = probeA.prev.val + 1; // puts carry out into next space in linked list
						ans.insertFirst(answer); // inserts answer 
						probeA = probeA.prev; //go to previous digit in A
						probeB = probeB.prev;} //go to previous digit in B
				}	
				else if((probeA.val + probeB.val) <= 9){ // does normal addition and puts correct answer into spot 
					String answer = Integer.toString(probeA.val + probeB.val); //adds digits at probeA and probeB
					ans.insertFirst(answer); //inserts answer into list
					probeA = probeA.prev; //go to previous digit in A
					probeB = probeB.prev;//go to previous digit in B
				}
				else {
					String answer = Integer.toString(probeA.val); // bring A values down when B is less than A 
					ans.insertFirst(answer); //insert A digit when no B digits are l
					probeA = probeA.prev;//go to previous digit in A
					probeB = probeB.prev;//go to previous digit in B
				}
			}
			if(probeB!=null){ //if there are no digits at probe B
				String answer = Integer.toString((probeA.val+probeB.val)); //answer is addition of values at A and B
				if(answer.length()==2){ //if carry
					ans.insertFirst(Character.toString(answer.charAt(1))); //insert digit at index 1
					ans.insertFirst(Character.toString(answer.charAt(0))); //then insert digit at index 0
				}
				else
					ans.insertFirst(answer);} //insert answer if there is no carry
			else{ //if probe B is equal to null
				String answer = Integer.toString(probeA.val); //answer is digit at A
				ans.insertFirst(answer);} //insert digit at A
		}
		if(A.size<B.size){ //if B is bigger
			while(probeB.prev!=null){ //while B has a prev
				if(probeA==null){ // if there are no more digits in A
					String answer = Integer.toString(probeB.val); // bring B values down when A is less than B
					ans.insertFirst(answer); //insert B
					if(probeB.prev==null) //if there are no more digits left in A
						return ans; //return answer
					else
						probeB = probeB.prev; //go to previous node
				}
				else if((probeA.val + probeB.val) > 9){ //if A+B has a carry out
					String answer = Integer.toString((probeA.val+probeB.val)-10); //takes carry out of previous spot
					if(probeB.prev==null){ //if prev is null
						ans.insertFirst(answer); //insert answer
						ans.insertFirst("1"); //insert carry of 1
					}
					else{
					probeB.prev.val = probeB.prev.val + 1; //puts carry out into next space in linked list
					ans.insertFirst(answer); //inserts answer
					probeA = probeA.prev; //go to previous digit in A
					probeB = probeB.prev; //go to previous digit in B
					}
				}	
				else if((probeA.val + probeB.val) <= 9){ //does normal addition and puts correct answer into spot
					String answer = Integer.toString(probeA.val + probeB.val); //adds digit at probeA and probeB
					ans.insertFirst(answer); //inserts answer into list
					probeA = probeA.prev;//go to previous digit in A
					probeB = probeB.prev;//go to previous digit in B
				}
				else {
					String answer = Integer.toString(probeB.val); // bring B down to ans when A ends in addition 
					ans.insertFirst(answer); //insert A digit when no B digits are left
					probeA = probeA.prev;//go to previous digit in A
					probeB = probeB.prev;//go to previous digit in B
				}
			}
			if(probeA!=null){ //if there is more to A
				String answer = Integer.toString((probeA.val+probeB.val));
				if(answer.length()==2){ //if there is a carry out
					ans.insertFirst(Character.toString(answer.charAt(1))); //insert digit
					ans.insertFirst(Character.toString(answer.charAt(0))); //insert carry
				}
				else
					ans.insertFirst(answer);} //if there isnt a carry out, insert digit
			else{ //if there is no more to A
				String answer = Integer.toString(probeB.val);//bring B value down
				ans.insertFirst(answer);}//insert B value
		}
		
		return ans;
	}	

	    
	public static BigNumberLL subtract(BigNumberLL A,BigNumberLL B){ // need to accomodate for three cases in subtraction A.size<B.size, B.size<A.size, A.size=B.size
		BigNumberLL ans = new BigNumberLL();
		Link probeA = A.last;
		Link probeB = B.last;
		String aS = A.stringMaker();
		String bS = B.stringMaker();
		aS = Character.toString(aS.charAt(0));
		bS = Character.toString(bS.charAt(0));
		if (!aS.equals("-") &&!bS.equals("-")){
				if(A.size>B.size){ // case #1
					while(probeA.prev!=null && probeB!=null){ //if probeA.prev == null then the while needs to break because it is at A.first
						if(probeA.val>probeB.val){// if A at that point is greater than B at that point then normal subtraction can occur
							ans.insertFirst(Integer.toString(probeA.val-probeB.val));
							probeA=probeA.prev;
							probeB=probeB.prev;
						} 
						else if(probeA.val==probeB.val){// if they are the same value the result will be 0
							ans.insertFirst(Integer.toString(probeA.val-probeB.val));
							probeA=probeA.prev;
							probeB=probeB.prev;
						} 
						else{//probeA.val<probeB.val
							if(probeA.prev.val>0){ // can do normal carry in 
								probeA.prev.val--;
								probeA.val=probeA.val+10;
								String answer = Integer.toString(probeA.val-probeB.val);
								ans.insertFirst(answer);
								probeA=probeA.prev;
								probeB=probeB.prev;
							}
							else{ //probeA.prev.val==0 (less than 0 should never happen) this else makes it that carry in can happen normally when the while loop repeats 
								while(probeA.prev.val==0){probeA=probeA.prev;}
								while(probeA.val==0){
								probeA.prev.val--;
								probeA.val=probeA.val+10;
								if(probeA.next==null){break;}
								probeA=probeA.next;}
		
							}}}
					while(probeA.prev!=null){//
					ans.insertFirst(Integer.toString(probeA.val));
					probeA=probeA.prev;
					}
					ans.insertFirst(Integer.toString(probeA.val));
		
				}		
				else if(A.size<B.size){// case #2
					while(probeB.prev!=null && probeA!=null){ //if probeB.prev == null then the while needs to break because it is at B.first
						if(probeB.val>probeA.val){// if B at that point is greater than A at that point then normal subtraction can occur
							ans.insertFirst(Integer.toString(probeB.val-probeA.val));
							probeA=probeA.prev;
							probeB=probeB.prev;
						} 
						else if(probeA.val==probeB.val){// if they are the same value the result will be 0
							ans.insertFirst(Integer.toString(probeB.val-probeA.val));
							probeA=probeA.prev;
							probeB=probeB.prev;
						} 
						else{//probeB.val<probeA.val
							if(probeB.prev.val>0){ // can do normal carry in 
								probeB.prev.val--;
								probeB.val=probeB.val+10;
								String answer = Integer.toString(probeB.val-probeA.val);
								ans.insertFirst(answer);
								probeA=probeA.prev;
								probeB=probeB.prev;
							}
							else{ //probeA.prev.val==0 (less than 0 should never happen) this else makes it that carry in can happen normally when the while loop repeats 
								while(probeB.prev.val==0){probeB=probeB.prev;}
								while(probeB.val==0){
								probeB.prev.val--;
								probeB.val=probeB.val+10;
								if(probeB.next==null){break;}
								probeB=probeB.next;}
		
							}}}
					while(probeB.prev!=null){
					ans.insertFirst(Integer.toString(probeB.val));
					probeB=probeB.prev;
					}
					ans.insertFirst(Integer.toString(probeB.val));
					ans.setSign(false);
		
				}		
				else{//A.size==B.size case#3
					if(A.first.val>B.first.val){
						while(probeA.prev!=null && probeB!=null){ //if probeA.prev == null then the while needs to break because it is at A.first
							if(probeA.val>probeB.val){// if A at that point is greater than B at that point then normal subtraction can occur
								ans.insertFirst(Integer.toString(probeA.val-probeB.val));
								probeA=probeA.prev;
								probeB=probeB.prev;
							} 
							else if(probeA.val==probeB.val){// if they are the same value the result will be 0
								ans.insertFirst(Integer.toString(probeA.val-probeB.val));
								probeA=probeA.prev;
								probeB=probeB.prev;
							} 
							else{//probeA.val<probeB.val
								if(probeA.prev.val>0){ // can do normal carry in 
									probeA.prev.val--;
									probeA.val=probeA.val+10;
									String answer = Integer.toString(probeA.val-probeB.val);
									ans.insertFirst(answer);
									probeA=probeA.prev;
									probeB=probeB.prev;
								}
								else{ //probeA.prev.val==0 (less than 0 should never happen) this else makes it that carry in can happen normally when the while loop repeats 
									while(probeA.prev.val==0){probeA=probeA.prev;}
									while(probeA.val==0){
									probeA.prev.val--;
									probeA.val=probeA.val+10;
									if(probeA.next==null){break;}
									probeA=probeA.next;}
			
								}}}
						while(probeA.prev!=null){
						ans.insertFirst(Integer.toString(probeA.val));
						probeA=probeA.prev;
						}
						ans.insertFirst(Integer.toString(probeA.val-probeB.val));
			
					}
					else if(A.first.val<B.first.val){
						while(probeB.prev!=null && probeA!=null){ //if probeB.prev == null then the while needs to break because it is at B.first
							if(probeB.val>probeA.val){// if B at that point is greater than A at that point then normal subtraction can occur
								ans.insertFirst(Integer.toString(probeB.val-probeA.val));
								probeA=probeA.prev;
								probeB=probeB.prev;
							} 
							else if(probeA.val==probeB.val){// if they are the same value the result will be 0
								ans.insertFirst(Integer.toString(probeB.val-probeA.val));
								probeA=probeA.prev;
								probeB=probeB.prev;
							} 
							else{//probeB.val<probeA.val
								if(probeB.prev.val>0){ // can do normal carry in 
									probeB.prev.val--;
									probeB.val=probeB.val+10;
									String answer = Integer.toString(probeB.val-probeA.val);
									ans.insertFirst(answer);
									probeA=probeA.prev;
									probeB=probeB.prev;
								}
								else{ //probeA.prev.val==0 (less than 0 should never happen) this else makes it that carry in can happen normally when the while loop repeats 
									while(probeB.prev.val==0){probeB=probeB.prev;}
									while(probeB.val==0){
									probeB.prev.val--;
									probeB.val=probeB.val+10;
									if(probeB.next==null){break;}
									probeB=probeB.next;}
		
								}}}
						while(probeB.prev!=null){
						ans.insertFirst(Integer.toString(probeB.val));
						probeB=probeB.prev;
						}
						ans.insertFirst(Integer.toString(probeB.val-probeA.val));
						ans.setSign(false);
					}
					else {
						ans.insertLast("0");
					}
				
				}}
		else if(!aS.equals("-")&&bS.equals("-")){
			B.deleteFirst(B);
			ans = add(A,B);
		}
		else if(aS.equals("-")&&!bS.equals("-")){
			A.deleteFirst(A);
			ans = add(A,B);
			ans.setSign(false);
		}
		return ans;
	}







	
	public String getSign(BigNumberLL A){
		String a  = A.stringMaker();
		if(a.charAt(0)=='-') //if first character of the big number is a negative sign
			return "Negative"; //number is negative
		else{
			return "Positive"; //number is positive
		}
	}
	public static BigNumberLL multiply(BigNumberLL A,BigNumberLL B){
		BigNumberLL ansStore = new BigNumberLL(); //stores the answer
		BigNumberLL ans2 = new BigNumberLL(); //stores current row of product before addition
		BigNumberLL ans = new BigNumberLL(); //used to add then copy into ansStore
		BigNumberLL nonPiv = new BigNumberLL(); //multiplicand 
		Link probeA = A.last; //start at end of A
		Link probeB = B.last; //start at end of B
		Link pivot;
		String ans2String ="";
		String zeros = "0"; //zeros to add for rows of product
		int rowNum = 0; //rowNum determines how many zeros to 
		if(A.size<B.size){
			pivot = probeA; //if A is smaller, it is multiplier
			nonPiv = B;}//if A is smaller, B is multiplicand
		else{
			pivot = probeB;//if B is smaller, it is multiplier
			nonPiv = A;} //if B is smaller, A is multiplicand
		ansStore = ans.multiplyBase(nonPiv, pivot.val); //multiply first number
		while(pivot.prev!=null){ //if there are more numbers to multiply by in multiplier
			zeros = "0";
			for(int i = 0;i<rowNum;i++) //add zeros as you add rows of product
				zeros = zeros+"0";
			ans2 = ans.multiplyBase(nonPiv, pivot.prev.val); //multiply previous number in multiplier by multipland
			ans2String = ans2.stringMaker(); //turn product into string
			ans2String = ans2String + zeros; //add appropriate zeros
			ans2 = new BigNumberLL(ans2String); //turn back into big number
			ansStore = (ans.add(ansStore,ans2)); //add to previous product and store in ansStore
			pivot = pivot.prev; //go to next number in multiplier
			rowNum++;} //increase row count of product
		
		return ansStore; //return result of additions
		}
	public String stringMaker(){ //turns big number into string without commas
		Link current  = first;
		String ans = "";
		int count=0;
		if(!isPositive) //if it's negative, append negative sign to beginning
			ans = "-";
		while(current!=null){
			ans = ans + Integer.toString(current.val); //takes value at current node and puts into string
			current = current.next; //go to next digit
		} 	
	ans = ans.replaceAll("[^0-9]+", ""); // remove weird characters
	return ans; //returns String representation of BigNumberLL
}
		

	public static int compare(BigNumberLL a,BigNumberLL b){
		BigNumberLL ans = new BigNumberLL();
		String aS;
		String bS;
		aS = Character.toString((a.stringMaker()).charAt(0));
		bS = Character.toString((b.stringMaker()).charAt(0));// to check if beginning is negative 
		if (aS.equals("-")&&bS.equals("-")){ // has to do compare differently if compare is working with two negative numbers 
			a.deleteFirst(a);// gets rid of equal sign in string
			b.deleteFirst(b);
			a.setSign(true); // makes number a positive number
			b.setSign(true);			
			ans = subtract(a,b);
			String ansS = ans.stringMaker();
			ansS =Character.toString(ansS.charAt(0)); // gets the first character to see what sign the number is or if its 0
			if(ansS.equals("-")) 
				return 1; // the outputs needs to be reversed because the signs were flipped 
			else if(ansS.equals("0"))
				return 0;
			else
				return -1;		}
		ans = subtract(a,b); // does subtract normally if there is only 1 negative number 
		String ansS = ans.stringMaker(); // turns result of ans subtraction into a string with no commas to work with 
		ansS =Character.toString(ansS.charAt(0)); // gets the first character to see what sign the number is or if its 0
		if(ansS.equals("-")) 
			return -1;
		else if(ansS.equals("0"))
			return 0;
		else
			return 1;
	}	


	public void insertFirst(String a){
			a = a.replaceAll("[^0-9]+", ""); //remove all weird characters
			Link newNode = null; //creates new node
			for(int i = 0;i<a.length();i++){ //for as many characters there are in String a
				 newNode = new Link(Character.getNumericValue(a.charAt((i)))); //places new node containing numerical value
				 if (isEmpty()) { //if list is empty
			            newNode.next = null; //next points to null
			            newNode.prev = null;//prev points to null
			            this.first = newNode; //first points to newNode
			            this.last = newNode; //last points to newNode

			        } else {
			            this.first.prev = newNode; //old first's prev points to newNode
			            newNode.next = first; //newNode's next points to old first
			            newNode.prev = null;//prev points to null
			            this.first = newNode; //first points to newNode
			        }
			}
 
	    }
	public void insertLast(String a){
		a = a.replaceAll("[^0-9]+", ""); //removes all weird characters
		Link newNode = null; //creates new node
		for(int i = 0;i<a.length();i++){//for as many characters there are in String a
			newNode = new Link((int)Character.getNumericValue(a.charAt(i)));//places new node containing numerical value
			if(newNode.val==-1)
				newNode.val=0;
			if(isEmpty()){ //if list is empty
				newNode.next = null;//next points to null
				newNode.prev = null;//next points to null
				this.first = newNode;//first points to newNode
				this.last = newNode;//last points to newNode
			}
			else{
				this.last.next = newNode;//old last's next points to newNode
	        	newNode.prev = last;//newNode's prev points to old last
	            newNode.next = null;//newNode's next points to null
	            this.last = newNode;//last points to newNode
			}}

	}
	
	public void setSign(boolean sign){ //sets sign of BigNumberLL
		this.isPositive = sign; //true makes 
	}
	public BigNumberLL multiplyBase(BigNumberLL X, int i){ 
		Link current;
		BigNumberLL answer = new BigNumberLL();
		String num;
		String carry;
		String base = Integer.toString(i); //convert i to String
		current = X.last; //start at end of digits inX
		int carryOfNext = 0;
		while(current.prev!=null){
				if((current.next==null) && (current.val*i)>=10){ //if first number has carry
					num = Integer.toString((current.val*i)); //store product into string
					answer.insertFirst(Character.toString(num.charAt(1))); //insert digit
					carryOfNext = Character.getNumericValue(num.charAt(0)); //carryOfNext is the carry
				}
				else if((current.next==null) && (current.val*i)<10){ //if first number does not have carry
					num = Integer.toString((current.val*i)); //store product into string
					answer.insertFirst(num); //insert product
				}
				else if((current.next.val*i)>=10 && current.next.next!=null){ //if there is a carry in
					if(current.val*i>=10){//if there is a carry out
						String carryNextNext = Integer.toString(current.next.next.val*i); //store carry of next next val for sake of carry in for current
						char cNN = carryNextNext.charAt(0); //store carry of next next
						int carr = ((current.next.val*i)+ carryOfNext); //get product of current next with carry
						num = Integer.toString(carr); //store product 
						carry = Integer.toString(Character.getNumericValue(num.charAt(0))); //store carry
						if(num.length()==1)
							answer.insertFirst(num); //if product is one digit, insert
						else{
							int in = Character.getNumericValue(num.charAt(0)); //store carry
							int insert = ((current.val*i)+in); //store value of current with carry
							String inS = Integer.toString(insert); //make insert into string
							carryOfNext = Character.getNumericValue(inS.charAt(0)); //carryOfNext is the carry
							answer.insertFirst(Character.toString(inS.charAt(1))); //insert digit
						}
					}
					else if(current.val*i<10){//if there is no carry out 
						num = Integer.toString((current.val*i) + carryOfNext); //looks for carry in
						if(Integer.parseInt(num)>=10){ //if there is carry out after addition of carry in
							answer.insertFirst(Character.toString(num.charAt(1))); //insert digit
							carryOfNext = Character.getNumericValue(num.charAt(0)); //carryOfNext is the carry
						}
						else{ //if no carry out after addition of carry in
							answer.insertFirst(num); //insert digit
							carryOfNext = 0;}//carry is 0
					}
					}
				else if (current.next.next==null && current.next!=null){ //if there is no carry in to consider for current next
					num = Integer.toString(current.val*i); //store product of current and i
					if((current.next.val*i)>=10){ //if there is a carry in
						num = Integer.toString((current.val*i)+carryOfNext); //num is addition after carry in
						if(Integer.parseInt(num)>=10){ //if there is a carry out
							answer.insertFirst(Character.toString(num.charAt(1))); //insert digit
							carryOfNext = Character.getNumericValue(num.charAt(0)); //carryOfNext is the carry
						}
						else{ //if there is no carry out
							answer.insertFirst(num);  //insert digit
							carryOfNext = 0;}//carryOfNext is 0
					}
					else{ //does not match any other cases, no carry in
						if((current.val*i)>=10){ //if there is a carry out
							carry = Integer.toString((current.val*i)); //store product of current and i
							int carryNum = Integer.parseInt(Character.toString(carry.charAt(1))); //carryNum is digit
							answer.insertFirst(Integer.toString(carryNum)); //insert digit 
							carryOfNext = Character.getNumericValue(carry.charAt(0)); //carryOfNext is the carry
						}
						else{ //no carry out
							answer.insertFirst(Integer.toString((current.val*i)));  //insert digit
							carryOfNext=0;} //carryOfNext is 0
							 
						
					}
					}
					
				else if((current.next.next.val*i)>=10 && (((current.next.val*i) + (Character.getNumericValue(Integer.toString((current.next.next.val*i)).charAt(0))))>=10)){
					//if theres a carry into current.next and the carry makes current.next have a carry out
					num = Integer.toString((current.val*i)+carryOfNext); //if there is carry in to next and carry into current
					if(Integer.parseInt(num)>=10){ //if there is a carry out
						answer.insertFirst(Character.toString(num.charAt(1))); //insert digit
						carryOfNext = Character.getNumericValue(num.charAt(0)); //carryOfNext is the carry
					}
					else{ //if there is no carry out
						answer.insertFirst(num); //insert digit
						carryOfNext=0;} //carryOfNext is 0
				}
				else{ //does not match any other cases, no carry in
					if((current.val*i)>=10){ //if there is a carry out
						carry = Integer.toString((current.val*i)); //store product of current and i
						int carryNum = Integer.parseInt(Character.toString(carry.charAt(1))); //carryNum is digit
						answer.insertFirst(Integer.toString(carryNum)); //insert digit
						carryOfNext = Integer.parseInt(Character.toString(carry.charAt(0))); //carryOfNext is the carry
					}
					else{ //no carry out
						answer.insertFirst(Integer.toString((current.val*i))); //insert digit
						carryOfNext = 0; ////carryOfNext is 0
					}
					
				}
					
				current = current.prev; //go to previous digit in multiplicand
		} ///////////////////////////////////////////////end while, means that there is one number left in multiplicand
		if(current.next==null){ //if single number
			num = Integer.toString(current.val*i); //stores product of current and i
			if(num.length()==1) //without carry
				answer.insertFirst(num); //insert digit
			else{ //with carry
				answer.insertFirst(Character.toString(num.charAt(1))); //insert digit
				answer.insertFirst(Character.toString(num.charAt(0)));}} //insert carry
		else if((current.next.val*i)>=10 && (current.next.next.val*i)>=10){ //if there is a carry in and a carry into current .next
			if(((current.val*i)+carryOfNext)>=10){//if there is a carry out
					String carryNextNext = Integer.toString(current.next.next.val*i); //store product of next next and i
					char cNN = carryNextNext.charAt(0); //take carry into current next
					int carr = ((current.next.val*i)+ Character.getNumericValue(carryNextNext.charAt(0))); //value of current next
					num = Integer.toString(carr); //string value of current next
					carry = Integer.toString(Character.getNumericValue(num.charAt(0))); //carry into current
					if(num.length()==1) //if current next length is 1
							answer.insertFirst(num); //insert it
					else{ //if there is a carry out
						int in = Character.getNumericValue(num.charAt(0)); //get carry
						int insert = ((current.val*i)+in); //add carry to product of current and i
						String inS = Integer.toString(insert); //inS is string representation of insert
						answer.insertFirst(Character.toString(inS.charAt(1))); //insert digit
						answer.insertFirst(Character.toString(inS.charAt(0)));//insert carry
						}
			}		
			else if(current.val*i<10){ //if there is no carry out but there is carry in
				num = Integer.toString(current.next.val*i); //need carry from previous
				String carryNextNext = Integer.toString(current.next.next.val*i); //get carry into current next
				char cNN = carryNextNext.charAt(0); //get carry
				int carr = ((current.next.val*i)+ Character.getNumericValue(carryNextNext.charAt(0))); //value of current next with carry in
				carry = Integer.toString(carr); //String representation of value of current next with carry in
				carry = Character.toString(carry.charAt(0)); //get carry in
				num = Integer.toString((current.val*i)+Integer.parseInt(carry)); //num is addition of (product of carry and i) and carry in
				if(Integer.parseInt(num)>=10){ //if addition of carry makes a carry out 
					answer.insertFirst(Character.toString(num.charAt(1))); //insert digit 
					answer.insertFirst(Character.toString(num.charAt(0))); //insert carry
				}
				else //addition of carry does not make carry out
					answer.insertFirst(num); //insert digit
			}
		}
		else if(current.next.next==null && current.next!=null){ //if there's no carry into current next
			num = Integer.toString(current.val*i); //store product of current and i
			if((current.next.val*i)>=10){ //if there is a carry in
				carry = Integer.toString((current.next.val*i)); //store product of current next and i
				int carryNum = Integer.parseInt(Character.toString(carry.charAt(0))); // store carry in
				num = Integer.toString((current.val*i)+carryNum); //num is addition of (product of current and i) and carry
				if(Integer.parseInt(num)>=10){ //if carry out
					answer.insertFirst(Character.toString(num.charAt(1))); //insert digit
					answer.insertFirst(Character.toString(num.charAt(0)));} //insert carry
				else //if no carry out
					answer.insertFirst(num); //insert digit
			}
			else{ //no carry in
				if((current.val*i)>=10){ //carry out
					carry = Integer.toString((current.val*i)); //store product of current and i in String
					answer.insertFirst(Character.toString(carry.charAt(1))); //insert digit
					answer.insertFirst(Character.toString(carry.charAt(0)));//insert carry
					}
				else //no carry out
					answer.insertFirst(Integer.toString((current.val*i))); //insert digit
				}
		}
		else if((current.next.val*i)>=10 && (current.next.next.val*i)<10){ //if no carry into current next and there is carry into current
			int carr = (current.next.val*i); //store product of current next and i
			num = Integer.toString(carr); //store product of current next and i into String
			carry = Integer.toString(Character.getNumericValue(num.charAt(0))); //store carry
			if(((current.val*i)+Integer.parseInt(carry))>=10){ //if addition of carry and (product of current and i) produces carry out
				String curr = Integer.toString((current.val*i)+Integer.parseInt(carry)); //String representation of addition of carry and (product of current and i) 
				answer.insertFirst(Character.toString(curr.charAt(1))); //insert digit
				answer.insertFirst(Character.toString(curr.charAt(0))); //insert carry
			}
			else //if there is no carry out
				answer.insertFirst(Integer.toString((current.val*i) +Integer.parseInt(carry) )); //insert digit
			}

		else if(((current.val*i)+carryOfNext)>=10){ //if addition of carry produces carry out
			carry = Integer.toString(((current.val*i)+carryOfNext)); //String representation of addition of carry and (product of current and i) 
			answer.insertFirst(Character.toString(carry.charAt(1))); //insert digit
			answer.insertFirst(Character.toString(carry.charAt(0)));} //insert carry
		else //if no carry out and there is not necessarily a carry in (carry can be equal to zero)
			answer.insertFirst(Integer.toString(((current.val*i)+carryOfNext))); //insert digit
	//}
	return answer;
	}
	public BigNumberLL trimFront(BigNumberLL A){ //removes zeros from front
		Link current = A.first; //start at beginning of A
		while(current.next!=null){ //until end of list
			if(current.val==0){ //if 0 is encountered before a nonzero is encounters
				this.deleteFirst(A); //delete that zero
				current = current.next;} //go to next digit
			if(current.val!=0) //if a nonzero is encountered, break and return A
				break;
		}	
		return A;
	}
	public void deleteFirst(BigNumberLL A){ //used by trimfront to delete first element
		A.first.next.prev = null; //previous of A.first.next is null
		A.first = A.first.next; //A.first is now old A.first.next
	}
		
	 /* insertFirst
	 * trimFront: if the big number list contains a lot of zeros on the left, 
	 * 			  we can trim this number (remove zeros)
	 * deleteFirst: used by trimFront

	 * multiplyBase: multiply one BigNumberLL X with an integer i and return a new 
	 * 				 BigNumberLL, this kernel(base) can be used by multiply to perform the multiplication
	 * 				 step by step
	 */
	
}

