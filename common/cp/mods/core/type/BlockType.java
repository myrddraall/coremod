package cp.mods.core.type;

import cp.mods.core.type.exception.BlockTypeAlreadyInitialized;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public abstract class BlockType {

	private boolean initialized = false;
	private int blockId;
	private Block block;

	public int getBlockId() {
		return blockId;
	}

	protected void setBlockId(int blockId) throws BlockTypeAlreadyInitialized {
		if (!initialized) {
			this.blockId = blockId;
		} else {
			throw new BlockTypeAlreadyInitialized();
		}
	}

	public Block getBlock() {
		return block;
	}

	protected void setBlock(Block block) throws BlockTypeAlreadyInitialized {
		if (!initialized) {
			this.block = block;
		} else {
			throw new BlockTypeAlreadyInitialized();
		}
	}

	protected void init(int blockId) throws BlockTypeAlreadyInitialized {
		if (!initialized) {
			setBlockId(blockId);
			initializeBlock();
			initializeSubTypes();
			registerBlock();
			initializeRecipes();
			registerNetworkHandlers();
			registerEventHandlers();
			initialized = true;
		} else {
			throw new BlockTypeAlreadyInitialized();
		}
	}

	protected abstract void initializeBlock();

	protected abstract void initializeSubTypes();

	protected void registerBlock() {

	}

	protected void initializeRecipes() {
	}

	protected void registerNetworkHandlers() {
	}

	protected void registerEventHandlers() {
	}

	protected void addSubType(int index, String name, TileEntity e) {

	}
}
