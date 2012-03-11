package pojo;

import org.apache.commons.fileupload.ProgressListener;

public class UploadProgressListener implements ProgressListener {

	private double percentComplete = 0;
	private long time = System.currentTimeMillis();

	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		System.out.println("Bytes Read:" + pBytesRead);
		System.out.println("Content Length:" + pContentLength);
		if (pContentLength == -1)
			return;
		else {
			percentComplete = Math.round((double) pBytesRead / pContentLength * 100);
		}
		System.out.println("Pct Complete:" + percentComplete);
		System.out.println("Time:" + (System.currentTimeMillis() - time));
	}

	public double getPercentComplete() {
		return percentComplete;
	}

}
