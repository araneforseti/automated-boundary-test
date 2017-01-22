package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.MessageConfiguration

class EnumField extends Field {
    List<String> acceptedValues

    EnumField(String name, String correctValue, boolean required, List acceptedValues, String messageName=null, MessageConfiguration messageConfiguration=null) {
        super(name, correctValue, required, messageName, messageConfiguration)
        init(messageName, acceptedValues)
    }

    EnumField(String name, List<String> acceptedValues, boolean required, String messageName=null, MessageConfiguration messageConfiguration=null) {
        super(name, acceptedValues.first(), required, messageName, messageConfiguration)
        init(messageName, acceptedValues)
    }

    private void init(String messageName, List acceptedValues) {
        this.messageConfiguration.validationMessage = "$messageName must be one of $acceptedValues"
        this.acceptedValues = acceptedValues
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        acceptedValues.each { value ->
            String numValue = value + "12"
            scenarios.add(enumScenario(numValue))

            String typoValue = value.substring(0, value.length()-1)
            scenarios.add(enumScenario(typoValue))
        }

        scenarios.add(enumScenario("''"))
        scenarios.add(enumScenario("null"))
        scenarios.add(enumScenario(1))

        return scenarios
    }

    @Override
    String fieldType() {
        return "String"
    }

    BoundaryScenario enumScenario(value) {
        new BoundaryScenario("$name as $value", messageConfiguration.validationMessage, value)
    }
}
