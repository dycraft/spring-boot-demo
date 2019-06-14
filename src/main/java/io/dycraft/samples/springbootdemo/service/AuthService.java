package io.dycraft.samples.springbootdemo.service;

/**
 * @author Dayang Li on 14/06/2019
 */
public interface AuthService {

    String login(String username, String password);

    String register(String username, String password);
}
