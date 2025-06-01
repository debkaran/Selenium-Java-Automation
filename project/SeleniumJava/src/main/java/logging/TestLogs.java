package logging;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class TestLogs {

	private TestLogs() {
	}

//	private static Logger logger = Logger.getLogger(TestLogs.class);
	private static final Logger logger = LogManager.getLogger(TestLogs.class);
	
	public static void main(String[] args) {

//		PropertyConfigurator.configure(null);
		
//		new Thread(TestLogs::startLogServer).start();

		logger.trace("This is a TRACE level log message.");
 		logger.debug("This is a DEBUG level log message.");
		logger.info("This is an INFO level log message.");
		logger.fatal("This is a FATAL level log message.");
		logger.warn("This is a WARN level log message.");
		logger.error("This is an ERROR level log message.");
		
 	}
	
	// Method to start the log server
	public static void startLogServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
            server.createContext("/logs", new HttpHandler() {

				public void handle(HttpExchange exchange) throws IOException {
					// handle only POST requests
					if("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
						String response = "Log received successfully";
						exchange.sendResponseHeaders(200, response.getBytes().length);
						OutputStream os = exchange.getResponseBody();
						os.write(response.getBytes());
						os.close();
					} else {
						// Respond with a method not allowed status for non-POST requests
                        exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
					}
				}
            	
            });
         // Start the server
            server.start();
            System.out.println("Log server is running");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
