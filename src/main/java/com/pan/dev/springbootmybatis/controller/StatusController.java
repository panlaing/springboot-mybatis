package com.pan.dev.springbootmybatis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liang.pan
 * @Description: TODO
 * @date 2019/10/15 10:25
 */
@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping("/version")
    public ResponseEntity status() {

        return ResponseEntity.ok("v1.0");
    }
}
