package za.co.eltengroup.domain.airtime;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name="voucherTopUpResponse",namespace="mtn.evd")
@XmlAccessorType(XmlAccessType.FIELD)
public class MTNVoucherTopUpResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name="result",namespace="mtn.evd")
    private Result result;

    @XmlElement(name="topUpInfo",namespace="mtn.evd")
    private TopUpInfo topUpInfo;

    private String xmlns;


    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public TopUpInfo getTopUpInfo() {
        return topUpInfo;
    }

    public void setTopUpInfo(TopUpInfo topUpInfo) {
        this.topUpInfo = topUpInfo;
    }


}