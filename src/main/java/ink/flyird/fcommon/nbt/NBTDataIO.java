package ink.flyird.fcommon.nbt;

import io.flybird.util.nbt.NBTTagCompound;

/**
 * simple data io object standard
 * @author GrassBlock2022
 */
public interface NBTDataIO {
    NBTTagCompound getData();
    void setData(NBTTagCompound tag);
}
