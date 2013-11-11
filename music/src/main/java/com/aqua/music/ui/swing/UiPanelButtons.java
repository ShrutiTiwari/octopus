package com.aqua.music.ui.swing;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;

import com.aqua.music.audio.manager.AudioLifeCycleManager;
import com.aqua.music.audio.manager.AudioPlayConfig;
import com.aqua.music.logic.FrequencySequence;
import com.aqua.music.model.FrequencySet;

enum UiPanelButtons {
	FREQUENCY_SET_PATTERNED_PLAYER("Play $$", "Click this to play $$", 300) {
		@Override
		JButton createInstanceWith(final TextArea textArea, final Object[] arg) {
			final FrequencySequence frequencySequence = (FrequencySequence) arg[0];
			final String buttonTitle = frequencySequence.name();
			JButton button = configurableNamedButton(this, buttonTitle);

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String text = frequencySequence.play(AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);
					System.out.println(":::::::::::" + text);
					setText(textArea, buttonTitle + "===>" + "\n" + text);
				}
			};

			button.addActionListener(actionListener);
			return button;
		}
	},
	FREQUENCY_SET_PLAYER("Play $$", "Click this to play $$", 200) {
		@Override
		JButton createInstanceWith(final TextArea textArea, Object[] arg) {
			final FrequencySequence frequencySequence = (FrequencySequence) arg[0];
			final String buttonTitle = frequencySequence.name();
			JButton button = configurableNamedButton(this, buttonTitle);

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String text = frequencySequence.play(AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);
					setText(textArea, buttonTitle + "===>" + "\n" + text);
				}
			};

			button.addActionListener(actionListener);

			return button;
		}
	},
	PLAY_ALL("PLAY_ALL", "Click this to play all!", 400) {
		@Override
		JButton createInstanceWith(final TextArea textArea, final Object[] arg) {
			JButton button = fixedNameButton(this);

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Runnable audioTask = new Runnable() {
						@Override
						public void run() {
							textArea.setText("Playing all items:\n");
							System.out.println(arg.length);
							for (Object each : arg) {
								FrequencySequence frequencySequence = (FrequencySequence) each;
								
								String text = frequencySequence.play(AudioPlayConfig.SYNCHRONOUS_DYNAMIC_PLAYER);
								setText(textArea, frequencySequence.name() + "===>" + "\n" + text);
								
								AudioLifeCycleManager.instance.awaitStop();
							}
						}
					};
					AudioLifeCycleManager.instance.execute(audioTask);
				}
			};

			button.addActionListener(actionListener);
			return button;
		}
	},
	QUIT("Quit", "Click this to quit!", 400) {
		@Override
		JButton createInstanceWith(TextArea Object, Object[] newParam) {
			JButton button = fixedNameButton(this);

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			};

			button.addActionListener(actionListener);
			return button;
		}
	};

	private static final int BUTTON_HEIGHT = 30;
	static final int X_COORIDNATE = 30;
	private final int displayWidth;
	private final String text;
	private final String tooltip;

	private UiPanelButtons(String text, String tooltip, int buttonWidth) {
		this.text = text;
		this.tooltip = tooltip;
		this.displayWidth = buttonWidth;
	}

	static final int HEIGHT() {
		return BUTTON_HEIGHT;
	}

	private static JButton configurableNamedButton(UiPanelButtons itemType, String replaceName) {
		JButton resultButton = new JButton(itemType.text.replace("$$", replaceName));
		resultButton.setToolTipText(itemType.tooltip.replace("$$", replaceName));
		return resultButton;
	}

	private static JButton fixedNameButton(UiPanelButtons itemType) {
		JButton resultButton = new JButton(itemType.text);
		resultButton.setToolTipText(itemType.tooltip);
		return resultButton;
	}

	public JButton createButton(TextArea textArea, int yCoordinate, Object[] arg) {
		JButton buttonItem = createInstanceWith(textArea, arg);
		buttonItem.setOpaque(true);
		buttonItem.setBounds(X_COORIDNATE, yCoordinate, displayWidth, BUTTON_HEIGHT);
		return buttonItem;
	}

	abstract JButton createInstanceWith(TextArea textArea, Object[] arg);

	static FrequencySet convertToFrequencySet(Object[] arg) {
		return convert(FrequencySet.class, arg[0]);
	}

	static <T> T convert(Class<T> t, Object arg) {
		return (T) arg;
	}

	private static void setText(final TextArea textArea, final String name) {
		String displayText = "\n\n Playing::" + name;
		System.out.println(displayText);
		if (AudioLifeCycleManager.instance.isPlayInProgress()) {
			textArea.append(displayText);
		} else {
			textArea.setText(displayText);
		}
	}
}