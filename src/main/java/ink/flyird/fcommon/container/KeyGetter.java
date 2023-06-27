package ink.flyird.fcommon.container;

/**
 * generates a key.
 * @param <T> key class.
 * @author GrassBlock2022
 */
public interface KeyGetter <T extends Key>{
    T getKey();
}
