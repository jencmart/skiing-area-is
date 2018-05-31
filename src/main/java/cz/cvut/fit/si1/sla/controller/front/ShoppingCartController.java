package cz.cvut.fit.si1.sla.controller.front;


import cz.cvut.fit.si1.sla.domain.SlaCustomer;
import cz.cvut.fit.si1.sla.domain.SlaOrder;
import cz.cvut.fit.si1.sla.domain.SlaSkipass;
import cz.cvut.fit.si1.sla.domain.SlaUser;
import cz.cvut.fit.si1.sla.dto.CustomerOrderDto;
import cz.cvut.fit.si1.sla.interceptor.UserInterceptor;
import cz.cvut.fit.si1.sla.model.ShoppingCart;
import cz.cvut.fit.si1.sla.serviceImpl.ShoppingCartService;
import cz.cvut.fit.si1.sla.serviceImpl.SlaCustomerService;
import cz.cvut.fit.si1.sla.serviceImpl.SlaOrderService;
import cz.cvut.fit.si1.sla.serviceImpl.SlaSkipassService;
import cz.cvut.fit.si1.sla.validator.CustomerOrderFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("shoppingcart")
public class ShoppingCartController {

    @Autowired
    SlaSkipassService skipassService;

    @Autowired
    SlaCustomerService customerService;

    @Autowired
    SlaOrderService orderService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    CustomerOrderFormValidator customerOrderFormValidator;


    private ShoppingCart getShoppingCart(HttpServletRequest request) {
        return (ShoppingCart) request.getSession().getAttribute("shoppingCart");
    }


    @RequestMapping(value = "{id}/add", method = RequestMethod.POST)
    public @ResponseBody
    int addToShoppingCart(@PathVariable("id") long id,
                          HttpServletRequest request) {
        ShoppingCart shoppingCart = getShoppingCart(request);

        SlaSkipass skipass = skipassService.findById(id);
        if (skipass != null) {
            shoppingCartService.addToCart(shoppingCart, skipass);
        }

        return shoppingCartService.nuberOfItems(shoppingCart);
    }

    @RequestMapping(value = "{id}/setcount/{cnt}", method = RequestMethod.POST)
    public @ResponseBody
    HashMap<String, Long> setCountOfItem(@PathVariable("id") long id, @PathVariable("cnt") int cnt,
                                         HttpServletRequest request) {
        ShoppingCart shoppingCart = getShoppingCart(request);

        SlaSkipass skipass = skipassService.findById(id);

        if (skipass != null) {
            shoppingCartService.setCountOfItem(shoppingCart, skipass, cnt);

            HashMap<String, Long> map = new HashMap<>();
            map.put("cnt", (long) shoppingCartService.nuberOfItems(shoppingCart));
            map.put("price", skipass.getPrice());
            return map;
        }

        return null;
    }

    @RequestMapping(value = "{id}/remove", method = RequestMethod.POST)
    public @ResponseBody
    int removeFromShoppingCart(@PathVariable("id") long id,
                               HttpServletRequest request) {
        ShoppingCart shoppingCart = getShoppingCart(request);

        SlaSkipass skipass = skipassService.findById(id);
        if (skipass != null) {
            shoppingCartService.removeFromCart(shoppingCart, skipass);
        }

        return shoppingCartService.nuberOfItems(shoppingCart);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllItems(Model model,
                               HttpServletRequest request) {
        ShoppingCart shoppingCart = getShoppingCart(request);
        model.addAttribute("items", shoppingCartService.getAllList(shoppingCart));
        model.addAttribute("totalPrice", shoppingCart.getTotalPrice());
        return "jsp/front/shoppingcart/index";
    }


    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkoutConfirm(@ModelAttribute("customerOrderForm") CustomerOrderDto customerOrderDto,
                                  HttpServletRequest request,
                                  BindingResult result, Model model) {

        customerOrderFormValidator.validate(customerOrderDto, result);

        if (result.hasErrors()) {
            return "jsp/front/shoppingcart/checkout";
        }


        SlaCustomer customer;

        // if user is logged in
        if (UserInterceptor.isUserLogged()) {
            SlaUser user = (SlaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (user.getCustomer() != null) {
                customer = customerService.updateCustomerUser(customerOrderDto);
            } else {
                customer = customerService.registerNewCustomerToUser(customerOrderDto, user);
            }
        }

        // if not logged in
        else {
            customer = customerService.registerNewCustomer(customerOrderDto);
        }

        // for all items in shopping cart
        ShoppingCart shoppingCart = getShoppingCart(request);
        SlaOrder order = orderService.createOrderFromCartItems(customer, shoppingCart.getCartItems());
        model.addAttribute("order", order);

        request.getSession().removeAttribute("shoppingCart");
        return "jsp/front/shoppingcart/orderSuccess";
    }


    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkout(Model model,
                           HttpServletRequest request) {

        ShoppingCart shoppingCart = getShoppingCart(request);
        if (shoppingCart.getCartItems().size() == 0)
            return ("redirect:/");

        CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        prepopulateCustomerForm(customerOrderDto);

        model.addAttribute("customerOrderForm", customerOrderDto);
        model.addAttribute("items", shoppingCartService.getAllList(shoppingCart));
        model.addAttribute("totalPrice", shoppingCart.getTotalPrice());

        return "jsp/front/shoppingcart/checkout";
    }


    private void prepopulateCustomerForm(CustomerOrderDto customerOrderDto) {
        // if user is logged
        if (UserInterceptor.isUserLogged()) {
            SlaUser user = (SlaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (user.getCustomer() != null) {
                customerOrderDto.setName(user.getCustomer().getName());
                customerOrderDto.setSurname(user.getCustomer().getSurname());
                customerOrderDto.setEmail(user.getCustomer().getEmail());
                customerOrderDto.setPhone(user.getCustomer().getPhone());
            } else {
                customerOrderDto.setName(user.getEmployee().getName());
                customerOrderDto.setSurname(user.getEmployee().getSurname());
                customerOrderDto.setPhone(user.getEmployee().getPhone());
            }
        }
    }


}

