package BackEnd;

public class DifusedBomb extends Bomb {

	public DifusedBomb(Board b) {
		super(b);
	}
	
	@Override
	protected void changeIcon() {
		icon = Images.difusedBomb;
	}
	//TODO na ezt tüntessük el
	@Override
	protected void execute() {
		
	}
}
