package com.hepsiBurada.utilities.Models;

import java.util.List;

public class RequestBodyModel {
    private Object spec;
    private Options options;
    private String swaggerUrl;

    private boolean usingFlattenSpec;
    public static void main(String[] args) {
        Options.builder().additionalProp1("test").additionalProp2("Test2").additionalProp3("test3").build();
    }

//    {
//        "spec": {},
//        "options": {
//        "additionalProp1": "string",
//                "additionalProp2": "string",
//                "additionalProp3": "string"
//    },
//        "swaggerUrl": "http://petstore.swagger.io/v2/swagger.json",
//            "authorizationValue": {
//        "value": "string",
//                "type": "string",
//                "keyName": "string",
//                "urlMatcher": {}
//    },
//        "usingFlattenSpec": true,
//            "securityDefinition": {
//        "type": "string",
//                "description": "string"
//    }
//    }
}
