package org.ngo.user.controller;

import org.ngo.basic.controller.BasicController;
import org.ngo.basic.model.ResultVO;
import org.ngo.user.model.User;
import org.ngo.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BasicController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String uniquename, String passwd) {
        userService.userLogin(uniquename, passwd);
        return "success";
    }

    @RequestMapping(value = "/{userid}", method = RequestMethod.GET)
    public String getUser(@PathVariable Integer userid) {
        User user = userService.get(userid);
        assert (user.getUsername().equals("abc"));
        return "success";
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public String logout(Integer id) {
        userService.userLogout(id);
        return "";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO addUser(User user) {
        if (userService.insert(user) > 0) {
            this.result.setSuccess(true);
        }
        else {
            this.result.setSuccess(false);
        }
        return this.result;
    }

    public ResultVO modifyUser(User user) {
        userService.update(user);
        this.result.setData("update user success");
        this.result.setSuccess(true);
        return this.result;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResultVO getUserProfile(Integer id) {
        this.result.setData(userService.getProfile(id));
        return this.result;
    }

}
