package com.epf.rentmanager.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.epf.rentmanager.service", "com.epf.rentmanager.dao", "com.epf.rentmanager.persistence" })

public class AppConfiguration {

}
