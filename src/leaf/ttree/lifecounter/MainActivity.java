package leaf.ttree.lifecounter;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends Activity {

	ControlTimer controlTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		controlTimer = new ControlTimer(
				(Chronometer) this.findViewById(R.id.chronometer));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void clickStartPauseButton(View v) {
		if (controlTimer.isStop()) {
			controlTimer.startTimer();
		} else {
			controlTimer.toggleCountupPause();
		}

		if (controlTimer.isCountup()) {
			changeButtonLayout(getStartStopButton(), R.string.pause_button,
					R.drawable.pause_button);
		} else if (controlTimer.isPause()) {
			changeButtonLayout(getStartStopButton(), R.string.restart_button,
					R.drawable.start_button);
		}
	}

	private void changeButtonLayout(Button targetButton, int buttonID,
			int drawableID) {

		targetButton.setText(buttonID);
		targetButton.setBackgroundDrawable(getResources().getDrawable(
				drawableID));
		int padding = getResources().getDimensionPixelSize(R.dimen.padding);
		targetButton.setPadding(padding, padding, padding, padding);
	}

	public void clickResetButton(View v) {
		controlTimer.resetTimer();
		changeButtonLayout(getStartStopButton(), R.string.start_button,
				R.drawable.start_button);

	}

	public Button getStartStopButton() {
		return (Button) this.findViewById(R.id.start_pause_button);

	}

}
