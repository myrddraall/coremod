package cp.mods.core.old.type.exception;

@SuppressWarnings("serial")
public abstract class BlockTypeException extends Exception {

	public BlockTypeException() {
		super();
	}

	public BlockTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BlockTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlockTypeException(String message) {
		super(message);
	}

	public BlockTypeException(Throwable cause) {
		super(cause);
	}

}
