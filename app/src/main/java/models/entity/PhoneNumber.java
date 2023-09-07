package models.entity;

public class PhoneNumber
{
    private String countryCode;
    private int cityCode;
    private Long number;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public PhoneNumber(String countryCode, int cityCode, Long number) {
        this.countryCode = countryCode;
        this.cityCode = cityCode;
        this.number = number;
    }

    public PhoneNumber() {
    }
}
