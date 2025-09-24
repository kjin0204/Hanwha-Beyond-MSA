package com.ohgiraffers.userservice.Controller;

import com.ohgiraffers.userservice.dto.HelloDTO;
import com.ohgiraffers.userservice.dto.RequestRegistUserDTO;
import com.ohgiraffers.userservice.dto.ResponseRegistUserDTO;
import com.ohgiraffers.userservice.dto.UserDTO;
import com.ohgiraffers.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
