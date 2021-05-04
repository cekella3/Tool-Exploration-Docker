package lvc.cds;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class FileServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            URI givenURI = t.getRequestURI();
            String uri = givenURI.toString();
            String fileName = uri.substring(uri.lastIndexOf("/")+1);
            
            File file = new File("/Files/" + fileName);
            Scanner sc = new Scanner(file);
            String response= "";
            while(sc.hasNextLine())
            {
                response = response + sc.nextLine() + " ";
            }

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            sc.close();
            os.close();
        }
    }

}
