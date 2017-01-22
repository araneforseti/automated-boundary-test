package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class DateField extends Field {
    String format
    String errorMessage

    DateField(String name, String format, boolean required, String messageName=null) {
        super(name, DateTime.now().toString(DateTimeFormat.forPattern(format)), required, messageName)
        this.format = format
        this.errorMessage = "${this.messageName} must be a valid Datetime"
        print(errorMessage)
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        if (isRequired) {
            scenarios.add(new BoundaryScenario("${name} as empty string", "${messageName} is a required field", ""))
        }

        scenarios.add(new BoundaryScenario("${name} as a number", errorMessage, 1))
        scenarios.add(new BoundaryScenario("${name} as a boolean true", errorMessage, true))
        scenarios.add(new BoundaryScenario("${name} as a boolean false", errorMessage, false))
        scenarios.add(new BoundaryScenario("${name} as an invalid Datetime", errorMessage, "asdf"))
        scenarios.add(new BoundaryScenario("${name} as a different Datetime format",
                errorMessage,
                DateTime.now().toString(DateTimeFormat.forPattern("HH:mm:SS.sssZ - DD-MM-YYYY"))))

        return scenarios
    }
}
