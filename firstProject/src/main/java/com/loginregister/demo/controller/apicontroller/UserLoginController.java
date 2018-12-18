package com.loginregister.demo.controller.apicontroller;

import com.loginregister.demo.dto.CommonResponse;
import com.loginregister.demo.entity.User;
import com.loginregister.demo.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = {"/user"})
public class UserLoginController {

    /**
     * 最开始希望用Map的形式接参数，后来不用了，将请求对应的接受方式记录一下
     *
     * @RequestBody Map<String , Object> map      post请求
     * @RequestParam Map<String , Object> map     get请求
     */

    /**
     * 注入service
     */
    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping(value = "/userLogin" , method = RequestMethod.POST)
    public CommonResponse login(@RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "password", required = false) String password) {
        CommonResponse<User> res = new CommonResponse<>();

        User user = userLoginService.userLogin(username, password);
        if(user == null){
            res.setCode(-1);
            res.setMsg("登录失败，用户名或密码错误");
            return res;
        }

        res.setCode(1);
        res.setMsg("登录成功");
        res.setData(user);
        return res;
    }

    /**
     * 注册新用户
     *
     * @return 注册结果
     */
    @ResponseBody
    @RequestMapping(value = "/uregister", method = RequestMethod.POST)
    public CommonResponse addUser(@RequestParam(value = "username", required = false) String username,
                                  @RequestParam(value = "password", required = false) String password,
                                  @RequestParam(value = "password2", required = false) String password2,
                                  @RequestParam(value = "age", required = false) Integer age) {
        // 如果数据库中没有该用户，可以注册

        User user = new User();
        user.setAge(age);
        user.setUsername(username);
        user.setPassword(password);

        User u = userLoginService.findByUserName(username);

        CommonResponse response = new CommonResponse();

        if(u != null){
            response.setCode(-1);
            response.setMsg("用户名已注册");
            return response;
        }

        userLoginService.register(user);
        response.setCode(1);
        response.setMsg("注册成功");
        return response;

    }
}
