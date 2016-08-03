import org.junit.Test;

import java.io.IOException;

public class TestOthers {
	public void declareThrownUncheckException() throws IllegalStateException {
		throw new IllegalStateException();
	}

	public void noDeclareThrownUncheckException() {
		throw new IllegalStateException();
	}

	public void declareThrownCheckedException() throws Exception {
		throw new IllegalStateException();
	}

	public void declareThrownIOException() throws IOException {
		throw new IllegalStateException();
	}

	@Test
	public void testUncheckedException() {
/*
		try {
			declareThrownUncheckException();
		} catch (Exception e) {
			System.out.println("unchecked exception caught");
		}

		try {
			noDeclareThrownUncheckException();
		} catch (Exception e) {
			System.out.println("unchecked exception caught");
		}

		try {
			declareThrownCheckedException();
		} catch (Exception e) {
			System.out.println("unchecked exception caught");
		}
*/

		try {
			declareThrownIOException();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
