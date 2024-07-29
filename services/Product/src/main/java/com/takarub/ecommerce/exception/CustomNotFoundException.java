package com.takarub.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class CustomNotFoundException extends RuntimeException {

    private final String msg;

}
