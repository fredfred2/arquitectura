package com.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class UserAgentBean {

    private String userAgent;
    private boolean mobile = false;
    private String browserName = "unknown";
    private int browserVersion = 0;
    private int browserMinorVersion = 0;

    public UserAgentBean() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        userAgent = externalContext.getRequestHeaderMap().get("User-Agent");
        String lowercaseUserAgent = userAgent.toLowerCase();
        if (lowercaseUserAgent.indexOf("mobile") != -1) {
            mobile = true;
        }
        try {
            if (lowercaseUserAgent.indexOf("android") != -1) {
                //Mozilla/5.0 (Linux; U; Android 2.2.1; en-us; Nexus One Build/FRG83) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1
                browserName = "android";
                int i1 = lowercaseUserAgent.indexOf(browserName) + browserName.length() + 1;
                int i2 = lowercaseUserAgent.indexOf(".", i1);

                String strVer = lowercaseUserAgent.substring(i1, i2).trim();
                browserVersion = Integer.parseInt(strVer);
                i2++;
                String strMinorVer = lowercaseUserAgent.substring(i2, lowercaseUserAgent.indexOf(" ", i2)).trim();
                if (strMinorVer.indexOf(".") != -1) {
                    strMinorVer = strMinorVer.substring(0, strMinorVer.indexOf("."));
                }
                browserMinorVersion = Integer.parseInt(strMinorVer);
            } else if (lowercaseUserAgent.indexOf("chrome") != -1) {
                //Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11
                browserName = "chrome";
                int i1 = lowercaseUserAgent.indexOf(browserName) + browserName.length() + 1;
                int i2 = lowercaseUserAgent.indexOf(".", i1);
                String strVer = lowercaseUserAgent.substring(i1, i2).trim();
                browserVersion = Integer.parseInt(strVer);
                i2++;
                String strMinorVer = lowercaseUserAgent.substring(i2, lowercaseUserAgent.indexOf(" ", i2)).trim();
                if (strMinorVer.indexOf(".") != -1) {
                    strMinorVer = strMinorVer.substring(0, strMinorVer.indexOf("."));
                }
                browserMinorVersion = Integer.parseInt(strMinorVer);
            } else if (lowercaseUserAgent.indexOf("safari") != -1) {
                //Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5
                //Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.52.7 (KHTML, like Gecko) Version/5.1.2 Safari/534.52.7
                browserName = "safari";
                int i1 = lowercaseUserAgent.indexOf("version") + "version".length() + 1;
                int i2 = lowercaseUserAgent.indexOf(".", i1);
                String strVer = lowercaseUserAgent.substring(i1, i2).trim();
                browserVersion = Integer.parseInt(strVer);
                i2++;
                String strMinorVer = lowercaseUserAgent.substring(i2, lowercaseUserAgent.indexOf(" ", i2)).trim();
                if (strMinorVer.indexOf(".") != -1) {
                    strMinorVer = strMinorVer.substring(0, strMinorVer.indexOf("."));
                }
                browserMinorVersion = Integer.parseInt(strMinorVer);
            } else if (lowercaseUserAgent.indexOf("msie") != -1) {
                //Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/5.0)
                browserName = "msie";
                int i1 = lowercaseUserAgent.indexOf(browserName) + browserName.length() + 1;
                int i2 = lowercaseUserAgent.indexOf(".", i1);
                String strVer = lowercaseUserAgent.substring(i1, i2).trim();
                browserVersion = Integer.parseInt(strVer);
                i2++;
                String strMinorVer = lowercaseUserAgent.substring(i2, lowercaseUserAgent.indexOf(";", i2)).trim();
                if (strMinorVer.indexOf(".") != -1) {
                    strMinorVer = strMinorVer.substring(0, strMinorVer.indexOf("."));
                }
                browserMinorVersion = Integer.parseInt(strMinorVer);
            } else if (lowercaseUserAgent.indexOf("firefox") != -1) {
                //Mozilla/5.0 (Windows NT 6.1; WOW64; rv:10.0.2) Gecko/20100101 Firefox/10.0.2
                //mozilla/5.0 (windows nt 6.1; wow64; rv:11.0) gecko/20100101 firefox/11.0
                browserName = "firefox";
                int i1 = lowercaseUserAgent.indexOf(browserName) + browserName.length() + 1;
                int i2 = lowercaseUserAgent.indexOf(".", i1);
                String strVer = lowercaseUserAgent.substring(i1, i2).trim();
                browserVersion = Integer.parseInt(strVer);
                i2++;
                int i3 = lowercaseUserAgent.indexOf(" ", i2);
                if (i3 == -1) {
                    i3 = lowercaseUserAgent.length();
                }
                String strMinorVer = lowercaseUserAgent.substring(i2, i3).trim();
                if (strMinorVer.indexOf(".") != -1) {
                    strMinorVer = strMinorVer.substring(0, strMinorVer.indexOf("."));
                }
                browserMinorVersion = Integer.parseInt(strMinorVer);
            }
        } catch (Exception e) {
            //leave the version numbers at zero if we had trouble parsing
        }
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getBrowserName() {
        return browserName;
    }

    public int getBrowserVersion() {
        return browserVersion;
    }

    public int getBrowserMinorVersion() {
        return browserMinorVersion;
    }

    public boolean isMobile() {
        return mobile;
    }
}