package cp.mods.core.type;

import cp.mods.core.type.exception.BlockTypeAlreadyInitialized;
import net.minecraft.block.Block;

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

	protected void setBlockId(Block block) throws BlockTypeAlreadyInitialized {
		if (!initialized) {
			this.block = block;
		} else {
			throw new BlockTypeAlreadyInitialized();
		}
	}

	public void initialize(int blockId) throws BlockTypeAlreadyInitialized {
		if (!initialized) {
			setBlockId(blockId);
			initializeRecipes();
			registerNetworkHandlers();
			registerEventHandlers();
			initialized = true;
		} else {
			throw new BlockTypeAlreadyInitialized();
		}
	}

	protected abstract void initializeRecipes();

	protected abstract void registerNetworkHandlers();

	protected abstract void registerEventHandlers();
}
