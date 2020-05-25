/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author alberto
 */
public class InformacionAndroid implements Serializable {
    private String phoneNumber;
    private String IMEI;
    private String softwareVersion;
    private String countryCode;
    private String operatorCode;
    private String operatorName;
    private String simOperatorCode;
    private String simOperatorName;
    private String simCountryCode;
    private String simSerial;
    private boolean wifiAvailable;
    private boolean wifiConnectedOrConnecting;
    private String wifiExtraInfos;
    private String wifiReason;
    private String mobileNetworkName;
    private boolean mobileNetworkAvailable;
    private boolean mobileNetworkConnectedOrConnecting;
    private String mobileNetworkExtraInfos;
    private String mobileNetworkReason;
    private String androidVersion;
    private int androidSdk;
    private ArrayList<String> sensors;
    private int batteryHealth;
    private int batteryLevel;
    private int batteryPlugged;
    private boolean batteryPresent;
    private int batteryScale;
    private int batteryStatus;
    private String batteryTechnology;
    private int batteryTemperature;
    private int batteryVoltage;
    public InformacionAndroid() {
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getIMEI() {
        return IMEI;
    }
    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }
    public String getSoftwareVersion() {
        return softwareVersion;
    }
    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getOperatorCode() {
        return operatorCode;
    }
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }
    public String getOperatorName() {
        return operatorName;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    public String getSimOperatorCode() {
        return simOperatorCode;
    }
    public void setSimOperatorCode(String simOperatorCode) {
        this.simOperatorCode = simOperatorCode;
    }
    public String getSimOperatorName() {
        return simOperatorName;
    }
    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }
    public String getSimCountryCode() {
        return simCountryCode;
    }
    public void setSimCountryCode(String simCountryCode) {
        this.simCountryCode = simCountryCode;
    }
    public String getSimSerial() {
        return simSerial;
    }
    public void setSimSerial(String simSerial) {
        this.simSerial = simSerial;
    }
    public boolean isWifiAvailable() {
        return wifiAvailable;
    }
    public void setWifiAvailable(boolean wifiAvailable) {
        this.wifiAvailable = wifiAvailable;
    }
    public boolean isWifiConnectedOrConnecting() {
        return wifiConnectedOrConnecting;
    }
    public void setWifiConnectedOrConnecting(boolean wifiConnectedOrConnecting) {
        this.wifiConnectedOrConnecting = wifiConnectedOrConnecting;
    }
    public String getWifiExtraInfos() {
        return wifiExtraInfos;
    }
    public void setWifiExtraInfos(String wifiExtraInfos) {
        this.wifiExtraInfos = wifiExtraInfos;
    }
    public String getWifiReason() {
        return wifiReason;
    }
    public void setWifiReason(String wifiReason) {
        this.wifiReason = wifiReason;
    }
    public String getMobileNetworkName() {
        return mobileNetworkName;
    }
    public void setMobileNetworkName(String mobileNetworkName) {
        this.mobileNetworkName = mobileNetworkName;
    }
    public boolean isMobileNetworkAvailable() {
        return mobileNetworkAvailable;
    }
    public void setMobileNetworkAvailable(boolean mobileNetworkAvailable) {
        this.mobileNetworkAvailable = mobileNetworkAvailable;
    }
    public boolean isMobileNetworkConnectedOrConnecting() {
        return mobileNetworkConnectedOrConnecting;
    }
    public void setMobileNetworkConnectedOrConnecting(boolean mobileNetworkConnectedOrConnecting) {
        this.mobileNetworkConnectedOrConnecting = mobileNetworkConnectedOrConnecting;
    }
    public String getMobileNetworkExtraInfos() {
        return mobileNetworkExtraInfos;
    }
    public void setMobileNetworkExtraInfos(String mobileNetworkExtraInfos) {
        this.mobileNetworkExtraInfos = mobileNetworkExtraInfos;
    }
    public String getMobileNetworkReason() {
        return mobileNetworkReason;
    }
    public void setMobileNetworkReason(String mobileNetworkReason) {
        this.mobileNetworkReason = mobileNetworkReason;
    }
    public String getAndroidVersion() {
        return androidVersion;
    }
    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }
    public int getAndroidSdk() {
        return androidSdk;
    }
    public void setAndroidSdk(int androidSdk) {
        this.androidSdk = androidSdk;
    }
    public ArrayList<String> getSensors() {
        return sensors;
    }
    public void setSensors(ArrayList<String> sensors) {
        this.sensors = sensors;
    }
    public int getBatteryHealth() {
        return batteryHealth;
    }
    public void setBatteryHealth(int batteryHealth) {
        this.batteryHealth = batteryHealth;
    }
    public int getBatteryLevel() {
        return batteryLevel;
    }
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
    public int getBatteryPlugged() {
        return batteryPlugged;
    }
    public void setBatteryPlugged(int batteryPlugged) {
        this.batteryPlugged = batteryPlugged;
    }
    public boolean getBatteryPresent() {
        return batteryPresent;
    }
    public void setBatteryPresent(boolean batteryPresent) {
        this.batteryPresent = batteryPresent;
    }
    public int getBatteryScale() {
        return batteryScale;
    }
    public void setBatteryScale(int batteryScale) {
        this.batteryScale = batteryScale;
    }
    public int getBatteryStatus() {
        return batteryStatus;
    }
    public void setBatteryStatus(int batteryStatus) {
        this.batteryStatus = batteryStatus;
    }
    public String getBatteryTechnology() {
        return batteryTechnology;
    }
    public void setBatteryTechnology(String batteryTechnology) {
        this.batteryTechnology = batteryTechnology;
    }
    public int getBatteryTemperature() {
        return batteryTemperature;
    }
    public void setBatteryTemperature(int batteryTemperature) {
        this.batteryTemperature = batteryTemperature;
    }
    public int getBatteryVoltage() {
        return batteryVoltage;
    }
    public void setBatteryVoltage(int batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }
    @Override
    public String toString() {
        return "Informacion{" + "phoneNumer=" + phoneNumber + ", IMEI=" + IMEI + ", softwareVersion=" + softwareVersion + ", countryCode=" + countryCode + '}';
    }
}
