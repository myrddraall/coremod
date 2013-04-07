package cp.mods.core.type;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cp.mods.core.game.block.MultiTypeBlock;
import cp.mods.core.game.block.TileEntityExtended;
import cp.mods.core.type.exception.BlockTypeAlreadyInitialized;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class BlockType {

	private static HashMap<Integer, BlockType> blockTypes = new HashMap<Integer, BlockType>();

	public static void registerBlockType(Integer id, BlockType bt) {
		blockTypes.put(id, bt);
	}

	public static BlockType getBlockType(Integer id) {
		return blockTypes.get(id);
	}

	public static BlockType[] getBlockTypes() {
		BlockType[] bts = new BlockType[blockTypes.size()];
		return blockTypes.values().toArray(bts);
	}

	private boolean initialized = false;
	private int blockId;
	private String blockName;
	private MultiTypeBlock block;
	private Class<ItemBlock> itemClass;
	private ArrayList<String> subTypeNames = new ArrayList<String>();
	private HashMap<String, Integer> subTypeIds = new HashMap<String, Integer>();
	private ArrayList<Class<? extends TileEntityExtended>> subTypeTEs = new ArrayList<Class<? extends TileEntityExtended>>();

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

	public String getBlockName() {
		return blockName;
	}

	protected void setBlockName(String blockName) throws BlockTypeAlreadyInitialized {
		if (!initialized) {
			this.blockName = blockName;
		} else {
			throw new BlockTypeAlreadyInitialized();
		}
	}

	public Block getBlock() {
		return block;
	}

	protected void setBlock(MultiTypeBlock block) throws BlockTypeAlreadyInitialized {
		if (!initialized) {
			this.block = block;
		} else {
			throw new BlockTypeAlreadyInitialized();
		}
	}

	public Class<ItemBlock> getItemClass() {
		return itemClass;
	}

	protected void setItemClass(Class<ItemBlock> itemClass) throws BlockTypeAlreadyInitialized {
		if (!initialized) {
			this.itemClass = itemClass;
		} else {
			throw new BlockTypeAlreadyInitialized();
		}
	}

	protected void init(int blockId) throws BlockTypeAlreadyInitialized {
		if (!initialized) {
			setBlockId(blockId);
			initializeBlock();
			initializeSubTypes();
			registerSubTypes();
			registerBlock();
			initializeRecipes();
			registerNetworkHandlers();
			registerEventHandlers();
			registerBlockType(blockId, this);
			initialized = true;
		} else {
			throw new BlockTypeAlreadyInitialized();
		}
	}

	protected abstract void initializeBlock() throws BlockTypeAlreadyInitialized;

	protected abstract void initializeSubTypes();

	protected void registerBlock() {
		if (getItemClass() != null) {
			GameRegistry.registerBlock(block, getItemClass(), getBlockName());
		} else {
			GameRegistry.registerBlock(getBlock(), getBlockName());
		}
	}

	protected void registerSubTypes() {
		int len = subTypeNames.size();
		for (int i = 0; i < len; i++) {
			String name = subTypeNames.get(i);
			Class<? extends TileEntityExtended> tc = subTypeTEs.get(i);
			GameRegistry.registerTileEntity(tc, name);
		}

	}

	protected void initializeRecipes() {
	}

	protected void registerNetworkHandlers() {
	}

	protected void registerEventHandlers() {
	}

	// protected void addSubType(int index, String name, BlockTileEntity te,
	// Class<Item> itemClass) {
	// addSubType(index, name, te);
	// subTypeItems.add(index, itemClass);
	// }

	protected void addSubType(int index, String name, Class<? extends TileEntityExtended> te) {
		String n = getBlockName() + "." + name;
		subTypeNames.add(index, n);
		subTypeIds.put(n, index);
		subTypeTEs.add(index, te);

	}

	public Class<? extends TileEntityExtended>[] getSubEntities() {
		@SuppressWarnings("unchecked")
		Class<TileEntityExtended>[] tes = new Class[subTypeTEs.size()];
		subTypeTEs.toArray(tes);
		return tes;
	}
}
