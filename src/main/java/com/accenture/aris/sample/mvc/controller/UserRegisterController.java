package com.accenture.aris.sample.mvc.controller;

import java.util.Date;
import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.accenture.aris.core.support.ServiceResult;
import com.accenture.aris.core.support.codeloader.CodeLoader;
import com.accenture.aris.core.support.codeloader.StaticCodeLoader;
import com.accenture.aris.core.support.message.Messages;
import com.accenture.aris.sample.mvc.form.UserRegisterForm;
import com.accenture.aris.sample.mvc.form.UserRegisterFormValidator;
import com.accenture.aris.sample.business.entity.RoleEntity;
import com.accenture.aris.sample.business.entity.UserEntity;
import com.accenture.aris.sample.business.service.RoleService;
import com.accenture.aris.sample.business.service.UserService;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = "userRegisterForm")
public class UserRegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisterController.class);

    @Autowired
    private Messages messages;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private StaticCodeLoader staticCodeLoader;

    @Autowired
    private CodeLoader codeLoader;
    
    @Autowired
    private UserRegisterFormValidator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    // [Action] 新規入力
    @RequestMapping(value = "/registerInput")
    public String registInput(UserRegisterForm userRegisterForm, BindingResult result, Model uiModel, SessionStatus status) {
        // セッションクリア
        status.setComplete();
        //リスト値読み込み
        setInitialValue(uiModel);
        // ラジオボタンの初期値設定用
        userRegisterForm.setSex("s1");
        return "user/userRegisterInput";
    }

    // [Action] 新規入力（再入力）
    @RequestMapping(value = "/registerReInput")
    public String registReInput(UserRegisterForm userRegisterForm, BindingResult result, Model uiModel, SessionStatus status) {
        //リスト値読み込み
        setInitialValue(uiModel);
        // 入力チェックエラーの時はセッションをクリア
        status.setComplete();
        return "user/userRegisterInput";
    }
    
    // [Action] 入力内容確認
    @RequestMapping(value = "/registerConfirm")
    public String registConfirm(@Valid UserRegisterForm userRegisterForm, BindingResult result, Model uiModel, SessionStatus status) {
        
        if (result.hasErrors() == false) {
            validator.validateConfirmedPassword(userRegisterForm, result, uiModel);
        }
        if (result.hasErrors() == true
                || validator.validateDuplicateId(userRegisterForm, result, uiModel) == false) {
            LOGGER.debug("invalid request.");
            //エラーのときはもう一回初期値設定戻す。
            setInitialValue(uiModel);
            // 入力チェックエラーの時はセッションをクリア
            status.setComplete();
            return "user/userRegisterInput";
        }
        setDataFromCode(userRegisterForm);
        userRegisterForm.setStartDate(new Date());
        return "user/userRegisterConfirm";
    }

    // [Action] 登録実行
    @RequestMapping(value = "/register")
    public String regist(UserRegisterForm userRegisterForm, Model uiModel, SessionStatus status) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userRegisterForm, userEntity);
        try {
            userService.saveUserService(userEntity);
        } catch(DuplicateKeyException e) {
            userRegisterForm.setId(null);
            uiModel.addAttribute("message", messages.getMessage("W00001"));
            LOGGER.debug("input user id is already exist.");
            setInitialValue(uiModel);
            userRegisterForm.setId(null);
            // 入力チェックエラーの時はセッションをクリア
            status.setComplete();
            return "user/userRegistInput";
        }
        status.setComplete();
        return "redirect:/user/detail/" + userRegisterForm.getId();
    }

    private void setInitialValue(Model uiModel){
        //Role値取得
        ServiceResult<List<RoleEntity>> serviceResult = roleService.selectRoleByEntity();
        uiModel.addAttribute("roles", serviceResult.getResult());
        //ラジオボタン、コンボボックス用値取得
        uiModel.addAttribute("sexs", codeLoader.getCodeDataList("sex"));
        uiModel.addAttribute("nationalities", codeLoader.getCodeDataList("nationality"));
    }
    
    private UserRegisterForm setDataFromCode(UserRegisterForm userRegisterForm){
        userRegisterForm.setLabelRoleId(roleService.getRoleName(userRegisterForm.getRoleId()));
        userRegisterForm.setLabelSex(codeLoader.getCodeDataValue("sex", userRegisterForm.getSex()));
        userRegisterForm.setLabelNationality(codeLoader.getCodeDataValue("nationality", userRegisterForm.getNationality()));
        return userRegisterForm;
    }
}
