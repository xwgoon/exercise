import React from "react";
import { 
    DateTimePicker, 
    DatePicker,
    TimePicker,
    MuiPickersUtilsProvider,
} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import { zhCN } from "date-fns/locale";
import { useInput } from "react-admin";

const generateRaMuiDateInput = (DateInputComponent) => {
    const RaMuiDateInput = ({
        utils, locale, ...props
    }) => {
        const {
            input,
            meta: { touched, error },
            isRequired,
        } = useInput(props);
        const {
            label,
            className,
            options,
        } = props;

        return (
            <MuiPickersUtilsProvider
                utils={utils || DateFnsUtils} locale={locale || zhCN}
            >
                <DateInputComponent
                    label={label}
                    error={!!(touched && error)}
                    errorText={touched && error}
                    className={className}
                    {...options} 
                    {...input}
                    required={isRequired}
                    value={input.value || null}
                    margin="normal"
                />
            </MuiPickersUtilsProvider>
        );
    };
    return RaMuiDateInput;
};

export const DateTimeInput = generateRaMuiDateInput(DateTimePicker);
export const DateInput = generateRaMuiDateInput(DatePicker);
export const TimeInput = generateRaMuiDateInput(TimePicker);
