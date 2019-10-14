package com.pan.dev.springbootmybatis.controller;

import com.pan.dev.springbootmybatis.entity.PtasliInfo;
import com.pan.dev.springbootmybatis.service.PtasliInfoBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:panliang
 * @Description:
 * @params:$params$
 * @return: $returns$
 * @Date: $date$ $time$
 */
@RestController
@RequestMapping("/admin")
public class PtasliInfoController {

    @Autowired
    private PtasliInfoBiz ptasliInfoBiz;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity login(String id) {

        PtasliInfo ptasliInfo = ptasliInfoBiz.sayHello(Long.getLong(id));
        return ResponseEntity.ok(ptasliInfo);
    }
}
