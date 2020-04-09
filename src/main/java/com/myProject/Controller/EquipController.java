package com.myProject.Controller;

import com.myProject.Services.AuthService;
import com.myProject.Services.EquipService;
import com.myProject.entity.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class EquipController {
    private EquipService equipService;
    private AuthService authService;

    @Inject
    public EquipController(EquipService equipService, AuthService authService) {
        this.equipService = equipService;
        this.authService = authService;
    }

    @GetMapping("/equip")
    @ResponseBody
    public EquipListResult getUserEquips(@RequestParam(value = "page") Integer page, @RequestParam("userId") Integer userId) {
        if (page == null || page < 0) {
            page = 1;
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        return equipService.getEquips(page, 6, userId);
    }

    @PostMapping("/equip/delete")
    @CrossOrigin
    @ResponseBody
    public Result deleteEquip(@RequestBody Map<String, String> eids) {
        try {
            return authService.getCurrentUser()
                    .map(user -> equipService.deleteEquip(eids.get("eids"), user))
                    .orElse(EquipListResult.failure("登录后才能操作"));
        } catch (IllegalArgumentException e) {
            return EquipListResult.failure(e);
        }
    }

    @PostMapping("/equip")
    @ResponseBody
    public EquipResult addEquip(@RequestBody Map<String, String> param) {
        try {
            return authService.getCurrentUser()
                    .map(user -> equipService.insertEquip(fromParam(param, user)))
                    .orElse(EquipResult.failure("登陆后才能操作"));

        } catch (IllegalArgumentException e) {
            return EquipResult.failure(e);
        }

    }

    @PatchMapping("/equip/{EId}")
    @ResponseBody
    public EquipResult updateEquip(@PathVariable("EId") Integer EId, @RequestBody Map<String, String> param) {
        try {
            return authService.getCurrentUser()
                    .map(user -> equipService.updateEquip(EId, fromParam(param, user)))
                    .orElse(EquipResult.failure("登录后才能操作"));

        } catch (IllegalArgumentException e) {
            return EquipResult.failure(e);
        }

    }

    private Equip fromParam(Map<String, String> param, User user) {
        Equip equip = new Equip();
        equip.setEName(param.get("ename"));
        equip.setUserId(user.getId());
        equip.setCategory(param.get("category"));
        equip.setDescription(param.get("description"));
        equip.setProtocol(param.get("protocol"));
        return equip;
    }
}
