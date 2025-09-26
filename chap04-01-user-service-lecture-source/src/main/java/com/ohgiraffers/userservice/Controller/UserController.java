package com.ohgiraffers.userservice.Controller;

import com.ohgiraffers.userservice.dto.*;
import com.ohgiraffers.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    private Environment env;
    private HelloDTO hello;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserController(Environment env
                        , HelloDTO hello
                        , UserService userService
                        , ModelMapper modelMapper) {
        this.env = env;
        this.hello = hello;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    // 로드밸런스가 서버가 살아있는지 확인을 하므로 만들어 둬야 함
    @GetMapping("health")
    public String status() {
        return "I'm Working in User Service " + env.getProperty("local.server.port");
    }

    @GetMapping("/welcome")
    public String welcome(){
        return hello.getMessage();
    }

    /* 설명. 로그인 기능 전 회원가입 기능 먼저 만들기 */
    @PostMapping("/users")
    public ResponseEntity<ResponseRegistUserDTO> registUser(@RequestBody RequestRegistUserDTO newUser){
        UserDTO userDTO = modelMapper.map(newUser, UserDTO.class);

        userService.registUser(userDTO);

        ResponseRegistUserDTO responseUser = modelMapper.map(userDTO,ResponseRegistUserDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users/{memNo}")
    public ResponseEntity<ResponseFindUserDTO> getUsers(@PathVariable String memNo){
        UserDTO userDTO = userService.getUserById(memNo);

        ResponseFindUserDTO responseUser = modelMapper.map(userDTO, ResponseFindUserDTO.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseUser);
    }

    /* 설명. user 서비스에는 설정이 없지만 설정 서버(config server)에 있는 설정 값을 불러 올 수 있는지 테스트 */
    @GetMapping("/test")
    public String test(@Value("${test.message}") String message){
        return message;
    }


}
