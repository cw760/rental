package org.incito.cw.rental.service;

import java.util.List;

import org.incito.cw.rental.controller.UserController;
import org.incito.cw.rental.dao.UserDao;
import org.incito.cw.rental.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
