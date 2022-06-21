package com.grupoagoc.test.controller;

import com.grupoagoc.test.controller.model.UserRequest;
import com.grupoagoc.test.exception.UserException;
import com.grupoagoc.test.persist.User;
import com.grupoagoc.test.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {
    private LoginService loginService;

    @Operation(description = "Add or update User register.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Created or updated successfully"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Parameter(required = true)
    @PostMapping("/addUser")
    public @ResponseBody ResponseEntity newRegister(@RequestBody @Valid UserRequest request) {
        log.info("User: {}", request);
        User user;
        try {
            user = loginService.registerOrUpdateUserDetails(request);
        } catch (UserException e) {
            return new ResponseEntity<>(e.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
