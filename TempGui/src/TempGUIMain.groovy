import static javax.swing.JFrame.EXIT_ON_CLOSE
import groovy.swing.SwingBuilder

import java.awt.*


class TempGUIMain {
	
	static void main(args)
	{
		
		
		
		def swingBuilder = new SwingBuilder()
		swingBuilder.edt {  // edt method makes sure UI is build on Event Dispatch Thread.
			lookAndFeel 'nimbus'  // Simple change in look and feel.
			frame(title: 'Temperature Converter', size: [350, 230],
			show: true, locationRelativeTo: null, id: 'MainFrame',
			defaultCloseOperation: EXIT_ON_CLOSE) {
				borderLayout(vgap: 5)
				
				
				panel(constraints: BorderLayout.CENTER,
					border: compoundBorder([emptyBorder(10), titledBorder('Enter your address:')])) {
					tableLayout {
		
						tr{
							td{
								label 'Menu'
							}
							td{
								textField id: 'optionField', columns: 20, text: ''
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
								textField id: 'AnswerField', columns: 20, text: 'Answer'//, setEnabled = false 
							}
						}
						

						
						tr{
							td{
								button(text: "Convert", id: 'btn1', actionPerformed: {
										AnswerField.text = optionField.text
									})
							}
						}

					}//end frame
				
				}
				
			}//end defaultCloseOperation
		}//end edt
	}//end main
}
