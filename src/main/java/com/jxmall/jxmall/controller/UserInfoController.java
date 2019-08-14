package com.jxmall.jxmall.controller;


import com.jxmall.jxmall.modle.UserInfo;
import com.jxmall.jxmall.service.UserInfoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

   //select info from user_info
    @GetMapping(value = "/list")
    @ResponseBody
    public List<UserInfo> getUserList()
    {
        return userInfoService.findAll();
    }

    @GetMapping(value = "/login/userName={userName}")
    @ResponseBody
    public UserInfo getUserByUserId(@PathVariable("userName") String userName,
                                    @RequestParam("userPassword") String userPassword,
                                    HttpSession httpSession)
    {
        UserInfo userInfo = userInfoService.findOneforLogin(userName,userPassword);
        httpSession.setAttribute("userId",userInfo.getUserId());
        return userInfo;
    }


    //add user to user_info
    @PostMapping(value = "/addUser")
    public UserInfo addUser(@RequestParam("userName") String userName,
                            @RequestParam("userPassword") String userPassword)
    {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(userPassword);
        return userInfoService.save(userInfo);
    }



    //update user to user_info
    @PutMapping(value = "/updUser/{userId}")
    public UserInfo updUser(@PathVariable("userId") Integer userId,
                            @RequestParam("userName") String userName,
                            @RequestParam("password") String userPassword)
    {
        UserInfo userInfo = new UserInfo();
        userInfo = userInfoService.findOne(userId);
        userInfo.setUserName(userName);
        userInfo.setUserPassword(userPassword);
        return userInfoService.save(userInfo);

    }


    //delete user to user_info
    @DeleteMapping(value = "/delUser/{userId}")
    public void delUser(@PathVariable("userId") Integer userId)
    {
        UserInfo userInfo = new UserInfo();
        userInfo = userInfoService.findOne(userId);
        userInfoService.delete(userInfo);
    }



}
