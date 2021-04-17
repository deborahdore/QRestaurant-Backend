package com.certimetergroup.qrestaurant.utility;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PathUtility {

    public static String getCurrentPath() {
        UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        return builder.buildAndExpand().getPath();
    }
}