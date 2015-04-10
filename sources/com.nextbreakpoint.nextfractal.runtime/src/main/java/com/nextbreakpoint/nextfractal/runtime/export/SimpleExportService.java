package com.nextbreakpoint.nextfractal.runtime.export;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import com.nextbreakpoint.nextfractal.core.encoder.EncoderException;
import com.nextbreakpoint.nextfractal.core.export.AbstractExportService;
import com.nextbreakpoint.nextfractal.core.export.ExportJob;
import com.nextbreakpoint.nextfractal.core.export.ExportRenderer;
import com.nextbreakpoint.nextfractal.core.export.ExportSession;
import com.nextbreakpoint.nextfractal.core.export.ExportSessionHolder;
import com.nextbreakpoint.nextfractal.core.session.SessionState;
import com.nextbreakpoint.nextfractal.runtime.encoder.RAFEncoderContext;

public class SimpleExportService extends AbstractExportService {
	private final Map<String, List<Future<ExportJob>>> futures = new HashMap<>();
	private final ExportRenderer exportRenderer;

	public SimpleExportService(ThreadFactory threadFactory, ExportRenderer exportRenderer) {
		super(threadFactory);
		this.exportRenderer = exportRenderer;
	}

	@Override
	protected void updateSessionsInBackground(List<ExportSessionHolder> holders) {
		for (Iterator<ExportSessionHolder> i = holders.iterator(); i.hasNext();) {
			ExportSessionHolder holder = i.next();
			updateSession(holder);
			ExportSession session = holder.getSession();
			if (session.isStopped()) {
				futures.remove(session.getSessionId());
				i.remove();
			}
		}
	}

	@Override
	protected void cancelJobs(ExportSession session) {
		List<Future<ExportJob>> list = futures.get(session.getSessionId());
		if (list != null) {
			for (Future<ExportJob> future : list) {
				future.cancel(true);
			}
		}
	}

	private void updateSession(ExportSessionHolder holder) {
		ExportSession session = holder.getSession();
		if (session.isStarted()) {
			dispatchJobs(session);
			holder.setState(SessionState.DISPATCHED);
		} else if (session.isInterrupted()) {
			holder.setState(SessionState.STOPPED);
		} else if (session.isCompleted()) {
			holder.setState(SessionState.STOPPED);
		} else if (session.isFailed()) {
			if (session.isCancelled()) {
				holder.setState(SessionState.STOPPED);
			}
		} else {
			processSession(holder);
		}
		session.updateProgress();
	}

	private void processSession(ExportSessionHolder holder) {
		ExportSession session = holder.getSession();
		List<Future<ExportJob>> list = futures.get(session.getSessionId());
		if (list != null) {
			removeTerminatedJobs(list);
			if (list.size() == 0) {
				if (session.isCancelled()) {
					holder.setState(SessionState.INTERRUPTED);
				} else if (isSessionCompleted(session)) {
					try {
						encodeData(session);
						holder.setState(SessionState.COMPLETED);
					} catch (Exception e) {
						holder.setState(SessionState.FAILED);
					}
				} else {
					holder.setState(SessionState.SUSPENDED);
				}
			}
		}
	}
	
	private void removeTerminatedJobs(List<Future<ExportJob>> list) {
		for (Iterator<Future<ExportJob>> i = list.iterator(); i.hasNext();) {
			Future<ExportJob> future = i.next();
			if (future.isCancelled() || future.isDone()) {
				i.remove();
			}
		}
	}

	private boolean isSessionCompleted(ExportSession session) {
		return session.getCompletedJobsCount() == session.getJobsCount();
	}

	private void dispatchJobs(ExportSession session) {
		for (ExportJob job : session.getJobs()) {
			if (!job.isCompleted()) {
				Future<ExportJob> future = exportRenderer.dispatch(job);
				List<Future<ExportJob>> list = futures.get(session.getSessionId());
				if (list == null) {
					list = new ArrayList<>();
					futures.put(session.getSessionId(), list);
				}
				list.add(future);
			}
		}
	}

	private void encodeData(ExportSession session) throws IOException, EncoderException {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(session.getTmpFile(), "r");
			final int frameRate = (int)Math.rint(1 / session.getFrameRate());
			final int imageWidth = session.getSize().getWidth();
			final int imageHeight = session.getSize().getHeight();
			final double stopTime = session.getStopTime();
			final double startTime = session.getStartTime();
			final int frameCount = (int) Math.floor((stopTime - startTime) * frameRate);
			session.getEncoder().encode(new RAFEncoderContext(raf, imageWidth, imageHeight, frameRate, frameCount), session.getFile());
		} finally {
			session.getTmpFile().delete();
			if (raf != null) {
				try {
					raf.close();
				} catch (final IOException e) {
				}
			}			
		}
	}
}
