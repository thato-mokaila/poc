package za.co.eltengroup.service;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;
import za.co.eltengroup.domain.airtime.Credentials;
import za.co.eltengroup.domain.airtime.MTNVoucherTopUpRequest;
import za.co.eltengroup.domain.airtime.MTNVoucherTopUpResponse;
import za.co.eltengroup.interceptor.HttpMessageInterceptor;

import java.util.ArrayList;

@Component
public class RestTemplateService {

    public static final String URI = "http://demo5364857.mockable.io/1/EVD/VoucherTopUp";
    //public static final String URI = "http://196.11.241.46:8312/1/EVD/VoucherTopUp";

    public MTNVoucherTopUpResponse doVoucherTopUp(MTNVoucherTopUpRequest request) {

        RestTemplate template = new RestTemplate();
        template.setInterceptors(new ArrayStack<ClientHttpRequestInterceptor>(){{
            add(new HttpMessageInterceptor());
        }});
        template.setMessageConverters(new ArrayList<HttpMessageConverter<?>>(){{
            add(new Jaxb2RootElementHttpMessageConverter());
        }});

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml");
        headers.add("Accept", "application/xml");

        HttpEntity<MTNVoucherTopUpRequest> requestEntity = new HttpEntity<MTNVoucherTopUpRequest>(request, headers);

        System.out.println(requestEntity.getHeaders());

        ResponseEntity<MTNVoucherTopUpResponse> topUpResponse =
                template.exchange(
                        URI,
                        HttpMethod.POST,
                        requestEntity,
                        MTNVoucherTopUpResponse.class
                );

        MTNVoucherTopUpResponse _response = template.postForObject(URI, request, MTNVoucherTopUpResponse.class);

        System.out.println("***** Response: " + _response);

        return topUpResponse.getBody();

    }

}
