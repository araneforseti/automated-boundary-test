package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.MessageConfiguration
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class DateField extends Field {
    String format

    DateField(String name, String format, boolean required, MessageConfiguration messageConfiguration = new MessageConfiguration(name, "Datetime")) {
        super(name, DateTime.now().toString(DateTimeFormat.forPattern(format)), required, messageConfiguration)
        this.format = format
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        if (isRequired) {
            scenarios.add(new BoundaryScenario("${name} as empty string", messageConfiguration.requiredMessage, ""))
        }

        scenarios.add(new BoundaryScenario("${name} as a number", messageConfiguration.validationMessage, 1))
        scenarios.add(new BoundaryScenario("${name} as a boolean true", messageConfiguration.validationMessage, true))
        scenarios.add(new BoundaryScenario("${name} as a boolean false", messageConfiguration.validationMessage, false))
        scenarios.add(new BoundaryScenario("${name} as an invalid Datetime", messageConfiguration.validationMessage, "asdf"))
        scenarios.add(new BoundaryScenario("${name} as a different Datetime format",
                messageConfiguration.validationMessage,
                DateTime.now().toString(DateTimeFormat.forPattern("HH:mm:SS.sssZ - DD-MM-YYYY"))))

        return scenarios
    }
}
