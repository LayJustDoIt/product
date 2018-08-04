package org.lay.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Lay
 * 2018-04-29 12:38
 */
@RestController
@RefreshScope
@RequestMapping(value = "/env")
public class EnvController {

    @Value("${env}")
    private String env;

    @GetMapping(value = "/print")
    public String print() {
        return env;
    }
}
