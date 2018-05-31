package cz.cvut.fit.si1.sla.controller.front;


import cz.cvut.fit.si1.sla.domain.*;
import cz.cvut.fit.si1.sla.interceptor.UserInterceptor;
import cz.cvut.fit.si1.sla.serviceImpl.SlaCustomerService;
import cz.cvut.fit.si1.sla.serviceImpl.SlaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileWelcomeController {

    @Autowired
    SlaCustomerService customerService;

    @Autowired
    SlaOrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showProfile(Model model) {
        if (!UserInterceptor.isUserLogged()) {
            return "redirect:/login";
        }


        SlaUser user = (SlaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for (SlaUserRole role : user.getUserRoles()) {
            if (role.getRole().getRoleName().equals("ROLE_ADMIN"))
                return "redirect:/";
        }

        SlaCustomer customer = user.getCustomer();

        List<SlaOrder> orderList = orderService.getOrdersOfCustomer(customer);

        HashMap<SlaOrder, Integer> orderIntegerHashMap = new HashMap<>();
        for (SlaOrder o : orderList) {
            Integer i = 0;
            for (SlaOrderSkipassArticle a : o.getArticles()) {
                i += a.getSkipass().getPrice().intValue();
            }
            orderIntegerHashMap.put(o, i);
        }

        model.addAttribute("user", user);
        model.addAttribute("customer", customer);
        model.addAttribute("orders", orderIntegerHashMap);

        return "jsp/front/profile";
    }

}
