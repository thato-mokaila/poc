@XmlSchema(
        namespace = "mtn.evd",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix="ns1", namespaceURI="mtn.evd")
        }
)
package za.co.eltengroup.domain.airtime;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;