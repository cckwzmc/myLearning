package com.toney.mvc.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.core.constants.Constants;
import com.toney.core.exception.PortalEIBusinessException;
import com.toney.core.model.UserAuthInfo;
import com.toney.web.commons.annotation.AuthRequired;
import com.toney.web.commons.utils.JsonPackageWrapper;
import com.toney.web.commons.utils.UserAuthInfoHolder;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :com.xiu.portal.web.controller.UserController
 * @DESCRIPTION :用户信息管理Controller
 * @AUTHOR :dick.xiao@xiu.com
 * @VERSION :v1.0
 * @DATE :2012-4-9 下午3:58:06
 *       </p>
 **************************************************************** 
 */
@Controller
@AuthRequired
@RequestMapping(value = "/user")
public class UserController {


    @RequestMapping(value = "modifyPassword", method = RequestMethod.GET)
    public String modifyUserPassword() {
        return "pages/user/modifyPassword";
    }
//
//    /**
//     * 
//     * @Description: 修改用户密码
//     * @param modifyUserPassword
//     * @param result
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "modifyPassword", method = RequestMethod.POST, produces = "application/json", params = "format=json")
//    public String modifyUserPassword(@Valid ModifyUserPassword modifyUserPassword, BindingResult result, Model model) {
//        // 得到登录用户信息
//        UserAuthInfo userAuthInfo = UserAuthInfoHolder.getUserAuthInfo();
//        Assert.notNull(userAuthInfo);
//        modifyUserPassword.setUserId(userAuthInfo.getSsoUserId());
//
//        // 修改用户密码
//        JsonPackageWrapper json = new JsonPackageWrapper();
//        if (result.hasErrors()) {
//            json.setScode(JsonPackageWrapper.S_ERR);
//            json.setData("数据校验失败！");
//        } else {
//            try {
//                userManager.modifyUserPassword(modifyUserPassword);
//                json.setScode(JsonPackageWrapper.S_OK);
//                json.setData("密码修改成功！");
//            } catch (PortalEIBusinessException e) {
//                json.setScode(JsonPackageWrapper.S_ERR);
//                json.setData(e.getExtMessage());
//            } catch (Exception e) {
//                json.setScode(JsonPackageWrapper.S_ERR);
//                json.setData("系统发生异常！");
//            }
//        }
//        model.addAttribute(Constants.JSON_MODEL__DATA, json);
//        return "";
//    }


}
