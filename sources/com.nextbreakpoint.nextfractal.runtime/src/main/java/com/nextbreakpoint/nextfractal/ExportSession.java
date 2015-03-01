package com.nextbreakpoint.nextfractal;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.nextbreakpoint.nextfractal.encoder.Encoder;
import com.nextbreakpoint.nextfractal.mandelbrot.renderer.RendererSize;

public class ExportSession {
	private static final Logger logger = Logger.getLogger(ExportSession.class.getName());
	private static final int BORDER_SIZE = 0;
	private final List<ExportJob> jobs = new ArrayList<>();
	private final String sessioinId;
	private final Encoder encoder;
	private final RendererSize size;
	private final String pluginId;
	private final Object data;
	private final File tmpFile;
	private final File file;
	private final int tileSize;
	private final float quality;
	private final float frameRate;
	private final float startTime;
	private final float stopTime;
	private volatile float progress;
	private volatile int frameNumber;
	private volatile boolean cancelled;
	private volatile SessionState state = SessionState.SUSPENDED;

	public ExportSession(String pluginId, Object data, File file, File tmpFile, RendererSize size, int tileSize, Encoder encoder) {
		this.pluginId = pluginId;
		this.tmpFile = tmpFile;
		this.file = file;
		this.data = data;
		this.size = size;
		this.encoder = encoder;
		this.tileSize = tileSize;
		this.quality = 1;
		this.frameRate = 1.0f / 24.0f;
		this.startTime = 0;
		this.stopTime = 0;
		sessioinId = UUID.randomUUID().toString();
		jobs.addAll(createJobs(0));
	}
	
	public String getSessionId() {
		return sessioinId;
	}

	public String getPluginId() {
		return pluginId;
	}

	public Object getData() {
		return data;
	}

	public RendererSize getSize() {
		return size;
	}

	public Encoder getEncoder() {
		return encoder;
	}

	public File getFile() {
		return file;
	}

	public File getTmpFile() {
		return tmpFile;
	}

	public void dispose() {
		jobs.clear();
	}

	public SessionState getState() {
		return state;
	}

	public void setState(SessionState state) {
		this.state = state;
		logger.fine(getSessionId() + " -> state = " + state);
	}

	public List<ExportJob> getJobs() {
		return Collections.unmodifiableList(jobs);
	}

	public void updateProgress() {
		setProgress((float)getCompletedJobsCount() / (float)jobs.size());
	}

	public int getCompletedJobsCount() {
		int count = 0;
		for (ExportJob job : jobs) {
			if (job.isCompleted()) {
				count += 1;
			}
		}
		return count;
	}

	public float getProgress() {
		return progress;
	}

	protected void setProgress(float progress) {
		this.progress = progress;
	}

	public int getJobsCount() {
		return jobs.size();
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}

	protected float getFrameRate() {
		return frameRate;
	}

	public float getStartTime() {
		return startTime;
	}

	public float getStopTime() {
		return stopTime;
	}

	protected List<ExportJob> createJobs(int frameNumber) {
		final List<ExportJob> jobs = new ArrayList<ExportJob>();
		final int frameWidth = size.getWidth();
		final int frameHeight = size.getHeight();
		final int nx = frameWidth / tileSize;
		final int ny = frameHeight / tileSize;
		final int rx = frameWidth - tileSize * nx;
		final int ry = frameHeight - tileSize * ny;
		if ((nx > 0) && (ny > 0)) {
			for (int tx = 0; tx < nx; tx++) {
				for (int ty = 0; ty < ny; ty++) {
					int tileOffsetX = tileSize * tx;
					int tileOffsetY = tileSize * ty;
					jobs.add(createJob(createProfile(frameNumber, frameWidth, frameHeight, tileOffsetX, tileOffsetY)));
				}
			}
		}
		if (rx > 0) {
			for (int ty = 0; ty < ny; ty++) {
				int tileOffsetX = tileSize * nx;
				int tileOffsetY = tileSize * ty;
				jobs.add(createJob(createProfile(frameNumber, frameWidth, frameHeight, tileOffsetX, tileOffsetY)));
			}
		}
		if (ry > 0) {
			for (int tx = 0; tx < nx; tx++) {
				int tileOffsetX = tileSize * tx;
				int tileOffsetY = tileSize * ny;
				jobs.add(createJob(createProfile(frameNumber, frameWidth, frameHeight, tileOffsetX, tileOffsetY)));
			}
		}
		if (rx > 0 && ry > 0) {
			int tileOffsetX = tileSize * nx;
			int tileOffsetY = tileSize * ny;
			jobs.add(createJob(createProfile(frameNumber, frameWidth, frameHeight, tileOffsetX, tileOffsetY)));
		}
		return jobs;
	}

	private ExportProfile createProfile(int frameNumber, final int frameWidth, final int frameHeight, int tileOffsetX, int tileOffsetY) {
		final ExportProfile profile = new ExportProfile();
		profile.setPluginId(pluginId);
		profile.setQuality(quality);
		profile.setFrameNumber(frameNumber);
		profile.setFrameRate(frameRate);
		profile.setFrameWidth(frameWidth);
		profile.setFrameHeight(frameHeight);
		profile.setTileWidth(tileSize);
		profile.setTileHeight(tileSize);
		profile.setTileOffsetX(tileOffsetX);
		profile.setTileOffsetY(tileOffsetY);
		profile.setBorderWidth(BORDER_SIZE);
		profile.setBorderHeight(BORDER_SIZE);
		profile.setData(data);
		return profile;
	}

	protected ExportJob createJob(ExportProfile profile) {
		return new ExportJob(this, profile);
	}

	public boolean isStopped() {
		return state == SessionState.STOPPED;
	}

	public boolean isDispatched() {
		return state == SessionState.DISPATCHED;
	}

	public boolean isStarted() {
		return state == SessionState.STARTED;
	}

	public boolean isSuspended() {
		return state == SessionState.SUSPENDED;
	}

	public boolean isInterrupted() {
		return state == SessionState.INTERRUPTED;
	}

	public boolean isCompleted() {
		return state == SessionState.COMPLETED;
	}

	public boolean isFailed() {
		return state == SessionState.FAILED;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}