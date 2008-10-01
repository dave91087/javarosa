package org.javarosa.formmanager.view.clforms;

//import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.Ticker;

import org.javarosa.core.model.QuestionDef;
import org.javarosa.core.model.data.IAnswerData;
import org.javarosa.formmanager.controller.FormEntryController;
import org.javarosa.formmanager.model.FormEntryModel;


public abstract class SingleQuestionScreen extends Form {

	protected FormEntryController controller;
	protected FormEntryModel model;

	protected QuestionDef qDef;
	protected IAnswerData answer;

	// GUI elements
	public Gauge progressBar;

	public static Command previousCommand;
	public static Command nextCommand;
	public static Command viewAnswersCommand;

	public static Command nextItemCommand = new Command("next", Command.ITEM, 1);
	
	//#style button
	public StringItem nextItem = new StringItem(null,"NEXT",Item.BUTTON);
	public ItemCommandListener itemListner;

	public SingleQuestionScreen(QuestionDef prompt) {
		super(prompt.getName());		
		this.qDef = prompt;
		this.creatView();
		this.setUpCommands();
	}
	
	//hack: this is for moving to NEXT question
	public SingleQuestionScreen(QuestionDef prompt, int temp) {
	//#style nextQuestion
		super(prompt.getName());
		
		this.qDef = prompt;
		this.creatView();
		this.setUpCommands();
	}

	//hack: this is for moving to PREV question
	public SingleQuestionScreen(QuestionDef prompt, String str) {
	//#style prevQuestion
		super(prompt.getName());
		this.qDef = prompt;
		this.creatView();
		this.setUpCommands();
	}

	//hack: this is for moving to question fromViewAnswers
	public SingleQuestionScreen(QuestionDef prompt, char str) {
	//#style fromViewAnswers
		super(prompt.getName());
		this.qDef = prompt;
		this.creatView();
		this.setUpCommands();
	}
	

/*
 * This constructor would have been the non-hacked version, but it doesn't work:
 * The first line of a constructor must be super(), not even an if statement may 
 * come before it
*/
/*public SingleQuestionScreen(QuestionDef prompt, boolean direction) {
	
		if (direction == true)
		{
			//#style nextQuestion
			super(prompt.getName());
	//		SingleQuestionScreen(prompt,1);
		}
		else
		{

			//#style prevQuestion
			super(prompt.getName());
	//		SingleQuestionScreen(prompt,"s");
		}
		this.qDef = prompt;
		this.creatView();
		this.setUpCommands();
	}*/



	public abstract void creatView();
    public abstract IAnswerData getWidgetValue ();
    
	public void setHint(String helpText) {
		Ticker tick = new Ticker("HELP: "+helpText);
		this.setTicker(tick);
	}

	private void setUpCommands(){
		nextCommand = new Command("next", Command.SCREEN, 0);
		previousCommand = new Command("back", Command.SCREEN, 2);
		viewAnswersCommand = new Command("View Answers", Command.SCREEN, 3);

		this.addCommand(previousCommand);
		this.addCommand(viewAnswersCommand);
		this.addCommand(nextCommand);
	}

	public void addNavigationButtons()
	{	
		this.append(nextItem);
	    nextItem.setDefaultCommand(nextItemCommand);     // add Command to Item.
	}

	public SingleQuestionScreen (String formTitle) {
        //#style CL_Forms_Form
    	super(formTitle);
	}

    private void initProgressBar () {
        //#style progressbar
        progressBar = new Gauge(null, false, model.getNumQuestions(), 0);
        this.append(progressBar);
    }

	public void setItemCommandListner(ItemCommandListener itemListner) {
		this.itemListner = itemListner;
	}

}
