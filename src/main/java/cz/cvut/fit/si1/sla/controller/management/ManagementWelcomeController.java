package cz.cvut.fit.si1.sla.controller.management;

import cz.cvut.fit.si1.sla.domain.SlaOrderSkipassArticle;
import cz.cvut.fit.si1.sla.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/management")
public class ManagementWelcomeController {


    @Autowired
    SlaAddressService addressService;


    @Autowired
    SlaCustomerService customerService;

    @Autowired
    UserService userService;

    @Autowired
    SlaEmployeeService employeeService;

    @Autowired
    SlaChipCardService chipCardService;

    @Autowired
    SlaOrderService orderService;

    @Autowired
    SlaSkipassService skipassService;


    @RequestMapping("")
    public ModelAndView managementWelcome() {
        //    SlaUser user = (SlaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ModelAndView modelAndView = new ModelAndView("jsp/management/index");
        modelAndView.addObject("cntCustomer", customerService.getAllList().size());
        modelAndView.addObject("cntEmployee", employeeService.getAllList().size());
        modelAndView.addObject("cntUser", userService.getAllList().size());
        modelAndView.addObject("cntChipCard", chipCardService.getAllList().size());
        modelAndView.addObject("cntOrder", orderService.getAllList().size());
        modelAndView.addObject("cntSkipass", skipassService.getAllList().size());

        List<SlaOrderSkipassArticle> articleList = orderService.getAllArticlesList();

        modelAndView.addObject("cntArticles", articleList.size());

        int price = 0;
        for (SlaOrderSkipassArticle article : articleList) {
            price += article.getSkipass().getPrice();
        }
        modelAndView.addObject("priceArticles", price);
        return modelAndView;
    }
}
