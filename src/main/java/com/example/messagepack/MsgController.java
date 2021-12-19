package com.example.messagepack;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.MessagePack;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MsgController {

    @Bean
    HttpMessageConverter messagePackMessageConverter() {
        return new AbstractJackson2HttpMessageConverter(
                new ObjectMapper(new MessagePackFactory()),
                new MediaType("application", "x-msgpack")) {
        };
    }

    @GetMapping("students")
    public List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student();
        student.setId(1L);
        student.setName("Andy");
        student.setStudentid(1149);
        studentList.add(student);
        return studentList;
    }


    @GetMapping("msgpack")
    public String msgpackClient() throws IOException {
        String data = "";
        URL url = new URL("http://localhost:7815/students");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/x-msgpack");
        InputStream responseStream = connection.getInputStream();
        MessagePack msgpack = new MessagePack();
        data = msgpack.read(responseStream) + "";
        return data;
    }


}
