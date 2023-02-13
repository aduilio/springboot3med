package com.aduilio.med.problemdetails;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConstraintViolation {
    
    private String field;
    private String message;
}
