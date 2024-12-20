package com.hepsiBurada.utilities.Models;

import lombok.*;


@Getter
@Setter
@Builder
public class AuthorizationValue {

    private String value;
    private String type;
    private String keyName;
    private Object urlMatcher;

}
