package io.github.ooknight.universe.support.utils.component;

import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

import java.util.Locale;

public final class MobileUtils {

    private static final int CONTRY_CODE_CHINA = 86;
    private static final PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();

    public String region(String mobile) {
        try {
            Phonenumber.PhoneNumber pn = new Phonenumber.PhoneNumber();
            pn.setCountryCode(CONTRY_CODE_CHINA);
            pn.setNationalNumber(Long.parseLong(mobile));
            return geocoder.getDescriptionForNumber(pn, Locale.CHINA);
        } catch (NumberFormatException e) {
            return StringUtils.EMPTY;
        }
    }
}
