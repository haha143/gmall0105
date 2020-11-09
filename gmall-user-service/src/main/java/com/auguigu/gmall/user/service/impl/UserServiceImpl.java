package com.auguigu.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.auguigu.gmall.bean.UmsMember;
import com.auguigu.gmall.bean.UmsMemberReceiveAddress;
import com.auguigu.gmall.service.UserService;
import com.auguigu.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.auguigu.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Override
    public List<UmsMember> getAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(Integer memberId) {
        //方法一
        /*Example e=new Example(UmsMemberReceiveAddress.class);//是谁的泛型
        e.createCriteria().andEqualTo(memberId);//设置是根据那个属性进行查询*/
       /* 方法二
        直接生成对象并设置属性，通过该对象进行查询*/
        UmsMemberReceiveAddress umsMemberReceiveAddress=new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        //umsMemberReceiveAddress.setDefaultStatus(defaultStatus);
        List<UmsMemberReceiveAddress>umsMemberReceiveAddressList=umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);

       /* Example e=new Example(UmsMemberReceiveAddress.class);//是谁的泛型
        e.createCriteria().andEqualTo("memberId",memberId);//设置是根据那个属性进行查询
        List<UmsMemberReceiveAddress>umsMemberReceiveAddressList=umsMemberReceiveAddressMapper.selectByExample(e);*/
        return umsMemberReceiveAddressList;
    }
}
