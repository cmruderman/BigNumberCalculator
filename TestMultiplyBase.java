
public class TestMultiplyBase {
	public static void main(String[] args){
		BigNumberLL big1;
		BigNumberLL big2;
		EasyIn easy = new EasyIn();
		System.out.println("Enter number to trim: ");
		big1 = new BigNumberLL(easy.readString());
		big2 = big1.trimFront(big1);
		System.out.println("The answer is : " + (big2.toString()));
	}

}
