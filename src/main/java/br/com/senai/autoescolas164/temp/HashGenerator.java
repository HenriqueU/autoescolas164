package br.com.senai.autoescolas164.temp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenerator { //Serve para criptografar a senha do usuario

    static void main() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("senai");

        System.out.println(hash);
    }
}
