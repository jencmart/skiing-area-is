package cz.cvut.fit.si1.sla.validator;


import cz.cvut.fit.si1.sla.domain.SlaSkipass;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class SkipassFormValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return SlaSkipass.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        SlaSkipass skipass = (SlaSkipass) o;


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.slaSkipassForm.price");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberOfDays", "NotEmpty.slaSkipassForm.numberOfDays");

        if (skipass.getPrice() == null) {
            errors.rejectValue("price", "NotEmpty.slaSkipassForm.price");
        } else if (skipass.getPrice() <= 0) {
            errors.rejectValue("price", "NotPositive.slaSkipassForm.price");
        }

        if (skipass.getNumberOfDays() == null) {
            errors.rejectValue("numberOfDays", "NotEmpty.slaSkipassForm.numberOfDays");
        } else if (skipass.getNumberOfDays() > 365 || skipass.getNumberOfDays() < 1) {
            errors.rejectValue("numberOfDays", "NotInRange.slaSkipassForm.numberOfDays");
        }

    }
}
