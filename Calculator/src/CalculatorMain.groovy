import groovy.swing.SwingBuilder

import java.awt.BorderLayout

import javax.swing.JFrame

@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')

import wslite.soap.*

class CalculatorMain {

	static void main(args){
		
		
		def swingBuilder = new SwingBuilder()
		swingBuilder.edt {  // edt method makes sure UI is build on Event Dispatch Thread.
			lookAndFeel 'nimbus'  // Simple change in look and feel.
			frame(id: 'MainFrame',
				title: 'Calculator', 
				size: [400, 400],
				show: true, 
				locationRelativeTo: null,  
				defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {

				borderLayout(vgap: 5)
		
				panel(constraints: BorderLayout.CENTER,
					border: compoundBorder([emptyBorder(10), titledBorder('Enter Values:')])) {
					tableLayout {
						
						tr{
							td{
								label 'First Number'
							}
							td{
								textField id: 'firstNumberField', columns: 20, text: ''
							}
						}
						
						tr{
							td{
								label 'Second Number'
							}
							td{
								textField id: 'secondNumberField', columns: 20, text: ''
							}
						}
						
						tr{
							td{
								label 'Answer'
							}
							td{
								textField id: 'answerField', columns: 20, text: 'Answer'
								answerField.setEnabled(false)
							}
						}
					}
						//~~~~~~~~~~~ BUTONS GUI ~~~~~~~~~~~~~~~~
					tableLayout {
						tr{
							td{
								def calc = new Calculator()
								button(text: "+", id: 'addBtn', preferredSize: [100,100], actionPerformed: {
									if(firstNumberField.text.trim().isInteger() && secondNumberField.text.trim().isInteger()){
										answerField.text = calc.solve(firstNumberField.text, secondNumberField.text, 'Add')
									}
									else{
										answerField.text = 'Whole Numbers Only'
									}
								})
							}
							td{
								
								def calc = new Calculator()
								button(text: "-", id: 'subtractBtn1', preferredSize: [100,100], actionPerformed: {
									if(firstNumberField.text.trim().isInteger() && secondNumberField.text.trim().isInteger()){
										answerField.text = calc.solve(firstNumberField.text, secondNumberField.text, 'Subtract')
									}
									else{
										answerField.text = 'Whole Numbers Only'
									}
								})
							
							}
						}
						
						tr{
							td{
								def calc = new Calculator()
								button(text: "*", id: 'multiplyBtn', preferredSize: [100,100],  actionPerformed: {
									if(firstNumberField.text.trim().isInteger() && secondNumberField.text.trim().isInteger()){
										answerField.text = calc.solve(firstNumberField.text, secondNumberField.text, 'Multiply')
									}
									else{
										answerField.text = 'Whole Numbers Only'
									}
								})
							}
							td{
								
								def calc = new Calculator()
								button(text: "/", id: 'divideBtn1', preferredSize: [100,100],  actionPerformed: {
									if(firstNumberField.text.trim().isInteger() && 
										secondNumberField.text.trim().isInteger())
									{
										answerField.text = calc.solve(firstNumberField.text, secondNumberField.text, 'Divide')
									}
									else{
										answerField.text = 'Whole Numbers Only'
									}
								})
							
							}
						}

					}//end tableLayout
				}//end panel
				
			}//end frame
		}//end swingbuilder.edt
	
	}//end main
}//end CalculatorMain

 


class Calculator{
	
	def solve(def firstNum, def secondNum, def searchBy){
		def client = new SOAPClient('http://www.dneonline.com/calculator.asmx')
		def response = client.send(SOAPAction : "http://tempuri.org/${searchBy}")
		{
			body{
				"$searchBy"('xmlns':"http://tempuri.org/"){
					intA(firstNum)
					intB(secondNum)
				}
			}
		}
//		result = response.envelope.children().toString()
		def result = response."${searchBy}Response"."${searchBy}Result"
		return result
	}
}