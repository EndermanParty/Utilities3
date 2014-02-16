package darkevilmac.utilities.item;

import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;

public class ItemPipeLinker extends ItemUtilities {

    public ItemPipeLinker(int id) {
        super(id);
        setUnlocalizedName(Strings.PIPELINKER_UNLOCALIZEDNAME);
        setTextureName(Reference.MOD_TEXTURE_ID + getUnlocalizedName().substring(5));
    }

}