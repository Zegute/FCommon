package ink.flybird.fcommon.file;

import org.w3c.dom.Element;

public interface DocumentUtil {
    static String getAttribute(Element element, String name, String fallback) {
        if (!element.hasAttribute(name)) {
            return fallback;
        }
        return element.getAttribute(name);
    }

    static int getAttributeI(Element element, String name, int fallback) {
        return Integer.parseInt(getAttribute(element,name, String.valueOf(fallback)));
    }


    static boolean getAttributeB(Element element, String name, boolean fallback) {
        return Boolean.parseBoolean(getAttribute(element,name, String.valueOf(fallback)));
    }
}
