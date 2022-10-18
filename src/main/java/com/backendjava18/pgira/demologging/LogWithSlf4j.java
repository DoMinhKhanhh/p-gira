package com.backendjava18.pgira.demologging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo-log-slf4j")
public class LogWithSlf4j {

    @RequestMapping("demoLog")
    public String demoLog(@RequestParam("object") Object params) {
        log.info("hello demo log");
        log.error("error error");
        log.debug("this is  debug 1");
        log.debug("this is  debug 2");
        try {
            int n1 = Integer.parseInt((String) params);
        } catch (Exception e) {
            log.error("FROM METHOD DEMOLOG: ", e.getMessage());
        }
        return "demo logging slf4j";
    }
}
