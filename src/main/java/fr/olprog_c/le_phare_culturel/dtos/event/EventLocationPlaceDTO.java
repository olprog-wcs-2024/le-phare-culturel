package fr.olprog_c.le_phare_culturel.dtos.event;
/* ****** ****** ****** ****** */
// EventLocationPlaceDTO.java
/* ****** ****** ****** ****** */
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventLocationPlaceDTO {
    private EventLocationCoordinatesDTO eventLocationCoordinatesDTO;
    private String locationUid;
    private String name;
    private String address;
    private String postalCode;
    private String city;
    private String department;
    private String region;
    private String email;
    private String phone;
    private Object district;
    private Object inseeCode;

    @JsonProperty("location_coordinates")
    public EventLocationCoordinatesDTO getLocationCoordinates() {
        return eventLocationCoordinatesDTO;
    }

    @JsonProperty("location_coordinates")
    public void setLocationCoordinates(EventLocationCoordinatesDTO value) {
        this.eventLocationCoordinatesDTO = value;
    }

    @JsonProperty("location_uid")
    public String getLocationUid() {
        return locationUid;
    }

    @JsonProperty("location_uid")
    public void setLocationUid(String value) {
        this.locationUid = value;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.name = value;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String value) {
        this.address = value;
    }

    @JsonProperty("postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postal_code")
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String value) {
        this.city = value;
    }

    @JsonProperty("department")
    public String getDepartment() {
        return department;
    }

    @JsonProperty("department")
    public void setDepartment(String value) {
        this.department = value;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(String value) {
        this.region = value;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String value) {
        this.email = value;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String value) {
        this.phone = value;
    }

    @JsonProperty("district")
    public Object getDistrict() {
        return district;
    }

    @JsonProperty("district")
    public void setDistrict(Object value) {
        this.district = value;
    }

    @JsonProperty("insee_code")
    public Object getInseeCode() {
        return inseeCode;
    }

    @JsonProperty("insee_code")
    public void setInseeCode(Object value) {
        this.inseeCode = value;
    }
}
