package za.co.eltengroup.domain.airtime;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class TopUpInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @XmlElement(namespace="mtn.evd")
    private String transactionReference;

    @XmlElement(namespace="mtn.evd")
    private String customerID;

    @XmlElement(namespace="mtn.evd")
    private String topUpAmount;

    @XmlElement(namespace="mtn.evd")
    private Long merchantBalance;

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getTopUpAmount() {
        return topUpAmount;
    }

    public void setTopUpAmount(String topUpAmount) {
        this.topUpAmount = topUpAmount;
    }

    public Long getMerchantBalance() {
        return merchantBalance;
    }

    public void setMerchantBalance(Long merchantBalance) {
        this.merchantBalance = merchantBalance;
    }





}
