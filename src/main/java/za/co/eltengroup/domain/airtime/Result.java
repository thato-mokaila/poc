package za.co.eltengroup.domain.airtime;


import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name="resultCode",namespace="mtn.evd")
    private String resultCode;

    @XmlElement(name="resultDescription",namespace="mtn.evd")
    private String resultDescription;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }


}
