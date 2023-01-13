package com.example.apibasic.res;

import com.example.apibasic.req.ApiController2;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

//@Controller
//@ResponseBody  --> 두개를 합쳐서 restcontroller
@RestController
@Slf4j

public class ApiController3 {

    @GetMapping("/res1")
    public String res1(){
        log.info("/res1 GET!!");
        return "hello";
    }
    @GetMapping("/res2") //원래는 produces ="application/json"
    public List<String> res2(){
        log.info("/res2 GET!!");
        return List.of("짜장면","볶음밥","탕수육");
    }


    @GetMapping(value="/res3",produces = "application/json")
    public ApiController2.OrderInfo res3(){
        log.info("/res3 GET!!");
        ApiController2.OrderInfo orderInfo=new ApiController2.OrderInfo();
        orderInfo.setOrderNo(12L);
        orderInfo.setGoodsName("세탁기");
        orderInfo.setGoodsAmount(2);
        return orderInfo;
    }


    //사원 목록 정보
    @GetMapping("/employees")
    public List<Employee> empList(){
        return List.of(
                new Employee("홍길동","영업부",LocalDate.of(2019,12,21))
                ,new Employee("박영희","구매부",LocalDate.of(2020,12,14))
                ,new Employee("김수호","개발부",LocalDate.of(2021,1,2))
        );
    }
    @Setter @Getter @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Builder
    public static class Employee{
        private String empName;
        private String deptName;
        private LocalDate hireDate;

    }


    //응답시에 응답헤더정보와 응답상태코드를 조작하려면 ResponseEntity 객체 사용
    @GetMapping("/res4")
    public ResponseEntity<?> res4(String nick){
        if(nick == null || nick.equals("")){
            return ResponseEntity.badRequest().build();
        }
        Employee employee = Employee.builder()
                .empName(nick)
                .deptName("영업부")
                .hireDate(LocalDate.of(2019, 2, 2))
                .build();

        //응답헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.set("department","business");
        headers.set("glkaje","haeppo");


        return ResponseEntity
                .ok()
                //.status(HttpStatus.OK)
                .headers(headers)
                .body(employee);
    }
}
