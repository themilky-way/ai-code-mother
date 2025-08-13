package com.qian.aicodemother.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.qian.aicodemother.model.dto.user.UserQueryRequest;
import com.qian.aicodemother.model.entity.User;
import com.qian.aicodemother.model.vo.LoginUserVO;
import com.qian.aicodemother.model.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 *  服务层。
 *
 * @author <a href="https://github.com/themilky-way">程序员AndyQian</a>
 */
public interface UserService extends IService<User> {
     /**
      * 用户注册
      * @param userAccount 用户账号
      * @param userPassword 用户密码
      * @param checkPassword 确认密码
      * @return 新用户 id
      */
     long userRegister(String userAccount, String userPassword,String checkPassword);

     /**
      * 获取脱敏后的已登录用户信息
      */
     LoginUserVO getLoginUserVO(User user);

     /**
      * 用户登录
      * @param userAccount 用户账号
      * @param userPassword 用户密码
      * @param request 请求
      * @return 脱敏后用户信息
      */
     LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

     /**
      * 获取当前登录用户
      * @param request 请求
      * @return 用户信息
      */
     User getLoginUser(HttpServletRequest request);

     /**
      * 获取脱敏后用户信息
      * @param user 用户信息
      * @return 脱敏后用户信息
      */
     UserVO getUserVO(User user);

     /**
      * 获取脱敏后用户信息列表
      * @param userList 用户信息列表
      * @return 脱敏后用户信息列表
      */
     List<UserVO> getUserVOList(List<User> userList);

     /**
      * 用户注销
      * @param request 请求
      * @return 是否注销成功
      */
     boolean userLogout(HttpServletRequest request);

     /**
      * 根据查询条件构造查询参数
      *
      */
     QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest);

     /**
      * 密码加密
      * @param userPassword 用户密码
      * @return 加密后的用户密码
      */
     String getEncryptPassword(String userPassword);


}
