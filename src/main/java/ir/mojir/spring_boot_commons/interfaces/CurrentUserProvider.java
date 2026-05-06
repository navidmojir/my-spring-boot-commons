package ir.mojir.spring_boot_commons.interfaces;

public interface CurrentUserProvider {
    String getCurrentUserId();

    String getCurrentAccessToken();
    boolean isPoweredUser(); //powered user has access to all records
}
