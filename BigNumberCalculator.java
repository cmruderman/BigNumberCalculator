class BigNumberCalculator{
    // this class just has a main method
    static public void main(String args[]) {
	
	BigNumberLL big1,big2,big3;
	EasyIn easy = new EasyIn();
	System.out.println("Welcome to BigNumber Calculator");
	
        System.out.println("Choose Operation (p,=,+,-,*) or X for exit");
	char command = easy.readChar();
	
	
	// keep on performing calculations until the user enters 'X'
	while (command != 'X') {
	    
	    // read and convert first input string to a BigNumber
	    System.out.println("Enter first number: ");
	    big1 = new BigNumberLL(easy.readString());
            
	    if (command!='p'){
		// read and convert second input string to a BigNumber
		System.out.println("Enter second number: ");
		big2 = new BigNumberLL(easy.readString());
	    }
	    else
		big2 = new BigNumberLL(); // dummy        
	    
	    // perform the specified arithmetic operation and print 
	    // result
	    if (command == 'p') {
		System.out.println("Number is "+big1.toString());
	    }
	    else if (command == '=') {
		int temp=BigNumberLL.compare(big1, big2);
		System.out.println("sign(number1-number2) is "+temp);
	    }
	    else if (command == '+') {
		big3 = BigNumberLL.add(big1, big2);
		System.out.println("answer is "+big3.toString());
	    }
	    else if (command == '-') {
		big3 = BigNumberLL.subtract(big1,big2 );
	        System.out.println("answer is "+big3.toString());
	    }
	    else if (command == '*') {
		big3 = BigNumberLL.multiply(big1,big2);	
	        System.out.println("answer is "+big3.toString());
	    }
      	    else {
		System.out.println("Operation not valid ! ");
	    } 
	    // read in next operation
	    System.out.println(" ");
	    System.out.println("Choose Operation (p,=,+,-,*) or X for exit");
	    command = easy.readChar();
	}
	System.out.println("Bye !");
    }          
}
