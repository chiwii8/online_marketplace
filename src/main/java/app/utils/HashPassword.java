package app.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

    private static final int STRONG_SELECTED = 16;

    @Setter
    @Getter
    private static PasswordEncoder encoder = new BCryptPasswordEncoder(STRONG_SELECTED);

    private HashPassword(){
        throw new UnsupportedOperationException("This class cannot be instantiate");
    }


    public static String encoded(String password){
        try {

            ///Create the PasswordEncoder encoder
            PasswordEncoder encoder = getEncoder();

            ///encode the password
            return encoder.encode(password);

        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean matches(String rawPassword, String encodedPassword){
        PasswordEncoder encoder = getEncoder();

        return encoder.matches(rawPassword,encodedPassword);
    }
}