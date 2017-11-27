package com.xutrade.platform.model;

public class AuthenticationRequest {
    private String message;
    private String address;
    private String hexV;
    private String hexR;
    private String hexS;

    public String getAddress() {
        return address;
    }
    public String getMessage() { return message; }
    public String getHexV() { return hexV; }
    public String getHexR() { return hexR; }
    public String getHexS() { return hexS; }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setMessage(String message) { this.message = message; }
    public void setHexV(String hexV) { this.hexV = hexV; }
    public void setHexR(String hexR) { this.hexR = hexR; }
    public void setHexS(String hexS) { this.hexS = hexS; }
}
