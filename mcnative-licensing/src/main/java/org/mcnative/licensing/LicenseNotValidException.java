package org.mcnative.licensing;

public class LicenseNotValidException extends RuntimeException{

    public LicenseNotValidException() {
        super("Resource license is not valid");
    }

    public LicenseNotValidException(Exception exception) {
        super("Resource license is not valid",exception);
    }
}
