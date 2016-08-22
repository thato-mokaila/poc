package za.co.eltengroup.domain.airtime;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "voucherTopUp", namespace = "mtn.evd")
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "MTNVoucherTopUpRequest", propOrder =
        { "transactionId", "endUserIdentifier", "agentCode", "amount", "clientTerminalId", "authKey"}, namespace = "mtn.evd")
public class MTNVoucherTopUpRequest {

    @XmlElement
    private String transactionId;
    @XmlElement
    private String endUserIdentifier;
    @XmlElement
    private String agentCode;
    @XmlElement
    private BigDecimal amount;
    @XmlElement
    private String clientTerminalId;
    @XmlElement
    private String authKey;

    //private ExtensionInfo extensionInfo;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getEndUserIdentifier() {
        return endUserIdentifier;
    }

    public void setEndUserIdentifier(String endUserIdentifier) {
        this.endUserIdentifier = endUserIdentifier;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getClientTerminalId() {
        return clientTerminalId;
    }

    public void setClientTerminalId(String clientTerminalId) {
        this.clientTerminalId = clientTerminalId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

//    public ExtensionInfo getExtensionInfo() {
//        return extensionInfo;
//    }
//
//    public void setExtensionInfo(ExtensionInfo extensionInfo) {
//        this.extensionInfo = extensionInfo;
//    }

    @Override
    public String toString() {
        return "MTNVoucherTopUpRequest{" +
                "transactionId='" + transactionId + '\'' +
                ", endUserIdentifier='" + endUserIdentifier + '\'' +
                ", agentCode='" + agentCode + '\'' +
                ", amount=" + amount +
                ", clientTerminalId='" + clientTerminalId + '\'' +
                ", authKey='" + authKey + '\'' +
                '}';
    }
}


