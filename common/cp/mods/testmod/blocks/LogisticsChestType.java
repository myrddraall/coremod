package cp.mods.testmod.blocks;

import cp.mods.core.block.MultiTypeBlock;
import cp.mods.core.type.BlockType;
import cp.mods.core.type.exception.BlockTypeAlreadyInitialized;

public final class LogisticsChestType extends BlockType {

	private static LogisticsChestType instance;

	public static LogisticsChestType getInstance() {
		return instance;
	}

	public static void initialize(int blockId)
			throws BlockTypeAlreadyInitialized {
		if (instance == null) {
			instance = new LogisticsChestType();
			instance.init(blockId);
		}
	}
	
	 

	@Override
	protected void initializeBlock() throws BlockTypeAlreadyInitialized{
		MultiTypeBlock lcBlock = new LogisticsChestBlock(this.getBlockId());
		setBlock(lcBlock);
		
		//lcBlock.
		
		
	}

	@Override
	protected void initializeSubTypes() {
		addSubType(0, "firstBlock", TestLogisticsChest.class);

	}

}
