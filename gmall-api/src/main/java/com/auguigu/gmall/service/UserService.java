package com.auguigu.gmall.service;

import com.auguigu.gmall.bean.UmsMember;
import com.auguigu.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}
