package com.auguigu.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.auguigu.gmall.bean.RestResult;
import com.auguigu.gmall.bean.UmsMember;
import com.auguigu.gmall.bean.UmsMemberReceiveAddress;
import com.auguigu.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Reference
    UserService userService;

    @RequestMapping("index")
    @ResponseBody
    public String index()
    {
        return "hello user";
    }

    @RequestMapping("getReceiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(Integer memberId)
    {
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses= userService.getReceiveAddressByMemberId(memberId);
        return umsMemberReceiveAddresses;
    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser()
    {
        List<UmsMember> umsMembers= userService.getAllUser();
        return umsMembers;
    }
}
