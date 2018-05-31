package cz.cvut.fit.si1.sla.validator;


import cz.cvut.fit.si1.sla.dto.CustomerOrderDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerOrderFormValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerOrderDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomerOrderDto customerOrderDto = (CustomerOrderDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerOrderDto.name");

        if (customerOrderDto.getName() == null) {
            errors.rejectValue("name", "NotEmpty.customerOrderDto.name");
        }

        // todo
    }
}
