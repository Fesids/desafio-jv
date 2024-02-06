package com.example.project.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface Utils {

    static URI toUri(String uri){
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(uri).toString());
    }
}
