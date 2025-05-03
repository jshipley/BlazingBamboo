package net.paddedshaman.blazingbamboo.entity.fabric;

import net.minecraft.world.entity.EntityType.EntityFactory;
import net.paddedshaman.blazingbamboo.entity.BBChestRaftEntity;
import net.paddedshaman.blazingbamboo.entity.BBRaftEntity;

public class BBEntitiesImpl {

    public static EntityFactory<BBRaftEntity> getRaftEntityFactory() {
        return BBRaftEntity::new;
    }

    public static EntityFactory<BBChestRaftEntity> getChestRaftEntityFactory() {
        return BBChestRaftEntity::new;            
    }    
}
