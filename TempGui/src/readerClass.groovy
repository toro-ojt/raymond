@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')

class readerClass {

	def Result(def Input)
	{
		switch(Input)
		{
			case 'c to f':
				def InputTemperature = br.readLine()
				tt.CtoF(InputTemperature as float)
				return "${tt.Result()}F"
				break
				
			case 'f to c':
				def InputTemperature = br.readLine()
				tt.FtoC(InputTemperature as float)
				return "${tt.Result()}C"
				break
				
			case 'help':
				return tt.help()
				break
				
			case 'exit':
				System.exit(0)
				break
				
			default:
				return 'Invalid Input'
				break
		}
	}
	
}





class temp2
{
	def client
	def response
	def result
	def tempto
	def temp
	
	temp2(){ }
	
	def CtoF(def tempVar){
		
		this.client = new SOAPClient('http://www.w3schools.com/webservices/tempconvert.asmx')
		this.response = client.send(SOAPAction: "http://www.w3schools.com/webservices/CelsiusToFahrenheit")
		{
			body {
				CelsiusToFahrenheit('xmlns':"http://www.w3schools.com/webservices/") {
					Celsius(tempVar)
				}
			}
		}
		
		this.temp = 'celsius'
	}
	
	def FtoC(def tempVar){
		
		this.client = new SOAPClient('http://www.w3schools.com/webservices/tempconvert.asmx')
		this.response = client.send(SOAPAction: "http://www.w3schools.com/webservices/FahrenheitToCelsius")
		{
			body {
				FahrenheitToCelsius('xmlns':"http://www.w3schools.com/webservices/") {
					Fahrenheit(tempVar)
				}
			}
		}
		
		this.temp = 'farenheit'
	}
	
	
	
	def Result()
	{
		if(this.temp.toString().toLowerCase() == 'celsius')
		{
			return response.CelsiusToFahrenheitResponse.CelsiusToFahrenheitResult
		}
		else
		{
			return response.FahrenheitToCelsiusResponse.FahrenheitToCelsiusResult
		}
		
	}
	def help()
	{
		println 'c to f'
		println 'f to c'
		println 'help'
		println 'exit'
	}
	
}