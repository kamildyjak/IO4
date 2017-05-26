package pl.io4.model.labels;

/**
 * Created by Marcin on 26.05.2017.
 */
public class ExceptionsLabels {
    public static final String PRODUCT_NOT_FOUND;
    public static final String DISCOUNT_NOT_FOUND;
    public static final String DISCOUNT_OVERFLOW;
    public static final String CODE_READ_OUT_ERROR;

    static {
        PRODUCT_NOT_FOUND = "Nie znaleniono produktu";
        DISCOUNT_NOT_FOUND = "Nadmiar zniżek";
        DISCOUNT_OVERFLOW = "Nadmiar zniżek";
        CODE_READ_OUT_ERROR = "Błąd odczytu kodu";
    }
}
