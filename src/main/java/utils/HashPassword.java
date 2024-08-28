package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

    private HashPassword(){
        throw new UnsupportedOperationException("This class cannot be instantiate");
    }

    public static String encodedPassword(String password){
        try {

            // Crear un objeto MessageDigest con el algoritmo MD5
            final MessageDigest md = MessageDigest.getInstance("MD5");

            // Obtener los bytes del password
            final byte[] passwordBytes = password.getBytes();

            // Calcular el hash MD5
            final byte[] digest = md.digest(passwordBytes);

            // Convertir el hash en una cadena hexadecimal
            final StringBuilder hexString = new StringBuilder();
            for (final byte b : digest)
                hexString.append(String.format("%02x", b));
            return hexString.toString();

        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}