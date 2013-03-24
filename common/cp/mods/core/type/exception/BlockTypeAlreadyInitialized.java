package cp.mods.core.type.exception;

@SuppressWarnings("serial")
public class BlockTypeAlreadyInitialized extends BlockTypeException {

	public BlockTypeAlreadyInitialized() {
		super("Block type has already been initialized");
	}

	public BlockTypeAlreadyInitialized(String message) {
		super(message);
	}

}
