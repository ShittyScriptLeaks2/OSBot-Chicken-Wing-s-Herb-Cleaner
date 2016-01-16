/*
 * Decompiled with CFR 0_110.
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class PriceAPI2 {
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
        //Object object;
        URL url = new URL(new StringBuilder().insert(0, "https://api.rsbuddy.com/grandExchange?a=guidePrice&i=").append(n).toString());
        BufferedReader object2 = new BufferedReader(new InputStreamReader(url.openStream()));
        String string3 = null;
        while ((string2 = object2.readLine()) != null) {
            if (!string2.contains("{")) continue;
            string3 = string2.trim();
        }
        if (string.equals("buying")) {
            String string4 = string3;
            string3 = string4.substring(string3.indexOf(",") + 10, a(string4, ',', 1)).trim();
        } else {
            String string5 = string3;
            if (string.equals("selling")) {
                string3 = string5.substring(a(string3, ',', 2) + 11, string3.indexOf("sellingQuantity") - 2).trim();
            } else {
                string3 = string5.substring(string3.indexOf(":") + 1, string3.indexOf(",")).trim();
            }
        }
        object2.close();
        return Integer.parseInt(string3);
    }

    public final int a(int n) {
        try {
            return a(n, "overall");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public final int b(int n) {
        try {
            return a(n, "buying");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public final int c(int n) {
        try {
            return a(n, "selling");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

