package com.dav.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dav.mybatis.common.logger.LoggerUtils;
import com.dav.mybatis.domain.User;
import com.dav.mybatis.service.UserService;



@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public User getAll() {
        String job = getNamePackageAndClass(HelloController.class.getPackage().getName());
        String step = getNamePackageAndClass(HelloController.class.getName());
        // Log Start Job
        LoggerUtils.logStart(this, job, step);

        return userService.getUserByUsername("duonganhvu");
    }

    public static String getNamePackageAndClass(String packageName) {
        if (packageName.contains(".")) {
            String[] result = packageName.split("\\.");
            return result[(result.length) - 1];
        } else {
            return packageName;
        }

    }
}
