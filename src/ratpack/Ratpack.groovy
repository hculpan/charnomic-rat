import static ratpack.groovy.Groovy.ratpack
import static groovy.json.JsonOutput.toJson

class DatabaseConfig {
	String host = "localhost"
	String user = "root"
	String password
	String db = "charnomic"
}

ratpack {
	serverConfig {
		json "dbconfig.json"
		env()
		require("/database", DatabaseConfig)
	}

	handlers{
		prefix("v1") {
			get("config") { DatabaseConfig config ->
				render toJson(config)
			}
		}
		get {
			render "Hello"
		}
		files {
			dir("static").indexFiles("index.html")
		}
	}
}
