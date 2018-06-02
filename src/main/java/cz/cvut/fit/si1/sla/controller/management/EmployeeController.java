package cz.cvut.fit.si1.sla.controller.management;


import cz.cvut.fit.si1.sla.domain.SlaEmployee;
import cz.cvut.fit.si1.sla.domain.SlaJob;
import cz.cvut.fit.si1.sla.dto.EmployeeUserRegisterDto;
import cz.cvut.fit.si1.sla.service.SlaEmployeeService;
import cz.cvut.fit.si1.sla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing employees
 */
@Controller
@RequestMapping("/management/employee")
public class EmployeeController {

    private static final String flashMsgNotFound = "Zamestnanec nenalezen!";

    private static final String viewList = "jsp/management/employee/list";
    private static final String viewShow = "jsp/management/employee/show";


    private static final String allObjectsAttribute = "slaEmployees";
    private static final String oneObjectAttribute = "slaEmployee";

    private static final String contextRedirect = "/management/employee/";

    @Autowired
    SlaEmployeeService employeeService;

    @Autowired
    UserService userService;

    /**
     * List all employees
     *
     * @param model model
     * @return view
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllEmployees(Model model) {
        model.addAttribute(allObjectsAttribute, employeeService.getAllList());

        EmployeeUserRegisterDto customerUserDto = new EmployeeUserRegisterDto();
        model.addAttribute("employeeForm", customerUserDto);

        Map<Integer, String> jobs = new LinkedHashMap<>();
        List<SlaJob> jobList = employeeService.getAllJobs();

        for (SlaJob job : jobList) {
            jobs.put(job.getIdJob().intValue(), job.getTitle());
        }

        model.addAttribute("jobList", jobs);


        return viewList;
    }

    /**
     * Show certain employee
     *
     * @param id    id of employee
     * @param model model
     * @return view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showEmployee(@PathVariable("id") int id, Model model) {

        SlaEmployee slaEmployee = employeeService.findById(id);
        if (slaEmployee == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", flashMsgNotFound);
        }
        model.addAttribute(oneObjectAttribute, slaEmployee);
        return viewShow;
    }

    /**
     * Save employee
     *
     * @param employeeUserRegisterDto dto of employee filled from form
     * @param model                   model
     * @param redirectAttributes      redirect
     * @return redirect to new employee
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employeeForm") EmployeeUserRegisterDto employeeUserRegisterDto,
                               Model model,
                               final RedirectAttributes redirectAttributes) {
        // todo verification unique username !!!

        SlaEmployee employee = userService.registerNewEmployee(employeeUserRegisterDto);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Zamestnanec uspesne vytvoren!");

        return "redirect:/management/employee/" + employee.getIdEmployee();
    }
}