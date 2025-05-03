package net.paddedshaman.blazingbamboo.entity.neoforge;

import net.minecraft.world.entity.EntityType.EntityFactory;
import net.paddedshaman.blazingbamboo.entity.BBChestRaftEntity;
import net.paddedshaman.blazingbamboo.entity.BBRaftEntity;

public class BBEntitiesImpl {

    public static EntityFactory<BBRaftEntity> getRaftEntityFactory() {
        return BBRaftEntityNeo::new;
    }

    public static EntityFactory<BBChestRaftEntity> getChestRaftEntityFactory() {
        return BBChestRaftEntityNeo::new;            
    }    
}
