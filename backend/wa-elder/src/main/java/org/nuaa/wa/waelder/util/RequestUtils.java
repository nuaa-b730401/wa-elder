package org.nuaa.wa.waelder.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2019/3/3 15:15
 */
public class RequestUtils {
    /**
     * extract ip from request
     * @param request http request
     * @return ip
     */
    public static String getRequestIp(HttpServletRequest request) {
        if (request == null) {
            throw new NullPointerException("HttpServletRequest is null when extract ip");
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.trim().isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.trim().isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.trim().isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String[] addr = ip.split(",");
        for (String s : addr) {
            if (!"unknown".equalsIgnoreCase(s)) {
                ip = s;
                break;
            }
        }
        return ip;
    }

    /**
     * extract os info from request
     * @param request request
     * @return os info
     */
    public static String getOs(HttpServletRequest request){
        String  userAgent =   request.getHeader("User-Agent");
        String os = "";

        if (userAgent.toLowerCase().contains("windows")) {
            os = "Windows";
        } else if(userAgent.toLowerCase().contains("mac"))                                                                             {
            os = "Mac";
        } else if(userAgent.toLowerCase().contains("x11")) {
            os = "Unix";
        } else if(userAgent.toLowerCase().contains("android")) {
            os = "Android";
        } else if(userAgent.toLowerCase().contains("iphone")) {
            os = "IPhone";
        }else {
            os = "UnKnown, More-Info: "+userAgent;
        }
        return os;
    }
    /**
     * extract browser info from request
     * @param request request
     * @return browser info
     */
    public static String getBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String user = userAgent.toLowerCase();
        String browser = "";
        if (user.contains("edge")) {
            browser = (userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("msie")) {
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]
                    + "-" +(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( user.contains("opr") || user.contains("opera")) {
            if(user.contains("opera")){
                browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]
                        + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            } else if(user.contains("opr")){
                browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
                        .replace("OPR", "Opera");
            }
        } else if (user.contains("chrome")) {
            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.contains("mozilla/7.0")) || (user.contains("netscape6"))  ||
                (user.contains("mozilla/4.7")) || (user.contains("mozilla/4.78")) ||
                (user.contains("mozilla/4.08")) || (user.contains("mozilla/3")) ) {
            browser = "Netscape-?";
        } else if (user.contains("firefox")) {
            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(user.contains("rv")) {
            String ie = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
            browser = "IE" + ie.substring(0, ie.length() - 1);
        } else {
            browser = "UnKnown, More-Info: "+userAgent;
        }
        return browser;
    }
}


