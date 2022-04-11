package com.gy.formatter;

import com.gy.model.User;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class UserFormatter implements Formatter<User> {
    @Override
    public User parse(String text, Locale locale) throws ParseException {
        if(null == text) {
            throw new IllegalArgumentException("IAE");
        }
        // 男性：M, T; 女性：F
        // zhangshan-123-123@test.com-12345-T
        String[] parts = text.split("-");
        if(parts.length == 0) {
            throw new IllegalArgumentException("格式不对");
        }
        User user = new User();
        user.setUsername(parts[0]);
        if(parts.length > 1) {
            user.setPwdHash(parts[1]);
        }
        if(parts.length > 2) {
            user.setEmail(parts[2]);
        }
        if(parts.length > 3) {
            user.setPhone(parts[3]);
        }
        if(parts.length > 4) {
            if(parts[4].equalsIgnoreCase("M") ||
                    parts[4].equalsIgnoreCase("T")) {
                user.setGender(true);
            } else if(parts[4].equalsIgnoreCase("F")) {
                user.setGender(false);
            } else {
                user.setGender(true);
            }
        }
        return user;
    }

    @Override
    public String print(User user, Locale locale) {
        return String.format("%s-%s-%s-%s-%s",
                user.getUsername(),
                user.getPwdHash(),
                user.getEmail(),
                user.getPhone(),
                user.isGender() ? "M" : "F");
    }
}
