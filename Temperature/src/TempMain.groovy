import groovy.swing.SwingBuilder

import java.awt.BorderLayout

@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*
import javax.swing.*

class TempMain {
	
	static void main(args){
		
		def menuInput = 'c to f'
		def swingBuilder = new SwingBuilder()
		swingBuilder.edt {  // edt method makes sure UI is build on Event Dispatch Thread.
			lookAndFeel 'nimbus'  // Simple change in look and feel.
			frame(title: 'Temperature Converter', size: [350, 230],
			show: true, locationRelativeTo: null, id: 'MainFrame', defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
				borderLayout(vgap: 5)
				
				
				panel(constraints: BorderLayout.CENTER,
					border: compoundBorder([emptyBorder(10), titledBorder('Enter Values:')])) {
					tableLayout {
		
						
						tr{
							td{
								label 'Menu'
							}
							td{
								comboBox( id: 'cbx1', items:['Celsius to Farenheit', 'Farenheit to Celsius'],  
									actionPerformed:{
										if (cbx1.selectedIndex == 0){
											menuInput = 'c to f'
										}
										else{
											menuInput = 'f to c'
										}
									})//end cbx1
							}
						}
						
						tr{
							td{
								label 'Degree'
							}
							td{
								textField id: 'DegreeField', columns: 20, text: ''
							}
						}
						
						tr{
							td{
								label 'Answer'
							}
							td{
								textField id: 'AnswerField', columns: 20, text: 'Answer'
								AnswerField.setEnabled(false)
							}
						}
	
						tr{
							td{
								def input = new Input()
								button(text: "Convert", id: 'btn1', actionPerformed: {
									if(DegreeField.text.trim().isNumber()){
										AnswerField.text = input.GetValue(menuInput, DegreeField.text)
									}
									else{
										AnswerField.text = 'Numbers Only'
									}
								})
							}
						}

					}//end tableLayout
				}//end panel
				
			}//end frame
		}//end swingbuilder.edt
	
	}//end main
		
}//end class	
		
		

//start read		
class Input{
	

	def tt = new temp2()
	
	
	def GetValue(def Input, def InputTemperature){
		
		switch(Input)
		{
			case 'c to f':
				tt.CtoF(InputTemperature as float)
				return "${tt.Result()}F"
				break
				
			case 'f to c':
				tt.FtoC(InputTemperature as float)
				return "${tt.Result()}C"
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

	
}