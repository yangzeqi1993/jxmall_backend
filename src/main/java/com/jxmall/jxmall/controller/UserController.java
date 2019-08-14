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

    @GetMapping(value = "/getUserName/userId={userId}")
    public String getUserName(@PathVariable("userId") Integer userId)
    {
        return userService.getUserByUserId(userId).getUserName();
    }

    //add user to user_info
    @PostMapping(value = "/addUser")
    public String addUser(@RequestBody User user)
    {
        User isUser = userService.getUserByUserId(user.getUserId());
        System.out.println(isUser.getUserName());

        if (isUser.getUserId()!=null) {
            return "false";
        }else {System.out.println("Success");
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
