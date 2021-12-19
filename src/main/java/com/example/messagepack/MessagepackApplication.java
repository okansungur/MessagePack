package com.example.messagepack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MessagepackApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(MessagepackApplication.class, args);
        //Sample client call
        /*URL url = new URL("http://localhost:7815/students");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/x-msgpack");
        InputStream responseStream = connection.getInputStream();
        MessagePack msgpack = new MessagePack();
        System.out.println(msgpack.read(responseStream));*/

    }

}
