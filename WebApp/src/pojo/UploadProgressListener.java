package pojo;

import org.apache.commons.fileupload.ProgressListener;

public class UploadProgressListener implements ProgressListener {

	private int percentComplete = 0;

	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		if (pContentLength == -1)
			return;
		else {
			percentComplete = (int) (pBytesRead / pContentLength * 100);
		}
	}

	public int getPercentComplete() {
		return percentComplete;
	}

}
