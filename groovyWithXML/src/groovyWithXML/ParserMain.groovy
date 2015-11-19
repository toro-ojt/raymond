package groovyWithXML

class ParserMain {
	static void main(args){
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
		//print 'Input ID: '
		//def id = br.readLine() as int
		
		def list = new XmlSlurper().parse('http://wsf.cdyne.com/WeatherWS/Weather.asmx/GetWeatherInformation')

		//print inputted ID, information
//		def asdf = list.WeatherDescription.find{ it.WeatherID == id }
//		
		//condition if no ID found
//		if(asdf.size() == 0)
//		{
//			println 'No weather found'
//		}
//		else
//		{
//			println 'Weather ID: ' +  asdf.WeatherID
//			println 'Weather Description: ' +  asdf.Description
//
//		}
		

		//new id, des, pic
		//list all
//		def sss = list.WeatherDescription
//		
//		sss.each
//		{
//			println "Weather ID: $it.WeatherID" 
//			println "Weather Description: $it.Description"
//			println "Weather PictureURL: $it.PictureURL"
//		}
//		
//		
//		def lll = list.WeatherDescription.collect{ it.WeatherID }
//		
//		println lll
		

		//find with partial string
		int i = 0
		def kkk = list.WeatherDescription.findAll{ it.Description.toString().contains('Snow') }
		
		kkk.each{
			println it.WeatherID + ' : ' + it.Description
		}
	}
	
}
