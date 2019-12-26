package org.nuaa.wa.waelder.controller;

import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name: WishController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-19 21:12
 * @Version: 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/wish")
public class WishController {
    @Autowired
    private MailService mailService;
    @GetMapping
    public Response wish(String wishContent) {
        return mailService.sendWishMail(wishContent);
    }
}
