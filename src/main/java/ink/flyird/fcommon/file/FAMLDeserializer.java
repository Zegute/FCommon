package ink.flyird.fcommon.file;

import org.w3c.dom.Element;

/**
 * simple deserializer
 * @param <T> Template class
 */
public interface FAMLDeserializer<T> {
    T deserialize(Element element, XmlReader reader);
}
