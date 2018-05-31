package cz.cvut.fit.si1.sla.controller.management;


import cz.cvut.fit.si1.sla.domain.SlaOrder;
import cz.cvut.fit.si1.sla.domain.SlaOrderSkipassArticle;
import cz.cvut.fit.si1.sla.domain.SlaSkipass;
import cz.cvut.fit.si1.sla.model.ShoppingCart;
import cz.cvut.fit.si1.sla.serviceImpl.ShoppingCartService;
import cz.cvut.fit.si1.sla.serviceImpl.SlaCustomerService;
import cz.cvut.fit.si1.sla.serviceImpl.SlaOrderService;
import cz.cvut.fit.si1.sla.serviceImpl.SlaSkipassService;
import cz.cvut.fit.si1.sla.validator.CustomerOrderFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;

@Controller
@SessionAttributes("shoppingCart")
@RequestMapping("/management/order/new")
public class OrderShoppingCartController {

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

    @ModelAttribute("shoppingCart")
    private ShoppingCart getShoppingCart() {
        return new ShoppingCart();
    }

    @RequestMapping(value = "{id}/add", method = RequestMethod.POST)
    public @ResponseBody
    int addToShoppingCart(@PathVariable("id") long id, @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
        SlaSkipass skipass = skipassService.findById(id);
        if (skipass != null) {
            shoppingCartService.addToCart(shoppingCart, skipass);
        }

        return shoppingCartService.nuberOfItems(shoppingCart);
    }

    @RequestMapping(value = "{id}/setcount/{cnt}", method = RequestMethod.POST)
    public @ResponseBody
    HashMap<String, Long> setCountOfItem(@PathVariable("id") long id, @PathVariable("cnt") int cnt,
                                         @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {

        SlaSkipass skipass = skipassService.findById(id);

        if (skipass != null) {
            shoppingCartService.setCountOfItem(shoppingCart, skipass, cnt);

            HashMap<String, Long> map = new HashMap<String, Long>();
            map.put("cnt", (long) shoppingCartService.nuberOfItems(shoppingCart));
            map.put("price", skipass.getPrice());
            return map;
        }

        return null;
    }

    @RequestMapping(value = "{id}/remove", method = RequestMethod.POST)
    public @ResponseBody
    int removeFromShoppingCart(@PathVariable("id") long id,
                               @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {


        SlaSkipass skipass = skipassService.findById(id);
        if (skipass != null) {
            shoppingCartService.removeFromCart(shoppingCart, skipass);
        }

        return shoppingCartService.nuberOfItems(shoppingCart);
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllItems(Model model,
                               @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {

        model.addAttribute("items", shoppingCartService.getAllList(shoppingCart));
        model.addAttribute("totalPrice", shoppingCart.getTotalPrice());
        model.addAttribute("skipasses", skipassService.getAllList());
        return "jsp/management/order/new";
    }


    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkoutConfirm(@ModelAttribute("shoppingCart") ShoppingCart shoppingCart,
                                  SessionStatus sessionStatus,
                                  final RedirectAttributes redirectAttributes) {
        SlaOrder order = orderService.createOrderFromCartItems(null, shoppingCart.getCartItems());
        sessionStatus.setComplete();


        int price = 0;
        for (SlaOrderSkipassArticle article : order.getArticles()) {
            price += article.getSkipass().getPrice();
        }
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Objednavka c. " + order.getIdOrder() + " uspesne vytvorena.");
        redirectAttributes.addFlashAttribute("msg", "Cena objednavky " + price + " Kc");
        return "redirect:/management/order/" + order.getIdOrder();
    }

}
