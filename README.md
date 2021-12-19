# MessagePack

It's like JSON but fast and small. That is how MessagePack  is described by [MessagePack](https://msgpack.org/). 
It has an easy setup and usage. Decoding for a single operation is faster than json about 1 ms. We can get benefit from MsssagPack especially, when we are working with microservices
Redis, FluentD and Pinterest are the famous companies that use MessagePack.
From their homepage compact = true and schema = 0 are the entries they use for benchmarking. Json encoded version is 27 bytes while MessagePack encoded version is 18 bytes.
It has also a  huge support for programming languages. 
But migrating  from json to message pack we have to be careful as every applications has different conditions and challenges. So we have to see the test results before giving any desicions. 


We are going to create a simple tutorial for MessagePack with Spring Boot. We create a RESTful Web Service application. We add the dependencies to our pom.xml.
```
        <dependency>
            <groupId>org.msgpack</groupId>
            <artifactId>msgpack</artifactId>
            <version>0.6.12</version>
        </dependency>

        <dependency>
            <groupId>org.msgpack</groupId>
            <artifactId>jackson-dataformat-msgpack</artifactId>
            <version>0.9.0</version>
        </dependency>

```
We create a Student class with lombok. If you are using intellij idea and you can add the Lombok plugin from settings-plugins menu.
```
@Data
public class Student {
    private long id;
    private String name;
    private int studentid;
}
```


We create our RestController. The Bean injection converters the message, with  response type "application", "x-msgpack".
We have created a simple student list. 
```
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

```
When we click the url /students you will see a file downloading. But it is not human readable. To read the data we will use the /msgpack url.








