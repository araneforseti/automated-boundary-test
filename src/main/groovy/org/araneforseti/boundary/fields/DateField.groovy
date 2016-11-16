package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class DateField extends Field {
    String format

    DateField(String name, String format, boolean required) {
        super(name, DateTime.now().toString(DateTimeFormat.forPattern(format)), required)
        this.format = format
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        if (isRequired) {
            scenarios.add(new BoundaryScenario("${name} as empty string", "${name} is a required field", ""))
        }

        scenarios.add(new BoundaryScenario("${name} as a number", "${name} must be a valid Datetime", 1))
        scenarios.add(new BoundaryScenario("${name} as a boolean true", "${name} must be a valid Datetime", true))
        scenarios.add(new BoundaryScenario("${name} as a boolean false", "${name} must be a valid Datetime", false))
        scenarios.add(new BoundaryScenario("${name} as an invalid Datetime", "${name} must be a valid Datetime", "asdf"))
        scenarios.add(new BoundaryScenario("${name} as a different Datetime format",
                "${name} must be a valid Datetime",
                DateTime.now().toString(DateTimeFormat.forPattern("HH:mm:SS.sssZ - DD-MM-YYYY"))))

        return scenarios
    }
}
