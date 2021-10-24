package com.sullivan.lmall.controller;

import com.sullivan.lmall.controller.ex.*;
import com.sullivan.lmall.model.User;
import com.sullivan.lmall.service.UserService;
import com.sullivan.lmall.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 描述 用户管理Controller
 *
 * @author : 小蚊子
 * @date : 2021-09-20 21:17
 **/
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /** 头像文件大小的上限值(10MB) */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    /** 允许上传的头像的文件类型 */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    @RequestMapping("/register")
    //@ResponseBody 表示此方法的响应结果以json的格式进行数据的响应给到前端
    public JsonResult<Void> registerUser(User user) {
        userService.registerUser(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/login")
    public JsonResult<User> loginUser(String username, String password, HttpSession session) {
        User userData = userService.loginUser(username, password);
        session.setAttribute("uid", userData.getUid());
        session.setAttribute("username", userData.getUsername());
        return new JsonResult<>(OK, userData);
    }

    @RequestMapping("/update-password")
    public JsonResult<Void> updateUserPassword(String oldPassword,
                                               String newPassword, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @GetMapping("/getByUid")
    public JsonResult<User> getUserByUid(HttpSession session) {
        User userInfoByUid = userService.getUserInfoByUid(getUidFromSession(session));
        return new JsonResult<>(OK, userInfoByUid);
    }

    @RequestMapping("/update-info")
    public JsonResult<Void> updateUserInfo(User user, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updateUserInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/update-avatar")
    public JsonResult<String> updateAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空！");
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件超出限制！");
        }
        // 判断文件的格式是否是我们规定的后缀类型
        String contentType = file.getContentType();
        // 如果集合包含某个元素则返回true
        if (!AVATAR_TYPES.contains(contentType)) {
            throw new FileTypeException("文件类型不支持！");
        }
        // 上传的文件
        String parent = session.getServletContext().getRealPath("upload");
        // File对象指向这个路径，File是否存在
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdir();
        }
        // 获取这个文件的名称，UUID工具来将生成一个新的字符串作为文件名
        String originalFileName = file.getOriginalFilename();
        int index = originalFileName.lastIndexOf(".");
        String suffix = originalFileName.substring(index);
        String fileName = UUID.randomUUID().toString().toUpperCase() + suffix;
        File dest = new File(dir, fileName); // 此时的文件是一个空文件
        // 将参数file中的数据写入空文件中（dest）
        try {
            file.transferTo(dest); // 将file文件中的数据写入dest文件中
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常!");
        } catch (IOException e) {
            throw new FileUploadException("文件读写异常!");
        }
        String avatar = "/upload/" + fileName;
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updateAvatarByUid(uid, avatar, username);
        // 返回用户头像的路径给前端界面，将来用来头像展示使用
        return new JsonResult<>(OK, avatar);
    }
}
