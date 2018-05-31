package cz.cvut.fit.si1.sla.interceptor;

import cz.cvut.fit.si1.sla.model.ShoppingCart;
import cz.cvut.fit.si1.sla.serviceImpl.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cz.cvut.fit.si1.sla.interceptor.UserInterceptor.isRedirectView;


@Component("shoppingCartInterceptor")
public class ShoppingCartInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    ShoppingCartService shoppingCartService;


    /**
     * Creates shoppingCart and saves it to the session in case it's not already there
     * @param req
     * @param res
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(
            HttpServletRequest req,
            HttpServletResponse res,
            Object handler) throws Exception {

        if (null == req.getSession().getAttribute("shoppingCart")) {
            req.getSession().setAttribute("shoppingCart", new ShoppingCart());
        }
        return true;
    }

    /**
     * Handles adding number of items in shopping cart into the model
     * @param request
     * @param response
     * @param handler
     * @param model
     * @throws Exception
     */
    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView model) throws Exception {


        if (model != null && !isRedirectView(model)) {
            ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
            if (shoppingCart != null)
                model.addObject("shoppingCartCount", shoppingCartService.nuberOfItems(shoppingCart));
            else
                model.addObject("shoppingCartCount", 0);
        }

    }


}
