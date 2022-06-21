package com.grupoagoc.test.exception;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserException extends Exception{
    String msg;
}
