package org.incito.cw.rental.controller;

import java.util.List;

import org.incito.cw.rental.dao.UserDao;
import org.incito.cw.rental.model.User;
import org.incito.cw.rental.utils.Encrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserDao userDao;

  @RequestMapping(method = RequestMethod.GET, value = "edit")
  public ModelAndView editUser(@RequestParam(value = "id", required = false) String id) {
    logger.debug("Received request to edit user id : " + id);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("userEdit");
    User user = null;
    if (id == null) {
      user = new User();
    } else {
      user = userDao.find(id);
    }

    mav.addObject("user", user);
    return mav;
  }

  @RequestMapping(method = RequestMethod.GET, value = "list")
  public ModelAndView listUser() {
    logger.debug("Received request to list users");
    ModelAndView mav = new ModelAndView();
    List<User> user = userDao.getUserList();
    logger.debug("User Listing count = " + user.size());
    mav.addObject("user", user);
    mav.setViewName("userList");
    return mav;

  }

  @RequestMapping(method = RequestMethod.GET, value = "login")
  public ModelAndView login(@RequestParam(value = "account", required = false) String account,
      @RequestParam(value = "pwd", required = false) String pwd) {
    logger.debug("Received request to login user account: " + account);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("login");
    User user = new User();
    user.setAccount(account);
    user.setPwd(pwd);

    mav.addObject("user", user);
    return mav;
  }

  @RequestMapping(method = RequestMethod.POST, value = "edit")
  public String saveUser(@ModelAttribute User user) {
    logger.debug("Received postback on User " + user);
    user.setStatus("0");// 未激活
    user.setType("2");// 普通用户
    user.setPwd(Encrypt.encrypt(user.getPwd()));
    userDao.save(user);
    return "redirect:list";
  }

  @RequestMapping(method = RequestMethod.POST, value = "login")
  public String valid(@ModelAttribute User user) {
    logger.debug("login " + user);
    if (StringUtils.isEmpty(user.getAccount())) {
      return "账户不能为空";
    }
    if (StringUtils.isEmpty(user.getPwd())) {
      return "密码不正确";
    }
    User findByAccount = userDao.findByAccount(user.getAccount());
    if (Encrypt.encrypt(user.getPwd()).equals(findByAccount.getPwd())) {
      return "redirect:list";// 重定向，防止重复提交
    } else {
      return "redirect:login";
    }
  }
}
