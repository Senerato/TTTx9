package tttx9;

public class Controller {

	TTTx9Game ttt;
	View view;
	
	public Controller(TTTx9Game ttt, View view) {
		this.ttt = ttt;
		this.view = view;
		
		ttt.play();
	}

}