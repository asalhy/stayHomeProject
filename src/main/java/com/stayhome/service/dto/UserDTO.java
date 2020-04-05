package com.stayhome.service.dto;

import com.stayhome.config.Constants;

import com.stayhome.domain.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    @NotBlank
    private String firstName;

    @Size(max = 50)
    @NotBlank
    private String lastName;

    // FIXME - Validate length & pattern
    @NotBlank
    private String cin;

    // FIXME - Validate length & patterns
    @NotBlank
    private String phone;

    @NotBlank
    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = false;

    @Size(min = 2, max = 10)
    private String langKey;

    private String groupName;
    private String membershipId;
    private String createdBy;
    private Instant createdDate;
    private String lastModifiedBy;
    private Instant lastModifiedDate;
    private Set<String> authorities;

    @NotNull
    @Valid
    private OrganizationDTO organization;

    // FIXME - List or Set
    private Set<ServiceTypeDTO> serviceTypes;
    private Set<LocalityDTO> localities;

    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.activated = user.getActivated();
        this.imageUrl = user.getImageUrl();
        this.langKey = user.getLangKey();
        this.createdBy = user.getCreatedBy();
        this.createdDate = user.getCreatedDate();
        this.lastModifiedBy = user.getLastModifiedBy();
        this.lastModifiedDate = user.getLastModifiedDate();
        this.phone = user.getPhone();
        this.cin = user.getCin();
        this.membershipId = user.getMembershipId();
        this.groupName = user.getGroupName();

        /*
        this.organization = this.map(user.getOrganization());
        this.localities = (user.getLocalities() != null)
            ? user.getLocalities().stream().map(this::map).collect(Collectors.toSet())
            : Collections.emptySet();
        this.serviceTypes = (user.getServiceTypes() != null)
            ? user.getServiceTypes().stream().map(this::map).collect(Collectors.toSet())
            : Collections.emptySet();
        */

        this.authorities = user.getAuthorities().stream()
            .map(Authority::getName)
            .collect(Collectors.toSet());
    }

    // FIXME - To remove (Use mapstruct)
    private LocalityDTO map(Locality locality) {
        if (locality != null) {
            LocalityDTO result = new LocalityDTO();
            result.setId(locality.getId());
            result.setName(locality.getName());
            result.setPostalCode(locality.getPostalCode());
            result.setDelegationId((locality.getDelegation() != null) ? locality.getDelegation().getId() : null);
            return result;
        }

        return null;
    }

    // FIXME - To remove (Use mapstruct)
    private ServiceTypeDTO map(ServiceType serviceType) {
        if (serviceType != null) {
            ServiceTypeDTO result = new ServiceTypeDTO();
            result.setId(serviceType.getId());
            result.setName(serviceType.getName());
            return result;
        }

        return null;
    }

    // FIXME - To remove (Use mapstruct)
    private OrganizationDTO map(Organization organization) {
        if (organization != null) {
            OrganizationDTO result = new OrganizationDTO();
            result.setId(organization.getId());
            result.setName(organization.getName());
            return result;
        }

        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    public Set<LocalityDTO> getLocalities() {
        return localities;
    }

    public void setLocalities(Set<LocalityDTO> localities) {
        this.localities = localities;
    }

    public Set<ServiceTypeDTO> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(Set<ServiceTypeDTO> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }
}
