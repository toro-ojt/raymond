
class groovy1 {
	static void main(String...args)
	{
		println 'Sample1'	
		
		
		testLoop()
		
		def AC = new otherClass()

		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
		print 'Input Name: '
		def name = br.readLine()
		println 'Your Name is: ' + name
		
		print 'Input a Number: '
		def userInput = br.readLine()
		//int asdf = int.parseInt(userInput)
		int asdf = userInput as int
		println "You entered an: " + AC.getOddEven(asdf) + " Number"
		
//		AC.Add(1)
//		AC.Add(1,2)
	}//end main
	

	
	
	public static void testLoop()
	{
		for(a in 5..1)
		{
			println a + '...'
		}	
	}
	
}


