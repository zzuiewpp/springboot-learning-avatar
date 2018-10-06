package com.walker.server.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author walker
 * @version 1.0
 * @since 2018-09-07 01:12
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public Map<String, Object> hello(String str) {
        System.out.println(str);
        Map<String, Object> res = new HashMap<>();
        res.put("id", new Long(111111L));
        res.put("name", "rpcDemo");
        res.put("ctime", new Date());
        return res;
    }
}
