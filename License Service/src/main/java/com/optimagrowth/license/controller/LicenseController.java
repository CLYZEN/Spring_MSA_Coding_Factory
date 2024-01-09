package com.optimagrowth.license.controller;

import com.optimagrowth.license.entity.License;
import com.optimagrowth.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Locale;


@RestController
@RequiredArgsConstructor
@RequestMapping("v1/organization/{organizationId}/license")
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId
    ) {
        License license = licenseService.getLicense(licenseId,organizationId);
        license.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
                        .getLicense(organizationId,license.getLicenseId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
                        .createLicense(organizationId,license,null)).withRel("createLicense"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
                        .updateLicense(organizationId,license)).withRel("updateLicense"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
                        .deleteLicense(organizationId,license.getLicenseId())).withRel("deleteLicense")
        );

        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request
    ) {
        return ResponseEntity.ok(licenseService.updateLicense(request, organizationId));
    }

    @PostMapping
    public ResponseEntity<String> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request,
            @RequestHeader(value = "Accept-Language", required = false)Locale locale // MessageSource Bean 구현하며 추가
            ) {
        return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId
    ) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId,organizationId));
    }

}
