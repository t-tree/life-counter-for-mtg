package leaf.ttree.lifecounter;

import android.os.SystemClock;
import android.widget.Chronometer;

/**
 * @author t-tree
 * 
 */
public class ControlTimer {

	// for state
	int state;
	private static final int STOP = 0;
	private static final int COUNT_UP = 1;
	private static final int PAUSE = 2;

	// for calculate resume timer
	long startTime;
	long totalTime;

	// controled chronometer
	Chronometer chrono;

	public ControlTimer(Chronometer chrono) {
		this.chrono = chrono;
	}

	public void startTimer() {
		if (isStop()) {
			startTime = SystemClock.elapsedRealtime();
			chrono.setBase(startTime);
			totalTime = 0;
			chrono.start();
			state = COUNT_UP;
		}
	}

	public void puaseTimer() {
		if (isCountup()) {
			chrono.stop();
			// add running time of this term
			totalTime += (SystemClock.elapsedRealtime() - startTime);
			state = PAUSE;
		}
	}

	public void resumeTimer() {
		if (isPause()) {
			long currentTime = SystemClock.elapsedRealtime();
			// rewinds time
			chrono.setBase(currentTime - totalTime);
			startTime = currentTime;

			chrono.start();
			state = COUNT_UP;
		}
	}

	public void resetTimer() {
		chrono.setBase(SystemClock.elapsedRealtime());
		chrono.stop();
		state = STOP;
	}

	public void toggleCountupPause() {
		if (isCountup()) {
			puaseTimer();
		} else if (isPause()) {
			resumeTimer();
		}
	}

	public Boolean isCountup() {
		if (state == COUNT_UP) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isPause() {
		if (state == PAUSE) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isStop() {
		if (state == STOP) {
			return true;
		} else {
			return false;
		}
	}
}
