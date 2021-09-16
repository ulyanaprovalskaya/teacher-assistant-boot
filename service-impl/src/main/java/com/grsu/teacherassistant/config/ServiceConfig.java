package com.grsu.teacherassistant.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
