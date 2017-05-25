package pl.io4.model.cachable;

/**
 * Created by Zax37 on 20.05.2017.
 *
 * Najbardziej ogólny interfejs, mówiący o tym, że
 * zawartość klasy można cache'ować(zapisywać i odczytywać).
 */
public interface Cachable {
    Object cache();
    void load(Object data);
}
