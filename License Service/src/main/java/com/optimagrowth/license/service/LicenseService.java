package com.optimagrowth.license.service;

import com.optimagrowth.license.entity.License;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Locale;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messages;

    public License getLicense(String licenseId, String organizationId) {
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("0stock");
        license.setLicenseType("full");
        return license;
    }

    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        /*
        *  MessageSource Bean 구현하며 주석
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format("This is the post and the object is: %s", license.toString());
        }
        * */
       if (!StringUtils.isEmpty(license)) {
           license.setOrganizationId(organizationId);
           responseMessage = String.format(messages.getMessage(
                   "license.create.message", null, locale), // 특정 메시지를 조회하기 위해 전달된 로케일 전달
                   license.toString());
       }


        return responseMessage;
    }

    public String updateLicense(License license, String organizationId) {
        String responseMessage = null;

        /*
        * MessageSource Bean 구현하며 주석
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format("This is the put and the object is: %s", license.toString());
        }
        * */

        if (StringUtils.isEmpty(license)) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messages.getMessage(
                    "license.update.message", null, null), // 특정 메시지를 조회하기 위해 NULL 로케일 전달
                    license.toString());
        }

        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId) {
        String responseMessage = null;
        responseMessage = String.format("Deleting license with id %s for the organization %s", licenseId, organizationId);

        return responseMessage;
    }

}
