package cp.mods.testmod.blocks;

import cp.mods.core.type.BlockType;
import cp.mods.core.type.exception.BlockTypeAlreadyInitialized;

public class LogisticsChestType extends BlockType {

	private static LogisticsChestType instance;

	public static void initialize(int blockId)
			throws BlockTypeAlreadyInitialized {
		if (instance != null) {
			instance = new LogisticsChestType();
			instance.init(blockId);
		}
	}

	@Override
	protected void initializeBlock() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initializeSubTypes() {
		// TODO Auto-generated method stub
		
	}

	
}
