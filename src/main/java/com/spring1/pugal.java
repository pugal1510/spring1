/**package com.spring1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
                                                                                              
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "com.spring1")
@RestController
public class pugal {
    public static void main(String[] args) {
        SpringApplication.run(pugal.class, args);
    }
    @GetMapping("/welcome")
    public String hello()
    {
        return "hello world";
    }
}**/
/**
package com.spring1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class pugal {
    public static void main(String[] args) {
        SpringApplication.run(pugal.class, args);
    }
    @GetMapping("/welcome")
    public GiveResponse hello() {
        return new GiveResponse("hello world");
    }
    record GiveResponse(String message) {

    }
}
 */
package com.spring1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.util.stream.Stream;

import static java.util.spi.ToolProvider.findFirst;

@SpringBootApplication
@RestController
public class pugal {
    private static List<student> students ;
    static   {
        students = new ArrayList<>();
        student pugal=new student( "Pugal", 1, "male");
        student rithika=new student("rithika",2,"female");
        students.add(pugal);
       students.add(rithika);

    }
    public static void main(String[] args) {
        SpringApplication.run(pugal.class, args);
    }
@GetMapping ("api/v1/students")
    public static List<student> getStudents() {
        return students;
    }
    @GetMapping ("api/v1/students/{studentID}")
    public student getStudents(@PathVariable("studentid")Integer id)
    {
        return students.stream().filter(student -> student.id.equals(id))
            .findFirst()
                .orElseThrow(
                        ()-> new IllegalArgumentException("id not found")
                );


    }


    static  class student
        {
        private String name;
        private Integer id;
        private String gender;
        public student(){};

            public student(String name, Integer id, String gender) {
                this.name = name;
                this.id = id;
                this.gender = gender;
            }

            public String getName() {
                return name;
            }

            public Integer getId() {
                return id;
            }

            public String getGender() {
                return gender;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                student student = (student) o;
                return Objects.equals(name, student.name) && Objects.equals(id, student.id) && Objects.equals(gender, student.gender);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, id, gender);
            }

            @Override
            public String toString() {
                return "student{" +
                        "name='" + name + '\'' +
                        ", age=" + id +
                        ", gender='" + gender + '\'' +
                        '}';
            }
        }
    }

