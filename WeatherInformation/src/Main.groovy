@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')


import wslite.soap.*
import javax.swing.*
import groovy.swing.SwingBuilder
import java.awt.BorderLayout

class Main {


	static void main(args){

		def swingBuilder = new SwingBuilder()
		swingBuilder.edt {  // edt method makes sure UI is build on Event Dispatch Thread.
			lookAndFeel 'nimbus'  // Simple change in look and feel.
			frame(title: 'Geo IP', size: [550, 630],
			show: true, locationRelativeTo: null, id: 'MainFrame', defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
				borderLayout(vgap: 5)
				
				panel(constraints: BorderLayout.CENTER,
					border: compoundBorder([emptyBorder(10)])) {
					tableLayout {
						tr{
							td{
								label 'Menu'
							}
							td{
								comboBox( id: 'cbx1', items:['Get City Forecast By ZIP', 'Get City Weather By ZIP', 'Get Weather Information'], actionPerformed:{
									if(cbx1.selectedIndex == 0 || cbx1.selectedIndex == 1)
									{
										txtField.setEnabled(true)
									}
									else
									{
										txtField.setEnabled(false)
									}
								})//end cbx1
							}
						}
						tr{
							td{
								label 'Value'
							}
							td{
								textField id: 'txtField' , columns: 20
							}
						}
						tr{
							td{
								def reader = new Reader()
								button(text: "Transmit", id: 'btn1', actionPerformed: {
									if(txtField.text.trim().isInteger() || txtField.text == ''){
										txtArea.text = reader.getVal(cbx1.selectedItem, txtField.text)
										println txtField.text
									}
									else
									{ txtArea.text = 'INVALID INPUT' }
								})
							}
						}
						tr{
							td(colspan: 2){
								textArea id: 'txtArea', columns: 30, rows: 30
							}
						}
						
					}//end tbllayout
				}
			}//end frame
		}//end swing builder
	}
}



class Reader{
	
	
//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
//	print 'Input Query: '

	def text

	def getVal(def query, def zipcode){
		switch (query){
	//			case ~/\d{1,5}/:	//input decimal 1-5 characters
			
			case "Get City Forecast By ZIP":
				Weathering w = new Weathering(zipcode, "Get City Forecast By ZIP")
				if(w.isitSuccess().toString().toLowerCase() == 'false')
				{
					text = 'INVALID ZIP CODE'
				}
				else{
					text = w.output1()
				}
				break
				
			case 'Get City Weather By ZIP':
			Weathering w = new Weathering(zipcode, "Get City Weather By ZIP")
			
					if(w.isitSuccess().toString().toLowerCase() == 'false')
					{
						text = 'INVALID ZIP CODE'
					}
					else{
						text = w.output1()
					}
				break
				
			case 'Get Weather Information':
				Weathering w = new Weathering(null, "Get Weather Information")
				text = w.output1()
				break
				
				default:
					text = 'Invalid Input'
				break
		}
	return text
	}
}


class Weathering
{
	def client = new SOAPClient('http://wsf.cdyne.com/WeatherWS/Weather.asmx')
	def zip
	def response
	
	def GetCity
	def Success
	def ResponseText
	def State
	def City
	def WeatherStationCity
	def ForecastResult
	def Forecast
	def Date
	def WeatherID
	def Desciption
	def Temperatures
	def MorningLow
	def DaytimeHigh
	def ProbabilityOfPrecipiation
	def Nighttime
	def Daytime
	
	def RelativeHumidity
	def Wind
	def Pressure
	def Visibility
	def WindChill
	def Remarks
	
	def WeatherDescription
	def PictureUrl
	
	def What
	
	Weathering(){ }
	
	Weathering(def Zip, def what)
	{
		
		//FORECAST
		if(what == "Get City Forecast By ZIP")
		{
//			this.client = new SOAPClient('http://wsf.cdyne.com/WeatherWS/Weather.asmx')
			this.response = client.send(SOAPAction: "http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP")
			{
				body {
					GetCityForecastByZIP('xmlns':"http://ws.cdyne.com/WeatherWS/") {
						ZIP(Zip as int)
					}
				}
			}
//			println response.GetCityForecastByZIPResponse.GetCityForecastByZIPResult.ForecastResult.Forecast.Temperatures.children().toString()
			this.GetCity = response.GetCityForecastByZIPResponse.GetCityForecastByZIPResult
			this.Success = GetCity.Success
			this.ResponseText = GetCity.ResponseText
			this.State = GetCity.State
			this.City = GetCity.City
			this.WeatherStationCity = GetCity.WeatherStationCity
			this.ForecastResult = GetCity.ForecastResult
			this.Forecast = ForecastResult.Forecast
			this.Date = Forecast.Date
			this.WeatherID = Forecast.WeatherID
			this.Desciption = Forecast.Desciption
			this.Temperatures = Forecast.Temperatures
			this.MorningLow = Temperatures.MorningLow
			this.DaytimeHigh = Temperatures.DaytimeHigh
			this.ProbabilityOfPrecipiation = Forecast.ProbabilityOfPrecipiation
			this.Nighttime = ProbabilityOfPrecipiation.Nighttime
			this.Daytime = ProbabilityOfPrecipiation.Daytime
			
			this.What = what
		}//end FORECAST
		
		
		//WEATHER 
		if(what == "Get City Weather By ZIP"){
//			this.client = new SOAPClient('http://wsf.cdyne.com/WeatherWS/Weather.asmx')
			this.response = client.send(SOAPAction: "http://ws.cdyne.com/WeatherWS/GetCityWeatherByZIP")
			{
				body {
					GetCityWeatherByZIP('xmlns':"http://ws.cdyne.com/WeatherWS/") {
						ZIP(Zip as int)
					}
				}
			}
			
			this.GetCity = response.GetCityWeatherByZIPResponse.GetCityWeatherByZIPResult
			this.Success = GetCity.Success
			this.ResponseText = GetCity.ResponseText
			this.State = GetCity.State
			this.City = GetCity.City
			this.WeatherStationCity = GetCity.WeatherStationCity
			this.WeatherID = GetCity.WeatherID
			this.Desciption = GetCity.Description
			this.Temperatures = GetCity.Temperature
			this.RelativeHumidity = GetCity.RelativeHumidity
			this.Wind = GetCity.Wind
			this.Pressure = GetCity.Pressure
			this.Visibility = GetCity.Visibility
			this.WindChill = GetCity.WindChill
			this.Remarks = GetCity.Remarks
			
			this.What = what
		}//end WEATHER
		
		//INFORMATION
		if(what == "Get Weather Information"){
//			this.client = new SOAPClient('http://wsf.cdyne.com/WeatherWS/Weather.asmx')
			this.response = client.send(SOAPAction: "http://ws.cdyne.com/WeatherWS/GetWeatherInformation")
			{
				body {
					GetWeatherInformation('xmlns':"http://ws.cdyne.com/WeatherWS/") {
						
					}
				}
			}
			
			this.GetCity = response.GetWeatherInformationResponse.GetWeatherInformationResult
			this.WeatherDescription = GetCity.WeatherDescription
			
			this.What = what
		}//end INFORMATION
	}
	
	
	def isitSuccess()
	{
		return Success
	}
	
	def output1()
	{
		def text
		if(What == "Get City Forecast By ZIP")
			text = "Success: $Success" + "\n" +
			"City: $City" + "\n" 
			Forecast.each{
				text += '' + "\n" 
				text += "Date: $it.Date" + "\n" 
				text += "ID: $it.WeatherID" + "\n" 
				text += "Description: $it.Desciption" + "\n" 
				text += "Morning Low: $it.Temperatures.MorningLow" + "\n" 
				text += "DaytimeHigh: $it.Temperatures.DaytimeHigh"
		}
		
		if(What == "Get City Weather By ZIP"){
			text =  "ID: $WeatherID"  + "\n" 
			text +=  "State: $State" + "\n" 
			text +=  "Temperature: $Temperatures" + "\n" 
			text +=  "RelativeHumidity: $RelativeHumidity" + "\n" 
			text +=  "Wind: $Wind" + "\n" 
			text +=  "Pressure: $Pressure"
		}
		
		if(What == "Get Weather Information"){
			WeatherDescription.each{
				text =  "\n"
				text +=  "ID: $it.WeatherID" + "\n" 
				text +=  "Description: $it.Description" + "\n" 
				text +=  "PictureURL: $it.PictureURL"
			}
		}
		return text
	}

}//end Weathering class



