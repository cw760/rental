package org.incito.cw.rental.service;

import java.util.List;

import org.incito.cw.rental.controller.UserController;
import org.incito.cw.rental.dao.UserDao;
import org.incito.cw.rental.model.User;
import org.incito.cw.rental.utils.Encrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@RequestMapping("/service/user/")
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserDao userDao;

  @RequestMapping(method = RequestMethod.GET, value = "list")
  @ResponseBody
  public List<User> list() {
    logger.debug("get user list");
    return userDao.getUserList();
  }

  @RequestMapping(method = RequestMethod.POST, value = "login")
  @ResponseBody
  public User valid(@RequestBody User user) throws Exception {
    logger.debug("login " + user);
    if (StringUtils.isEmpty(user.getAccount())) {
      throw new Exception("账户不能为空");
    }
    if (StringUtils.isEmpty(user.getPwd())) {
      throw new Exception("密码不能为空");
    }
    User findByAccount = userDao.findByAccount(user.getAccount());
    if (Encrypt.encrypt(user.getPwd()).equals(findByAccount.getPwd())) {
      return findByAccount;
    } else {
      throw new Exception("用户名密码不正确");
    }
  }

}
