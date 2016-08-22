package za.co.eltengroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import za.co.eltengroup.domain.airtime.MTNVoucherTopUpRequest;
import za.co.eltengroup.domain.airtime.MTNVoucherTopUpResponse;
import za.co.eltengroup.domain.airtime.Result;
import za.co.eltengroup.domain.airtime.TopUpInfo;
import za.co.eltengroup.service.RestTemplateService;

import java.math.BigDecimal;
import java.util.Random;

@SpringBootApplication
@Controller
@RequestMapping(value = "1/EVD")
public class EvdRestApplication {

    Logger logger = LoggerFactory.getLogger(EvdRestApplication.class);

    @Autowired
    private RestTemplateService service;

	public static void main(String[] args) {
		SpringApplication.run(EvdRestApplication.class, args);
	}

	@RequestMapping(
            method = RequestMethod.POST,
            path = "VoucherTopUp",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody MTNVoucherTopUpResponse voucherTopTup(
            @RequestBody MTNVoucherTopUpRequest request) {

        request.setTransactionId(String.valueOf(new Random().nextInt(219999999)));
        logger.info(request.toString());

        try {
            return service.doVoucherTopUp(request);
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
        }
        return null;
	}
}
