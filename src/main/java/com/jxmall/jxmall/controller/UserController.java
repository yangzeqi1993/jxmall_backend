package com.jxmall.jxmall.controller;


import com.jxmall.jxmall.modle.User;
import com.jxmall.jxmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/getOne/{userId}")
    @ResponseBody
    public User getUserForLogin(@PathVariable("userId") Integer userId)
    {
        return userService.getUserByUserId(userId);
    }

    // 根据userID得到用户名userName
    @GetMapping(value = "/getUserName/userId={userId}")
    public String getUserName(@PathVariable("userId") Integer userId)
    {
        return userService.getUserByUserId(userId).getUserName();
    }

    // 用户注册
    @PostMapping(value = "/addUser")
    public String addUser(@RequestBody User user)
    {

        if (userService.getUserByUserName(user.getUserName()) != null) {
            System.out.println(userService.getUserByUserName(user.getUserName()));
            return "userName false";
        }else if(userService.getUserByUserPhone(user.getUserPhone()) != null){
            return "userPhone false";
        }else if(userService.getUserByUserMallName(user.getUserMallName()) != null){
            return "userMallName false";
        }else {
            userService.save(user);
            return "Success";
        }
    }

    @PutMapping(value = "/updUser/{userId}")
    public User updateUser(@PathVariable("userId") Integer userId,
                           @RequestParam("userEmail") String userEmail,
                           @RequestParam("userPhone") String userPhone,
                           @RequestParam("userSex") String userSex,
                           @RequestParam("userRealName") String userRealName,
                           @RequestParam("userMallName") String userMallName,
                           @RequestParam("userAddress") String userAddress)
    {

        User user = new User();
        user = userService.getUserByUserId(userId);
        user.setUserId(userId);
        user.setUserEmail(userEmail);
        user.setUserPhone(userPhone);
        user.setUserSex(userSex);
        user.setUserRealName(userRealName);
        user.setUserMallName(userMallName);
        user.setUserAddress(userAddress);

        return userService.save(user);
    }





}
