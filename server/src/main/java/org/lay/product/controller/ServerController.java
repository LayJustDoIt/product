package org.lay.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Lay
 * 2018-03-15 0:39
 */
@RestController
public class ServerController {

    @GetMapping(value = "/msg")
    public String msg() {
        return "this is product' msg. of Feign 2...";
    }

}
