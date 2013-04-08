package com.toney.publish.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.publish.annotation.AuthRequired;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :com.xiu.portal.web.controller.UserController
 * @DESCRIPTION :对外公布的发布action接口.
 * @AUTHOR :dick.xiao@xiu.com
 * @VERSION :v1.0
 * @DATE :Dec 15, 2012
 *       </p>
 **************************************************************** 
 */
@Controller
@AuthRequired
@RequestMapping(value = "/publish")
public class UserController {


    @RequestMapping(value = "modifyPassword", method = RequestMethod.GET)
    public String modifyUserPassword() {
        return "pages/user/modifyPassword";
    }

}
