package com.example.workshop.module1;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Processes bookstore orders and returns.
 */
public class OrderManager {

    public static double TAX = 0.17;
    public static int cnt = 0;
    public static List<String> log = new ArrayList<>();

    public String proc(String c, String[] n, int[] q, double[] p, String t, String pr, boolean x) {
        String res = "";
        try {
            if (c == null || c.length() == 0) {
                return "ERR";
            }
            double s = 0;
            for (int i = 0; i < n.length; i++) {
                s = s + q[i] * p[i];
            }
            double d = 0;
            if (t.equals("R")) {
                if (s > 100) {
                    d = s * 0.05;
                }
                if (s > 500) {
                    d = s * 0.1;
                }
            } else if (t.equals("V")) {
                if (s > 100) {
                    d = s * 0.1;
                }
                if (s > 500) {
                    d = s * 0.2;
                }
            } else if (t.equals("E")) {
                d = s * 0.3;
            }
            if (pr != null) {
                if (pr.equals("SAVE10")) {
                    d = d + s * 0.1;
                } else if (pr.equals("HALF")) {
                    d = d + s * 0.5;
                } else if (pr.equals("FREESHIP")) {
                    x = false;
                }
            }
            double sh = 0;
            if (x) {
                sh = 25;
            } else if (s < 50) {
                sh = 9.99;
            }
            double tx = (s - d) * TAX;
            double tot = s - d + tx + sh;

            cnt++;
            res = "ORDER #" + cnt + "\n";
            res = res + "Date: " + new Date() + "\n";
            res = res + "Customer: " + c + "\n";
            for (int i = 0; i < n.length; i++) {
                res = res + n[i] + " x" + q[i] + " @ " + p[i] + " = " + q[i] * p[i] + "\n";
            }
            res = res + "Subtotal: " + Util.r(s) + "\n";
            res = res + "Discount: " + Util.r(d) + "\n";
            res = res + "Tax: " + Util.r(tx) + "\n";
            res = res + "Shipping: " + Util.r(sh) + "\n";
            res = res + "TOTAL: " + Util.r(tot) + "\n";

            FileWriter fw = new FileWriter(System.getProperty("java.io.tmpdir") + "/orders.txt", true);
            fw.write(res);
            fw.close();

            if (t.equals("V") || tot > 200) {
                new Mailer().send(c + "@bookstore.example", res);
            }
            log.add("processed " + cnt);
        } catch (Exception e) {
            return "ERR";
        }
        return res;
    }

    public double procReturn(String c, double a, String t, String pr) {
        double d = 0;
        if (t.equals("R")) {
            if (a > 100) {
                d = a * 0.05;
            }
            if (a > 500) {
                d = a * 0.1;
            }
        } else if (t.equals("V")) {
            if (a > 100) {
                d = a * 0.1;
            }
            if (a > 500) {
                d = a * 0.2;
            }
        } else if (t.equals("E")) {
            d = a * 0.3;
        }
        if (pr != null && pr.equals("SAVE10")) {
            d = d + a * 0.1;
        }
        double ref = (a - d) + (a - d) * TAX;
        log.add("return " + c + " " + ref);
        return ref;
    }
}
