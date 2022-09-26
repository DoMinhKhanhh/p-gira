package com.backendjava18.pgira;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@RestController
public class Wellcome {

    @GetMapping("/welcome")
    public HashMap<String, String> welcome(HttpServletRequest request){
        Enumeration e = request.getHeaderNames();
        HashMap<String, String> mapHeader = new HashMap<>();
        while(e.hasMoreElements()){
            String key = (String) e.nextElement();
            String value = request.getHeader(key);
            mapHeader.put(key,value);
        }
        return mapHeader;
    }
}
