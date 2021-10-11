package Translate;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GoogleTTS {
    public static String GOOGLE_TRANSLATE_URL = "https://translate.googleapis.com/translate_a/single";
    public static String GOOGLE_AUDIO_URL = "http://translate.google.com/translate_tts";
    public static String GOOGLE_SEARCH_URL = "https://clients1.google.com/complete/search";
    public static void speak(String text) throws IOException {
        speak("en", text);
    }

    public static void speak(String language, String text) throws IOException {
        String url = generateSpeakURL(language, text);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream audioSrc = con.getInputStream();
            try {
                new Player(new BufferedInputStream(audioSrc)).play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }
    private static String generateSpeakURL(String language, String text) throws UnsupportedEncodingException {
        String url = GOOGLE_AUDIO_URL + "?ie=UTF-8" +
                "&q=" + URLEncoder.encode(text, "UTF-8") +
                "&tl=" + language +
                "&tk=" + generateToken(text) +
                "&client=t";
        return url;
    }
    private static int[] TKK() {
        return new int[]{0x6337E, 0x217A58DC + 0x5AF91132};
    }

    private static int shr32(int x, int bits) {
        if (x < 0) {
            long x_l = 0xffffffffl + x + 1;
            return (int) (x_l >> bits);
        }
        return x >> bits;
    }

    private static int RL(int a, String b) {//I am not entirely sure what this magic does.
        for (int c = 0; c < b.length() - 2; c += 3) {
            int d = b.charAt(c + 2);
            d = d >= 65 ? d - 87 : d - 48;
            d = b.charAt(c + 1) == '+' ? shr32(a, d) : (a << d);
            a = b.charAt(c) == '+' ? (a + (d & 0xFFFFFFFF)) : a ^ d;
        }
        return a;
    }

    private static String generateToken(String text) {
        int tkk[] = TKK();
        int b = tkk[0];
        int e = 0;
        int f = 0;
        List<Integer> d = new ArrayList<Integer>();
        for (; f < text.length(); f++) {
            int g = text.charAt(f);
            if (0x80 > g) {
                d.add(e++, g);
            } else {
                if (0x800 > g) {
                    d.add(e++, g >> 6 | 0xC0);
                } else {
                    if (0xD800 == (g & 0xFC00) && f + 1 < text.length() && 0xDC00 == (text.charAt(f + 1) & 0xFC00)) {
                        g = 0x10000 + ((g & 0x3FF) << 10) + (text.charAt(++f) & 0x3FF);
                        d.add(e++, g >> 18 | 0xF0);
                        d.add(e++, g >> 12 & 0x3F | 0x80);
                    } else {
                        d.add(e++, g >> 12 | 0xE0);
                        d.add(e++, g >> 6 & 0x3F | 0x80);
                    }
                }
                d.add(e++, g & 63 | 128);
            }
        }

        int a_i = b;
        for (e = 0; e < d.size(); e++) {
            a_i += d.get(e);
            a_i = RL(a_i, "+-a^+6");
        }
        a_i = RL(a_i, "+-3^+b+-f");
        a_i ^= tkk[1];
        long a_l;
        if (0 > a_i) {
            a_l = 0x80000000l + (a_i & 0x7FFFFFFF);
        } else {
            a_l = a_i;
        }
        a_l %= Math.pow(10, 6);
        return String.format(Locale.US, "%d.%d", a_l, a_l ^ b);
    }

    public static void main(String[] args) throws IOException {
        speak("chó ăn chó");
    }
}
