

import java.awt.BorderLayout

@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*
import javax.swing.*
import groovy.swing.SwingBuilder

class GeoIPMain {
	static void main(args)
	{
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
								comboBox( id: 'cbx1', items:['Get IP', 'Get Geo IP'], actionPerformed:{
									if(cbx1.selectedIndex == 0)
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
								def Reader = new reader()
								button(text: "Transmit", id: 'btn1', actionPerformed: {
									txtArea.text = Reader.GetValue(cbx1.selectedItem, txtField.text)
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
	}//end main
}//end class


class reader{

	def GetValue(def query, def inputAddress){

		def ip = new getIP()
		switch(query)
		{
			case 'Get IP':			
				ip.getip(inputAddress)
				break
				
			case 'Get Geo IP':
				ip.getgeoip()
				break
				
			case 'help':
				ip.help()
				break	
				
			default:
				println 'INVALID INPUT'
				break
			
		}
	}
}


class getIP{
	
	def client = new SOAPClient('http://www.webservicex.net/geoipservice.asmx')
	def text
	def getip(def ipNum){
		
		def response = client.send(SOAPAction: "http://www.webservicex.net/GetGeoIP")
		{
			body {
				GetGeoIP('xmlns':"http://www.webservicex.net/") {
					IPAddress(ipNum as String)
				}
			}
		}
		
		text = "IP Address: $response.GetGeoIPResponse.GetGeoIPResult.IP" + "\n" +
		"Return Code: $response.GetGeoIPResponse.GetGeoIPResult.ReturnCode" + "\n" +
		"Country Name: $response.GetGeoIPResponse.GetGeoIPResult.CountryName" + "\n" +
		"Country Code: $response.GetGeoIPResponse.GetGeoIPResult.CountryCode"
		
		return text as String
	}
	
	
	def getgeoip()
	{
		def response = client.send(SOAPAction: "http://www.webservicex.net/GetGeoIPContext")
		{
			body {
				GetGeoIPContext('xmlns':"http://www.webservicex.net/") {
				}
			}
		}
		
		text = "IP Address: $response.GetGeoIPContextResponse.GetGeoIPContextResult.IP" + "\n" +
		 "Return Code: $response.GetGeoIPContextResponse.GetGeoIPContextResult.ReturnCode" + "\n" +
		 "Return Code Details: $response.GetGeoIPContextResponse.GetGeoIPContextResult.ReturnCodeDetails" + "\n" +
		 "Country Name: $response.GetGeoIPContextResponse.GetGeoIPContextResult.CountryName" + "\n" +
		 "Country Code: $response.GetGeoIPContextResponse.GetGeoIPContextResult.CountryCode"
		return text as String
	}
	
	def help()
	{
		println "Get IP"
		println "Get Geo IP"
		println "help"
	}
	
}