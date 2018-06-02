package cz.cvut.fit.si1.sla.interceptor;

import cz.cvut.fit.si1.sla.model.ShoppingCart;
import cz.cvut.fit.si1.sla.service.ShoppingCartService;
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
     *
     * @param req     request
     * @param res     response
     * @param handler handler
     * @return true if everything is ok
     * @throws Exception in case of errors
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
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @param model    model
     * @throws Exception throws exception in case of errors
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
