package cz.cvut.fit.si1.sla.interceptor;

import cz.cvut.fit.si1.sla.domain.SlaUser;
import cz.cvut.fit.si1.sla.domain.SlaUserRole;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Component("userInterceptor")
public class UserInterceptor extends HandlerInterceptorAdapter {


    /**
     * Checks if user is logged in
     *
     * @return true if user logged in, otherwise returns false
     */
    public static boolean isUserLogged() {
        try {
            return !SecurityContextHolder.getContext().getAuthentication()
                    .getName().equals("anonymousUser");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Helps determine if current view is redirected
     *
     * @param mv modelAndView
     * @return true if redirected false otherwise
     */
    static boolean isRedirectView(ModelAndView mv) {
        String viewName = mv.getViewName();
        if (viewName.startsWith("redirect:/")) {
            return true;
        }
        View view = mv.getView();
        return (view != null && view instanceof SmartView && ((SmartView) view).isRedirectView());
    }

    /**
     * Handles adding user details to the model
     *
     * @param req   request
     * @param res   response
     * @param o     object
     * @param model model
     * @throws Exception in case of errors
     */
    @Override
    public void postHandle(
            HttpServletRequest req,
            HttpServletResponse res,
            Object o,
            ModelAndView model) throws Exception {

        if (model != null && !isRedirectView(model)) {
            if (isUserLogged()) {
                addToModelUserDetails(model);
            }
        }
    }

    /**
     * Add basic info about the logged user to the model
     *
     * @param model model
     */
    private void addToModelUserDetails(ModelAndView model) {

        SlaUser user = (SlaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String name;
        String surname;

        if (user.getCustomer() == null) {
            name = user.getEmployee().getName();
            surname = user.getEmployee().getSurname();
        } else {
            name = user.getCustomer().getName();
            surname = user.getCustomer().getSurname();
        }
        model.addObject("loggedUsername", user.getUsername());
        model.addObject("loggedName", name);
        model.addObject("loggedSurname", surname);

        List<SlaUserRole> roles = user.getUserRoles();
        for (SlaUserRole role : roles) {
            if (!Objects.equals(role.getRole().getRoleName(), "ROLE_CUSTOMER")) {
                model.addObject("role", "ROLE_ADMIN");
                return;
            }
        }

        model.addObject("role", "ROLE_CUSTOMER");
    }

}
