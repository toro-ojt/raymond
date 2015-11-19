import groovy.swing.SwingBuilder

import java.awt.BorderLayout

import javax.swing.JFrame

@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*
import javax.swing.*


class CurrencyConverterMain {
	static void main(args){
		
		def list = 'AFA or ALL or DZD or ARS or AWG or AUD or BSD or BHD or BDT or BBD or BZD or BMD or BTN or BOB or BWP or BRL or GBP or BND or BIF or XOF or XAF or KHR or CAD or CVE or KYD or CLP or CNY or COP or KMF or CRC or HRK or CUP or CYP or CZK or DKK or DJF or DOP or XCD or EGP or SVC or EEK or ETB or EUR or FKP or GMD or GHC or GIP or XAU or GTQ or GNF or GYD or HTG or HNL or HKD or HUF or ISK or INR or IDR or IQD or ILS or JMD or JPY or JOD or KZT or KES or KRW or KWD or LAK or LVL or LBP or LSL or LRD or LYD or LTL or MOP or MKD or MGF or MWK or MYR or MVR or MTL or MRO or MUR or MXN or MDL or MNT or MAD or MZM or MMK or NAD or NPR or ANG or NZD or NIO or NGN or KPW or NOK or OMR or XPF or PKR or XPD or PAB or PGK or PYG or PEN or PHP or XPT or PLN or QAR or ROL or RUB or WST or STD or SAR or SCR or SLL or XAG or SGD or SKK or SIT or SBD or SOS or ZAR or LKR or SHP or SDD or SRG or SZL or SEK or CHF or SYP or TWD or TZS or THB or TOP or TTD or TND or TRL or USD or AED or UGX or UAH or UYU or VUV or VEB or VND or YER or YUM or ZMK or ZWD or TRY' as String
		def menuInput1 = list.split(' or ')
		
		
		def swingBuilder = new SwingBuilder()
		swingBuilder.edt {  // edt method makes sure UI is build on Event Dispatch Thread.
			lookAndFeel 'nimbus'  // Simple change in look and feel.
			frame(title: 'Temperature Converter', size: [350, 230],
			show: true, locationRelativeTo: null, id: 'MainFrame', defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
				borderLayout(vgap: 5)
				
				
				panel(constraints: BorderLayout.CENTER,
					border: compoundBorder([emptyBorder(10), titledBorder('Enter your address:')])) {
					tableLayout {
		
						
						tr{
							td{
								label 'From'
							}
							td{
								comboBox( id: 'cbx1')
								menuInput1.each{ cbx1.addItem(it) }
							}
						}
						
						tr{
							td{
								label 'To'
							}
							td{
								comboBox( id: 'cbx2')
								menuInput1.each{ cbx2.addItem(it) }
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
								def input = new Currency()
								button(text: "Convert", id: 'btn1', actionPerformed: {
									println cbx1.selectedItem + " " + cbx2.selectedItem
									AnswerField.text = input.Result(cbx1.selectedItem,cbx2.selectedItem)
									})
							}
						}

					}//end tableLayout
				}//end panel	
			}//end frame
		}//end swingbuilder.edt
	}//end main
}//end CurrencyConverterMain class






class Currency{

	def client
	def response
	def result
	
	Currency(){ }
	
	def Result(def FromCurr, def ToCurr){
		
		this.client = new SOAPClient('http://www.webservicex.net/CurrencyConvertor.asmx')
		this.response = client.send(SOAPAction: "http://www.webserviceX.NET/ConversionRate")
		{
			body {
				ConversionRate('xmlns':"http://www.webserviceX.NET/") {
					FromCurrency(FromCurr as String)
					ToCurrency(ToCurr as String)
				}
			}
		}
		this.result = response.ConversionRateResponse.ConversionRateResult
		
		return this.result
	}
	
}//end Currency class