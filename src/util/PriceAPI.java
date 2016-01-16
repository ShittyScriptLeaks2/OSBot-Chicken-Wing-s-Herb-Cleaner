package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class PriceAPI {

    private static final /* synthetic */ String a = "https://api.rsbuddy.com/grandExchange?a=guidePrice&i=";

    private static /* synthetic */ int a(String string, char c, int n) {
        /*c = (char)string.indexOf(44, 0);
        int n2 = n;
        while (n2 > 0) {
            if (c == '\uffffffff') return c;
            c = (char)string.indexOf(44, c + '\u0001');
            n2 = --n;
        }
        return c;*/
        return 0;
    }

    private /* synthetic */ int a(int n, String string) throws IOException {
        String string2;
        URL url = null;
        BufferedReader rdr = null;

        try {
            url = new URL(new StringBuilder().insert(0, "https://api.rsbuddy.com/grandExchange?a=guidePrice&i=").append(n).toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            rdr = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String string3 = null;
        while ((string2 = rdr.readLine()) != null) {
            if (!string2.contains("{")) continue;
            string3 = string2.trim();
        }
        if (string.equals("buying")) {
            String string4 = string3;
            string3 = string4.substring(string3.indexOf(",") + 10, PriceAPI.a(string4, ',', 1)).trim();
        } else {
            String string5 = string3;
            if (string.equals("selling")) {
                string3 = string5.substring(PriceAPI.a(string3, ',', 2) + 11, string3.indexOf("sellingQuantity") - 2).trim();
            } else {
                string3 = string5.substring(string3.indexOf(":") + 1, string3.indexOf(",")).trim();
            }
        }

        try {
            rdr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(string3);
    }

    public int a(int n) {
        try {
            return this.a(n, "overall");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int b(int n) {
        try {
            return this.a(n, "buying");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int c(int n) {
        try {
            return this.a(n, "selling");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

