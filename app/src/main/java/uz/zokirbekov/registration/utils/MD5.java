package uz.zokirbekov.registration.utils;

import java.security.MessageDigest;

public class MD5 {
    public static String encrypt(String s)
    {
        final String MD5 = "MD5";
        try
        {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder b = new StringBuilder();
            for (byte dig : messageDigest)
            {
                String t = Integer.toHexString(0xFF & dig);
                while (t.length() < 2)
                    t = "0" + t;
                b.append(t);
            }
            return b.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
