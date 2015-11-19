@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')

import wslite.soap.*
class weatherMain {
  static void main(args){
    def client = new SOAPClient('http://wsf.cdyne.com/WeatherWS/Weather.asmx')
    def response = client.send(
        """<?xml version="1.0" encoding="utf-8"?>
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:weat="http://ws.cdyne.com/WeatherWS/">
           <soapenv:Header/>
           <soapenv:Body>
              <weat:GetCityForecastByZIP>
                 <!--Optional:-->
                 <weat:ZIP>93657</weat:ZIP>
              </weat:GetCityForecastByZIP>
           </soapenv:Body>
        </soapenv:Envelope>"""
    )
    println response.GetCityForecastByZIPResponse.GetCityForecastByZIPResult.City
  }
}

