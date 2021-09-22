package Project1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a GUI interface to control the functions of the countdowntimer
 *
 * @author Ferguson
 * @version August 7, 2021
 */
public class CountDownTimerPanelSwing extends JPanel {

	private CountDownTimer watch;
	private Timer javaTimer;

	private JButton startButton, stopButton, saveButton, loadButton, addButton, stringInputButton, continueButton;
	private JTextField hourField, minField, secondField, addSecondsField, newStringField;

	private JLabel lblTime;

	public CountDownTimerPanelSwing() {

		// create the game object as well as the GUI1024 Frame
		watch = new CountDownTimer();
		javaTimer = new Timer(1000, new TimerListener());

		setLayout(new GridLayout(9, 4));
		setBackground(Color.lightGray);

		// Fields
		hourField = new JTextField();
		add(hourField);
		add(new JLabel("Hours:"));

		minField = new JTextField();
		add( minField );
		add(new JLabel("Minutes:"));

		secondField = new JTextField();
		add(secondField);
		add(new JLabel("Seconds:"));

		stopButton = new JButton("Stop");
		add(stopButton);

		startButton = new JButton("Start");
		add(startButton);

		loadButton = new JButton("Load");
		add(loadButton);

		saveButton = new JButton("Save");
		add(saveButton);

		addButton = new JButton("Add");
		add(addButton);

		addSecondsField = new JTextField();
		add(addSecondsField);

		stringInputButton = new JButton("New");
		add(stringInputButton);

		newStringField = new JTextField();
		add(newStringField);

		continueButton = new JButton("Continue");
		add(continueButton);

		lblTime = new JLabel();
		lblTime.setText(watch.toString());
		add(lblTime);

		add(new JLabel("0:00:00"));

		add(new JLabel("Time:"));

		startButton.addActionListener(new ButtonListener());
		stopButton.addActionListener(new StopButtonListener());
		continueButton.addActionListener(new ContinueButtonListener());
		addButton.addActionListener(new AddButtonListener());
		stringInputButton.addActionListener(new StringInputButtonListener());
		loadButton.addActionListener(new loadButtonListener());
		saveButton.addActionListener(new SaveButtonListener());
	}

	private class StopButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			try {
				javaTimer.stop();
			}
			catch(Exception exception) {
			}
		}
	}

	private class ContinueButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			try {
				javaTimer.restart();
			}
			catch(Exception exception) {
			}

		}
	}

	private class AddButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			try {
				int milli;
				milli = Integer.parseInt(addSecondsField.getText());
				watch.add(milli);
			}catch(Exception exception) {

			}
		}
	}
	
	private class StringInputButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			try {
				String newStr;
				newStr = newStringField.getText();
				CountDownTimer watch2 = new CountDownTimer(newStr);
				watch.add(watch2);
			}
			catch(Exception exception) {
			}

		}
	}
	
	private class loadButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			try {
				watch.load("project1");
			}
			catch(Exception exception) {
			}

		}
	}
	
	private class SaveButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			try {
				watch.save("project1");
			}
			catch(Exception exception) {
			}

		}
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {


			if (event.getSource() == startButton) {
				System.out.println("Testing");
				int mins, sec, milli;
				try {
					mins = Integer.parseInt(hourField.getText());
					sec = Integer.parseInt(minField.getText());
					milli = Integer.parseInt(secondField.getText());
					watch = new CountDownTimer(mins, sec, milli);
					javaTimer.start();
				} catch (NumberFormatException io) {
					JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, "Error in field");
				}
			}

			lblTime.setText(watch.toString());
		}
	}

	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				watch.sub(1);
				lblTime.setText(watch.toString());
			}
			catch (Exception exception) {
			}
		}
	}
}
