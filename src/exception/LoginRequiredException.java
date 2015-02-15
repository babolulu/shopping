package exception;

public class LoginRequiredException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginRequiredException(String errorMsg) {
		super(errorMsg);
	}
}
