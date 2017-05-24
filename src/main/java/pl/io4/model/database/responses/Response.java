package pl.io4.model.database.responses;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Klasa opisująca odpowiedź z bazy danych.
 * Powinna zostać rozszerzona na konkretne rodzaje odpowiedzi,
 * jakich aplikacja może oczekiwać po danych zapytaniach.
 */
public class Response {
    private final boolean status;

    public Response(boolean status) {
        this.status = status;
    }

    public final boolean wasSuccessful() {
        return status;
    }
}
