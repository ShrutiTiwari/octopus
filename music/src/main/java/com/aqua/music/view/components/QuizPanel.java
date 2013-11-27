package com.aqua.music.view.components;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;
import com.aqua.music.view.action.listeners.QuizPlayActionListener;
import com.aqua.music.view.components.UiDropdown.ThaatAndPatternDropdownActionListener;

/**
 * @author "Shruti Tiwari"
 * 
 */
class QuizPanel extends AbstractMusicPanel {
	private final QuizLevel quizLevel;

	QuizPanel(final QuizLevel quizLevel) {
		super();
		this.quizLevel = quizLevel;
		final JComboBox quizDropdown = UiDropdown.quizDropdown(quizLevel);
		quizDropdown.addActionListener(new UiDropdown.QuizDropdownActionListener(this));
		addToCommonComponentPanel(quizDropdown);
	}

	protected JPanel createSpecificComponentPanel(final Object selectedObject) {
		QuizLevel quizLevel = (QuizLevel) selectedObject;
		if (quizLevel == null) {
			quizLevel = this.quizLevel;
		}
		final Collection<JComponent> allButtonsIncludingQuizAndChoice = new ArrayList<JComponent>();
		int i = 1;
		List<JButton> allQuizPlayButtons = new ArrayList<JButton>();
		for (Quiz<CyclicFrequencySet> eachQuiz : (Collection<Quiz<CyclicFrequencySet>>) quizLevel.quizSections()) {
			final String quizName = "Quiz " + i;
			JButton quizPlayButton = UiButtons.MusicButtons.QUIZ_PLAY.createDynamicNamedButton(quizName);

			final Collection<JButton> multipleChoiceButtons = new ArrayList<JButton>();
			for (CyclicFrequencySet eachOption : eachQuiz.quizItems()) {
				JButton b = UiButtons.MusicButtons.CHOICE_OPTIONS.createDynamicNamedButton(eachOption.name());
				allButtonsIncludingQuizAndChoice.add(b);
				multipleChoiceButtons.add(b);

			}
			quizPlayButton.addActionListener(new QuizPlayActionListener(eachQuiz, multipleChoiceButtons, allQuizPlayButtons));
			allButtonsIncludingQuizAndChoice.add(quizPlayButton);

			allQuizPlayButtons.add(quizPlayButton);
			i++;
		}

		JPanel result = new JPanel();
		result.setLayout(new FlowLayout());
		// result.setPreferredSize(new Dimension(400, 300));
		for (JComponent each : allButtonsIncludingQuizAndChoice) {
			result.add(each);
		}

		return result;
	}
}
