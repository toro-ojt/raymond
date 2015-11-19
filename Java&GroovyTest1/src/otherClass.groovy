
class otherClass {
		
	def outputNumbers()
	{
		for(a in 1..5)
		{
			println 'a' + a
		}
	}
	
	def getNewNumber(int newNumber)
	{
		println newNumber
	}
	
	def getOddEven(int Number)
	{
		try{
			if(Number%2 == 0){
				return 'Even'
			}
			else
			{
				return 'Odd'
			}
		}
		catch(all)
		{
			println all
		}
	}
	
	
	def Add(int a)
	{
		println a
	}
	
	def Add(int a, int b)
	{
		int c = a + b
		println a+b
	}
}
