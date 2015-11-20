//import com.toro.map.GoogleMapsClient;
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
import groovyx.net.http.HTTPBuilder


class GoogleAPIsMain {
	static void main(args){
	
		def cuts = new ShortCuts()

		//POST
//		def stateS = []
//		stateS << ["name" : "aprika"]
//		stateS << ["name" : "murika"]
//		def jsonObj = ["name":"Africa", "states":stateS]
//		cuts.PostRequest(jsonObj)
		
		//DELETE
//		def id = '564e8e21a826bb5a4419f2b9'
//		cuts.DeleteRequest(id)
		
		
		
		//GET
		def apirka = cuts.GetRequest()[2]
		apirka.name = "APIRKA"
		
		//UPDATE
		cuts.UpdateRequest(apirka)
		
	}//end main
}//end GoogleAPIsMain


// Maven to update system and lessen all imports


class ShortCuts{

	
	//GET JSON
	def GetJSON(){
		def url = "http://maps.googleapis.com/maps/api/geocode/xml?address=Rios&sensor=false"
		def pathPrefix = ""
		def http = new HTTPBuilder(url)
		def profile = http.request(GET, JSON) { req ->
//			uri.path = "/maps/api/geocode/xml?address=Rios&sensor=false"
			response.success = {resp, xml ->
				xml
			}
		}
		
		profile.firstName = "Rafael"
	}	
	
	//PUSH
//	 this is not fine (i have 400 Bad Request)
//	 because it sends body not in XML
	def UpdateJSON(){
		def url = "http://192.168.22.42:8080"
		def pathPrefix = ""
		def http = new HTTPBuilder(url)
		def savedProfile = http.request(PUT, JSON) { req ->
			uri.path = "/candidates/564c3304a82656e348dd82fa"		//candidates/id
			body = profile
			response.success = {resp, xml ->
				xml
			}
		}
	}	
	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
		
	//BODY FOR update and insert; not used in delete
	
	//GET	
	def GetRequest(){
		def url = "http://192.168.22.42:8080"
		def pathPrefix = ""
		
		//GET
		def http = new HTTPBuilder(url)
		def profile = http.request(GET, JSON) { req ->
			uri.path = "/countries"
			response.success = {resp, xml ->
				xml
			}
		}
		
		def countries = profile._embedded.countries
		
//		PRINT ALL COUNTRIES
//		countries.each{
//			println "Country Name: $it.name"
//			println "ID: $it.id"
//			it.states.each{
//				println "Places: $it.name \n"
//			}
//			println "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
//		}
		
		return countries
	}
		

		

	//UPDATE PUT , INSERT POST
	// this is not fine (i have 400 Bad Request)
	// because it sends body not in XML
		
	//UPDATE
	def UpdateRequest(def country){
		def url = "http://192.168.22.42:8080"
		def pathPrefix = ""
		def http = new HTTPBuilder(url)

		def savedProfile = http.request(PUT, JSON) { req ->
			uri.path = "/countries/${country.id}"
			body = country
			response.success = {resp, xml ->
				println 'Success'
				xml
			}
		}
	}
		
	//INSERT
	def PostRequest(def JSONObj){
		def url = "http://192.168.22.42:8080"
		def http = new HTTPBuilder(url)
		def pathPrefix = ""
		def insertProfile = http.request(POST, JSON) { req ->
			uri.path = "/countries"
			body = JSONObj
			response.success = {resp, xml ->
				xml
			}
		}
	}

	//DELETE
	def DeleteRequest(def id){
		def url = "http://192.168.22.42:8080"
		def pathPrefix = ""
		def http = new HTTPBuilder(url)
		def deleteProfile = http.request(DELETE, JSON) { req ->
			uri.path = "/countries/$id"
			response.success = {resp, xml ->
				xml
			}
		}
	}
		
	
}//end class

